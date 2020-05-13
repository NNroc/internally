package com.cumt.internally.mapper;
import com.cumt.internally.model.RiskControl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface RiskControlMapper {
    @Delete({
        "delete from risk_control",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into risk_control (process, ",
        "point, riskId, describe, ",
        "controlObjectives, controlId, ",
        "controlName, controlMeasures, ",
        "responsiblePosition, correspondingSystem, ",
        "evidence, createTime, ",
        "updateTime)",
        "values ( #{process,jdbcType=VARCHAR}, ",
        "#{point,jdbcType=VARCHAR}, #{riskId,jdbcType=INTEGER}, #{describe,jdbcType=VARCHAR}, ",
        "#{controlObjectives,jdbcType=VARCHAR}, #{controlId,jdbcType=INTEGER}, ",
        "#{controlName,jdbcType=VARCHAR}, #{controlMeasures,jdbcType=VARCHAR}, ",
        "#{responsiblePosition,jdbcType=VARCHAR}, #{correspondingSystem,jdbcType=VARCHAR}, ",
        "#{evidence,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RiskControl record);

    @Select({
        "select * from risk_control",
        "where id = #{id,jdbcType=INTEGER}"
    })
    RiskControl selectByPrimaryKey(Integer id);

    @Select({
        "select * from risk_control"
    })
    List<RiskControl> selectAll();

    @Update({
        "update risk_control",
        "set process = #{process,jdbcType=VARCHAR},",
          "point = #{point,jdbcType=VARCHAR},",
          "riskId = #{riskId,jdbcType=INTEGER},",
          "describe = #{describe,jdbcType=VARCHAR},",
          "controlObjectives = #{controlObjectives,jdbcType=VARCHAR},",
          "controlId = #{controlId,jdbcType=INTEGER},",
          "controlName = #{controlName,jdbcType=VARCHAR},",
          "controlMeasures = #{controlMeasures,jdbcType=VARCHAR},",
          "responsiblePosition = #{responsiblePosition,jdbcType=VARCHAR},",
          "correspondingSystem = #{correspondingSystem,jdbcType=VARCHAR},",
          "evidence = #{evidence,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RiskControl record);
}