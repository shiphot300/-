package com.example.mainpage.Upload;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mainpage.R;

import org.json.JSONObject;

public class ProductUpload extends AppCompatActivity {
    EditText Edt_t;
    Button et_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Edt_t = (EditText) findViewById(R.id.et_title);

        et_btn = (Button) findViewById(R.id.et_button);

        et_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String eti = Edt_t.getText().toString().trim();

                    JSONObject jsonob = new JSONObject();
                    jsonob.accumulate("title", Edt_t);
                    Log.d("입력값", "Yes");

                    String url = "http://116.125.234.173:3000/api/board/upload";

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        }

}
