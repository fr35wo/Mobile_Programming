package com.example.progressbartest;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "ProgressBar Test";
    static final int MSG_CODE_DOWNLOADTHREAD = 0;
    ProgressBar pdDownload;

    Handler handler = new Handler() { //다운로드 스레드로부터 메시지를 받아 표시
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case MSG_CODE_DOWNLOADTHREAD:
                    pdDownload.incrementProgressBy(msg.arg1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogFragment downloadDialogFragment = new DownloadDialogFragment();

        Button btDownload = findViewById(R.id.btDownload);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fileSize = 100;
            downloadDialogFragment.setCancelable(false);
            downloadDialogFragment.show(getSupportFragmentManager(),"downloadDialogFragment");
            getSupportFragmentManager().executePendingTransactions();

            //프로그래스 바 가져오고 최댓값 현재값 넣어줌
            pdDownload = downloadDialogFragment.getDialog().findViewById(R.id.pdDownload);
            pdDownload.setMax(fileSize);
            pdDownload.setProgress(0);

            Thread thDownload = new Thread(new DownloadThread(fileSize));
            thDownload.start();
            }
        });


    }

    public static class DownloadDialogFragment extends DialogFragment{ //다운로드 진행 상황을 표시하는 대화상자
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("파일 다운로드")
                    .setView(R.layout.dialog_download)
                    .setPositiveButton("닫기",null);

            return adBuilder.create();

            //return super.onCreateDialog(savedInstanceState);
        }
    }

    class DownloadThread implements Runnable { //파일 다운로드를 시뮬레이션하는 스레드 클래스
        int fileSize;

        public DownloadThread(int fileSize) {
            this.fileSize = fileSize;
        }

        @Override
        public void run() { //매 초마다 다운로드 진행 상황을 Handler에 보냄
            double downloadSpeed = 10.0;
            int remainder = fileSize;

            while(remainder > 0){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                Log.i(TAG,"remainder :" + remainder);
                Message msg = new Message();
                msg.what = MSG_CODE_DOWNLOADTHREAD;
                msg.arg1 = (int)downloadSpeed;
                handler.sendMessage(msg);

                remainder -= (int)downloadSpeed;
            }
        }
    }

}