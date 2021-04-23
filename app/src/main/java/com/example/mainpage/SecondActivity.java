package com.example.mainpage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class SecondActivity extends AppCompatActivity {

    EditText ETI, ETP, ETN, ETU;
    Button btnClose, btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ETI = (EditText) findViewById(R.id.et_id);
        ETP = (EditText) findViewById(R.id.et_password);
        ETN = (EditText) findViewById(R.id.et_name);
        ETU = (EditText) findViewById(R.id.et_pnum);

        btnClose = (Button) findViewById(R.id.btn_close);
        btnSign = (Button) findViewById(R.id.btn_sign);

        btnSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    String eti = ETI.getText().toString().trim();
                    String etp = ETP.getText().toString().trim();
                    String etn = ETN.getText().toString().trim();
                    String etu = ETU.getText().toString().trim();

                    JSONObject jsonob = new JSONObject();

                    jsonob.accumulate("id", eti);
                    jsonob.accumulate("pw", etp);
                    jsonob.accumulate("pwcheck", etn);
                    jsonob.accumulate("email", etu);
                    Log.d("입력값", "Yes");

                    String url = "http://116.125.234.173:3000/api/board/signup";

                    new JSONTask().execute(url,jsonob.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            //JSONObject를 만들고 key value 형식으로 값을 저장해준다.

            HttpURLConnection nect = null;
            BufferedReader rea = null;

            try {
                URL url = new URL(urls[0]);
                nect = (HttpURLConnection) url.openConnection();

                nect.setRequestMethod("POST");//POST방식으로 보냄
                nect.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                nect.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송

                nect.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                nect.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                nect.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                nect.connect();

                //서버로 보내기위해서 스트림 만듬
                OutputStream out = nect.getOutputStream();
                out.write(urls[1].getBytes("utf-8"));
                out.flush();
                out.close();//버퍼를 받아줌

                //서버로 부터 데이터를 받음
                InputStream stream = nect.getInputStream();

                rea = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = rea.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

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
                        rea.close();//버퍼를 닫아줌
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(result,"yes");

            if(result.equals("Create Success")){
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }else{
                Log.d(result,"nono");
                Toast.makeText(SecondActivity.this, "Used ID", Toast.LENGTH_SHORT);
            }
        }
    }
}