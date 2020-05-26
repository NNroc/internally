package com.cumt.internally.mapper;

import com.cumt.internally.model.Risk;
import com.cumt.internally.model.RiskMark;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface RiskMarkMapper {
    @Delete({
            "delete from risk_mark",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into risk_mark (staffId, riskControlId,",
            "possibleGrade, effectGrade,",
            "createTime, updateTime)",
            "values (#{staffId,jdbcType=VARCHAR}, #{riskControlId,jdbcType=VARCHAR},",
            "#{possibleGrade,jdbcType=DOUBLE}, #{effectGrade,jdbcType=DOUBLE}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RiskMark record);

    @Select({
            "select * from risk_mark",
            "where riskControlId = #{riskControlId,jdbcType=INTEGER}"
    })
    RiskMark selectByRiskControlId(Integer riskControlId);

    @Select({
            "select * from risk_mark",
            "where staffId = #{staffId,jdbcType=VARCHAR}",
            "and riskControlId = #{riskControlId,jdbcType=INTEGER}"
    })
    RiskMark selectByStaffIdAndRiskControlId(String staffId, Integer riskControlId);

    @Select({
            "select risk_mark.*, staff.staffName, staff.staffDuty from risk_mark, staff",
            "where staff.staffId = risk_mark.staffId"
    })
    List<Risk> selectAll();

    @Update({
            "update risk_mark",
            "set staffId = #{staffId,jdbcType=VARCHAR},",
            "riskControlId = #{riskControlId,jdbcType=VARCHAR},",
            "possibleGrade = #{possibleGrade,jdbcType=DOUBLE},",
            "effectGrade = #{effectGrade,jdbcType=DOUBLE},",
            "createTime = #{createTime,jdbcType=TIMESTAMP},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    int updateByStaffId(RiskMark riskMark);

    @Update("truncate table risk_mark")
    void clear();
}