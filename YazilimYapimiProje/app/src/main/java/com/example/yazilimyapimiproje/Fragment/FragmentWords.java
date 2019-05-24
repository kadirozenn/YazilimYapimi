package com.example.yazilimyapimiproje.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganView;
import com.example.yazilimyapimiproje.Class.WordsClass;
import com.example.yazilimyapimiproje.R;
import com.example.yazilimyapimiproje.RecyclerView.RecyclerWords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FragmentWords extends Fragment {


   // private OnFragmentInteractionListener mListener;
   ArrayList<WordsClass> arrayTumListe=new ArrayList<>();
    RecyclerView mRecyclerViewMusteriListesi;
    RecyclerWords madapterMusteriListesi ;
    Spinner spinner;
    public FragmentWords() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_words, container, false);
        new Alagan(getContext());
        mRecyclerViewMusteriListesi=view.findViewById(R.id.Fragment_Word_RecyclerView);
        spinner=view.findViewById(R.id.Fragment_Word_Spinner);
        madapterMusteriListesi=new RecyclerWords(getContext(),arrayTumListe);
        LinearLayoutManager mLayoutmanager=new LinearLayoutManager(getContext());
        mRecyclerViewMusteriListesi.setLayoutManager(mLayoutmanager);
        mRecyclerViewMusteriListesi.setItemAnimator(new DefaultItemAnimator());

        Alagan.Instance.View.InitalizeSpinner(spinner,"Kelimelerim","Öğrendiğim Kelimelerim","1. Aşamadaki Kelimeler",
                "2. Aşamadaki Kelimeler","3. Aşamadaki Kelimeler","4. Aşamadaki Kelimeler","Test Aşamasındaki Kelimeler").ListeneSpinner(new AlaganView.AlaganSpinnerInterface() {
            @Override
            public void onSelectedItem(String data) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        listee("-1");
                        break;
                    case 1:
                        listee("5");
                        break;
                    case 2:
                      listee("1");
                        break;
                    case 3:
                        listee("2");
                        break;
                    case 4:
                        listee("3");
                        break;
                    case 5:
                        listee("4");
                        break;
                    case 6:
                        listee("6");
                        break;
                }

            }
        });
















        return view;
    }

    public void listee(final String status){
        arrayTumListe.clear();
        Alagan.Instance.dbString.put("command","wordList").put("userID","3").put("status",status).read(new AlaganStringDatabase.AlaganListener() {
            @Override
            public void onResponse(String response) {
                String isim[]=response.split("<");
                if(response.equals("null")){

                }else {
                    for(int i=0; i<isim.length; i++){
                        String veriler[]=isim[i].split(">");
                        arrayTumListe.add(new WordsClass(veriler[0],veriler[1],veriler[2],veriler[3]));

                    }

                    madapterMusteriListesi.notifyDataSetChanged();
                    mRecyclerViewMusteriListesi.setAdapter(madapterMusteriListesi);
                }


            }
        });

    }




}
