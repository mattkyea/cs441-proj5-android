package com.example.assign5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinData.DeveloperData;
import com.litesoftwares.coingecko.domain.Coins.CoinData.IcoData;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.domain.Coins.CoinTickerById;
import com.litesoftwares.coingecko.domain.Coins.MarketChart;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
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

        ArrayList<RowEntry> recyclerEntries = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    //Twitter twitter = TwitterFactory.getSingleton();
                    List<Status> statuses = null;

                    try {//try to get last 100 statuses
                        Paging paging = new Paging(1, 5);
                        statuses = twitter.getUserTimeline("elonmusk", paging);
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Showing elon's timeline, " + statuses.size() + " statuses");
                    CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
                    MarketChart mc;
                    for (Status status : statuses) {
                        //System.out.println(status.getUser().getName() + ":" + status.getText() + " at " + status.getCreatedAt());
                        //convert time to UNIX, and look at price 2 hours after
                        Date timeOfTweet = status.getCreatedAt();
                        long millisecondOfTweet = timeOfTweet.getTime()/1000;
                        long twoHoursAfterTweet = millisecondOfTweet + 7200000; //7,200,000 milliseconds in 2 hours
                        mc = client.getCoinMarketChartRangeById("dogecoin", "usd", Long.toString(millisecondOfTweet),Long.toString(twoHoursAfterTweet));
                        if(mc.getPrices().size() >=2){
                            float before  = Float.parseFloat(mc.getPrices().get(0).get(1));//at time of tweet
                            float after  = Float.parseFloat(mc.getPrices().get(mc.getPrices().size()-1).get(1));//2 hours after
                            System.out.println("added entry");
                            recyclerEntries.add(new RowEntry(status.getText(), after-before));
                        }else {
                            System.out.println("didn't add entry");
                            recyclerEntries.add(new RowEntry(status.getText(), 0));//no data, should change constructor
                        }
                    }

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            // Stuff that updates the UI
                            System.out.println("setting up recycler");

                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerList);

                            final RecycleViewAdapter a = new RecycleViewAdapter(recyclerEntries, recyclerView);//call my adapter with the arraylist and view
                            //need list for elements, view for click handler
                            recyclerView.setAdapter(a);//set view and my adapter
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        }
                    });


                    /*
                    for(List<String> s: mc.getPrices()){
                        System.out.println("at " +  s.get(0) + " doge was worth " + s.get(1));
                    }
                     */


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //thread2.start();

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

    //basic, but I'll leave front end for later
    //let's use crypto API next

    //crypto API a success thanks to wrapper and jitpack.io (able to pull entire git repo in as dependency, this is the future)
    //going to add recycler view stuff from last project then call it for today
    //still a lot to do after that though
    // -display as a "tweet" (i.e. profile pic, text with right font, pictures/attachments if I can)
    // -do the data crunching (turn time of each tweet into UNIX timestamp so I can track prices over time)



}