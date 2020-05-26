package com.cumt.internally.model.analysis;

import com.cumt.internally.model.Common;

import java.util.HashMap;
import java.util.Map;

/**
 * 废问卷统计实体
 *
 * @author NNroc
 * @date 2020/5/26 15:25
 */
public class PaperNumber implements Common {
    private String staffDuty; // 职级
    private Integer paperNum; // 问卷份数
    private double weight; // 权值系数

    public String getStaffDuty() {
        return staffDuty;
    }

    public void setStaffDuty(String staffDuty) {
        this.staffDuty = staffDuty;
    }

    public Integer getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(Integer paperNum) {
        this.paperNum = paperNum;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("职级", staffDuty);
        map.put("问卷份数", paperNum);
        map.put("权值系数", weight);
        return map;
    }
}
