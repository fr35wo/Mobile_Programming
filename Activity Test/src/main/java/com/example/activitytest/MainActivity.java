package com.example.activitytest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int REQ_CODE_SUB_0 = 0;
    TextView tvMainResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_result);

        tvMainResult = findViewById(R.id.tvMainResult);

        Button btMainCall = findViewById(R.id.btMainCall);
        btMainCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //이 인텐트는 얘를 호출하겠다 이런 의도
                Intent sIntent = new Intent(getApplicationContext(), SubActivity.class); //subactivity 컴포넌트를 띄우겠다
            //    startActivity(sIntent);
                sIntent.putExtra("strMainResult", tvMainResult.getText().toString());
            //2    startActivityForResult(sIntent, REQ_CODE_SUB_0);
                subActivityLauncher.launch(sIntent); //액티비티api사용하는방식?
                //3
            }
        });

    }
    //론처객체만들기 3
    ActivityResultLauncher<Intent> subActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        tvMainResult.setText(result.getData().getStringExtra("strSubInput"));
                    }
                }
            });

    //2
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch(requestCode){
//            case REQ_CODE_SUB_0:
//                if(resultCode == RESULT_OK){
//                    tvMainResult.setText(data.getStringExtra("strSubInput"));
//                }
//                break;
//        }
//    }
}