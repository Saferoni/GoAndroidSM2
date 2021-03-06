package com.safercrypt.goandroidsm2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private TextView textView;
    private Button buttonSave, buttonView, buttonLogOutAll;
    private Spinner songs;
    private ListView listView;
    private ArrayList<String> arraySongs = new ArrayList<>();
    private ArrayList<SongsItem> songslist = MainActivity.songslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView = (TextView) findViewById(R.id.textView);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonLogOutAll = (Button) findViewById(R.id.buttonLogOutAll);
        listView = (ListView)findViewById(R.id.listView);
        songs = (Spinner) findViewById(R.id.songs);

        // реализация адаптера для Spiner зи Enum
        songs.setAdapter(new ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1, Songs.values()));

        //обработка кнопки из лиснера по тображению лист вию
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songslist != null) {
                    arraySongs.clear();
                    //создаю аррайлист стрингов для вывода в листвию
                    for (int i = 0; i < songslist.size(); i++){
                        arraySongs.add(songslist.get(i).toString());
                    }
                    Toast.makeText(getApplicationContext(), "Та на тебе лидеров список",Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, arraySongs);
                    listView.setAdapter(adapter);
                }
                else {
                    textView.setText("Заполните пожалуйста форму или сохраните");
                }
            }
        });

        buttonLogOutAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(Activity2.this, MainActivity.class));
                intent.putExtra("logaut", 1);
                startActivity(intent);
            }
        });
    }

    // обработка кнопки методом, вызывается из кнопки сохранить добавляет выбранную песню в лист
    public void onClickSave(View view) {
        if (!songs.getSelectedItem().equals("")) {//проверку на есть ли елемент в спинере
            boolean truAdd = true;
            if (songslist.size() == 0) {
                songslist.add(new SongsItem(songs.getSelectedItem().toString()));
                truAdd = false;
            }else {
                for (int i = 0; i < songslist.size(); i++){
                    if (songslist.get(i).getS().equals(songs.getSelectedItem().toString())) {
                        songslist.get(i).increaseMyI();
                        truAdd = false;
                        break;
                    }
                }
            }
            if (truAdd) songslist.add(new SongsItem(songs.getSelectedItem().toString()));
        }
            Toast.makeText(getApplicationContext(), "Ваша заявка сохранена",Toast.LENGTH_SHORT).show();
    }

}
