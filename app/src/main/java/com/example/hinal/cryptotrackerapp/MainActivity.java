package com.example.hinal.cryptotrackerapp;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    recyclerViewAdapter rv;
    all_fragment_class a;
    List<AllCardClass> my_data = new ArrayList<>();
    View view;
    private Toolbar toolbar;
    MaterialSearchView searchView;

    all_fragment_class all = new all_fragment_class();

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // search button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);


        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (MaterialSearchView) findViewById(R.id.searchView);
        searchView.setMenuItem(searchItem);
        searchView.setHint("Search");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CyptoTracker");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.main_ViewPager);

        Fragment_Pager_Adapter my_adatper = new Fragment_Pager_Adapter(getSupportFragmentManager());
        my_adatper.addFragment(new all_fragment_class(), "All Coins");
        my_adatper.addFragment(new fev_fragment_class(), "Favorites");

        viewPager.setAdapter(my_adatper);
        tabLayout.setupWithViewPager(viewPager);


    }
}


