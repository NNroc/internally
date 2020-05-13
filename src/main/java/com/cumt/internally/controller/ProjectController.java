package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 18:28
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping("/send_pic")
    public Result sendPic() {
        return null;
    }

    /**
     * 获取图片
     *
     * @return
     */
    @RequestMapping("/get_pic")
    public Result getPic() {
        return null;
    }

    /**
     * 获取流程说明
     *
     * @return
     */
    @RequestMapping("/get_project")
    public Result getProject() {
        return null;
    }
}
