package com.valery.newsfeed.producer;

import com.valery.newsfeed.entity.Feature;
import com.valery.newsfeed.entity.Message;

import java.util.List;

public class TextEnrichmentService {

    public String process(Message featuredMessage) {
        StringBuilder resultBuilder = new StringBuilder();

        List<Feature> features = featuredMessage.getFeatures();

        for (Feature f : features) {

            if ("TWITTER".equals(f.getEntity())) {
                String twitterAccount = f.getValue().substring(1);
                resultBuilder.append(String.format("@<a href=\"http://twitter.com/%s\">%s</a>", twitterAccount, twitterAccount));
            } else if ("LINK".equals(f.getEntity())) {
                resultBuilder.append(String.format("<a href=\"%s\">%s</a>", f.getValue(), f.getValue()));
            } else if (f.getEntity() != null) {
                resultBuilder.append(String.format("<strong>%s</strong>", f.getValue()));
            } else {
                resultBuilder.append(f.getValue());
            }

            resultBuilder.append(" ");
        }

        String result = resultBuilder.toString();
        System.out.println(">>>" + result);
        return result;
    }
}
