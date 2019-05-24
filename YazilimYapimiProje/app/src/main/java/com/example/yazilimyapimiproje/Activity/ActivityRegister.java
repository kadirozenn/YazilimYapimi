package com.example.yazilimyapimiproje.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Class.Login;
import com.example.yazilimyapimiproje.R;

public class ActivityRegister extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void ActivityRegisterButton(View view) {
        EditText edtNamesurname=findViewById(R.id.Fragment_Add_Word_Word);
        EditText edtUsername=findViewById(R.id.Fragment_Add_Word_Sentence);
        EditText edtPassword1=findViewById(R.id.Activity_Register_Password1);
        EditText edtPassword2=findViewById(R.id.Activity_Register_Password2);

        if(edtPassword1.getText().toString().trim().equals(edtPassword2.getText().toString().trim())){
            Login.Register(edtNamesurname.getText().toString().trim(),edtUsername.getText().toString().trim(),edtPassword1.getText().toString().trim(),getApplicationContext());
        }else {
            Toast.makeText(getApplicationContext(),"Şifreler aynı değil.",Toast.LENGTH_LONG).show();
        }


    }
}
