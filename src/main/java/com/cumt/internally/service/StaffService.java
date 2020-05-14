package com.cumt.internally.service;

import com.cumt.internally.mapper.StaffMapper;
import com.cumt.internally.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class StaffService {
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
     * 按页查找所有人
     *
     * @return
     */
    public List<Staff> selectAll(int pageNum, int pageSize) {
        return staffMapper.selectAll((pageNum - 1) * pageSize, pageSize);
    }

    /**
     * 根据员工id更新员工信息
     * @param staff
     * @return
     */
    public int updateByStaffId(Staff staff){
        return staffMapper.updateByStaffId(staff);
    }
}
