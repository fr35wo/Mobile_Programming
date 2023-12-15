package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//2.인자 보내기?
public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_sub);
    setContentView(R.layout.activity_sub_result);
//        Button btSubExit = findViewById(R.id.btSubExit);
//        btSubExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        EditText etSubInput = findViewById(R.id.etSubInput);
        
        //액티비티끼리 주고받기 함
        String strMainResult = getIntent().getStringExtra("strMainResult");
        if(strMainResult != null){
            etSubInput.setHint(strMainResult);
        }

        Button btSubOk = findViewById(R.id.btSubOk);
        btSubOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(); // 이 인텐트는 데이터 보내려는 의도
                sIntent.putExtra("strSubInput", etSubInput.getText().toString());//extra는 키밸류로 데이터 실어보냄, inent에 사용자 입력 데이터 채움
                setResult(RESULT_OK,sIntent);
                finish();
            }
        });

        Button btSubCancel = findViewById(R.id.btSubCancel);
        btSubCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
