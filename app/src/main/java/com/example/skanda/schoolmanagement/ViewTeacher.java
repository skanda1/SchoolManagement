package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTeacher extends AppCompatActivity {
    Spinner batch;
    Button view1;
    ListView ls;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname="Teacher";
    String bat,dname,dstd,dsec,id,phone,email,add;

    ArrayList<String> teachers=new ArrayList<String>();
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teacher);
        batch = findViewById(R.id.viewteacherspinner);
        view1 = findViewById(R.id.button6);
        ls = findViewById(R.id.teacherview);
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,AdminHome.batch);
        batch.setAdapter(adapter);
        //adapter.clear();



        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bat=batch.getSelectedItem().toString();
                adapter=new ArrayAdapter<String>(ViewTeacher.this,R.layout.viewstyle,R.id.style,teachers);
                adapter.clear();
                getTdata(bat);
                ls.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,AdminHome.batch);
        batch.setAdapter(adapter);

    }

    void getTdata(String b)
    {
        String Displayteacher = "select * from " + tname +" where batch='"+ b+"'"  ;
        Cursor c = db.rawQuery(Displayteacher, null);
        Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

       while (c.moveToNext()) {
           id=c.getString(0);
            dname = c.getString(1);
           dstd=c.getString(4);
           dsec=c.getString(5);
           phone = c.getString(6);
           email=c.getString(7);
           add=c.getString(8);
            teachers.add("TeacherID     :"+id+"\nName            :"+dname+"\nBatch             :"+bat+"\nStandard      :"+dstd+"\nSection         :"+dsec+"\nPhone           :"+phone+"\nEmail            : "+email+"\nAddress       : "+add);
            //Toast.makeText(this, "teacher Data present "+teachers, Toast.LENGTH_SHORT).show();
        }
    }

}