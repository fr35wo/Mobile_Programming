package com.example.checkbox_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox cbMeat = findViewById(R.id.cbMeat);
        CheckBox cbCheese = findViewById(R.id.cbCheese);

//        cbMeat.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                                //v를 checkbox로 캐스팅
//                boolean checked = ((CheckBox)v).isChecked();
//                if(checked){
//                    Toast.makeText(getApplicationContext(), "Meat checked", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Meat unchecked", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        });
        //위 코드와 리스너 차이
        cbMeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Toast.makeText(getApplicationContext(), "Meat checked", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Meat unchecked", Toast.LENGTH_SHORT).show();
//                }
                }
            }
        });

//
//        cbCheese.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //v를 checkbox로 캐스팅
//                boolean checked = ((CheckBox)v).isChecked();
//                if(checked){
//                    Toast.makeText(getApplicationContext(), "Cheese checked", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Cheese unchecked", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        //2
//        cbMeat.setOnClickListener(mcbClickListener);
//        cbCheese.setOnClickListener(mcbClickListener);
//
//                                          }
//
//                //2 유사코드 하나로 묶어서 구현하기
//                View.OnClickListener mcbClickListener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean checked = ((CheckBox) v).isChecked();
//
//                        switch (v.getId()) {
//                            case R.id.cbMeat:
//                                if (checked) {
//                                    Toast.makeText(getApplicationContext(), "Meat checked", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Meat unchecked", Toast.LENGTH_SHORT).show();
//                                }
//                            case R.id.cbCheese:
//                                if (checked) {
//                                    Toast.makeText(getApplicationContext(), "Cheese checked", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Cheese unchecked", Toast.LENGTH_SHORT).show();
//                                }
//                        }
//                    }
//                };


    }
}