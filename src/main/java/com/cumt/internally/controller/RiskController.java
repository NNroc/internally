package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 18:02
 */
@RestController
@RequestMapping("/risk")
public class RiskController {
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
    public Result sendRiskGrade() {
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
