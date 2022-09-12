package com.example.mapbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        MapFragment mapFrag = (MapFragment) fm.findFragmentById(R.id.map);
        if(mapFrag == null) {
            mapFrag = new MapFragment();
            fm.beginTransaction().add(R.id.map, mapFrag).commit();
        }

        SelectorFragment selFrag = (SelectorFragment) fm.findFragmentById(R.id.selector);
        if(selFrag == null) {
            selFrag = new SelectorFragment();
            fm.beginTransaction().add(R.id.selector, selFrag).commit();
        }
    }
}