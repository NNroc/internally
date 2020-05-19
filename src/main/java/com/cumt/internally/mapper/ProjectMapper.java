package com.cumt.internally.mapper;

import com.cumt.internally.model.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
            "insert into project (type,",
            "num, stepName, department,",
            "controlId, stepDescribe,",
            "document, createTime,",
            "updateTime)",
            "values (#{type,jdbcType=VARCHAR},",
            "#{num,jdbcType=VARCHAR}, #{stepName,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR},",
            "#{controlId,jdbcType=VARCHAR}, #{stepDescribe,jdbcType=VARCHAR},",
            "#{document,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP},",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Project project);

    @Select({
            "select * from project"
    })
    List<Project> selectAll();

    @Update({
            "update project",
            "set type = #{type,jdbcType=VARCHAR},",
            "num = #{num,jdbcType=VARCHAR},",
            "stepName = #{stepName,jdbcType=VARCHAR},",
            "department = #{department,jdbcType=VARCHAR},",
            "controlId = #{controlId,jdbcType=VARCHAR},",
            "stepDescribe = #{stepDescribe,jdbcType=VARCHAR},",
            "document = #{document,jdbcType=VARCHAR},",
            "createTime = #{createTime,jdbcType=TIMESTAMP},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Project record);
}