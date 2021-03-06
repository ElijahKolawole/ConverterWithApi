package com.adefemikolawole.converterwithapi;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
   Button btAbout;
   Button btConvert;
   EditText txtMoneyValue;
   TextView tvResult;
   double userInput;
    String result = "";
   public final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.e(TAG, "Starting Application here...");
        System.out.println("Starting...");
        showAboutWhenClicked();

        showResultOnConvertClick();
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

    public void getUSerInput(){
        EditText txtMoneyValue = findViewById(R.id.txtMoneyValue);
        if (txtMoneyValue.getText().toString().equals("")){
            userInput = 0.0;
        }
        try {
            userInput = Double.parseDouble(txtMoneyValue.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            Toast.makeText(MainActivity.this, "Process Successful", Toast.LENGTH_LONG);
            Log.e(TAG, "userInput:" + userInput);
        }


    }

    public void setTvResult(){
        getUSerInput();
        if (userInput <= 9999.00) {
            tvResult.setText(String.valueOf(userInput));
        }
        else{
            tvResult.setText("9,999.00 is the maximum value allowed");
        }
    }



    public void showResultOnConvertClick(){

        btConvert = findViewById(R.id.btConvert);
        tvResult = findViewById(R.id.tvResult);

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //result = tvResult.getText().toString();

                setTvResult();

              new JSONTask().execute("http://data.fixer.io/api/latest?access_key=b70449fc1a6ad33f2940bfdbcf125c41");
                // tvResult.setText(result);
            }
        });



    }

    public class JSONTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;


            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
             return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e(TAG, e.toString());

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, e.toString());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.toString());

                }
            }
                return  null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvResult.setText(result);// show json array in textview

        }
    }
}
