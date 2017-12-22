package com.valery.newsfeed.producer;

import com.valery.newsfeed.entity.Message;
import com.valery.newsfeed.entity.Word;

import java.util.List;

public class TextEnrichmentService {

    /**
     * Enrich message words with HTML tags if they are natural language features, link or twitter acounts
     *
     * @param message input message
     * @return formatted string
     */
    public String process(Message message) {
        StringBuilder stringBuilder = new StringBuilder();

        List<Word> words = message.getWords();

        for (Word word : words) {

            if ("TWITTER".equals(word.getFeature())) {
                String twitterAccount = word.getValue().substring(1);
                stringBuilder.append(String.format("@<a href=\"http://twitter.com/%s\">%s</a>", twitterAccount, twitterAccount));
            } else if ("LINK".equals(word.getFeature())) {
                stringBuilder.append(String.format("<a href=\"%s\">%s</a>", word.getValue(), word.getValue()));
            } else if (word.getFeature() != null) {
                stringBuilder.append(String.format("<strong>%s</strong>", word.getValue()));
            } else {
                stringBuilder.append(word.getValue());
            }

            stringBuilder.append(" ");
        }

        String result = stringBuilder.toString();
        System.out.println(result);
        return result;
    }
}
