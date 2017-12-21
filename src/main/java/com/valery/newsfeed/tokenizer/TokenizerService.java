package com.valery.newsfeed.tokenizer;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.entity.Feature;
import com.valery.newsfeed.entity.Message;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import com.valery.newsfeed.pubsub.NewFeatureEvent;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class TokenizerService {

    private static Properties props = new Properties();

    static {
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    }

    private static StanfordCoreNLP nlp = new StanfordCoreNLP(props);

    public void process(String text) {
        EventBus eventBus = EventBusSingleton.getInstance();

        List<Feature> words = split(text);
        detectAdditionalFeatures(words);

        Annotation document = new Annotation(text);
        nlp.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String namedEntity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (!"O".equals(namedEntity)) {
                    Optional<Feature> first = words.stream().filter(f -> f.getStartPos() == token.beginPosition()).findFirst();

                    if (first.isPresent()) {
                        Feature feature = first.get();
                        feature.setEntity(namedEntity);
                    }
                }
            }
        }

        Message featuredMessage = new Message(words);
        NewFeatureEvent newFeatureEvent = new NewFeatureEvent(featuredMessage);
        eventBus.post(newFeatureEvent);
    }

    private List<Feature> split(String text) {

        String[] words = text.split(" ");

        List<Feature> features = new ArrayList<>(words.length);

        for (int i = 0; i < words.length; i++) {

            int startPos = text.indexOf(words[i]);
            int endPos = startPos + words[i].length();

            features.add(new Feature(startPos, endPos, words[i]));
        }

        return features;
    }

    private void detectAdditionalFeatures(List<Feature> text) {
        for (Feature feature : text) {
            String value = feature.getValue();
            if (value.startsWith("@") && value.length() > 1) {
                feature.setEntity("TWITTER");
            } else if (value.startsWith("http")) {
                feature.setEntity("LINK");
            }
        }
    }

}
