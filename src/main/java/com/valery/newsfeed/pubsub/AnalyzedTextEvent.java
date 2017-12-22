package com.valery.newsfeed.pubsub;

import com.valery.newsfeed.entity.Message;

public class AnalyzedTextEvent {

    private Message message;

    public AnalyzedTextEvent(Message feature) {
        this.message = feature;
    }

    public Message getMessage() {
        return message;
    }
}
