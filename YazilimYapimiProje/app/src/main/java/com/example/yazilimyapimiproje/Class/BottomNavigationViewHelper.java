package com.example.yazilimyapimiproje.Class;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.example.yazilimyapimiproje.Fragment.FragmentKelimeEkle;
import com.example.yazilimyapimiproje.Fragment.FragmentWords;
import com.example.yazilimyapimiproje.Fragment.FragmentProfil;
import com.example.yazilimyapimiproje.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class BottomNavigationViewHelper {
    private static Context mContext;

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewx){

        bottomNavigationViewx.enableAnimation(false);
        bottomNavigationViewx.enableShiftingMode(false);
        bottomNavigationViewx.enableItemShiftingMode(false);
        //bottomNavigationViewx.setTextVisibility(false);

    }

    public static void navigationSelected( final Context context ,BottomNavigationViewEx bottomNavigationViewEx){

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.navigation_Kelimelerim:
                        Log.d("dasdasadsdasd","1");
                       loadFragment(context,new FragmentWords());
                        return true;

                    case R.id.navigation_Kelime_Ekle:
                        Log.d("dasdasadsdasd","2");
                        loadFragment(context,new FragmentKelimeEkle());
                        return true;

                    case R.id.navigation_profil:
                        Log.d("dasdasadsdasd","3");
                        loadFragment(context,new FragmentProfil());
                        return true;

                }
                return false;
            }
        });

    }

    public static void loadFragment(Context context,Fragment fragment) {

        FragmentTransaction transaction =((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Activity_Main_Content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
