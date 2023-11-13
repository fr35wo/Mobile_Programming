package com.example.radiobutton_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이 activity에 존재하는 findviewbyid
        RadioButton rdRed = findViewById(R.id.rbRed);
        RadioButton rdGreen = findViewById(R.id.rbGreen);
        RadioButton rdBlue = findViewById(R.id.rbBlue);

        //textview코드
        TextView tvRed = findViewById(R.id.tvRed);
        TextView tvGreen = findViewById(R.id.tvGreen);
        TextView tvBlue = findViewById(R.id.tvBlue);

        //리스너 사용 => 변화가 있을 때만 toast띄움
        RadioGroup rgColor = findViewById(R.id.rgColor);
        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rbColor = findViewById(checkedId); //id 체크하여 메시지 띄움

                //이게 더 정확하게 찾음 activity와 view에 id가 다 있다. 밑에 코드는 view에 있는 findviewbyid
                RadioButton rbColor = group.findViewById(checkedId);

                Toast.makeText(getApplicationContext(), rbColor.getText() + "select 3", Toast.LENGTH_SHORT).show();

                //textview코드
                if(checkedId == R.id.rbRed){
                    tvRed.setVisibility(View.VISIBLE);
                    tvGreen.setVisibility(View.INVISIBLE);
                    tvBlue.setVisibility(View.INVISIBLE);
                }else if(checkedId == R.id.rbGreen){
                    tvRed.setVisibility(View.INVISIBLE);
                    tvGreen.setVisibility(View.VISIBLE);
                    tvBlue.setVisibility(View.INVISIBLE);
                }else if(checkedId == R.id.rbBlue){
                    tvRed.setVisibility(View.INVISIBLE);
                    tvGreen.setVisibility(View.INVISIBLE);
                    tvBlue.setVisibility(View.VISIBLE);
                }
//                if (checkedId == R.id.rbRed){ //정적 메시지 코드
//                    Toast.makeText(getApplicationContext(), "Red selected ", Toast.LENGTH_SHORT).show();
//                } else if(checkedId == R.id.rbGreen){
//                    Toast.makeText(getApplicationContext(), "Green selected ", Toast.LENGTH_SHORT).show();
//                } else if(checkedId == R.id.rbBlue){
//                    Toast.makeText(getApplicationContext(), "Blue selected ", Toast.LENGTH_SHORT).show();
//                }
            }
        });
//        rbRed.setOnClickListener(mrbClickListener);
//        rbGreen.setOnClickListener(mrbClickListener);
//        rbBlue.setOnClickListener(mrbClickListener);
    }

//    View.OnClickListener mrbClickListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View v) {
//            boolean checked = ((RadioButton)v).isChecked();
//
//            int id = v.getId()
//                    if(id== R.id.rbRed){
//                        if (checked){
//                            Toast.makeText(getApplicationContext(), "Red selected 2", Toast.LENGTH_SHORT).show();
//                        }
//                    }else if(id == R.id.rbGreen){
//                        if (checked){
//                            Toast.makeText(getApplicationContext(), "Green selected 2", Toast.LENGTH_SHORT).show();
//                        }
//        }else if(id == R.id.rbBlue){
//                        if (checked){
//                            Toast.makeText(getApplicationContext(), "Blue selected 2", Toast.LENGTH_SHORT).show();
//                        }
//    }
//}
}