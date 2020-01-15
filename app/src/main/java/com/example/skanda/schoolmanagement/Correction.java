package com.example.skanda.schoolmanagement;

import android.app.LauncherActivity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Correction extends AppCompatActivity {

    SharedPreferences sp;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    TextView ba,na,sid;
    SharedPreferences.Editor ob;
    String stdid;
    public  static  ArrayList<Task> tasks = new ArrayList<>();
    CorrectionAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction);
        ba=findViewById(R.id.b);
        na=findViewById(R.id.n);
        sid=findViewById(R.id.s);
        lv=findViewById(R.id.clv);

        sp=getSharedPreferences("Option",MODE_PRIVATE);
        ob=sp.edit();

        db=openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        ba.setText(sp.getString("batch","batch not found"));
        na.setText(sp.getString("name","not found"));
        selectID(sp.getString("name",null),sp.getString("batch","batch not found"));
        getTask(na.getText().toString());

        //ghadapter.clear();
        adapter = new CorrectionAdapter(this, R.layout.layout3,tasks );
        lv.setAdapter(adapter);


    }


    public void selectID(String name,String batch)
    {
        String query="select * from Student where name='"+ name +"' and batch='"+ batch +"'";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToNext())
        {
            stdid=c.getString(0);
            sid.setText(stdid);
        }
    }

    void getTask(String n)
    {
        String query = "select * from task where name='"+n+"'";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {

           String fname=c.getString(9);
           tasks.add(new Task(fname));

        }


        Toast.makeText(this, "tasks displayed ", Toast.LENGTH_SHORT).show();


    }

    void updateRemarks(String fname)
    {
        String query="update task set remarks='"+CorrectionAdapter.remarks+"' where filename='"+fname+"'";
        db.execSQL(query);
        String g="";
        String q="select remarks from task where filename='"+fname+"'";
        Cursor c=db.rawQuery(q,null);
        while(c.moveToNext())
        {
            g=c.getString(0);
        }
        Toast.makeText(this, "remarks: "+g, Toast.LENGTH_SHORT).show();
    }


}
