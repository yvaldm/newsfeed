package com.valery.newsfeed.reader;

import com.google.common.eventbus.EventBus;
import com.valery.newsfeed.pubsub.EventBusSingleton;
import com.valery.newsfeed.pubsub.NewMessageEvent;
import facebook4j.Event;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;

/**
 * FacebookStreamReader
 * <p>
 * Reads events feed from Facebook
 * <p>
 * https://developers.facebook.com/tools/explorer -- to get access token
 */
public class FacebookStreamReader implements Runnable {

    @Override
    public void run() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthAppId("1628819323844977")
                .setOAuthAppSecret("41321fddf81df0bdd069e20789dbf4d9")
                .setOAuthAccessToken("EAACEdEose0cBAEQAgORpyyEdBSJHZCgHY6TgycXkVuZBLeqsuIFNZCmPIZBi85cHoejIZAyFegyeOoOpzEx7T3ZAZAQir54JlL6M9ALmqfBFkWIl9JvhB3s0vXgCiU647dTjsDlxs2pOx860QZAKZAebjEyLLQbeFZC86FcJlMfb5xNdBDakMjubxKfHk4fCnlZA84ZD");

        FacebookFactory facebookFactory = new FacebookFactory(cb.build());
        Facebook facebook = facebookFactory.getInstance();

        ResponseList<Event> facebookEvents;

        try {

            while (true) {
                facebookEvents = facebook.searchEvents("Trump");
                EventBus eventBus = EventBusSingleton.getInstance();
                facebookEvents.stream().forEach(event -> eventBus.post(new NewMessageEvent(event.getName())));
                Thread.sleep(5000); // fetch new info every 5 seconds
            }

        } catch (FacebookException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
