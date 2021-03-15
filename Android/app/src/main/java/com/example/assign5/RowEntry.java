package com.example.assign5;

public class RowEntry {

    private String tweetContent;
    private float netGainLoss;
    private boolean gain;

    RowEntry(String text, float netdiff){
        tweetContent = text;
        netGainLoss = netdiff;
        gain = netdiff > 0 ? true : false;
    }

    public float getNetGainLoss() {
        return netGainLoss;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public boolean isGain() {
        return gain;
    }
}
