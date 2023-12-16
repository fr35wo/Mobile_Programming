package com.example.saverestorestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int amountOrder = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            amountOrder = savedInstanceState.getInt("amountOrder");
        }

        TextView tvOrder = findViewById(R.id.tvOrder);
        tvOrder.setText("총 주문량: "+ amountOrder);

        Button btIncrese = findViewById(R.id.btIncrease);
        btIncrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountOrder++;
                tvOrder.setText("총 주문량: "+ amountOrder);
            }
        });

        Button btDecrease = findViewById(R.id.btDecrease);
        btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(--amountOrder < 0){
                    amountOrder = 0;
                }
                tvOrder.setText("총 주문량: "+ amountOrder);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("amountOrder", amountOrder);
    }

}