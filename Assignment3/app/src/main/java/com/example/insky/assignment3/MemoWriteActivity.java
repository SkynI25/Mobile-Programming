package com.example.insky.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.id.edit;
import static android.R.id.switch_widget;
import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Created by InSky on 2016-10-18.
 */

public class MemoWriteActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    String fileName; // et1 의 제목이 저장됨

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        et1 = (EditText) findViewById(R.id.editText01); // xml 파일과 연결
        et2 = (EditText) findViewById(R.id.editText02);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button01: {
                    Intent intent = new Intent(); // 인텐트 생성
                    String data = et2.getText().toString(); // 내용란에 있는 문자열 값들을 data에 저장
                    save(data); // 문자열 값들을 save 함수를 통해 저장함
                    intent.putExtra("Title", et1.getText().toString()); // 제목란에 있는 문자열 값을 "Title" 키값과 함께 인텐트를 날려줌
                    setResult(RESULT_OK, intent); // 메인에 있는 onActivityResult에 저장됨
                    et1.setText(null); // 버튼을 눌렀을 때 제목 & 내용을 초기화시킨다
                    et2.setText(null);
                    Toast.makeText(this, "저장하였습니다", Toast.LENGTH_SHORT).show(); // 저장하였습니다라는 토스트 메시지를 띄운다
                    finish(); // 종료
                    break;
                }
            case R.id.button02: {
                Toast.makeText(this, "취소하였습니다", Toast.LENGTH_SHORT).show(); // 취소 버튼을 누를 시 취소하였습니다 라는 토스트 메시지를 띄운다
                et1.setText(null); // 제목 & 내용을 초기화 한다
                et2.setText(null);
                finish(); // 종료
                break;
            }
        }
    }


    public void save(String data) { // 문자열을 바이트 단위로 읽어 들여서 파일 출력 처리를 해주는 함수
        fileName = et1.getText().toString(); // 제목을 fileName에 저장함

        if (data == null || data.isEmpty() == true) {
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE); // private 모드로 파일 생성후 출력
            fos.write(data.getBytes());
            fos.close(); // 출력후 종료
        } catch (FileNotFoundException e) { // 예외처리. 파일 관련 API를 사용하는 경우 IOEXCEPTION을 해주어야 함
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

