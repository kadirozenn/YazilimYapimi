package com.example.yazilimyapimiproje.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganView;
import com.example.yazilimyapimiproje.Class.Login;
import com.example.yazilimyapimiproje.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentKelimeEkle extends Fragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_word, container, false);
        Button btnAdd=view.findViewById(R.id.Fragment_Add_Word_Add);
        final EditText edtWord=view.findViewById(R.id.Fragment_Add_Word_Word);
        final EditText edtSentence=view.findViewById(R.id.Fragment_Add_Word_Sentence);
        final EditText edtWordMean=view.findViewById(R.id.Fragment_Add_Word_Mean);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long oneDayLongValue = 1000 * 60 * 60 * 24;
                Date now = new Date();
                Date after15Day = new Date(now.getTime() + (15 * oneDayLongValue));
                SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

                Alagan.Instance.dbString.put("command","createWord").put("userID", Login.SharedGet(getContext())).put("sentence",edtSentence.getText().toString().trim()).put("word",edtWord.getText().toString().trim())
                        .put("date",today.format(after15Day)).put("status","1")
                        .put("wordMean",edtWordMean.getText().toString().trim()).read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                       if(response.equals("1")){
                           Toast.makeText(getContext(),"Kelime Eklendi",Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
        });





        return view;
    }



}
