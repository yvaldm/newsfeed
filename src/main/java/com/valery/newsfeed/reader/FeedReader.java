package com.valery.newsfeed.reader;

public class FeedReader {

    public void read() {

        Thread facebook = new Thread(new FacebookStreamReader());
        facebook.start();

        Thread twitter = new Thread(new TwitterStreamReader());
        twitter.start();

    }
}
