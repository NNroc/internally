package com.cumt.internally.service;

import com.cumt.internally.mapper.RiskMarkMapper;
import com.cumt.internally.model.RiskMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class RiskMarkService {
    @Autowired
    RiskMarkMapper riskMarkMapper;

    /**
     * 员工评分
     *
     * @param riskMark
     * @return
     */
    public int insert(RiskMark riskMark) {
        return riskMarkMapper.insert(riskMark);
    }

    public List<RiskMark> selectByStaffId(String staffId) {
        return riskMarkMapper.selectByStaffId(staffId);
    }

    public List<RiskMark> selectByStaffName(String staffName) {
        return riskMarkMapper.selectByStaffName(staffName);
    }

    public List<RiskMark> selectByRiskControlId(Integer riskControlId) {
        return riskMarkMapper.selectByRiskControlId(riskControlId);
    }

    public List<RiskMark> selectByStaffPosition(String staffPosition) {
        return riskMarkMapper.selectByStaffPosition(staffPosition);
    }

    /**
     * 按两个id为联合主键查找
     *
     * @param staffId
     * @param controlId
     * @return
     */
    public RiskMark selectByStaffIdAndRiskControlId(String staffId, Integer controlId) {
        return riskMarkMapper.selectByStaffIdAndRiskControlId(staffId, controlId);
    }

    /**
     * 查看全部情况
     *
     * @return
     */
    public List<RiskMark> selectAll() {
        return riskMarkMapper.selectAll();
    }

    /**
     * 清除记录
     */
    public void clear() {
        riskMarkMapper.clear();
    }
}
