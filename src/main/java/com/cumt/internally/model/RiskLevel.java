package com.cumt.internally.model;

import java.util.Map;

/**
 * @author NNroc
 * @date 2020/5/30 11:05
 */
public class RiskLevel implements Common {
    private double high;
    private double medium;
    private double low;

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    @Override
    public Map toDict() {
        return null;
    }
}
