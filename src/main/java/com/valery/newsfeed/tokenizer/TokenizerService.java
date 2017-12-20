package com.valery.newsfeed.tokenizer;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.entity.Feature;
import com.valery.newsfeed.entity.FeaturedMessage;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import com.valery.newsfeed.pubsub.NewFeatureEvent;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TokenizerService {

    private static Properties props = new Properties();

    static {
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    }

    private static StanfordCoreNLP nlp = new StanfordCoreNLP(props);

    public void process(String text) {
        EventBus eventBus = EventBusSingleton.getInstance();

        Annotation document = new Annotation(text);
        nlp.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        List<Feature> features = Collections.emptyList();

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                features = new ArrayList<>();
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (!"O".equals(ne)) {
                    features.add(new Feature(token.beginPosition(), token.endPosition(), token.get(CoreAnnotations.TextAnnotation.class), ne));
                }
            }
        }

        FeaturedMessage featuredMessage = new FeaturedMessage(text, features);
        NewFeatureEvent newFeatureEvent = new NewFeatureEvent(featuredMessage);
        eventBus.post(newFeatureEvent);
    }

}
