package com.example.yazilimyapimiproje.Alagan.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class AlaganCustomListViewAdapter  extends BaseAdapter
{

    private final List<?> itemList;
    // Declare Variables
    Context context;
    AlaganListViewListener listener;
    LayoutInflater inflater;
     int inflateLayoutID;

    public  AlaganCustomListViewAdapter(Context context, int layoutID, List<?> itemList , AlaganListViewListener listener) {
        this.context = context;
        this.listener = listener;
        this.inflateLayoutID = layoutID;
        this.itemList = itemList;
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(inflateLayoutID, parent, false);//list_item_row dan yeni bir view oluşturuyoruz
        listener.onView(itemView,position,itemList.get(position));


        return itemView;
    }




    public interface AlaganListViewListener
    {
        public void onView(View itemLayout, int position, Object item);
        public void onClick(Object item);
        public void onLongClick();
    }
}
