package com.cumt.internally.service;

import com.cumt.internally.mapper.RiskControlMapper;
import com.cumt.internally.model.RiskControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NNroc
 * @date 2020/5/13 17:59
 */
@Service
public class RiskControlService {
    @Autowired
    RiskControlMapper riskControlMapper;

    public void insert(RiskControl riskControl) {
        riskControlMapper.insert(riskControl);
    }

    public List<RiskControl> selectAll(){
        return riskControlMapper.selectAll();
    }
}
