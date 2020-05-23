package com.cumt.internally.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/12 13:11
 */
public class RiskMark implements Common {
    private Integer id;
    @NotBlank(message = "工号不能为空")
    private String staffId; // 工号
    @NotBlank(message = "风险id不能为空")
    private int riskControlId;
    @Digits(integer = 5, fraction = 2, message = "整数部分最多5位，小数最多2位")
    private double possibleGrade; //可能分
    @Digits(integer = 5, fraction = 2, message = "整数部分最多5位，小数最多2位")
    private double effectGrade; //影响分
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

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
}
