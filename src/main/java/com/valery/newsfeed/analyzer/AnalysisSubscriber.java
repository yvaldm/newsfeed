package com.valery.newsfeed.analyzer;

import com.google.common.eventbus.Subscribe;
import com.valery.newsfeed.pubsub.RawTextEvent;

public class AnalysisSubscriber {

    private AnalysisService tokenizer = new AnalysisService();

    @Subscribe
    public void handleNewMessageEvent(RawTextEvent event) {
        tokenizer.process(event.getText());
    }
}
