package com.example.yazilimyapimiproje.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganJsonDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.Class.Login;
import com.example.yazilimyapimiproje.R;

import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Alagan(getApplication());

        String kontrol=Login.SharedGet(getApplicationContext());

        if(!kontrol.equals("N/A")){
            startActivity(new Intent(getApplicationContext(),ActivityMain.class));
        }
        init();

    }

    private void init() {
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rellay1.setVisibility(View.VISIBLE);
                rellay2.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable, 1500);

    }





    public void ActivityLoginButton(View view) {
        EditText edtUsername=findViewById(R.id.Activity_Login_Username);
        EditText edtPassword=findViewById(R.id.Activity_Login_Password);
        switch (view.getId()){
            case R.id.ActivityLoginButtonLogin:

                Login.LoginControl(edtUsername.getText().toString().trim(),edtPassword.getText().toString().trim(),getApplicationContext());
                break;
            case R.id.ActivityLoginButtonRegister :

                startActivity(new Intent(getApplicationContext(),ActivityRegister.class));
                break;
            default:

        }

    }
}
