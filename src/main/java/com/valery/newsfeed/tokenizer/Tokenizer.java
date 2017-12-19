package com.valery.newsfeed.tokenizer;

import com.valery.newsfeed.entity.Token;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class Tokenizer {

    private static Properties props = new Properties();

    static {
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    }

    private static StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    public void process(String text) {

        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (!"O".equals(ne)) {
                    System.out.println(new Token(token.beginPosition(), token.endPosition(), token.get(CoreAnnotations.TextAnnotation.class), ne));
                }
            }
        }
    }

}
