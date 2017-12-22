package com.valery.newsfeed;

import com.valery.newsfeed.reader.FacebookStreamReader;
import com.valery.newsfeed.reader.TwitterStreamReader;

public class Main {

    public static void main(String[] args) {

        Thread facebook = new Thread(new FacebookStreamReader());
        facebook.start();

        Thread twitter = new Thread(new TwitterStreamReader());
        twitter.start();

    }
}
