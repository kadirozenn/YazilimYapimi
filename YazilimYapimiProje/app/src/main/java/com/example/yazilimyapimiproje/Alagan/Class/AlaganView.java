package com.example.yazilimyapimiproje.Alagan.Class;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class AlaganView
{

    Context main;

    public AlaganView(Context main) {
        this.main = main;
    }

    Spinner lastSpinner;
    public AlaganView InitalizeSpinner(Spinner spinner , String... args)
    {
        lastSpinner = spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(main, android.R.layout.simple_spinner_item,args);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return  this;
    }

    public  void  ListeneSpinner(final AlaganSpinnerInterface listener)
    {
        lastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onSelectedItem(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setListViewAdapter(Context context, ListView list, int layoutID, List<?> itemList, final AlaganCustomListViewAdapter.AlaganListViewListener listener)
    {
        final AlaganCustomListViewAdapter adapter = new AlaganCustomListViewAdapter(context, layoutID, itemList, listener);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//list view e tıklanınca çalışır

            @Override
            public void onItemClick(AdapterView  parent, View view,int position, long id) {

                listener.onClick(adapter.getItem(position));
            }

        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onLongClick();
                return false;
            }
        });
    }

    public  interface AlaganSpinnerInterface
    {
        public void onSelectedItem(String  data);

    }
}
