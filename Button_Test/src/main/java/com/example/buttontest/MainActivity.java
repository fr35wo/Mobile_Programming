package com.example.buttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_main);

        //Toast 대체 snackbar
        setContentView(R.layout.activity_main_snackbar);

        Button btNormal = findViewById(R.id.btNormal);

  //   1   ButtonClickListner btClickListner = new ButtonClickListner(); //객체만들기
  //   1   btNormal.setOnClickListener(btClickListner);
        //위와 같은 코드
  //   2   btNormal.setOnClickListener(new ButtonClickListner());


   //3     btNormal.setOnClickListener(mbtClickListner);


        //4 한번 쓸거면 이름 안줌
        btNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(), "Button clicked through anonymous class2", Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Button", Snackbar.LENGTH_SHORT).show();
            }
        });

    }
    //이벤트 처리하는 객체를 생성하여 이벤트 처리하는 방법 P.96    1,2
    //인터페이스 구현하는 클래스 만든 후에 객체 만들어 이벤트 처리    여러번 쓸거면 이름줌
    class ButtonClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    //3  클래스 선언과        변수이름              객체 선언 합침
    View.OnClickListener mbtClickListner = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Button clicked through anonymous class", Toast.LENGTH_SHORT).show();
        }
    };

}