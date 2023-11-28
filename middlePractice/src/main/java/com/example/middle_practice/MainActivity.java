package com.example.middle_practice;

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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), cb1.getText() + "check", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), cb1.getText() + "uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), cb2.getText() + "check", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), cb2.getText() + "uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RadioButton red = findViewById(R.id.red);
        RadioButton green = findViewById(R.id.green);
        RadioButton blue = findViewById(R.id.blue);

        TextView tvRed = findViewById(R.id.tvRed);
        TextView tvGreen = findViewById(R.id.tvGreen);
        TextView tvBlue = findViewById(R.id.tvBlue);

        RadioGroup color = findViewById(R.id.color);

        color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton color = group.findViewById(checkedId);
                Toast.makeText(getApplicationContext(), color.getText() + "select", Toast.LENGTH_SHORT).show();

                if (checkedId == R.id.red){
                    tvRed.setVisibility(View.VISIBLE);
                    tvGreen.setVisibility(View.INVISIBLE);
                    tvBlue.setVisibility(View.INVISIBLE);
                } else if(checkedId == R.id.green){
                    tvRed.setVisibility(View.INVISIBLE);
                    tvGreen.setVisibility(View.VISIBLE);
                    tvBlue.setVisibility(View.INVISIBLE);
                } else if(checkedId == R.id.blue){
                    tvRed.setVisibility(View.INVISIBLE);
                    tvGreen.setVisibility(View.INVISIBLE);
                    tvBlue.setVisibility(View.VISIBLE);
                }

            }
        });

        Button btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "submit", Toast.LENGTH_SHORT).show();
            }
        });

        Button btCancel = findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        EditText etName = findViewById(R.id.etName);
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(getApplicationContext(), "name: " + etName.getText(), Toast.LENGTH_SHORT).show();
                    handled = true;
                }
                return handled;
            }
        });

        EditText etAddress = findViewById(R.id.etAddress);
        etAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(getApplicationContext(), "address: " + etAddress.getText(), Toast.LENGTH_SHORT).show();
                    handled = true;
                }

                return handled;
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "b1", Toast.LENGTH_SHORT).show();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "b2", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(R.id.menuNew == itemId){
            Toast.makeText(getApplicationContext(), "new", Toast.LENGTH_SHORT).show();
        } else if (R.id.menuOpen == itemId) {
            Toast.makeText(getApplicationContext(), "option", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}