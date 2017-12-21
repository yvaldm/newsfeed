package com.valery.newsfeed.entity;

import java.util.List;

public class Message {

    private List<Feature> features;

    public Message(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

}
