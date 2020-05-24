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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
     * 个人修改+token(根据token权限修改or存储)
     *
     * @return
     */
    @UserToken
    @RequestMapping("send_modify")
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
     * 个人评分
     *
     * @return
     */
    @UserToken
    @RequestMapping("/send_riskGrade")
    public Result sendRiskGrade(@Valid RiskMark riskMark, BindingResult errors,HttpServletRequest httpServletRequest) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 404, list);
        }
        String token = httpServletRequest.getHeader("token");
        Staff staff = staffService.getStaffFromToken(token);
        riskMark.setStaffId(staff);
        riskMarkService.insert(riskMark);
        return responseData.write("评分成功", 200, new HashMap<>());
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

    /**
     * 清空风险评分
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/clear/risk")
    public Result clearRisk() {
        riskMarkService.clear();
        return null;
    }

}
