package com.example.threaduitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "ThreadUITest";
    static final int MSG_CODE_COUNTERTHREAD = 0;
    boolean bRunning = false;

    TextView tvCount;

    //핸들러로 같은 코드 짜기
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch(msg.what){
                case MSG_CODE_COUNTERTHREAD:
                    tvCount.setText("[" + Thread.currentThread().getName() + "] Count:" + msg.arg1);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tvCount);

        //쓰레드 생성 , 시작 , 종료
        Thread thCounter = new Thread(new CounterThread());
        bRunning = true;
        thCounter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bRunning = false;
    }

    class CounterThread implements Runnable{
        int count;

        @Override
        public void run() {
            for(int i=0; i<10 && bRunning; i++){
                Log.i(TAG,"[" + Thread.currentThread().getName() + "] Count:" + i);
                //tvCount.setText("[" + Thread.currentThread().getName() + "] Count:" + i);
//                count = i;
//                tvCount.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvCount.setText("[" + Thread.currentThread().getName() + "] Count:" + count);
//                    }
//                });
                Message msg = new Message();
                msg.what = MSG_CODE_COUNTERTHREAD;
                msg.arg1 = i;
                handler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}