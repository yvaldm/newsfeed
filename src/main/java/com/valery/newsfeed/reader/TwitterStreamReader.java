package com.valery.newsfeed.reader;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamReader {

    public static void streamFeed() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("wpZcmXP10hlRIVSEisbfG24fh")
                .setOAuthConsumerSecret("AqJ55NxaffxbRWKquzYCBLdfMg633ZkWWUPiqS5oOojTCNoOEW")
                .setOAuthAccessToken("280714044-03xDGvUhnfeSYhgfFC3cXiOYs21OnPOnjtwmKu2s")
                .setOAuthAccessTokenSecret("IVdOaaN85PrlXnfIS2qUVYaijJETyzwPYde4b3hbXmO9O");

        Configuration build = cb.build();

        TwitterStream twitterStream = new TwitterStreamFactory(build).getInstance();
        twitterStream.addListener(new SimpleStatusListener());

        twitterStream.filter("java");
    }

}
