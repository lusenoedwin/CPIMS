package com.example.cpims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class useractivity extends AppCompatActivity {

    private CardView usercarddefiled,usercardriskenv,usercardseparated,usercardvane,usercardchldhome,usercardchildoffenders,
            usercardhandicapped,usercardmarriage,usercardlabour,usercardsass,usercarddropped,usercarddispute,usercardtrauma,usercardaddicts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        usercarddefiled= findViewById(R.id.usercarddefiled);
        usercardriskenv= findViewById(R.id.usercardrisk);
        usercardvane= findViewById(R.id.usercardvane);
        usercardseparated= findViewById(R.id.usercardseparated);
        usercardchldhome= findViewById(R.id.usercardchildrenshome);
        usercardchildoffenders= findViewById(R.id.usercardchildoffenders);
        usercardhandicapped= findViewById(R.id.usercardhandicapped);
        usercardmarriage= findViewById(R.id.usercardmarriage);
        usercardlabour= findViewById(R.id.usercardlabour);
        usercardsass= findViewById(R.id.usercardsocialass);
        usercarddropped= findViewById(R.id.usercardschdropped);
        usercarddispute= findViewById(R.id.usercarddisputes);
        usercardtrauma= findViewById(R.id.usercardtrauma);
        usercardaddicts= findViewById(R.id.usercarddrugaddicts);


        usercarddefiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),addnewdefiledcase.class));
                finish();



            }
        });
    }


}