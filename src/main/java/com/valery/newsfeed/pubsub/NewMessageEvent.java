package com.valery.newsfeed.pubsub;

public class NewMessageEvent {

    private String text;

    public NewMessageEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
