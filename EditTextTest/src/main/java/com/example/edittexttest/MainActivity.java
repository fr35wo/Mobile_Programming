package com.example.edittexttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //키보드 종류, 동작 inputType
        //imeOptions도 구현 좋다
        EditText etName = findViewById(R.id.etName);
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { //익명클래스 사용
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getApplicationContext(), "Name sent: " + etName.getText(), Toast.LENGTH_SHORT).show();
                    handled = true;
                }
                return handled;
            }
        });


    }
}