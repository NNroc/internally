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
    @Insert({
            "insert into risk_control (mainName, processName,",
            "processPoint, riskId, riskDescribe,",
            "controlObjectives, controlId,",
            "controlName, controlMeasures,",
            "responsiblePosition, correspondingSystem,",
            "evidence, createTime,",
            "updateTime)",
            "values (#{mainName,jdbcType=VARCHAR}, #{processName,jdbcType=VARCHAR},",
            "#{processPoint,jdbcType=VARCHAR}, #{riskId,jdbcType=VARCHAR}, #{riskDescribe,jdbcType=VARCHAR},",
            "#{controlObjectives,jdbcType=VARCHAR}, #{controlId,jdbcType=VARCHAR},",
            "#{controlName,jdbcType=VARCHAR}, #{controlMeasures,jdbcType=VARCHAR},",
            "#{responsiblePosition,jdbcType=VARCHAR}, #{correspondingSystem,jdbcType=VARCHAR},",
            "#{evidence,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP},",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RiskControl riskControl);

    @Select({
            "select * from risk_control",
            "where id = #{id,jdbcType=INTEGER}"
    })
    RiskControl selectById(Integer id);

    @Select({
            "select * from risk_control"
    })
    List<RiskControl> selectAll();

    @Update({
            "update risk_control",
            "set mainName = #{mainName,jdbcType=VARCHAR},",
            "processName = #{processName,jdbcType=VARCHAR},",
            "processPoint = #{processPoint,jdbcType=VARCHAR},",
            "riskId = #{riskId,jdbcType=VARCHAR},",
            "riskDescribe = #{riskDescribe,jdbcType=VARCHAR},",
            "controlObjectives = #{controlObjectives,jdbcType=VARCHAR},",
            "controlId = #{controlId,jdbcType=VARCHAR},",
            "controlName = #{controlName,jdbcType=VARCHAR},",
            "controlMeasures = #{controlMeasures,jdbcType=VARCHAR},",
            "responsiblePosition = #{responsiblePosition,jdbcType=VARCHAR},",
            "correspondingSystem = #{correspondingSystem,jdbcType=VARCHAR},",
            "evidence = #{evidence,jdbcType=VARCHAR},",
            "createTime = #{createTime,jdbcType=TIMESTAMP},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RiskControl riskControl);

    @Insert({
            "insert into risk_post (id, mainName, processName,",
            "processPoint, riskId, riskDescribe,",
            "controlObjectives, controlId,",
            "controlName, controlMeasures,",
            "responsiblePosition, correspondingSystem,",
            "evidence, createTime,",
            "updateTime)",
            "values (#{id,jdbcType=INTEGER}, #{mainName,jdbcType=VARCHAR}, #{processName,jdbcType=VARCHAR},",
            "#{processPoint,jdbcType=VARCHAR}, #{riskId,jdbcType=VARCHAR}, #{riskDescribe,jdbcType=VARCHAR},",
            "#{controlObjectives,jdbcType=VARCHAR}, #{controlId,jdbcType=VARCHAR},",
            "#{controlName,jdbcType=VARCHAR}, #{controlMeasures,jdbcType=VARCHAR},",
            "#{responsiblePosition,jdbcType=VARCHAR}, #{correspondingSystem,jdbcType=VARCHAR},",
            "#{evidence,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP},",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insertRiskPost(RiskControl riskControl);

    @Select({
            "select * from risk_post"
    })
    List<RiskControl> selectRiskPost();

    @Delete({
            "delete from risk_post",
            "where postId = #{postId,jdbcType=INTEGER}"
    })
    int deleteInRiskPostById(int postId);
}