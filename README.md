# android-mvp-architecture
<b>FunWithFlags</b>

Fun With Flags. Learn how flags from different country look like and some basic information about every country.

Project is developed using MVP pattern. 

Webcalls are done using Retrofit + RxJava

Handeled orientation change - Same web request will continue. This is done by caching Observers in LruCache and cleanning the cache once data is saved to local repository.

API:
https://restcountries.eu/ , an open rest api to fetch the country information and for the flags  http://www.geonames.org/flags/

Some test cases in Unit test and Instrumentation these are just basic one.




