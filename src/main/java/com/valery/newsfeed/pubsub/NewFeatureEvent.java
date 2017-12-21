package com.valery.newsfeed.pubsub;

import com.valery.newsfeed.entity.Message;

public class NewFeatureEvent {

    private Message feature;

    public NewFeatureEvent(Message feature) {
        this.feature = feature;
    }

    public Message getFeature() {
        return feature;
    }
}
