package com.safercrypt.goandroidsm2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


// что нудно накидать
//SheredPerf есть в 5 ДЗ

//  адаптеры , ликствию,  Лиснеры  онТач,

public class MainActivity extends AppCompatActivity {

    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //иницализация кнопок
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        // переход на другое активити
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
                // пример реализации тоаст
                Toast.makeText(getApplicationContext(), "Перехожу на другое активити", Toast.LENGTH_SHORT).show();
            }
        });

        // вызов внешней активити и передача текста
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SM2","Это onStart");
    }
}
