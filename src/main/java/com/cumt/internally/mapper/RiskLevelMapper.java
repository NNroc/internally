package com.cumt.internally.mapper;

import com.cumt.internally.model.RiskLevel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author NNroc
 * @date 2020/5/30 11:36
 */
public interface RiskLevelMapper {
    @Select({
            "select * from risk_level"
    })
    RiskLevel select();

    @Update({
            "update risk_level",
            "set highGrade = #{high,jdbcType=DOUBLE},",
            "mediumGrade = #{medium,jdbcType=DOUBLE},",
            "lowGrade = #{low,jdbcType=DOUBLE}",
            "where id = 1"
    })
    int updateGrade(RiskLevel riskLevel);
}
