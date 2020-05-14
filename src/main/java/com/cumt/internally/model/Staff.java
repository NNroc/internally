package com.cumt.internally.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:10
 */
public class Staff implements Common {
    @NotNull(message = "工号不能为空")
    @Min(value = 1, message = "工号太短")
    @Max(value = 20, message = "工号太长")
    private String staffId; // 工号
    @NotNull(message = "姓名不能为空")
    @Min(value = 1, message = "姓名长度须大于等于1")
    @Max(value = 20, message = "姓名长度须小于等于20")
    private String staffName; // 姓名
    private String staffUnit; // 单位
    private String staffDuty; // 职务
    private String staffProfessional; // 职称
    private double staffWeight; // 权重
    private String staffPwd; // 密码
    @NotNull(message = "创建时间不能为空")
    private Date createTime; // 创建时间
    @NotNull(message = "更新时间不能为空")
    private Date updateTime; // 更新时间

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

    public String getStaffUnit() {
        return staffUnit;
    }

    public void setStaffUnit(String staffUnit) {
        this.staffUnit = staffUnit;
    }

    public String getStaffDuty() {
        return staffDuty;
    }

    public void setStaffDuty(String staffDuty) {
        this.staffDuty = staffDuty;
    }

    public String getStaffProfessional() {
        return staffProfessional;
    }

    public void setStaffProfessional(String staffProfessional) {
        this.staffProfessional = staffProfessional;
    }

    public double getStaffWeight() {
        return staffWeight;
    }

    public void setStaffWeight(double staffWeight) {
        this.staffWeight = staffWeight;
    }

    public String getStaffPwd() {
        return staffPwd;
    }

    public void setStaffPwd(String staffPwd) {
        this.staffPwd = staffPwd;
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
        map.put("staffName", staffName);
        map.put("staffUnit", staffUnit);
        map.put("staffDuty", staffDuty);
        map.put("staffProfessional", staffProfessional);
        map.put("staffWeight", staffWeight);
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        return map;
    }
}
