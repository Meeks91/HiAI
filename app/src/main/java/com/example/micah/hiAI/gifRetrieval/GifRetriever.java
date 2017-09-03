package com.example.micah.hiAI.gifRetrieval;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Micah on 18/06/2017.
 */

public class GifRetriever {

    private final GifJsonParser gifJsonParser;
    private OkHttpClient okHttpClient;

    public GifRetriever(OkHttpClient okHttpClient, GifJsonParser gifJsonParser){

        this.okHttpClient = okHttpClient;

        this.gifJsonParser = gifJsonParser;
    }

    /**
     * retrieves a random gif from the api.
     * It provides the result in the specified GifRetrievalCallback
     *
     * @param gifRetrievalCallback - a callback which is passed the gif if one is retrieved
     *                              of else it's passed null if no gif was retrieved
     */
    public void getRandomGif(final GifRetrievalCallback gifRetrievalCallback){


        //make the reqest to get the gif
        final Request getGifRequest = new Request.Builder().url("http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC").build();

        //execute request to get the gif
        okHttpClient.newCall(getGifRequest).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

                 e.printStackTrace();

                 //pass null as we failed to get the gif
                 gifRetrievalCallback.onGifRetrieved(null);
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {

                 //get the url of the gif from the respose. This will be assigned
                 //null if we fail to get the gif url
                 String gifUrl = gifJsonParser.extractGifUrlFrom(response.body().string());

                 gifRetrievalCallback.onGifRetrieved(gifUrl);
             }
         });
    }
}

