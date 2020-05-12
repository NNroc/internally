package com.cumt.internally.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:11
 */
public class Risk implements Common {
    private String id; //序号
    private Integer riskSort; //风险排序
    private String riskUnit; //单位业务
    private String riskClassification; //风险类别
    private String riskName; //风险名称
    private String riskDescribe; //风险描述
    private double riskPossibility; //可能性
    private double riskImpact; //影响性
    private String riskLevel; //风险等级
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRiskSort() {
        return riskSort;
    }

    public void setRiskSort(Integer riskSort) {
        this.riskSort = riskSort;
    }

    public String getRiskUnit() {
        return riskUnit;
    }

    public void setRiskUnit(String riskUnit) {
        this.riskUnit = riskUnit;
    }

    public String getRiskClassification() {
        return riskClassification;
    }

    public void setRiskClassification(String riskClassification) {
        this.riskClassification = riskClassification;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
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
        map.put("id", id);
        map.put("riskSort", riskSort);
        map.put("riskUnit", riskUnit);
        map.put("riskClassification", riskClassification);
        map.put("riskName", riskName);
        map.put("riskDescribe", riskDescribe);
        map.put("riskPossibility", riskPossibility);
        map.put("riskImpact", riskImpact);
        map.put("riskLevel", riskLevel);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }
}
