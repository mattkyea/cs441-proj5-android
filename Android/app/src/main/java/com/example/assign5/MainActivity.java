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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up Twitter API wrapper
        //authorization and private keys
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);


        //API object I can interact with
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        //this is all we need for the crypto API wrapper
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        ArrayList<RowEntry> recyclerEntries = new ArrayList<>();

        //web requests need to be on a different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    ResponseList<Status> statuses = null;
                    try {//try to get last 100 statuses
                        Paging paging = new Paging(1, 50);
                        while (statuses == null || statuses.size() == 0|| statuses.size() == 1){//sometimes the call only returns 0 or 1 status. so, retry until I get what I want
                            //System.out.println("0 statuses");
                            statuses = twitter.getUserTimeline("elonmusk", paging);
                        }
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }

                    //System.out.println("Showing elon's timeline, " + statuses.size() + " statuses");

                    MarketChart mc;//stores crypto info
                    for (Status status : statuses) {
                        Date timeOfTweet = status.getCreatedAt();
                        long secondOfTweet = timeOfTweet.getTime()/1000; //convert time to UNIX
                        long twoHoursAfterTweet = secondOfTweet + 7200; //and look at price 2 hours after (7,200 seconds is 2 hours)
                        mc = client.getCoinMarketChartRangeById("dogecoin", "usd", Long.toString(secondOfTweet), Long.toString(twoHoursAfterTweet));//request to crypto API
                        //features of this API were lacking, and I could only get cost at a specific time with a range like this
                        if(mc.getPrices().size() >=2){//sometimes doesn't return results (we need at least 2 entries)

                            double before  = Double.parseDouble(mc.getPrices().get(0).get(1));//cost at time of tweet
                            double after  = Double.parseDouble(mc.getPrices().get(mc.getPrices().size()-1).get(1));//cost 2 hours after
                            //need to use BigDecimal, I had rounding errors without
                            BigDecimal b = BigDecimal.valueOf(before);
                            BigDecimal a = BigDecimal.valueOf(after);
                            recyclerEntries.add(new RowEntry(status.getText(), (a.subtract(b))));

                        }else {
                            recyclerEntries.add(new RowEntry(status.getText(), BigDecimal.ZERO));//no data
                        }
                    }

                    //can't update entries until we're done with web requests
                    //but can't add UI elements in the same thread
                    //so use this UI thread
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
                            final RecycleViewAdapter a = new RecycleViewAdapter(recyclerEntries, recyclerView);//call my adapter with the arraylist and view
                            //need list for elements, view for click handler
                            recyclerView.setAdapter(a);//set view and my adapter
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }




}