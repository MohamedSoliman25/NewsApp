package com.example.newsapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.adapter.PagerAdapter;
import com.example.newsapp.constants.Constants;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout tabLayout;
    TabItem mHome,mSports,mScience,mEntertainment,mTechnology,mHealth;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TextView tvToolbarTitle;


    //old api key
    String api = "4ec78ba89d3c4fdab2a6d6a5abfafa81";
    //new api key
    String newapi = "c644e443863a4b748bdec3baa0b730bf";

    SharedPreferences sp ;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        tvToolbarTitle = findViewById(R.id.main_activity_tv_toolbar_title);
        setSupportActionBar(mToolbar);

        mHome = findViewById(R.id.home);
        mSports =findViewById(R.id.sports);
        mScience  = findViewById(R.id.science);
        mEntertainment = findViewById(R.id.entertainment);
        mTechnology = findViewById(R.id.technology);
        mHealth = findViewById(R.id.health);

        viewPager =  findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);


       sp = getSharedPreferences("country",MODE_PRIVATE);
        edit = sp.edit();

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        Constants.country = sp.getString("switch_country","eg");
        tvToolbarTitle.setText(sp.getString("toolbar_name","News Feed"));

        //   Toast.makeText(this, Constants.country, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, toolbarName, Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String switchCountry = null;
        String toolbarName = null;
        switch (item.getItemId()){
//            case R.id.egypt:
//                switchCountry = "eg";
//                edit.putString("switch_country",switchCountry);
//                edit.commit();
//                recreate();
//                return true;

            case R.id.egypt:
                switchCountry = "eg";
                toolbarName = "Egypt News";
                break;

            case R.id.united_states:
                switchCountry = "us";
                toolbarName = "United States News";

                break;
            case R.id.france:
                switchCountry = "fr";
                toolbarName = "France News";
                break;

            case R.id.england:
                switchCountry = "gb";
                toolbarName = "England News";
                break;
        }
        edit.putString("switch_country",switchCountry);
        edit.putString("toolbar_name",toolbarName);
        edit.commit();
        recreate();
        return true;

    }
}