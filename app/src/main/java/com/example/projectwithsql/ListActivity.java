package com.example.projectwithsql;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity  extends AppCompatActivity {
    MyDBAdapter myDBAdapter;
    ListView listView;
    List<String> stringArrayList;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        listView = findViewById(R.id.listView_events);

        myDBAdapter = new MyDBAdapter(this);
        addingDatatoList();

        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stringArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return stringArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.activity_words, parent, false);

                TextView text = convertView.findViewById(R.id.text);
                String texiiik =stringArrayList.get(position);
                System.out.println("view text");
                System.out.println(texiiik);
                text.setText(texiiik);
                System.out.println("text po pridani");
                System.out.println(text);
            }
            return convertView;
        }
    }

    private void addingDatatoList(){
        stringArrayList = new ArrayList<String>();
        Cursor cursor = myDBAdapter.getData();
        if (cursor !=null){
            System.out.println("cursor nie je null");
            while (cursor.moveToNext()){
                String textik = cursor.getString(1);
                System.out.println("vypisujem textik");
                System.out.println(textik);
                stringArrayList.add(textik);
            }

        }else{
            System.out.println("cursor je nullllll");
        }

    }
}
