package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskLevel;
import com.cumt.internally.service.RiskLevelService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/6/23 10:38
 */
@RestController
@RequestMapping("/risk/level")
public class RiskLevelController {
    @Autowired
    ResponseData responseData;
    @Autowired
    RiskLevelService riskLevelService;

    /**
     * 查看风险等级分数线
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select")
    public Result select() {
        return responseData.write("获取成功", 200, riskLevelService.select().toDict());
    }

    /**
     * 更新风险等级
     *
     * @param riskLevel
     * @return
     */
    @AdministratorToken
    @RequestMapping("/update")
    public Result update(RiskLevel riskLevel) {
        if (riskLevelService.updateGrade(riskLevel) == 1) {
            return responseData.write("修改成功", 200, new HashMap<>());
        } else {
            return responseData.write("修改失败", 400, new HashMap<>());
        }
    }
}
