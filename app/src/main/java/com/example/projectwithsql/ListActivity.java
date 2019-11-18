package com.example.projectwithsql;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity  extends AppCompatActivity {
    MyDBAdapter myDBAdapter;
    ListView listView;
    List<String> stringArrayList;
    private MyAdapter myAdapter;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        listView = findViewById(R.id.listView_events);

        myDBAdapter = new MyDBAdapter(this);
        addingDatatoList();

        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

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
            LayoutInflater li = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.string_lay, null);
            text = (TextView)convertView.findViewById(R.id.textString);


            String task = stringArrayList.get(position);
            System.out.println("........."+task);
            text.setText(task);

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
                stringArrayList.add(textik);
            }

        }else{
            System.out.println("cursor je nullllll");
        }

    }
}
