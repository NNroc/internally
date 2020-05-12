package com.cumt.internally.model;

import java.util.Date;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:13
 */
public class RiskControl implements Common {
    private Integer id;
    private String process; // 业务流程
    private String point; // 流程节点
    private String riskId; // 风险编号
    private String describe; // 风险描述
    private String controlObjectives; // 控制目标
    private String controlId; // 控制编号
    private String controlName; // 控制步骤名称
    private String controlMeasures; // 控制措施
    private String responsiblePosition; // 责任岗位
    private String correspondingSystem; // 对应制度
    private String evidence; // 实施证据
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getControlObjectives() {
        return controlObjectives;
    }

    public void setControlObjectives(String controlObjectives) {
        this.controlObjectives = controlObjectives;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlMeasures() {
        return controlMeasures;
    }

    public void setControlMeasures(String controlMeasures) {
        this.controlMeasures = controlMeasures;
    }

    public String getResponsiblePosition() {
        return responsiblePosition;
    }

    public void setResponsiblePosition(String responsiblePosition) {
        this.responsiblePosition = responsiblePosition;
    }

    public String getCorrespondingSystem() {
        return correspondingSystem;
    }

    public void setCorrespondingSystem(String correspondingSystem) {
        this.correspondingSystem = correspondingSystem;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
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
        return null;
    }
}
