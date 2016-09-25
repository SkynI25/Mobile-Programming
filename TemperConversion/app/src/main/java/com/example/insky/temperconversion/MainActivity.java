package com.example.insky.temperconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int rb = 0;
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.inputTemp);
        tv = (TextView)findViewById(R.id.convertedTemp);
    }

    public void onButtonClicked(View view) {
        String str = et.getText().toString();
        Float result = Float.parseFloat(str);

        float F;
        float C;

        if(rb==0) {
            C = (result - 32) * 5 / 9;
            tv.setText(""+C);

        }
        else {
            F = result * 9 / 5 + 32;
            tv.setText(""+F);

        }
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_C :
                if(checked) {
                    rb = 0;
                    break;
                }
            case R.id.radio_F :
                if(checked) {
                    rb = 1;
                    break;
                }
        }
    }
}
