package com.example.insky.assignment3;

import android.content.Intent;
import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView m_ListView; // 리스트뷰를 사용하기 위해 선언
    private ArrayAdapter<String> m_Adapter; // 뷰에 데이터를 제공하기 위해 어뎁터 선언
    public String Title; // 텍스트들의 제목
    public String delete; // 텍스트들을 삭제해주기 위한 변수
    //int ListCount; // 아이템의 위치를 받기 위한 변수
    int add = 3; // 추가 아이콘을 눌렀을 때 전달할 키값 add
    int content = 5; // 텍스트를 삭제할 때 전달할 키값 content

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> values = new ArrayList<>();
        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values); // 어뎁터 생성. simple_list_item_1은 리스트 뷰에 한 줄의 string 만을 보여줌
        m_ListView = (ListView) findViewById(R.id.list); // xml 파일과 연결
        m_ListView.setAdapter(m_Adapter); // 리스트 뷰에 어댑터 연결
        String[] list = fileList(); // 현재 리스트에 있는 파일목록을 보여줌
        for(int i=1; i<list.length; i++)
            m_Adapter.add(list[i]);
        m_ListView.setOnItemClickListener(onClickListItem); // 리스트뷰에 아이템 터치시 이벤트를 처리할 리스너 설정
    }

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() { // 아이템 클릭 리스너
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent in = new Intent(getApplicationContext(), MemoContentActivity.class); // 인텐트선언 후 인텐트값을 메모 작성엑티비티로 넘김
            in.putExtra("Title", m_Adapter.getItem(position)); // 클릭한 텍스트에 "Title"이라는 키 값을 넘겨준다
            //setResult(RESULT_OK, in); // 돌려준 결과를 저장하게하는 것, setResult를 해줌으로써 해당 intent가 onActivityResult로 결과가 들어감
            //ListCount=position; // ListCount라는 전역변수에 position이라는 위치값을 저장하게 했다.
            startActivityForResult(in, content); // in 인텐트에 content라는 키 값을 보낸다. 안드로이드 시스템이 엑티비티들 사이에서 인텐트값을 다른 엑티비티로 넘겨준다.
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 인텐트 결과를 받는 함수
        if (resultCode == RESULT_OK) { // resultcode가 RESULT_OK 일 때 add 또는 content 조건으로 넘어간다.
            if (requestCode == add) { // requestCode가 add 인 경우 어뎁터에 추가를 해준다.
                Title = data.getStringExtra("Title"); // 키 값인 "Title" 값을 받아 Title 변수에 저장을 해준다
                m_Adapter.add(Title); // 어뎁터에 추가

            } else if(requestCode == content) { // requestCode가 content 일 때 어뎁터에서 해당 파일을 삭제한다
                delete = data.getStringExtra("Delete"); // Delete 키값을 받아서 delete 변수에 저장해준다
                //Log.v("working","잘 돌아가나" + delete); // 값이 잘 넘어가는지 확인하기 위해 사용하였다
                //String item = m_Adapter.getItem(ListCount); // ListCount에 저장된 position값으로 해당 파일의 위치를 item에 대입
                //Log.v("working","ListCount의 값은?? ");
                m_Adapter.remove(delete); // 해당 파일을 삭제 해준다.
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) { // 메뉴바에 있는 아이콘을 실행 했을 때
        switch (item.getItemId()) {
            case R.id.action_add: // add 아이콘을 눌렀을 때
                Intent intent = new Intent(this, MemoWriteActivity.class); // 인텐트를 메모 작성 엑티비티로 넘김
                startActivityForResult(intent, add); // 인텐트게 add 키 값을 저장
                Log.v("ActionBar", "add button");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
