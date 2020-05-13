package com.cumt.internally.mapper;

import com.cumt.internally.model.Project;
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
public interface ProjectMapper {
    @Delete({
            "delete from project",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into project (id, type, ",
            "num, name, department, ",
            "controlId, describe, ",
            "document, createTime, ",
            "updateTime)",
            "values (#{id,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
            "#{num,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR}, ",
            "#{controlid,jdbcType=VARCHAR}, #{describe,jdbcType=VARCHAR}, ",
            "#{document,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
            "#{updatetime,jdbcType=TIMESTAMP})"
    })
    int insert(Project record);

    @Select({
            "select",
            "id, type, num, name, department, controlId, describe, document, createTime, ",
            "updateTime",
            "from project",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num", property = "num", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
            @Result(column = "controlId", property = "controlid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "describe", property = "describe", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document", property = "document", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createTime", property = "createtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updateTime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    Project selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, type, num, name, department, controlId, describe, document, createTime, ",
            "updateTime",
            "from project"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num", property = "num", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
            @Result(column = "controlId", property = "controlid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "describe", property = "describe", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document", property = "document", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createTime", property = "createtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updateTime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<Project> selectAll();

    @Update({
            "update project",
            "set type = #{type,jdbcType=VARCHAR},",
            "num = #{num,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "department = #{department,jdbcType=VARCHAR},",
            "controlId = #{controlid,jdbcType=VARCHAR},",
            "describe = #{describe,jdbcType=VARCHAR},",
            "document = #{document,jdbcType=VARCHAR},",
            "createTime = #{createtime,jdbcType=TIMESTAMP},",
            "updateTime = #{updatetime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Project record);
}