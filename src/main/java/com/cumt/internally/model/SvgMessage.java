package com.cumt.internally.model;

/**
 * @author NNroc
 * @date 2020/6/20 12:19
 */
public class SvgMessage implements Comparable<SvgMessage> {
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
        return svgMessage.title.compareTo(this.title);
    }
}
