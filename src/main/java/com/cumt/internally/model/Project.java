package com.cumt.internally.model;

import java.util.Date;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:13
 */
public class Project implements Common {
    private Integer id;
    private Integer riskSort; //编号
    private String riskUnit; //步骤名称
    private String riskClassification; //主管部门/岗位
    private String riskName; //控制编号
    private String riskDescribe; //步骤描述
    private double riskPossibility; //输出文档
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
