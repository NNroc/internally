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

    @Override
    public int compareTo(SvgMessage svgMessage) {
        return this.title.compareTo(svgMessage.title);
    }

    @Override
    public Map toDict() {
        return null;
    }
}
