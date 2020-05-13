package com.cumt.internally.mapper;
import com.cumt.internally.model.Risk;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface RiskMapper {
    @Delete({
        "delete from risk",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into risk (riskUnit, ",
        "riskClassification, riskName, ",
        "riskDescribe, riskPossibility, ",
        "riskImpact, riskLevel, ",
        "createTime, updateTime)",
        "values (#{riskUnit,jdbcType=VARCHAR}, ",
        "#{riskClassification,jdbcType=VARCHAR}, #{riskName,jdbcType=VARCHAR}, ",
        "#{riskDescribe,jdbcType=VARCHAR}, #{riskPossibility,jdbcType=DOUBLE}, ",
        "#{riskImpact,jdbcType=DOUBLE}, #{riskLevel,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Risk record);

    @Select({
        "select * from risk",
        "where id = #{id,jdbcType=INTEGER}"
    })
    Risk selectByPrimaryKey(Integer id);

    @Select({
        "select * from risk"
    })
    List<Risk> selectAll();

    @Update({
        "update risk",
        "set riskUnit = #{riskUnit,jdbcType=VARCHAR},",
          "riskClassification = #{riskClassification,jdbcType=VARCHAR},",
          "riskName = #{riskName,jdbcType=VARCHAR},",
          "riskDescribe = #{riskDescribe,jdbcType=VARCHAR},",
          "riskPossibility = #{riskPossibility,jdbcType=DOUBLE},",
          "riskImpact = #{riskImpact,jdbcType=DOUBLE},",
          "riskLevel = #{riskLevel,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Risk record);
}