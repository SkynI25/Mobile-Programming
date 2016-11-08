package com.example.insky.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by InSky on 2016-10-18.
 */

public class MemoContentActivity extends AppCompatActivity { // 메모 내용 엑티비티
    TextView mText;
    private static final int DIALOG_YES_NO_MESSAGE = 1; // 대화상자박스 실행을 위한 변수 선언
    public String fileName; // 파일 삭제가 될 제목내용

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mText = (TextView) findViewById(R.id.text); // xml과 연결
        Intent intent = getIntent(); // 인텐트를 받아온다
        fileName = intent.getStringExtra("Title"); // fileName에 Title 키값을 가진 인텐트의 내용을 fileName에 저장
        mText.setText(load()); // 텍스트뷰에 메모 내용 띄움
    }

    public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar2, menu); // 메뉴바 아이콘 사용을 위해 선언한 함수
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete: // delete 아이콘이 눌렸을 때
                Log.v("ActionBar", "delete button");
                showDialog(DIALOG_YES_NO_MESSAGE); // 삭제를 할지 묻는 대화상자를 띄운다
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DIALOG_YES_NO_MESSAGE:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("삭제 확인 대화 상자")
                        .setMessage("메모를 삭제 하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() { // 원래는 아니오 예 순으로 뜨지만
                            @Override// 우리 눈에 보기 편하게 만들고 싶어서 PositiveButton에 아니오를 넣고 Negative에 예를 넣었다.
                            public void onClick(DialogInterface dialog, int which) { // 아무것도 수행하지 않는다

                            }
                        })
                        .setNegativeButton("예", new DialogInterface.OnClickListener() { // 예를 누를 경우
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent Delin = new Intent();
                                Delin.putExtra("Delete", fileName); // 현재 메모에 대한 Delete라는 키값을 보낸다.
                                setResult(RESULT_OK, Delin); // onActivityResult에 Delin 결과를 보낸다.
                                deleteFile(fileName); // 해당 파일을 삭제한다
                                finish(); // 종료
                            }
                        });
                AlertDialog alert = builder.create();
                return alert;
        }
        return null;
    }


    public String load() { // 파일을 한 바이트씩 읽어주는 함수
        try {
            FileInputStream fis = openFileInput(fileName); // 파일읽기 위해 파일 오픈
            byte[] data = new byte[fis.available()]; // 버퍼 생성후 읽기 수행
            fis.read(data);
            fis.close();
            return new String(data);
        } catch (IOException e) { // 예외처리. 파일 관련 API를 사용하는 경우 IOEXCEPTION을 해주어야 함
            e.printStackTrace();
        }
        return "";
    }
}
