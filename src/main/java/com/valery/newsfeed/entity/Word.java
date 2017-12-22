package com.valery.newsfeed.entity;

public class Word {

    private int startPos;
    private int endPos;
    private String value;
    private String feature;

    public Word(int startPos, int endPos, String value) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.value = value;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "positions " + startPos +
                " through " + endPos +
                " -> " + value + " " + feature;
    }
}
