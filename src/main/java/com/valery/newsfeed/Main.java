package com.valery.newsfeed;

import com.valery.newsfeed.reader.TwitterStreamReader;

public class Main {

    public static void main(String[] args) {
        TwitterStreamReader str = new TwitterStreamReader();
        str.streamFeed();
    }

}
