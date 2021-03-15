package com.example.assign5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //updates, as I have no code here yet
    //- I set up a twitter developer account to access the API
    //- And found what request I need using Postman to test
    //- text will be easy, but I don't think I'll be able to do pictures easily
    //API link will be something like
    //https://api.twitter.com/2/users/:id/tweets?max_results=100&tweet.fields=attachments,created_at,text,entities
    //but I also need to send my token
    //and some of the tweet.fields might change



}