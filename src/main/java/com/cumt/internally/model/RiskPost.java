package com.cumt.internally.model;

import java.util.Date;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:11
 */
public class RiskPost implements Common {
    private Integer id;
    private String staffId; // 工号
    private String staffName; // 姓名
    private double possibleGrade; //可能分
    private double effectGrade; //影响分
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
