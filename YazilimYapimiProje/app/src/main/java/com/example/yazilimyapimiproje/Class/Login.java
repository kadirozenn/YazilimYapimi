package com.example.yazilimyapimiproje.Class;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Activity.ActivityLogin;
import com.example.yazilimyapimiproje.Activity.ActivityMain;
import com.example.yazilimyapimiproje.Activity.ActivityRegister;
import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;

public class Login {

    static Context mContext;


    public static void Register (String namesurname, String username, String password, final Context context){
        final String[] b = new String[1];
        Alagan.Instance.dbString.put("command","createUser").put("username",username).put("namesurname",namesurname)
                .put("password",password).read(new AlaganStringDatabase.AlaganListener() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("-1")){
                    Toast.makeText(context,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, ActivityMain.class));
                }else {
                    Toast.makeText(context,"Kayıt Başarılı değil",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static void LoginControl(String usurname, String password, final Context context){
        final String[] b = new String[1];
        Alagan.Instance.dbString.put("command","userLogin").put("data",usurname).put("password",password).read(new AlaganStringDatabase.AlaganListener() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("-1")){
                    String id=String.valueOf(response);
                    SharedSet(id,context);
                    context.startActivity(new Intent(context, ActivityMain.class));
                }


            }
        });


    }

    public static void SharedSet(String value,Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Kullanici_Keyi", value);
        editor.commit();

    }
    public static String SharedGet(Context context){
        String value;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        value = preferences.getString("Kullanici_Keyi", "N/A");

        return value;

    }
    public static void Remove(Context context){

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("Kullanici_Keyi");
                editor.commit();

    }


}
