package com.example.yazilimyapimiproje.Activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Class.Login;
import com.example.yazilimyapimiproje.Fragment.FragmentKelimeEkle;
import com.example.yazilimyapimiproje.Fragment.FragmentWords;
import com.example.yazilimyapimiproje.Fragment.FragmentProfil;
import com.example.yazilimyapimiproje.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ActivityMain extends AppCompatActivity {

    BottomNavigationViewEx bottomNavigationView;
    FrameLayout content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();
    }

    private void init() {
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        content=findViewById(R.id.Activity_Main_Content);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content=findViewById(R.id.Activity_Main_Content);
        loadFragment(new FragmentWords());
        //BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);


    }
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_Kelimelerim:
                    loadFragment(new FragmentWords());
                    return true;

                case R.id.navigation_Kelime_Ekle:
                    loadFragment(new FragmentKelimeEkle());
                    return true;

                case R.id.navigation_profil:
                    loadFragment(new FragmentProfil());
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Activity_Main_Content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
