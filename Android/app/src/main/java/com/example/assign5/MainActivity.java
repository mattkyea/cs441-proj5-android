package com.example.assign5;

import androidx.appcompat.app.AppCompatActivity;

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


                    CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

                    MarketChart mc = client.getCoinMarketChartRangeById("dogecoin", "usd", "1392577232","1422577232");
                    System.out.println(mc.getPrices().get(0));
                    for(List<String> s: mc.getPrices()){
                        System.out.println("at " +  s.get(0) + " doge was worth " + s.get(1));
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

    //basic, but I'll leave front end for later
    //let's use crypto API next



}