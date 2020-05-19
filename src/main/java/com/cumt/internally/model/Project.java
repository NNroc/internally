package com.cumt.internally.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:13
 */
public class Project implements Common {
    private Integer id;
    private String type; //表名
    private String num; //编号
    private String stepName; //步骤名称
    private String department; //主管部门/岗位
    private String controlId; //控制编号
    private String stepDescribe; //步骤描述
    private String document; //输出文档
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getStepDescribe() {
        return stepDescribe;
    }

    public void setStepDescribe(String stepDescribe) {
        this.stepDescribe = stepDescribe;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("num", num);
        map.put("name", stepName);
        map.put("department", department);
        map.put("controlId", controlId);
        map.put("describe", stepDescribe);
        map.put("document", document);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }
}
