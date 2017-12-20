package com.valery.newsfeed.producer;

import com.valery.newsfeed.entity.Feature;
import com.valery.newsfeed.entity.FeaturedMessage;

import java.util.List;
import java.util.Optional;

public class TextEnrichmentService {

    public String process(FeaturedMessage featuredMessage) {
        StringBuilder resultBuilder = new StringBuilder();

        String[] split = featuredMessage.getText().split(" ");

        List<Feature> features = featuredMessage.getFeatures();

        for (int i = 0; i < split.length; i++) {
            String value = split[i];
            Optional<Feature> first = features.stream().filter(f -> value.equals(f.getValue())).findFirst();

            if (first.isPresent()) {
                Feature feature = first.get();
                resultBuilder.append("<strong>" + feature.getValue() + "</strong>");
            } else {
                resultBuilder.append(value);
            }
            resultBuilder.append(" ");
        }

        String result = resultBuilder.toString();
        System.out.println(result);
        return result;
    }
}
