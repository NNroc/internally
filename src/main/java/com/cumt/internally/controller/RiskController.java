package com.cumt.internally.controller;

import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.service.RiskPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    RiskPostService riskPostService;

    /**
     * 获取所有风险
     *
     * @return
     */
    @RequestMapping("/get_all")
    public Result getAll() {
        return null;
    }

    /**
     * 获取单个风险
     *
     * @return
     */
    @RequestMapping("/get_all_risk")
    public Result getRisk() {
        return null;
    }

    /**
     * 个人修改+token(根据token权限修改or存储)
     *
     * @return
     */
    @RequestMapping("send_modify")
    public Result sendModify() {
        return null;
    }

    /**
     * 个人评分
     *
     * @return
     */
    @RequestMapping("/send_riskGrade")
    public Result sendRiskGrade(@Valid RiskMark riskMark, BindingResult errors) {
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            return responseData.write(errors.getAllErrors().toString(), 404, list);
        }

        return null;
    }

    /**
     * 获取所有个人评分
     *
     * @return
     */
    @RequestMapping("/get_all_grade")
    public Result getGrade() {
        return null;
    }

    /**
     * 清空风险
     *
     * @return
     */
    @RequestMapping("/clear/risk")
    public Result clearRisk() {
        return null;
    }

}
