package cse.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main); //R클래스안에 layout클래스 그 안에 activity_main(activity_main.xml)
                                                //한 화면 = 쌍으로 움직인다

    //    TextView tvHello = new TextView(this); //코드짜서 화면만들기
    //    tvHello.setText("Hello");
        //   setContentView(tvHello);

        setContentView(R.layout.activity_main);
        TextView tvHello = findViewById(R.id.tvHello);
        tvHello.setText("Hello Android in Code.");
    }
}