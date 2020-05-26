package com.cumt.internally.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:11
 */
public class RiskMark implements Common, Comparable<RiskMark> {
    private Integer id;
    private String staffId; // 工号
    private String staffName; // 员工姓名，风险统计时用
    private String staffDuty; // 职务，风险统计时用
    @NotNull(message = "风险id不能为空")
    private int riskControlId;
    @Digits(integer = 5, fraction = 2, message = "整数部分最多5位，小数最多2位")
    private double possibleGrade; // 可能分
    @Digits(integer = 5, fraction = 2, message = "整数部分最多5位，小数最多2位")
    private double effectGrade; // 影响分
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffDuty() {
        return staffDuty;
    }

    public void setStaffDuty(String staffDuty) {
        this.staffDuty = staffDuty;
    }

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

    public int getRiskControlId() {
        return riskControlId;
    }

    public void setRiskControlId(int riskControlId) {
        this.riskControlId = riskControlId;
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
        Map<Object, Object> map = new HashMap<>();
        map.put("staffId", staffId);
        map.put("riskControlId", riskControlId);
        map.put("possibleGrade", possibleGrade);
        map.put("effectGrade", effectGrade);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }

    @Override
    public int compareTo(RiskMark riskMark) {
        if (this.updateTime.before(riskMark.updateTime)) {
            return 1;
        } else {
            return -1;
        }
    }
}
