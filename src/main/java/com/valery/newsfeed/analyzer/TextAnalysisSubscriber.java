package com.valery.newsfeed.analyzer;

import com.google.common.eventbus.Subscribe;
import com.valery.newsfeed.pubsub.RawTextEvent;

public class TextAnalysisSubscriber {

    private TextAnalysisService tokenizer = new TextAnalysisService();

    @Subscribe
    public void handleNewMessageEvent(RawTextEvent event) {
        tokenizer.process(event.getText());
    }
}
