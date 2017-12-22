package com.valery.newsfeed.decorator;

import com.google.common.eventbus.Subscribe;
import com.valery.newsfeed.pubsub.AnalyzedTextEvent;

public class DecorationSubscriber {

    private DecorationService textEnrichmentService = new DecorationService();

    @Subscribe
    public void handleFeature(AnalyzedTextEvent event) {
        textEnrichmentService.process(event.getMessage());
    }

}
