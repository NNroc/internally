package com.cumt.internally.mapper;

import com.cumt.internally.model.Staff;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:40
 */
public interface StaffMapper {
    @Delete({
            "delete from staff",
            "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    int deleteByStaffId(String staffId);

    @Insert({
            "insert into staff (staffId, staffName, ",
            "staffUnit, staffDuty, ",
            "staffProfessional, staffWeight, ",
            "staffPwd, createTime, ",
            "updateTime)",
            "values (#{staffId,jdbcType=VARCHAR}, #{staffName,jdbcType=VARCHAR}, ",
            "#{staffUnit,jdbcType=VARCHAR}, #{staffDuty,jdbcType=VARCHAR}, ",
            "#{staffProfessional,jdbcType=VARCHAR}, #{staffWeight,jdbcType=DOUBLE}, ",
            "#{staffPwd,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Staff record);

    @Select({
            "select * from staff",
            "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    Staff selectByStaffId(String staffId);

    @Select({
            "select * from staff",
            "where staffName = #{staffName,jdbcType=VARCHAR}"
    })
    List<Staff> selectByStaffName(String staffName);

    @Select({
            "select * from staff order by createTime asc",
            "limit #{pageStart,jdbcType=INTEGER}, #{pageSize,jdbcType=INTEGER}"
    })
    List<Staff> selectAll(int pageStart, int pageSize);

    @Update({
            "update staff",
            "set staffName = #{staffName,jdbcType=VARCHAR},",
            "staffUnit = #{staffUnit,jdbcType=VARCHAR},",
            "staffDuty = #{staffDuty,jdbcType=VARCHAR},",
            "staffProfessional = #{staffProfessional,jdbcType=VARCHAR},",
            "staffWeight = #{staffWeight,jdbcType=DOUBLE},",
            "staffPwd = #{staffPwd,jdbcType=VARCHAR},",
            "createTime = #{createTime,jdbcType=TIMESTAMP},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    int updateByStaffId(Staff staff);

    @Update({
            "update staff",
            "set staffPwd = #{staffPwd,jdbcType=VARCHAR},",
            "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
            "where staffId = #{staffId,jdbcType=VARCHAR}"
    })
    int updatePwdByStaff(Staff staff);
}