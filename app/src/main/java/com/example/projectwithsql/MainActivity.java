package com.example.projectwithsql;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.projectwithsql.R.id.editText;

public class MainActivity extends AppCompatActivity {
     EditText inserterdWord;
     MyDBAdapter helper;
     Button addingWord;
     Button seeingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inserterdWord = findViewById(editText);
        helper= new MyDBAdapter(this);

        addingWord = findViewById(R.id.button);
        seeingList = findViewById(R.id.button2);

        seeingList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });

        addingWord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addWord(v);
            }
        });
    }



    @SuppressLint("ShowToast")
    public void addWord(View view) {
        String t1 = inserterdWord.getText().toString();
        if(t1.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "add word", Toast.LENGTH_SHORT);
        } else {
            boolean id = helper.insert_word(t1);
            if(id) {
                Toast.makeText(getApplicationContext(), "insertion not successful", Toast.LENGTH_SHORT);
                inserterdWord.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "insertion successful", Toast.LENGTH_SHORT);
                inserterdWord.setText("");
            }
        }
    }
}
