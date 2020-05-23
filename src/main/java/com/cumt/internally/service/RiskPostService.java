package com.cumt.internally.service;

import com.cumt.internally.mapper.RiskPostMapper;
import com.cumt.internally.model.RiskMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class RiskPostService {
    @Autowired
    RiskPostMapper riskPostMapper;

    /**
     * 员工评分
     *
     * @param riskMark
     * @return
     */
    public int insert(RiskMark riskMark) {
        return riskPostMapper.insert(riskMark);
    }


    /**
     * 根据查看计算评分
     *
     * @param riskControlId
     * @return
     */
    RiskMark selectByRiskControlId(Integer riskControlId) {
        return null;
    }


    /**
     * 查看全部情况
     *
     * @return
     */
    List<RiskMark> selectAll() {
        return null;
    }
}
