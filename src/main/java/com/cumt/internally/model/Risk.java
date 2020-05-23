package com.cumt.internally.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:11
 */
public class Risk implements Common {
    private int id; //序号
    private int riskSort; // 风险排序
    private String processName; // 业务流程
    private String processPoint; // 流程节点
    private String riskId; // 风险编号
    private String riskDescribe; // 风险描述
    private double riskPossibility; // 可能性
    private double riskImpact; // 影响性
    private String riskLevel; // 风险等级
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRiskSort() {
        return riskSort;
    }

    public void setRiskSort(int riskSort) {
        this.riskSort = riskSort;
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

    public double getRiskPossibility() {
        return riskPossibility;
    }

    public void setRiskPossibility(double riskPossibility) {
        this.riskPossibility = riskPossibility;
    }

    public double getRiskImpact() {
        return riskImpact;
    }

    public void setRiskImpact(double riskImpact) {
        this.riskImpact = riskImpact;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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
        map.put("riskSort", riskSort);
        map.put("processName", processName);
        map.put("processPoint", processPoint);
        map.put("riskId", riskId);
        map.put("riskDescribe", riskDescribe);
        map.put("riskPossibility", riskPossibility);
        map.put("riskImpact", riskImpact);
        map.put("riskLevel", riskLevel);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }
}
