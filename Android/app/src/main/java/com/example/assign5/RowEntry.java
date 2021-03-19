package com.example.assign5;

import java.math.BigDecimal;

public class RowEntry {

    private String tweetContent;
    private BigDecimal netGainLoss;
    private boolean gain;

    //simple class that stores all data for a row in the recycler
    RowEntry(String text, BigDecimal netdiff){
        tweetContent = text;
        netGainLoss = netdiff;
        if(netdiff.compareTo(BigDecimal.ZERO) > 0){
            gain = true;
        }else {
            gain = false;
        }
    }

    public BigDecimal getNetGainLoss() {
        return netGainLoss;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public boolean isGain() {
        return gain;
    }
}
