package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.annotation.PassToken;
import com.cumt.internally.annotation.UserToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Staff;
import com.cumt.internally.service.StaffService;
import com.cumt.internally.utils.JwtUtil;
import com.cumt.internally.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
    public Result login(@Valid Staff staff, BindingResult errors) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 404, list);
        }
        HashMap map = new HashMap();
        Staff staffChoose = staffService.selectByStaffId(staff.getStaffId());
        if (staffChoose != null) {
            // 验证密码
            if (staffChoose.getStaffPwd().equals(MD5Util.md5(staff.getStaffPwd()))) {
                String token = JwtUtil.getToken(staff, "salt", 60 * 24 * 30);
                map.put("token", token);
                // 返回token
                return responseData.write("登录成功", 200, map);
            } else {
                return responseData.write("密码错误", 200, new HashMap<>());
            }
        } else {
            return responseData.write("没有该用户", 200, new HashMap<>());
        }
    }

    /**
     * 重置密码，update
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/renew")
    public Result renew() {
        return null;
    }

    /**
     * 添加人员，insert
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/add")
    public Result add() {
        return null;
    }

    /**
     * 删除人员，delete
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/del")
    public Result del() {
        return null;
    }

    /**
     * 按id，姓名查找单个人员,select
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select")
    public Result select() {
        return null;
    }

    /**
     * 查找所有人员
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select_all")
    public Result selectAll(@PathVariable int pageNum, @PathVariable int pageSize) {
        return null;
    }
}
