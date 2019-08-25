package com.example.hinal.cryptotrackerapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class fev_fragment_class extends Fragment {
    public static final String ARG_ITEM_ID = "favorite_list";
    View view;
    RecyclerView favoriteList;
    SharedPreference sharedPreference;
    List<AllCardClass> favorites;
    recyclerViewAdapter adapter;

    public fev_fragment_class() {
        // write here.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fev_fragment, container, false);
            return view;


        }

    }

