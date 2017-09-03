package com.example.micah.hiAI.gifRetrieval;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Micah on 03/09/2017.
 */

public class GifJsonParser {

    /**
     * turns the specified jsonGifString to a JSONObject.
     * It then extracts the gif url from it and returns it.
     * If we failed to get the url the method returns null.
     *
     * @param jsonGifString - a string representation of a json object
     * @return                that contains the gif url
     */
    public String extractGifUrlFrom(String jsonGifString){

        String gifUrl = null;

        try {

             JSONObject jsonGifResponse = new JSONObject(jsonGifString);

             gifUrl = ((JSONObject) jsonGifResponse.get("data")).getString("image_url");

        } catch (JSONException e) {

            e.printStackTrace();
        }

        return gifUrl;
    }
}
