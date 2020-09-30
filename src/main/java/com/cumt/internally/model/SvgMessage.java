package com.cumt.internally.model;

import java.util.Map;

/**
 * @author NNroc
 * @date 2020/6/20 12:19
 */
public class SvgMessage implements Common, Comparable<SvgMessage> {
    private String title;
    private String SVGSrc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSVGSrc() {
        return SVGSrc;
    }

    public void setSVGSrc(String SVGSrc) {
        this.SVGSrc = SVGSrc;
    }

    /**
     * 将字符串转化为自定义的离散值，以便排序
     *
     * @param str 传入串
     * @return
     */
    public double deal(String str) {
        String use = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.' || (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                use += str.charAt(i);
            } else {
                break;
            }
        }
        double val = 0;
        String use2[] = use.split("\\.");
        for (int i = 0; i < use2.length; i++) {
            val = val + Double.parseDouble(use2[i]) * Math.pow(101, 20 - i);
        }
        return val;
    }

    @Override
    public int compareTo(SvgMessage svgMessage) {
        String str1 = this.title;
        String str2 = svgMessage.title;
        double num1 = deal(str1);
        double num2 = deal(str2);
        if (num1 > num2) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Map toDict() {
        return null;
    }
}
