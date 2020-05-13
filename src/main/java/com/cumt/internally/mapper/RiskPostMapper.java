package com.cumt.internally.mapper;
import com.cumt.internally.model.RiskPost;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface RiskPostMapper {
    @Delete({
        "delete from risk_post",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into risk_post (staffId, ",
        "possibleGrade, effectGrade, ",
        "createTime, updateTime)",
        "values (#{staffId,jdbcType=VARCHAR}, ",
        "#{possibleGrade,jdbcType=DOUBLE}, #{effectGrade,jdbcType=DOUBLE}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RiskPost record);

    @Select({
        "select * from risk_post",
        "where id = #{id,jdbcType=INTEGER}"
    })
    RiskPost selectByPrimaryKey(Integer id);

    @Select({
        "select * from risk_post"
    })
    List<RiskPost> selectAll();

    @Update({
        "update risk_post",
        "set staffId = #{staffId,jdbcType=VARCHAR},",
          "possibleGrade = #{possibleGrade,jdbcType=DOUBLE},",
          "effectGrade = #{effectGrade,jdbcType=DOUBLE},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(RiskPost record);
}