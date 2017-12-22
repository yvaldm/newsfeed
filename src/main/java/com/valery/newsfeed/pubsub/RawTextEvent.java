package com.valery.newsfeed.pubsub;

public class RawTextEvent {

    private String text;

    public RawTextEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
