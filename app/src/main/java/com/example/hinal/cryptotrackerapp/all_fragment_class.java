
package com.example.hinal.cryptotrackerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class all_fragment_class extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public List<AllCardClass> my_list = new ArrayList<>();
    View view;
    public static final String ARG_ITEM_ID = "product_list";
    SharedPreference sharedPreference;
    String[] obj_list = new String[10];

    String JsonURL = "https://api.coinmarketcap.com/v1/ticker/";
    String name;
    String imageURL;
    String symbol;
    String price;
    String price_1h;
    String price_24h;
    String price_7d;
    String Rank;
    String id;
    String avl_Supply;
    String max_Supply;
    String mkt_Cap;
    SwipeRefreshLayout swipeLayout;

    RequestQueue requestQueue;

    public all_fragment_class()
    {
        // write here.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        sharedPreference = new SharedPreference();
        view = inflater.inflate(R.layout.all_fragment,container,false);
        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recycler_view_id);
        final recyclerViewAdapter myAdapter = new recyclerViewAdapter(this.getContext(),my_list);
        myrv.setLayoutManager(new GridLayoutManager(this.getContext(),1));
        myrv.setAdapter(myAdapter);
        requestQueue = Volley.newRequestQueue(this.getContext());
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),getResources().getColor(android.R.color.holo_green_light),getResources().getColor(android.R.color.holo_orange_light),getResources().getColor(android.R.color.holo_red_light),getResources().getColor(android.R.color.holo_purple));

        JsonArrayRequest arrq = new JsonArrayRequest(JsonURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("resp", "response taken." + response);
                    int i;
                    for(i=0;i<99;i++)
                    {
                        JSONObject my_obj = response.getJSONObject(i);
                        name = my_obj.getString("name");
                        symbol = my_obj.getString("symbol");
                        price = my_obj.getString("price_usd");
                        id =my_obj.getString("id");
                        price_1h = my_obj.getString("percent_change_1h");
                        price_24h = my_obj.getString("percent_change_24h");
                        price_7d = my_obj.getString("percent_change_7d");
                        Rank = my_obj.getString("rank");
                        imageURL = "https://res.cloudinary.com/dxi90ksom/image/upload/"+symbol.toUpperCase()+".png";
                        avl_Supply =my_obj.getString("available_supply");
                        mkt_Cap =my_obj.getString("market_cap_usd");
                        max_Supply =my_obj.getString("max_supply");

                        Log.d("URL", imageURL);
                        Log.d("resp","it is parsed.");
                        my_list.add(new AllCardClass(name,symbol,price,price_1h,price_24h,price_7d,Rank,imageURL,avl_Supply,max_Supply,mkt_Cap));
                        myAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","error in parsing.");
                    }
                });

        requestQueue.add(arrq);


        return view;

    }
    public List<AllCardClass>getList()
    {
        return my_list;
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        },5000);
    }
    }

