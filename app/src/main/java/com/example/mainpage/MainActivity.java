package com.example.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    EditText ID, PW;
    Button btn_sign, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID = (EditText) findViewById(R.id.login_id_edit_text);
        PW = (EditText) findViewById(R.id.login_pw_edit_text);

        btn_sign = (Button) findViewById(R.id.btnsignup);
        btn_login = (Button) findViewById(R.id.sign_in);

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    String mid = ID.getText().toString().trim();
                    String mpw = PW.getText().toString().trim();

                    JSONObject jsonob = new JSONObject();

                    jsonob.accumulate("id", mid);
                    jsonob.accumulate("pwd", mpw);
                    String url = "http://192.168.0.12:3000/api/board/loginid";

                    new JSONTask().execute(url,jsonob.toString());
                    Log.d(jsonob.toString(), "합니다");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection nect = null;
            BufferedReader rea = null;
            try {
                URL url = new URL(urls[0]);
                nect = (HttpURLConnection) url.openConnection();
                nect.setRequestMethod("POST");
                nect.setRequestProperty("Cache-Control", "no-cache");
                nect.setRequestProperty("Content-Type", "application/json");

                nect.setRequestProperty("Accept", "text/html");
                nect.setDoOutput(true);
                nect.setDoInput(true);
                nect.connect();

                OutputStream out = nect.getOutputStream();
                out.write(urls[1].getBytes("utf-8"));
                out.flush();
                out.close();

                InputStream stream = nect.getInputStream();
                rea = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = rea.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (nect != null) {
                    nect.disconnect();
                }
                try {
                    if (rea != null) {
                        rea.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            if(result.equals("Success")) {
                finish();
                Intent intent = new Intent(getApplicationContext(), thirdActivity.class);
                startActivity(intent);
            }
            else{
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Temp");
                alert.setMessage("No user or fail id, password");
                alert.setPositiveButton("check",null);
                alert.show();
            }
        }
    }
}
