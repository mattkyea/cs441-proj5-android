# cs441-proj5-android

## current plan
Recently, a cryptocurrency called Dogecoin has been rising in popularity, in part due to tweets by Elon Musk (https://www.cnbc.com/2021/02/08/tweets-from-elon-musk-and-celebrities-send-dogecoin-to-a-record-high.html).
I'm going to make an app that:
-pulls all of Musk's tweets from the last 24 hours
-lists the change in value of Dogecoin following the tweet
-gives the user a notification everytime Musk tweets 

## APIs I'm going to try
#### https://developer.twitter.com/en/docs/twitter-api
-Twitter API

#### https://developer.twitter.com/en/docs/twitter-api/enterprise/account-activity-api/overview
-Specific Twitter API functions I'll probably need

#### https://www.coingecko.com/en/api
-Crypto API
---https://api.coingecko.com/api/v3/coins/dogecoin/history?date=15-03-2021
---this request gets price of Doge at a certain date
---or maybe https://api.coingecko.com/api/v3/coins/dogecoin/market_chart/range?vs_currency=usd&from=1392577232&to=1422577232


## sources
#### https://github.com/public-apis/public-apis#cryptocurrency
Where I found the APIs