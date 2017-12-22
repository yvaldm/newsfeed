package com.valery.newsfeed.pubsub;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.analyzer.AnalysisSubscriber;
import com.valery.newsfeed.decorator.DecorationSubscriber;

public class EventBusSingleton {

    private static EventBus INSTANCE = null;

    private EventBusSingleton() {
    }

    public static synchronized EventBus getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new EventBus();
            INSTANCE.register(new AnalysisSubscriber());
            INSTANCE.register(new DecorationSubscriber());
        }

        return INSTANCE;
    }
}
