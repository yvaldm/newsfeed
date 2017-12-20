package com.valery.newsfeed.producer;

import com.google.common.eventbus.Subscribe;
import com.valery.newsfeed.pubsub.NewFeatureEvent;

public class TextEnrichmentSubscriber {

    private TextEnrichmentService textEnrichmentService = new TextEnrichmentService();

    @Subscribe
    public void handleFeature(NewFeatureEvent event) {
        textEnrichmentService.process(event.getFeature());
    }

}
