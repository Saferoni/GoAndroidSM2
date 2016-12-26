package com.safercrypt.goandroidsm2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

//Вариант№6. Концерт по заявкам
//Разработать систему для формирования программы концерта по заявкам.
// Пользователи (фанаты) регистрируются в системе и выбирают песни из предложенного списка ((*)
// или добавляют свои). Каждый пользователь может выбрать любое количество песен. Когда время
// подачи заявок оканчивается, формируется программа концерта, включающая песни, набравшие число
// заявок не равное нулю.

public class MainActivity extends AppCompatActivity {

        private Button buttonLogin, buttonLogOut,buttonGo;
        private EditText editViewFirstName, editViewLastName;
        private TextView textViewInfo;
        private LinearLayout layoutLogin, layoutApp;
        private SharedPrefLogin sharedPrefLogin;
        protected static ArrayList<SongsItem> songslist = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            sharedPrefLogin = new SharedPrefLogin(this);
            buttonGo = (Button) findViewById(R.id.buttonGo);
            buttonLogin = (Button) findViewById(R.id.buttonLogin);
            buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
            editViewFirstName = (EditText) findViewById(R.id.editViewFirstName);
            editViewLastName = (EditText) findViewById(R.id.editViewLastName);
            textViewInfo = (TextView) findViewById(R.id.textViewInfo);
            layoutLogin = (LinearLayout) findViewById(R.id.layoutLogin);
            layoutApp = (LinearLayout) findViewById(R.id.layoutApp);

            layoutApp.setVisibility(View.GONE);
            layoutLogin.setVisibility(View.GONE);

            int logOutAll = getIntent().getIntExtra("logaut", 0);

            if (sharedPrefLogin.getFirstName().isEmpty()){
                needLogin();
                Toast.makeText(this, "Вы не вошли в систему", Toast.LENGTH_SHORT).show();
            } else if(logOutAll == 1) {
                layoutApp.setVisibility(View.GONE);
                sharedPrefLogin.setFirstName("");
                sharedPrefLogin.setLastName("");
                needLogin();
            }else {
                isLogin();
            }
        }

        //обработка логина
        public void needLogin(){
            layoutLogin.setVisibility(View.VISIBLE);
            editViewLastName.setText("");
            editViewFirstName.setText("");
            textViewInfo.setText("привет Залогинтесь");
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!editViewFirstName.getText().toString().equals("") &&
                            !editViewLastName.getText().toString().equals("")) {
                        sharedPrefLogin.setFirstName(editViewFirstName.getText().toString());
                        sharedPrefLogin.setLastName(editViewLastName.getText().toString());
                        layoutLogin.setVisibility(View.GONE);
                        isLogin();
                    } else {
                        textViewInfo.setText("Вы не заполнили доно из полей, просьба заполнить");
                    }
                }
            });
        }

    // Залогинениый пользователь
    public void isLogin(){
        layoutApp.setVisibility(View.VISIBLE);
        textViewInfo.setText("Вы вошли как: " +
                sharedPrefLogin.getFirstName() + " " +
                sharedPrefLogin.getLastName());

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
                Toast.makeText(getApplicationContext(), "Добро пожаловать в Концерт по заявкам", Toast.LENGTH_SHORT).show();
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutApp.setVisibility(View.GONE);
                sharedPrefLogin.setFirstName("");
                sharedPrefLogin.setLastName("");
                needLogin();
            }
        });

    }
}
