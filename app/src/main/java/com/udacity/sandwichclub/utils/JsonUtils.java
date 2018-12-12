package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN = "alsoKnownAs";
    private static final String PLACE_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {

            JSONObject sandwichJson = new JSONObject(json);

            JSONObject sandwich = sandwichJson.getJSONObject(NAME);
            String mainName = sandwich.getString(MAIN_NAME);

            JSONArray alsoKnownArray = sandwich.getJSONArray(ALSO_KNOWN);
            List<String> alsoKnownAs = convertFromJsonArray(alsoKnownArray);

            String placeOfOrigin = sandwichJson.getString(PLACE_ORIGIN);

            String description = sandwichJson.getString(DESCRIPTION);

            String image = sandwichJson.getString(IMAGE);

            JSONArray ingredientsArray = sandwichJson.getJSONArray(INGREDIENTS);
            List<String> ingredients = convertFromJsonArray(ingredientsArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> convertFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}