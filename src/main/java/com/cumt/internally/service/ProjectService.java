package com.cumt.internally.service;

import com.cumt.internally.mapper.ProjectMapper;
import com.cumt.internally.model.Project;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 根据 manage 和 type 查找表格
     *
     * @param manage
     * @param type
     * @param num
     * @return
     */
    public Project selectByTypeAndManage(String manage, String type, int num) {
        return projectMapper.selectByTypeAndManage(manage, type, num);
    }

    public void updateByTypeAndManage(Project record, String manage, String type, int num) {
        record.setUpdateTime(new Date());
        projectMapper.updateByTypeAndManage(record, manage, type, num);
    }
}
