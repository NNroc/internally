package com.cumt.internally.model;

import java.util.Map;

/**
 * @author NNroc
 * @date 2020/6/22 16:52
 */
public class PositionWeight implements Common {
    private String position;
    private double weight;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Map toDict() {
        return null;
    }
}
