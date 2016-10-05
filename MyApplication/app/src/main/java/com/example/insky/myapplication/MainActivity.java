package com.example.insky.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3; // EditText 객체를 각각 생성하였습니다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);  // EditText 객체와 레이아웃을 연결시켜주었습니다.
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);

    }

    public void onButtonClicked(View view) {
        String str1 = et1.getText().toString(); // EditText에서 입력한 값을 string으로 받아왔습니다
        String str2 = et2.getText().toString();

        if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) { // 만약 각 EditText란이 비어있다면 예외처리를 해주었습니다.
            Toast.makeText(getApplicationContext(), "Number1과 Number2에 값을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else { // string값을 float형으로 변환해주었습니다
            Float number1 = Float.parseFloat(str1);
            Float number2 = Float.parseFloat(str2);

            float num1 = number1;
            float num2 = number2;
            float result;


            switch (view.getId()) { // switch case 문으로 각 버튼을 눌렀을 때 id를 확인하고 각 연산이 되도록 하였습니다
                case R.id.button01:
                    result = num1 + num2;
                    et3.setText(number1 + "+" + number2 + "=" + result); // 연산자와 피연산자, 결과값이 표현이 되도록 하였습니다
                    et1.setText(null); // 결과값이 출력되는 입력하는 구간이 초기화가 되도록 하였습니다
                    et2.setText(null);
                    break;
                case R.id.button02:
                    result = num1 - num2;
                    et3.setText(number1 + "-" + number2 + "=" + result);
                    et1.setText(null);
                    et2.setText(null);
                    break;
                case R.id.button03:
                    result = num1 * num2;
                    et3.setText(number1 + "*" + number2 + "=" + result);
                    et1.setText(null);
                    et2.setText(null);
                    break;
                case R.id.button04:
                    if (num2 == 0) // 0의 값이 입력되고 나누기를 하였을 때 예외가 발생하게 하였습니다
                        Toast.makeText(getApplicationContext(), "0이 아닌 정수 및 실수를 입력해주세요", Toast.LENGTH_SHORT).show();
                    else {
                        result = num1 / num2;
                        et3.setText(number1 + "/" + number2 + "=" + result);
                        et1.setText(null);
                        et2.setText(null);
                        break;
                    }
            }
        }
    }
}