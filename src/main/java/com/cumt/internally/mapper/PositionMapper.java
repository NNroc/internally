package com.cumt.internally.mapper;

import com.cumt.internally.model.PositionWeight;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author NNroc
 * @date 2020/6/22 16:50
 */
public interface PositionMapper {
    @Select({"select * from position_weight where position = #{position,jdbcType=VARCHAR}"})
    PositionWeight selectByPosition(String position);

    @Select({"select * from position_weight"})
    List<PositionWeight> select();

    @Update({
            "update position_weight",
            "set weight = #{weight,jdbcType=DOUBLE}",
            "where position = #{position,jdbcType=VARCHAR}"
    })
    int update(String position,double weight);
}
