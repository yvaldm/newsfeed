package com.valery.newsfeed.pubsub;

import com.valery.newsfeed.entity.FeaturedMessage;

public class NewFeatureEvent {

    private FeaturedMessage feature;

    public NewFeatureEvent(FeaturedMessage feature) {
        this.feature = feature;
    }

    public FeaturedMessage getFeature() {
        return feature;
    }
}
