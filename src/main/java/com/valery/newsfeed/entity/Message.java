package com.valery.newsfeed.entity;

import java.util.List;

public class Message {

    private List<Word> words;

    public Message(List<Word> features) {
        this.words = features;
    }

    public List<Word> getWords() {
        return words;
    }

}
