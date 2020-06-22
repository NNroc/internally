package com.cumt.internally.service;

import com.cumt.internally.mapper.PositionMapper;
import com.cumt.internally.model.PositionWeight;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NNroc
 * @date 2020/6/22 16:50
 */
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public PositionWeight selectByPosition(String position) {
        return positionMapper.selectByPosition(position);
    }

    public List<PositionWeight> select() {
        return positionMapper.select();
    }

    public int update(String position, double weight) {
        return positionMapper.update(position, weight);
    }
}
