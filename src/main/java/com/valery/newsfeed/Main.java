package com.valery.newsfeed;

import com.valery.newsfeed.reader.FeedReader;

public class Main {

    public static void main(String[] args) {
        FeedReader feedReader = new FeedReader();
        feedReader.read();
    }
}
