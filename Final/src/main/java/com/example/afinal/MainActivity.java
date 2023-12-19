package com.example.afinal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ListViewUpdate
    ArrayList<String> itemList;
    //ListViewUpdate
    ArrayAdapter<String> adapter;

    //saverestorestate
    int amountOrder = 0;

    //ActivityTest
    TextView tvMainResult;

    //ProgressBarTest
    static final String TAG = "ProgressBar Test";

    static final int MSG_CODE_DOWNLOADTHREAD = 0;

    ProgressBar pdDownload;

    //ProgressBar Test
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

        //noticedialog
        Button btNoticeDialog = findViewById(R.id.btNoticeDialog);
        btNoticeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment noticeDialogFragment = new NoticeDialogFragment();
                noticeDialogFragment.setCancelable(false);
                noticeDialogFragment.show(getSupportFragmentManager(),"noticedailogfragment");
            }
        });

        Button btListMaintainDialog = findViewById(R.id.btListMaintainDialog);
        DialogFragment listMaintainDialogFragment = new ListMaintainDialogFragment();
        btListMaintainDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMaintainDialogFragment.setCancelable(false);
                listMaintainDialogFragment.show(getSupportFragmentManager(),"listMaintainDialogFragment");
            }
        });

        Button btLoginDialog = findViewById(R.id.btLoginDialog);
        DialogFragment loginDialogFragment = new LoginDialogFragment();
        btLoginDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialogFragment.setCancelable(false);
                loginDialogFragment.show(getSupportFragmentManager(),"loginDialogFragment");
            }
        });

        //ListViewUpdate
        if(savedInstanceState != null){
            itemList = savedInstanceState.getStringArrayList("itemList");
        } else {
            //데이터 arraylist로 만듬
            itemList = new ArrayList<String>();
        }

        ListView lvItems = findViewById(R.id.lvItems);
        //어댑터(변환기)에 데이터 넣기
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemList);

        lvItems.setAdapter(adapter);

        EditText etItem = findViewById(R.id.etItem);
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(etItem.getText().toString());
                etItem.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(lvItems);

        //saverestorestate
        if(savedInstanceState != null){
            amountOrder = savedInstanceState.getInt("amountOrder");
        }

        TextView tvOrder = findViewById(R.id.tvOrder);
        tvOrder.setText("총 주문량: " + amountOrder);

        Button btIncrease = findViewById(R.id.btIncrease);
        btIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountOrder++;
                tvOrder.setText("총 주문량: " + amountOrder);
            }
        });

        Button btDecrease = findViewById(R.id.btDecrease);
        btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(--amountOrder < 0){
                    amountOrder = 0;
                }
                tvOrder.setText("총 주문량: " + amountOrder);
            }
        });

        //ActivityTest
        tvMainResult = findViewById(R.id.tvMainResult);
        Button btMainCall = findViewById(R.id.btMainCall);
        btMainCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(getApplicationContext(), SubActivity.class);
                sIntent.putExtra("strMainResult", tvMainResult.getText().toString());
                subActivityLauncher.launch(sIntent);
            }
        });

        //ProgressBar Test
        DialogFragment downloadDialogFragment = new DownloadDialogFragment();

        Button btDownload = findViewById(R.id.btDownload);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fileSize = 100;
                downloadDialogFragment.setCancelable(false);
                downloadDialogFragment.show(getSupportFragmentManager(),"downloadDialogFragment");
                getSupportFragmentManager().executePendingTransactions();

                pdDownload = downloadDialogFragment.getDialog().findViewById(R.id.pdDownload);
                pdDownload.setMax(fileSize);
                pdDownload.setProgress(0);

                Thread thDownload = new Thread(new DownloadThread(fileSize));
                thDownload.start();
            }
        });

    }

    //noticedialog
    public static class NoticeDialogFragment extends DialogFragment{    //빌더 패턴 이용한 공지사항 생성
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("공지사항")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("시간 초과")
                    .setPositiveButton("닫기", null)
                    .setCancelable(false);

            return adBuilder.create();
        }
    }

    public static class ListMaintainDialogFragment extends DialogFragment{
        CharSequence[] items = {"RED","GREEN","BLUE"};
        boolean[] checkedItems = {false,true,false};

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            if(savedInstanceState != null){
                checkedItems = savedInstanceState.getBooleanArray("checkedItems");
            }
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("리 유 다")
                    .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            checkedItems[which] = isChecked;
                            String str = (isChecked) ? "Checked" : "Unchecked";
                            Toast.makeText(getActivity(), items[which] + str, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("확인", null);
            return adBuilder.create();
        }

        @Override // checkedItems를 저장하여 액티비티가 재생성될 때 이전 상태를 복원
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBooleanArray("checkedItems",checkedItems); //boolean배열 키 ,밸류 쌍으로저장
        }
    }

    public static class LoginDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setView(View.inflate(getActivity(),R.layout.dialog_login,null))
                    .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            return adBuilder.create();
        }
    }
//---------------------------------------------------------------------------------------
    //ListViewUpdate
    @Override // XML 메뉴 리소스 파일을 실제 메뉴 객체로 변환
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cmenu_activity_main_lvitems,menu);
    }

    @Override   //컨텍스트 메뉴의 항목이 선택될 때 호출
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int itemId = item.getItemId();

        if(itemId == R.id.cmenuDelete){
            itemList.remove(menuInfo.position);
            adapter.notifyDataSetChanged();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    @Override // itemList를 저장하여 액티비티가 재생성될 때 이전 상태를 복원
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("itemList", itemList);
    }

//saverestorestate
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("amountOrder", amountOrder);
//    }

//ActivityTest
    ActivityResultLauncher<Intent> subActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() { //론처객체 생성
            @Override   //액티비티 종료시 결과 반환
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    tvMainResult.setText(result.getData().getStringExtra("strSubInput"));
                }
            }
        });

    //ProgressBar Test
    public static class DownloadDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
            adBuilder.setTitle("파일 다운로드")
                    .setView(R.layout.dialog_download)
                    .setPositiveButton("닫기", null);

            return adBuilder.create();
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
