# cs441-proj5-android

## current plan
Recently, a cryptocurrency called Dogecoin has been rising in popularity, in part due to tweets by Elon Musk (https://www.cnbc.com/2021/02/08/tweets-from-elon-musk-and-celebrities-send-dogecoin-to-a-record-high.html).

I'm going to make an app that:
###### pulls all of Musk's tweets from the last 24 hours
###### lists the change in value of Dogecoin following the tweet
###### gives the user a notification everytime Musk tweets 

### to do
##### fix buggy api calls
##### make sure threads are ok
##### UI (font, size, etc)
##### check data (nums don't seem right)
##### try to get images/attachments


## APIs I used

#### https://developer.twitter.com/en/docs/twitter-api
-Twitter API

#### http://twitter4j.org/en/index.html
-Java wrapper around the Twitter API I used. I'd rather deal with these rather than messy with REST and whatnot.
-I've done something similar in the past (Spotipy for Python Spotify wrapper) and had much better luck this way.

#### https://www.coingecko.com/en/api#explore-api
-Crypto API. I'll use to track prices at certain times.

#### https://github.com/Philipinho/CoinGecko-Java
-Another Java wrapper, this time around CoinGecko.

#### https://www.postman.com/
-Tool I used to test REST endpoints quickly in the browser.



## Additional Sources

#### https://github.com/public-apis/public-apis#cryptocurrency
Where I found the APIs

#### https://medium.com/@sunnat629/use-twitter-rest-api-in-android-c8ba0dc08315
Article that encouraged me to use a wrapper instead of Twitter API directly.

#### https://stackoverflow.com/questions/28309053/twitter4j-integration-through-android-studio
How to add Twitter4J to Android Studio via dependencies rather than JAR file.

#### https://stackoverflow.com/questions/20638190/twitter4j-authentication-credentials-are-missing
Helped with auth bug I had (I moved auth to MainActivity rather than external file).

#### https://stackoverflow.com/questions/17360924/securityexception-permission-denied-missing-internet-permission
How to allow my app to access the Internet.

#### https://stackoverflow.com/questions/6343166/how-to-fix-android-os-networkonmainthreadexception
How to access the Internet correctly on Android (on another thread).

#### https://stackoverflow.com/questions/18748436/is-it-possible-to-declare-git-repository-as-dependency-in-android-gradle
How to add git repos as gradle dependencies. I actually used this a few projects ago without realizing it, and it was super handy here with the crypto wrapper.

#### https://stackoverflow.com/questions/7784421/getting-unix-timestamp-from-date
How to get UNIX Timestamp from Date object.