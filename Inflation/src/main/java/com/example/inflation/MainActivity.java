package com.example.inflation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  //      1
 //       setContentView(R.layout.activity_main);

 //       TextView tvHello = findViewById(R.id.tvHello);
 //       tvHello.setText("Hello, ");

        //밑에 코드가 setcontentview 랑 같은 코드
        TextView tvHello = new TextView(this);
        tvHello.setText("Hello, Android in code");

//        Button btOk = new Button(this);
//        btOk.setText("OK in code");

        Button btMybutton = (Button) View.inflate(this,R.layout.mybutton, null);

        LinearLayout llManager = new LinearLayout(this);
        llManager.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //       llManager.addView(tvHello);
        //       llManager.addView(btOk);

        llManager.addView(tvHello, llParams);
        llManager.addView(btMybutton, llParams);

        //layout을 xml로 만듬 xml을 setcontentview에 넣음 위는 이걸 코드로 구현한것
        //2     밑에 코드는 위의 많은 코드를 간단하게 구현한것?
//        LayoutInflater inflater = (LayoutInflater)getSystemService((Context.LAYOUT_INFLATER_SERVICE));
//        LinearLayout llManager = (LinearLayout) inflater.inflate(R.layout.activity_main, null);

        //3
//        LayoutInflater inflater = LayoutInflater.from(this);
 //       LinearLayout llManager = (LinearLayout)inflater.inflate(R.layout.activity_main, null);

        //4
 //      LinearLayout llManager = (LinearLayout) View.inflate(this, R.layout.activity_main, null);

        //Linear 매달기
        setContentView(llManager);

    }
}