package com.adefemikolawole.converterwithapi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   Button btAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
        showAboutWhenClicked();
    }
    public void showAboutWhenClicked(){
        btAbout = findViewById(R.id.btAbout);
        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Author: Adefemi Kolawole\nClass: ITEC 4500-Fall 2018",Toast.LENGTH_LONG ).show();
            }
        });

    }
}
