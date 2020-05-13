package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 18:01
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping("/login")
    public Result login() {
        return null;
    }

    /**
     * 重置密码
     *
     * @return
     */
    @RequestMapping("/renew")
    public Result renew() {
        return null;
    }

    /**
     * 添加人员
     *
     * @return
     */
    @RequestMapping("/add")
    public Result add() {
        return null;
    }

    /**
     * 删除人员
     *
     * @return
     */
    @RequestMapping("/del")
    public Result del() {
        return null;
    }

    /**
     * 查找所有人员
     *
     * @return
     */
    @RequestMapping("/select")
    public Result select() {
        return null;
    }

    /**
     * 查找所有人员
     *
     * @return
     */
    @RequestMapping("/select_all")
    public Result selectAll() {
        return null;
    }
}
