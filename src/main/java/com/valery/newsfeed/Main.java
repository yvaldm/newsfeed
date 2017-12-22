package com.valery.newsfeed;

import com.valery.newsfeed.reader.FacebookStreamReader;
import facebook4j.FacebookException;

public class Main {

    public static void main(String[] args) throws FacebookException {

//        TwitterStreamReader str = new TwitterStreamReader();
//        str.streamFeed();

        FacebookStreamReader facebookStreamReader = new FacebookStreamReader();


        facebookStreamReader.streamFeed();


    }

}
