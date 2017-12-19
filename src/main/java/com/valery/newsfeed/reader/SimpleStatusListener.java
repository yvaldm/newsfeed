package com.valery.newsfeed.reader;

import com.valery.newsfeed.tokenizer.Tokenizer;
import twitter4j.Status;
import twitter4j.StatusAdapter;

public class SimpleStatusListener extends StatusAdapter {

    private Tokenizer tokenizer = new Tokenizer();

    public void onStatus(Status status) {

        String lang = status.getLang();
        if ("en".equals(lang)) {
            String text = status.getText();
            System.out.println(text);
            tokenizer.process(text);
        }
    }

}
