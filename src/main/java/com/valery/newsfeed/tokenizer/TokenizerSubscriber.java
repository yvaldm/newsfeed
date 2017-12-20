package com.valery.newsfeed.tokenizer;

import com.google.common.eventbus.Subscribe;
import com.valery.newsfeed.pubsub.NewMessageEvent;

public class TokenizerSubscriber {

    private TokenizerService tokenizer = new TokenizerService();

    @Subscribe
    public void handleNewMessageEvent(NewMessageEvent event) {
        tokenizer.process(event.getText());
    }
}
