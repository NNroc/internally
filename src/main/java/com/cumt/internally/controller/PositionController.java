package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.service.PositionService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/6/22 16:46
 */
@RequestMapping("/position")
@RestController
public class PositionController {
    @Autowired
    ResponseData responseData;
    @Autowired
    PositionService positionService;

    /**
     * 获取所有权重
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/select_all")
    public Result selectPosition() {
        return responseData.write("获取成功", 200, positionService.select());
    }

    /**
     * 修改权重
     *
     * @param position 职位
     * @param weight   权重
     * @return
     */
    @AdministratorToken
    @RequestMapping("/update")
    public Result updatePosition(@RequestParam String position, @RequestParam Double weight) {
        if (positionService.update(position, weight) == 1) {
            return responseData.write("修改成功", 200, new HashMap<>());
        } else {
            return responseData.write("修改失败", 400, new HashMap<>());
        }
    }
}
