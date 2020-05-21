package com.cumt.internally.service;

import com.cumt.internally.mapper.ProjectMapper;
import com.cumt.internally.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    /**
     * 根据类别删除流程图表格
     *
     * @param type
     */
    public void deleteByType(String type) {
        projectMapper.deleteByType(type);
    }

    /**
     * 插入流程图表格信息
     *
     * @param project
     */
    public void insert(Project project) {
        projectMapper.insert(project);
    }

    /**
     * 根据type查找表格
     */
    public Project selectByType(String type,int num) {
        return projectMapper.selectByType(type,num);
    }
}
