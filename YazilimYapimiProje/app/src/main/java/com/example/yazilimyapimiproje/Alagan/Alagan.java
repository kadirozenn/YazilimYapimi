package com.example.yazilimyapimiproje.Alagan;

import android.content.Context;

import com.example.yazilimyapimiproje.Alagan.Class.AlaganJsonDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganView;


public class Alagan {
    Context main;

    public static Alagan Instance;

    public AlaganStringDatabase dbString;
    public AlaganJsonDatabase dbJson;
    public AlaganView View;

    public Alagan(Context main)
    {
        this.main = main;
        Instance = this;
        dbString = new AlaganStringDatabase(main);
        dbJson = new AlaganJsonDatabase(main);
        View = new AlaganView(main);
    }



}
