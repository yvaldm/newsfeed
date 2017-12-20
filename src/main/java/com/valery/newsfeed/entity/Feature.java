package com.valery.newsfeed.entity;

public class Feature {
    private int startPos;
    private int endPos;
    private String value;
    private String entity;

    public Feature(int startPos, int endPos, String value, String entity) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.value = value;
        this.entity = entity;
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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "positions " + startPos +
                " through " + endPos +
                " -> " + value + " " + entity;
    }
}
