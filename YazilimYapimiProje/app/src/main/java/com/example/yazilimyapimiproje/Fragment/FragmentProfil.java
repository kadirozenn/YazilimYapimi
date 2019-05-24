package com.example.yazilimyapimiproje.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Activity.ActivityLogin;
import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganView;
import com.example.yazilimyapimiproje.Class.Login;
import com.example.yazilimyapimiproje.R;

import org.w3c.dom.Text;

public class FragmentProfil extends Fragment {



    ProgressBar progressBar;
    EditText edtYear;
    TextView counter;
    Spinner spinner;
    Button btnYearQuery,btnExit;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);

        progressBar=view.findViewById(R.id.Fragment_Profil_Progres);
        edtYear=view.findViewById(R.id.Fragment_Profil_Year);
        spinner=view.findViewById(R.id.Fragment_Profil_Spinner);
        btnYearQuery=view.findViewById(R.id.Fragment_Profil_Btn);
        btnExit=view.findViewById(R.id.Fragment_Profil_BtnCkisi);
        checkBox=view.findViewById(R.id.Fragment_Profil_Check);
        counter=view.findViewById(R.id.Fragment_Profil_Counter);

        Alagan.Instance.View.InitalizeSpinner(spinner,"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz",
                "Ağustos","Eylül","Ekim","Kasım","Aralık").ListeneSpinner(new AlaganView.AlaganSpinnerInterface() {
            @Override
            public void onSelectedItem(String data) {

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.Remove(getContext());
                startActivity(new Intent(getContext(), ActivityLogin.class));
            }
        });

        btnYearQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String degisken= "-1";
                if(checkBox.isChecked()){
                    degisken=""+(spinner.getSelectedItemPosition()+1);

                }

                Alagan.Instance.dbString.put("command","statistics").put("userID","3").
                        put("months",""+degisken).
                        put("year",edtYear.getText().toString().trim()).read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        int a=Integer.valueOf(response);
                        progressBar.setProgress(a);
                        counter.setText(""+a);
                    }
                });
            }
        });


        return view;
    }





}
