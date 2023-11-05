package com.example.dialogtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btDialog = findViewById(R.id.btDialog);
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빌더 패턴 사용하여 접근해야한다. , 빌더 사용하여 빌더객체 만듬 , context주의 (getApplication은 안됨)
 //               AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this);
 //               adBuilder.setTitle("Notice");                       //그냥 this면 new View.OnClickListener()를 가리키게 된다.
 //               adBuilder.setIcon(R.mipmap.ic_launcher);
 //               adBuilder.setMessage("Time out");

                //빌더 이용한 재사용성 없는 dialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("공지사항")   //얘의 리턴값이 빌더이기 때문에 이와 같은 패턴으로 사용 가능 하다.
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("시간 초과")
                        .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false) //사용자가 빈공간이나 back버튼 눌러서 dialog못닫게
                        .show();
            }
        });

        //fragment사용함
        Button btNoticeDialog = findViewById(R.id.btNoticeDialog);
        btNoticeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment noticeDialogFragment = new NoticeDialogFragment();
                noticeDialogFragment.setCancelable(false);
                noticeDialogFragment.show(getSupportFragmentManager(), "noticeDialogFragment");
            }
        });

        Button btListDialog = findViewById(R.id.btListDialog);
        btListDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment listDialogFragment = new ListDialogFragment();
                listDialogFragment.setCancelable(false);
                listDialogFragment.show(getSupportFragmentManager(), "listDialogFragment");
            }
        });

    }

    public static class NoticeDialogFragment extends DialogFragment{ //public static으로 만들지 않으면 죽음
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("공지사항")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("시간 초과")
                    .setPositiveButton("닫기", null)
                    .setCancelable(false);

            return adBuilder.create(); //관리까지 하고싶을때

        //    return super.onCreateDialog(savedInstanceState);
        }
    }

    public static class ListDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            CharSequence[] items = {"Red", "Green", "Blue"};
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("리스트 다이얼로그")
//                    .setItems(items, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getActivity(), items[which], Toast.LENGTH_SHORT).show();
//                        }
//                    }); setitems랑 같다 대신 radio버튼 이고 눌러도 안없어짐 checkeditem인자에 0넣으면 red가 default
//                    .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getActivity(), items[which], Toast.LENGTH_SHORT).show();
//                        }
//                    }) //멀티 초이스 handler좀 다름
                    .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override //boolean 매개변수
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            String str = (isChecked) ? "Checked" : "Unchecked";
                            Toast.makeText(getActivity(), items[which] + str, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("확인", null); //닫기버튼
            return adBuilder.create(); //관리까지 하고싶을때

        //    return super.onCreateDialog(savedInstanceState);
        }
    }

}