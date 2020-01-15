package com.example.skanda.schoolmanagement;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentComments extends AppCompatActivity {
    SQLiteDatabase db;
    ArrayList<Task> comm=new ArrayList<>();
    MyAdapter adapter;
    SharedPreferences sp;
    ListView ls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_comments);
        db=openOrCreateDatabase("Schoolmanagment",0,null);
        sp=getSharedPreferences("Option",0);
        ls=findViewById(R.id.comls);
        selectComments(sp.getString("studentname",""));

        adapter=new MyAdapter(this,R.layout.comts,comm);
        ls.setAdapter(adapter);

    }

    public void selectComments(String n)
    {
        String query="select * from task where name='"+n+"'";
        Cursor c=db.rawQuery(query,null);
        String title="",com="";
        while(c.moveToNext())
        {
            title=c.getString(3).toString();
            com=c.getString(8).toString();

        }
        comm.add(new Task(title,com));
        Toast.makeText(this, ""+comm, Toast.LENGTH_SHORT).show();




    }
}
