package com.example.middle_practice1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getApplicationContext(), checkBox.getText() + "check", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), checkBox.getText() + "uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getApplicationContext(), checkBox2.getText() + "check", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), checkBox2.getText() + "uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RadioGroup rgColor = findViewById(R.id.rgColor);

        RadioButton red = findViewById(R.id.red);
        RadioButton green = findViewById(R.id.green);
        RadioButton blue = findViewById(R.id.blue);

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rgColor = group.findViewById(checkedId);
                Toast.makeText(getApplicationContext(), rgColor.getText() + "select", Toast.LENGTH_SHORT).show();

                if(checkedId == R.id.red){
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.green) {
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.blue) {
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                }
            }
        });

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAddress = findViewById(R.id.editTextAddress);

        editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(getApplicationContext(), "name: " + editTextName.getText() , Toast.LENGTH_SHORT).show();
                    handled = true;
                }
                return handled;
            }
        });

        editTextAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(getApplicationContext(), "address: " + editTextAddress.getText(), Toast.LENGTH_SHORT).show();
                    handled = true;
                }

                return handled;
            }
        });

        Button submit = findViewById(R.id.submit);
        Button cancel = findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "submit", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menuNew){
            Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menuOpen) {
            Toast.makeText(getApplicationContext(), "o", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}