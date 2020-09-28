package com.cumt.internally.mapper;

import com.cumt.internally.model.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface ProjectMapper {
    @Delete({
            "delete from project",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    int deleteByType(String type);

    @Insert({
            "insert into project (type, manage,",
            "num, stepName, department,",
            "controlId, stepDescribe,",
            "document, createTime,",
            "updateTime)",
            "values (#{type,jdbcType=VARCHAR}, #{manage,jdbcType=VARCHAR},",
            "#{num,jdbcType=INTEGER}, #{stepName,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR},",
            "#{controlId,jdbcType=VARCHAR}, #{stepDescribe,jdbcType=VARCHAR},",
            "#{document,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP},",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Project project);

    @Select({
            "select * from project where type = #{type,jdbcType=VARCHAR}",
            "and num = #{num,jdbcType=INTEGER} and manage = #{manage,jdbcType=VARCHAR}"
    })
    Project selectByTypeAndManage(String manage, String type, int num);

    @Update({
            "update project",
            "set stepName = #{stepName,jdbcType=VARCHAR},",
            "department = #{department,jdbcType=VARCHAR},",
            "controlId = #{controlId,jdbcType=VARCHAR},",
            "stepDescribe = #{stepDescribe,jdbcType=VARCHAR},",
            "document = #{document,jdbcType=VARCHAR},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where type = #{type,jdbcType=VARCHAR}",
            "and num = #{num,jdbcType=INTEGER}",
            "and manage = #{manage,jdbcType=VARCHAR}"
    })
    int updateByTypeAndManage(Project record, String manage, String type, int num);
}