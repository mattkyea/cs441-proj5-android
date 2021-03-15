package com.example.assign5;

import androidx.appcompat.app.AppCompatActivity;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // The factory instance is re-useable and thread safe.

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    //Twitter twitter = TwitterFactory.getSingleton();
                    List<Status> statuses = null;
                    try {
                        Paging paging = new Paging(1, 100);
                        statuses = twitter.getUserTimeline("elonmusk", paging);
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Showing elon's timeline.");
                    for (Status status : statuses) {
                        System.out.println(status.getUser().getName() + ":" + status.getText() + " at " + status.getCreatedAt());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();



    }

    //updates, as I have no code here yet
    //- I set up a twitter developer account to access the API
    //- And found what request I need using Postman to test
    //- text will be easy, but I don't think I'll be able to do pictures easily
    //API link will be something like
    //https://api.twitter.com/2/users/:id/tweets?max_results=100&tweet.fields=attachments,created_at,text,entities
    //but I also need to send my token
    //and some of the tweet.fields might change

    //update 2
    //decided that using Twitter4J would be easier
    //its basically a Java wrapper around Twitter's API
    //i've done the same thing in the past (Spotipy for Python around Spotify's API) and liked it much more



}