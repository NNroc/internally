package com.cumt.internally.service;

import com.cumt.internally.mapper.RiskLevelMapper;
import com.cumt.internally.model.RiskLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NNroc
 * @date 2020/5/30 11:35
 */
@Service
public class RiskLevelService {
    @Autowired
    RiskLevelMapper riskLevelMapper;

    public RiskLevel select() {
        return riskLevelMapper.select();
    }

    public int updateGrade(RiskLevel riskLevel) {
        return riskLevelMapper.updateGrade(riskLevel);
    }
}
