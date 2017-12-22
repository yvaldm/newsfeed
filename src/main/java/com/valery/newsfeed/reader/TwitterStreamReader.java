package com.valery.newsfeed.reader;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import com.valery.newsfeed.pubsub.NewMessageEvent;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterStreamReader
 * <p>
 * Consumes twitter feed
 */
public class TwitterStreamReader implements Runnable {

    @Override
    public void run() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("wpZcmXP10hlRIVSEisbfG24fh")
                .setOAuthConsumerSecret("AqJ55NxaffxbRWKquzYCBLdfMg633ZkWWUPiqS5oOojTCNoOEW")
                .setOAuthAccessToken("280714044-03xDGvUhnfeSYhgfFC3cXiOYs21OnPOnjtwmKu2s")
                .setOAuthAccessTokenSecret("IVdOaaN85PrlXnfIS2qUVYaijJETyzwPYde4b3hbXmO9O");

        Configuration configuration = cb.build();

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
        twitterStream.addListener(new TwitterFeedListener());
        twitterStream.filter("java");
    }

    class TwitterFeedListener extends StatusAdapter {

        public void onStatus(Status status) {

            EventBus eventBus = EventBusSingleton.getInstance();

            String lang = status.getLang();
            if ("en".equals(lang)) {
                eventBus.post(new NewMessageEvent(status.getText()));
            }
        }
    }
}
