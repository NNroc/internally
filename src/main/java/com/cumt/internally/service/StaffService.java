package com.cumt.internally.service;

import com.cumt.internally.mapper.StaffMapper;
import com.cumt.internally.model.Staff;
import com.cumt.internally.utils.JwtUtil;
import com.cumt.internally.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class StaffService {
    private static final String encry = "salt";
    @Autowired
    private StaffMapper staffMapper;

    /**
     * 根据员工id删除员工
     *
     * @param staffId
     * @return
     */
    public int deleteByStaffId(String staffId) {
        return staffMapper.deleteByStaffId(staffId);
    }

    /**
     * 添加员工
     *
     * @param staff
     * @return
     */
    public int insert(Staff staff) {
        return staffMapper.insert(staff);
    }

    /**
     * 根据员工id查找员工
     *
     * @param message
     * @return
     */
    public Staff selectByStaffId(String message) {
        return staffMapper.selectByStaffId(message);
    }

    /**
     * 根据员工id或姓名查找员工
     *
     * @param message
     * @return
     */
    public List<Staff> selectByStaffIdOrStaffName(String message) {
        List<Staff> staffs = new ArrayList<>();
        Staff staff = staffMapper.selectByStaffId(message);
        if (staff.getStaffId().equals("")) {
            staffs = staffMapper.selectByStaffName(message);
        } else {
            staffs.add(staff);
        }
        return staffs;
    }

    /**
     * 查找所有人
     *
     * @return
     */
    public List<Staff> selectAll() {
        return staffMapper.selectAll();
    }

    /**
     * 按页查找所有人
     *
     * @return
     */
    public List<Staff> selectAllByPageNumAndPageSize(int pageNum, int pageSize) {
        return staffMapper.selectAllByPageNumAndPageSize((pageNum - 1) * pageSize, pageSize);
    }

    /**
     * 根据员工id更新员工信息
     *
     * @param staff
     * @return
     */
    public int updateByStaff(Staff staff) {
        staff.setUpdateTime(new Date());
        return staffMapper.updateByStaffId(staff);
    }

    /**
     * 从token找出staffId并核对是否存在
     *
     * @param token
     * @return
     */
    public Staff getStaffFromToken(String token) {
        if (JwtUtil.getClaims(token, encry) == null) {
            return new Staff();
        }
        try {
            String staffId = (String) JwtUtil.getClaims(token, encry).get("staffId");
            if (!StringUtils.isBlank(staffId)) {
                return staffMapper.selectByStaffId(staffId);
            }
        } catch (NullPointerException e) {
            return new Staff();
        }
        return new Staff();
    }

    /**
     * 修改密码
     *
     * @param staff
     * @param staffPwd
     * @return
     */
    public int updatePwd(Staff staff, String staffPwd) {
        staff.setUpdateTime(new Date());
        staff.setStaffPwd(MD5Util.md5(staffPwd));
        return staffMapper.updatePwdByStaff(staff);
    }

    /**
     * 重置密码
     *
     * @param staffId
     * @return
     */
    public int renewPwdByStaffId(String staffId) {
        Staff staff = staffMapper.selectByStaffId(staffId);
        staff.setUpdateTime(new Date());
        staff.setStaffPwd(MD5Util.md5(staff.getStaffId()));
        return staffMapper.updatePwdByStaff(staff);
    }
}
