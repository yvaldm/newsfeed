package com.valery.newsfeed.analyzer;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.entity.Message;
import com.valery.newsfeed.entity.Word;
import com.valery.newsfeed.pubsub.AnalyzedTextEvent;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class AnalysisService {

    private static Properties props = new Properties();

    static {
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    }

    private static StanfordCoreNLP nlp = new StanfordCoreNLP(props);

    public void process(String text) {
        EventBus eventBus = EventBusSingleton.getInstance();

        List<Word> words = split(text);
        detectAdditionalFeatures(words);

        Annotation document = new Annotation(text);
        nlp.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String namedEntity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (!"O".equals(namedEntity)) {
                    Optional<Word> word = words.stream().filter(f -> f.getStartPos() == token.beginPosition()).findFirst();

                    if (word.isPresent()) {
                        Word feature = word.get();
                        feature.setFeature(namedEntity);
                    }
                }
            }
        }

        eventBus.post(new AnalyzedTextEvent(new Message(words)));
    }

    private List<Word> split(String text) {

        String[] words = text.split(" ");
        List<Word> features = new ArrayList<>(words.length);

        for (int i = 0; i < words.length; i++) {
            int startPos = text.indexOf(words[i]);
            int endPos = startPos + words[i].length();

            features.add(new Word(startPos, endPos, words[i]));
        }

        return features;
    }

    private void detectAdditionalFeatures(List<Word> words) {

        for (Word feature : words) {
            String value = feature.getValue();
            if (value.startsWith("@") && value.length() > 1) {
                feature.setFeature("TWITTER");
            } else if (value.startsWith("http")) {
                feature.setFeature("LINK");
            }
        }
    }
}
