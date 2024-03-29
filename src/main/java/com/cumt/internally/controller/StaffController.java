package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.annotation.PassToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Staff;
import com.cumt.internally.service.StaffService;
import com.cumt.internally.utils.JwtUtil;
import com.cumt.internally.utils.MD5Util;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 18:01
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    ResponseData responseData;
    @Autowired
    StaffService staffService;

    /**
     * 登陆，select
     *
     * @param staff
     * @param errors
     * @return
     */
    @PassToken
    @RequestMapping("/login")
    public Result login(Staff staff, BindingResult errors) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 400, list);
        }
        HashMap map = new HashMap();
        Staff staffChoose = staffService.selectByStaffId(staff.getStaffId());
        if (staffChoose != null) {
            // 验证密码
            if (staffChoose.getStaffPwd().equals(MD5Util.md5(staff.getStaffPwd()))) {
                String token = JwtUtil.getToken(staff, "salt", 60 * 24 * 30);
                map.put("token", token);
                map.put("weight", staffChoose.getStaffWeight());
                map.put("staff",staff.toDict());
                // 返回token
                return responseData.write("登录成功", 200, map);
            } else {
                return responseData.write("密码错误", 400, new HashMap<>());
            }
        } else {
            return responseData.write("没有该用户", 400, new HashMap<>());
        }
    }

    /**
     * 添加人员，insert
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/add")
    public Result add(@Valid Staff staff, BindingResult errors) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 400, list);
        }
        if (staffService.selectByStaffId(staff.getStaffId()) != null) {
            return responseData.write("用户名重复", 400, new HashMap<>());
        }
        staff.setStaffPwd(MD5Util.md5(staff.getStaffId()));
        if (staffService.insert(staff) == 1) {
            return responseData.write("添加成功", 200, new HashMap<>());
        } else {
            return responseData.write("添加失败", 400, new HashMap<>());
        }
    }

    /**
     * 重置密码，update
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/renew")
    public Result renew(@RequestParam String staffId) {
        if (staffService.renewPwdByStaffId(staffId) == 1) {
            return responseData.write("重置成功", 200, new HashMap<>());
        } else {
            return responseData.write("重置失败", 400, new HashMap<>());
        }
    }

    /**
     * 修改密码，update
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/change_pwd")
    public Result changePwd(@RequestParam String staffPwd, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Staff staff = staffService.getStaffFromToken(token);
        if (staffService.updatePwd(staff, staffPwd) == 1) {
            return responseData.write("修改成功", 200, new HashMap<>());
        } else {
            return responseData.write("修改失败", 400, new HashMap<>());
        }
    }

    /**
     * 修改员工信息
     *
     * @param staff
     * @return
     */
    @AdministratorToken
    @RequestMapping("/change")
    public Result change(@Valid Staff staff) {
        if (staffService.updateByStaff(staff) == 1) {
            return responseData.write("修改成功", 200, staff.toDict());
        } else {
            return responseData.write("修改失败", 400, new HashMap<>());
        }
    }

    /**
     * 按id，姓名查找单个人员，select
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select")
    public Result select(@RequestParam String message) {
        List<Staff> staffs = staffService.selectByStaffIdOrStaffName(message);
        staffs.forEach(staff -> staff.setStaffPwd(""));
        if (staffs.size() != 0) {
            return responseData.write("成功", 200, staffs);
        } else {
            return responseData.write("未找到人员", 400, new HashMap<>());
        }
    }

    /**
     * 查找所有人员
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select_all")
    public Result selectAll(@RequestParam(value = "pageNum", required = false) String pageNum,
                            @RequestParam(value = "pageSize", required = false) String pageSize) {
        try {
            int num = Integer.parseInt(pageNum);
            int size = Integer.parseInt(pageSize);
            if (num < 0 || size < 0) {
                return responseData.write("传参错误", 400, new HashMap<>());
            }
            List<Staff> staffs = staffService.selectAllByPageNumAndPageSize(num, size);
            int sum = staffService.selectAll().size();
            HashMap map = new HashMap();
            map.put("staffs", staffs);
            map.put("staffSum", sum);
            if (staffs.size() != 0) {
                return responseData.write("成功", 200, map);
            } else {
                return responseData.write("本页没有人员", 400, new HashMap<>());
            }
        } catch (NumberFormatException e) {
            return responseData.write("传参错误", 400, new HashMap<>());
        }
    }

    /**
     * 删除人员，delete
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/del")
    public Result del(@RequestParam String staffId) {
        if (staffService.deleteByStaffId(staffId) == 1) {
            return responseData.write("删除成功", 200, new HashMap<>());
        } else {
            return responseData.write("删除失败", 400, new HashMap<>());
        }
    }

}
