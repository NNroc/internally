package com.cumt.internally.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:13
 */
public class RiskControl implements Common, Comparable<RiskControl> {
    private Integer id;
    private String mainName; // 流程总名称
    private String processName; // 业务流程，display1
    private String processPoint; // 流程节点，display2
    private String riskId; // 风险编号，display3
    private String riskDescribe; // 风险描述，display4
    private String controlObjectives; // 控制目标
    private String controlId; // 控制编号
    private String controlName; // 控制步骤名称
    private String controlMeasures; // 控制措施
    private String responsiblePosition; // 责任岗位
    private String correspondingSystem; // 对应制度
    private String evidence; // 实施证据
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    private int riskSort; // 风险排序，display0
    private double possibleGrade = 0.0; // 可能性，display5
    private double effectGrade = 0.0; // 影响性，display6
    private double sumGrade = 0.0; // 总分，display7
    private String riskLevel; // 风险等级，display8
    private int num = 0;

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessPoint() {
        return processPoint;
    }

    public void setProcessPoint(String processPoint) {
        this.processPoint = processPoint;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getRiskDescribe() {
        return riskDescribe;
    }

    public void setRiskDescribe(String riskDescribe) {
        this.riskDescribe = riskDescribe;
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

    public int getRiskSort() {
        return riskSort;
    }

    public void setRiskSort(int riskSort) {
        this.riskSort = riskSort;
    }

    public double getPossibleGrade() {
        return possibleGrade;
    }

    public void setPossibleGrade(double possibleGrade) {
        this.possibleGrade = possibleGrade;
    }

    public double getEffectGrade() {
        return effectGrade;
    }

    public void setEffectGrade(double effectGrade) {
        this.effectGrade = effectGrade;
    }

    public double getSumGrade() {
        return sumGrade;
    }

    public void setSumGrade(double sumGrade) {
        this.sumGrade = sumGrade;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("process", processName);
        map.put("point", processPoint);
        map.put("riskId", riskId);
        map.put("describe", riskDescribe);
        map.put("controlObjectives", controlObjectives);
        map.put("controlId", controlId);
        map.put("controlName", controlName);
        map.put("controlMeasures", controlMeasures);
        map.put("responsiblePosition", responsiblePosition);
        map.put("correspondingSystem", correspondingSystem);
        map.put("evidence", evidence);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }

    @Override
    public int compareTo(RiskControl riskControl) {
        if (this.sumGrade > riskControl.sumGrade) {
            return 1;
        } else if (this.sumGrade < riskControl.sumGrade) {
            return -1;
        } else {
            if (this.id <= riskControl.id) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
