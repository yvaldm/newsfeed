package com.valery.newsfeed.pubsub;

import com.valery.newsfeed.entity.Message;

public class NewFeatureEvent {

    private Message message;

    public NewFeatureEvent(Message feature) {
        this.message = feature;
    }

    public Message getMessage() {
        return message;
    }
}
