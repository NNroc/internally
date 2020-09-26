package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.annotation.UserToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskControl;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.model.Staff;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import java.util.Collections;
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
 * @date 2020/5/13 18:02
 */
@RestController
@RequestMapping("/risk")
public class RiskController {
    @Autowired
    ResponseData responseData;
    @Autowired
    RiskMarkService riskMarkService;
    @Autowired
    RiskControlService riskControlService;
    @Autowired
    StaffService staffService;

    /**
     * TODO 加入 manage 变量筛选
     * 获取风险矩阵
     *
     * @return
     */
    @UserToken
    @RequestMapping("/get_risk_control")
    public Result getAll() {
        return responseData.write("获取成功", 200, riskControlService.selectAll());
    }

    /**
     * 个人评分
     *
     * @return
     */
    @UserToken
    @RequestMapping("/send_riskGrade")
    public Result sendRiskGrade(@Valid RiskMark riskMark, BindingResult errors, HttpServletRequest httpServletRequest) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 404, list);
        }
        String token = httpServletRequest.getHeader("token");
        Staff staff = staffService.getStaffFromToken(token);
        riskMark.setStaffId(staff.getStaffId());
        if (checkIdExist(riskMark) == 1) {
            riskMarkService.insert(riskMark);
            return responseData.write("评分成功", 200, new HashMap<>());
        } else if (checkIdExist(riskMark) == 0) {
            if (riskMarkService.update(riskMark) == 1) {
                return responseData.write("修改评分成功", 200, new HashMap<>());
            } else {
                return responseData.write("修改失败", 400, new HashMap<>());
            }
        } else {
            return responseData.write("id不存在", 400, new HashMap<>());
        }
    }

    /**
     * 查看是否评论过
     * 1：直接插入
     * 0：更新
     * -1：不存在
     *
     * @param riskMark
     * @return
     */
    private int checkIdExist(RiskMark riskMark) {
        if (staffService.selectByStaffId(riskMark.getStaffId()) != null &&
                riskControlService.selectById(riskMark.getRiskControlId()) != null) {
            if (riskMarkService.selectByStaffIdAndRiskControlId(riskMark.getStaffId(), riskMark.getRiskControlId()) == null) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    /**
     * 获取所有个人评分，按时间降序
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_all_grade")
    public Result getGrade() {
        List<RiskMark> riskMarkList = riskMarkService.selectAll();
        Collections.sort(riskMarkList);
        return responseData.write("获取成功", 200, riskMarkList);
    }

    @AdministratorToken
    @RequestMapping("/get_grade_by_staffId")
    public Result getGradeByStaffId(@RequestParam String staffId) {
        List<RiskMark> riskMarkList = riskMarkService.selectByStaffId(staffId);
        Collections.sort(riskMarkList);
        return responseData.write("获取成功", 200, riskMarkList);
    }

    @AdministratorToken
    @RequestMapping("/get_grade_by_staffName")
    public Result getGradeByStaffName(@RequestParam String staffName) {
        List<RiskMark> riskMarkList = riskMarkService.selectByStaffName(staffName);
        Collections.sort(riskMarkList);
        return responseData.write("获取成功", 200, riskMarkList);
    }

    @AdministratorToken
    @RequestMapping("/get_grade_by_riskControlId")
    public Result getGradeByRiskId(@RequestParam Integer riskControlId) {
        List<RiskMark> riskMarkList = riskMarkService.selectByRiskControlId(riskControlId);
        Collections.sort(riskMarkList);
        return responseData.write("获取成功", 200, riskMarkList);
    }

    @AdministratorToken
    @RequestMapping("/get_grade_by_staffPosition")
    public Result getGradeByStaffDuty(@RequestParam String staffPosition) {
        List<RiskMark> riskMarkList = riskMarkService.selectByStaffPosition(staffPosition);
        Collections.sort(riskMarkList);
        return responseData.write("获取成功", 200, riskMarkList);
    }

    /**
     * 清空风险评分
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/clear")
    public Result clearRisk() {
        riskMarkService.clear();
        return responseData.write("清空成功", 200, new HashMap<>());
    }

    /**
     * 个人修改 + token(根据token权限修改or存储)
     *
     * @param riskControl
     * @param httpServletRequest
     * @return
     */
    @UserToken
    @RequestMapping("/send_modify")
    public Result sendModify(@Valid RiskControl riskControl, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Staff staff = staffService.getStaffFromToken(token);
        if (staff.getStaffWeight() == 1.0) {
            // 数据库写入
            riskControlService.insertRiskPost(riskControl);
            return responseData.write("提交成功", 200, new HashMap<>());
        } else if (staff.getStaffWeight() == 4.0) {
            // 数据库修改
            riskControlService.update(riskControl);
            return responseData.write("修改成功", 200, new HashMap<>());
        }
        return responseData.write("员工权值存在问题！", 444, new HashMap<>());
    }


    /**
     * 查找职工提交的数据
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select_risk_post")
    public Result selectRiskPost() {
        List<RiskControl> riskPost = riskControlService.selectRiskPost();
        if (riskPost != null) {
            return responseData.write("查找成功", 200, riskPost);
        } else {
            return responseData.write("没有数据", 200, new HashMap<>());
        }
    }

    /**
     * todo 管理员审核员工的并同意更新，删除。
     * 更新：先更新risk_control再从risk_post中删除
     * 删除：从risk_post中删除
     *
     * @param riskControl
     * @param choose      1 更新 0 删除
     * @return
     */
    @AdministratorToken
    @RequestMapping("/approve_risk_post")
    public Result approveRiskPost(@Valid RiskControl riskControl, BindingResult errors, @RequestParam int choose) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 404, list);
        }
        RiskControl use = riskControlService.selectById(riskControl.getId());
        if (use == null) {
            return responseData.write("未找到原信息", 404, new HashMap<>());
        }
        if (choose == 1) {
            if (riskControlService.update(riskControl) == 1) {
                riskControlService.deleteInRiskPostById(riskControl.getPostId());
                return responseData.write("更新成功", 200, new HashMap<>());
            } else {
                return responseData.write("更新失败", 400, new HashMap<>());
            }
        } else {
            if (riskControlService.deleteInRiskPostById(riskControl.getPostId()) == 1) {
                return responseData.write("删除成功", 201, new HashMap<>());
            } else {
                return responseData.write("删除失败", 401, new HashMap<>());
            }
        }
    }

}
