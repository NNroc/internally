package com.cumt.internally.mapper;

import com.cumt.internally.model.RiskLevel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

/**
 * @author NNroc
 * @date 2020/5/30 11:36
 */
public interface RiskLevelMapper {
    @Select({
            "select * from risk_level"
    })
    @Results(value = {
            @Result(column = "highGrade", property = "high", jdbcType = JdbcType.DOUBLE),
            @Result(column = "mediumGrade", property = "medium", jdbcType = JdbcType.DOUBLE),
            @Result(column = "lowGrade", property = "low", jdbcType = JdbcType.DOUBLE)
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
