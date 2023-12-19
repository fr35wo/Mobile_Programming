package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        EditText etSubInput = findViewById(R.id.etSubInput);

        String strMainResult = getIntent().getStringExtra("strMainResult");
        if(strMainResult != null){
            etSubInput.setHint(strMainResult);
        }

        Button btSubOK = findViewById(R.id.btSubOk);
        btSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent();
                sIntent.putExtra("strSubInput", etSubInput.getText().toString());
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
