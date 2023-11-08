package com.example.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //요버튼에 contextmenu를 등록하겠다
        Button btOk = findViewById(R.id.btOk);
        //등록
        registerForContextMenu(btOk);

        //textview눌렀을때 다른거 뜨게 해보자
        TextView tvHello = findViewById(R.id.tvHello);
        registerForContextMenu(tvHello);

        //팝업 메뉴
        ImageButton ibtReply = findViewById(R.id.ibtReply);
        ibtReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(),v);
                MenuInflater inflater = popup.getMenuInflater(); //팝업메뉴는 popup에 있는 getmenuinflater따로 얻어줘야한다.
                inflater.inflate(R.menu.pmenu_activity_main_ibtreply, popup.getMenu());

                //이벤트 처리 핸들러
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId(); //menunew나 menuopen id가 들어옴

                        if(itemId == R.id.pmenuReplySender){
                            Toast.makeText(getApplicationContext(),"popup menu reply to sender", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if(itemId == R.id.pmenuReplyAll){
                            Toast.makeText(getApplicationContext(),"popup menu reply to all", Toast.LENGTH_SHORT).show();
                            return true;
                        } else {
                            return false;
                        }

                    }
                });

                popup.show();
            }
        });

    }

    //activity가 생성될 때 얘 한번 호출 됨 , 여기서 menu xml부르면 됨~
    //메뉴생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    return super.onCreateOptionsMenu(menu); // 직접 inflation 해줘야함
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    //new나 open같은 item의 id를 읽어서 불러옴
    //메뉴 이벤트처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); //menunew나 menuopen id가 들어옴

        if(itemId == R.id.menuNew){
            Toast.makeText(getApplicationContext(),"options menu new", Toast.LENGTH_SHORT).show();
            return true;
        } else if(itemId == R.id.menuOpen){
            Toast.makeText(getApplicationContext(),"options menu open", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    //contextmenu만들기 , 누를떄마다 매번 호출
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.cmenu_activity_main_btok, menu); 버튼만 있을떄

        //textview눌렀을때 다른거 뜨게 해보자/ viewId를 찾아서 구별
        int cmenuResourceId = 0; //초기화해줘라. /if나 else-if가 아닐경우에 할당이 안되기 때문에 초기화 안하면 에러.
        int viewId = v.getId();
        if (viewId == R.id.btOk){
            cmenuResourceId = R.menu.cmenu_activity_main_btok;
        } else if (viewId == R.id.tvHello) {
            cmenuResourceId = R.menu.cmenu_activity_main_tvhello;
        }
        inflater.inflate(cmenuResourceId, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); //cmenucreate나 cmenudelete id가 들어옴 //컨텍스트 메뉴 플로팅 방식

        if(itemId == R.id.cmenuCreate){
            Toast.makeText(getApplicationContext(),"context menu create", Toast.LENGTH_SHORT).show();
            return true;
        } else if(itemId == R.id.cmenuDelete){
            Toast.makeText(getApplicationContext(),"context menu delete", Toast.LENGTH_SHORT).show();
            return true;
        } else if(itemId == R.id.cmenuCopy){
            Toast.makeText(getApplicationContext(),"context menu copy", Toast.LENGTH_SHORT).show();
            return true;
        }else if(itemId == R.id.cmenuPaste){
            Toast.makeText(getApplicationContext(),"context menu paste", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onContextItemSelected(item);
        }
    }

}