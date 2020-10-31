package com.kusofan.demo.fivegen.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kusofan.demo.fivegen.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = ((EditText) findViewById(R.id.et_count)).getText().toString();

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("PING_COUNT",count);
                startActivity(intent);
            }
        });
    }
}