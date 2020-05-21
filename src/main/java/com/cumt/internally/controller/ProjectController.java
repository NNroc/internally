package com.cumt.internally.controller;

import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Project;
import com.cumt.internally.model.Result;
import com.cumt.internally.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 18:28
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ResponseData responseData;
    @Autowired
    ProjectService projectService;

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
     * 获取流程说明，单个说明
     *
     * @return
     */
    @RequestMapping("/get_project")
    public Result getProject(@RequestParam String type, @RequestParam int num) {
        Project project = projectService.selectByType(type, num);
        return responseData.write(type, 200, project.toDict());
    }
}
