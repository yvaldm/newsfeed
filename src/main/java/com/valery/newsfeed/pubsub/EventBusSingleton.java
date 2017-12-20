package com.valery.newsfeed.pubsub;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.producer.TextEnrichmentSubscriber;
import com.valery.newsfeed.tokenizer.TokenizerSubscriber;

public class EventBusSingleton {

    private static EventBus INSTANCE = null;

    private EventBusSingleton() {
    }

    public static synchronized EventBus getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventBus();
            INSTANCE.register(new TokenizerSubscriber());
            INSTANCE.register(new TextEnrichmentSubscriber());
        }

        return INSTANCE;
    }
}
