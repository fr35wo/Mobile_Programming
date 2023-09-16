package com.example.buttontest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//이벤트 처리 방법 3가지 정리 해서 레포트 쓰기
//1그안에 4가지 정리
//2activity
//3xml
//코드 적고 discussion달기
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//setcontentview를 얘가 매달고 있다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    Button button = findViewById(R.id.button);
    //    button.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Button clicked through Activity", Toast.LENGTH_SHORT).show();
    }

    //xml 이용한 방법
    public void onButtonClicked(View v) {
        Toast.makeText(getApplicationContext(), "Button clicked through android:onClick xml", Toast.LENGTH_SHORT).show();
    }
}