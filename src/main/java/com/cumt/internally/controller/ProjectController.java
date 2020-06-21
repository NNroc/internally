package com.cumt.internally.controller;

import com.cumt.internally.annotation.UserToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Project;
import com.cumt.internally.model.Result;
import com.cumt.internally.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
     * 获取流程说明，单个说明
     * @param type 流程图名
     * @param num 序号
     * @return
     */
    @UserToken
    @RequestMapping("/get_project")
    public Result getProject(@RequestParam String type, @RequestParam int num) {
        Project project = projectService.selectByType(type, num);
        if(project!=null){
            return responseData.write(type, 200, project.toDict());
        }else {
            return responseData.write("未找到", 400, new HashMap<>());
        }
    }

}
