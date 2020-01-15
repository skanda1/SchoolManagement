package com.example.skanda.schoolmanagement;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {
    Spinner sp;
Button see;
    ArrayAdapter<String> ob;
    SharedPreferences s;
    SharedPreferences.Editor ed;
    ListView lt;
    SQLiteDatabase db;
    String tname="tasks";
    ArrayAdapter<String> adapter;
    ArrayList<String> stdrptlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        sp=findViewById(R.id.projectbatch);
        lt=findViewById(R.id.rptlst);
        see=findViewById(R.id.view);
        db = openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        ob=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Options.batch);
        sp.setAdapter(ob);

       see.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String batch=sp.getSelectedItem().toString();
               String query="select filename,criteria from task where batch='"+batch+"'";
               Cursor cursor=db.rawQuery(query,null);
               String fname="",criteria="";
               while (cursor.moveToNext())
               {
                   fname=cursor.getString(0);
                   criteria=cursor.getString(1);
                   stdrptlist.add("FileName : "+fname+"\n"+criteria);
               }
               adapter=new ArrayAdapter<String>(Reports.this,android.R.layout.simple_list_item_1,stdrptlist);
               lt.setAdapter(adapter);
           }

        });
    }
}
