package com.example.hinal.cryptotrackerapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, ArrayList<AllCardClass> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, AllCardClass product) {
        ArrayList<AllCardClass> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<AllCardClass>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, AllCardClass product) {
        ArrayList<AllCardClass> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<AllCardClass> getFavorites(Context context) {
        SharedPreferences settings;
        List<AllCardClass> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            AllCardClass[] favoriteItems = gson.fromJson(jsonFavorites,
                    AllCardClass[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<AllCardClass>(favorites);
        } else
            return null;

        return (ArrayList<AllCardClass>) favorites;
    }
}
