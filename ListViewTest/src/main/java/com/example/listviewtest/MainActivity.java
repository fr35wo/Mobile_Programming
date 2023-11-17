package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String[] FRUITS ={"Apple", "Banana", "Cherry", "Durian"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvFruits = findViewById(R.id.lvFruits);

        //코드 내 배열 사용
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,FRUITS);
        //리소스에서 읽어옴
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fruits, android.R.layout.simple_list_item_1);
        //내가만든 textview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextview, FRUITS);
        //내가만든 layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mylayout,R.id.tvFruits ,FRUITS);

        //데이터, 어댑터뷰와 어탭터의 관계
        //어댑터랑 어탭터뷰 연결, 어댑터: 데이터 읽어와서 뷰 만듬
        lvFruits.setAdapter(adapter);

        lvFruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override               //((TextView)view).getText() 인자를 읽어오는 방식
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}