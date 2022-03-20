package com.cristianomoraes.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class activity_feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            Log.d("EXTRAS-", String.valueOf(extras.getInt("cod_usuario")));

        }

    }
}