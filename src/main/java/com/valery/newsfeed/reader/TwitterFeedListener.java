package com.valery.newsfeed.reader;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import com.valery.newsfeed.pubsub.NewMessageEvent;
import twitter4j.Status;
import twitter4j.StatusAdapter;

public class TwitterFeedListener extends StatusAdapter {

    public void onStatus(Status status) {

        EventBus eventBus = EventBusSingleton.getInstance();

        String lang = status.getLang();
        if ("en".equals(lang)) {
            eventBus.post(new NewMessageEvent(status.getText()));
        }
    }

}
