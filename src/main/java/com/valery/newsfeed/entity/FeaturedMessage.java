package com.valery.newsfeed.entity;

import java.util.List;

public class FeaturedMessage {

    private String text;
    private List<Feature> features;

    public FeaturedMessage(String text, List<Feature> features) {
        this.text = text;
        this.features = features;
    }

    public String getText() {
        return text;
    }

    public List<Feature> getFeatures() {
        return features;
    }

}
