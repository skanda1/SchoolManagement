package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    Button b;
    Spinner batch;
    ListView lv;

    SQLiteDatabase db;
    String dbname="Schoolmanagment";
    String bat;
    String dname,dstd,dsec,id,sb,fname,mname,phone,email,add;

    ArrayList<String> students=new ArrayList<String>();
    ArrayAdapter<String> ob;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        b=findViewById(R.id.Viewstudent);
        batch=findViewById(R.id.batch);
        db = openOrCreateDatabase(dbname, MODE_PRIVATE, null);
        lv=findViewById(R.id.studentlv);
        ob=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,AdminHome.batch);
        batch.setAdapter(ob);
        //ob.clear();







        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bat=batch.getSelectedItem().toString();
                ob=new ArrayAdapter<String>(ViewStudent.this,R.layout.viewstyle,R.id.style,students);
                //ob.clear();
                getstudentdata(bat);
                lv.setAdapter(ob);
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
    }

    void getstudentdata(String b)
    {
        String Displaystudent = "select * from Student where batch='"+ b+"'"  ;
        Cursor c = db.rawQuery(Displaystudent, null);
        Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        while (c.moveToNext()) {
            id = c.getString(0);
            dname=c.getString(1);
            sb=c.getString(3);
            dstd=c.getString(4);
            dsec=c.getString(5);
            fname = c.getString(6);
            mname=c.getString(7);
            phone=c.getString(8);
            email=c.getString(9);
            add=c.getString(10);
            students.add("StudentID          : "+id+"\nName                 : "+dname+"\nBatch                 : "+sb+"\nStandard           : "+dstd+"\nSection              : "+dsec+"\nFather  Name   : "+fname+"\nMother Name   : "+mname+"\nPhone                :"+phone+"\nEmail                 : "+email+"\nAddress             : "+add);
            //Toast.makeText(this, "teacher Data present "+students, Toast.LENGTH_SHORT).show();
        }
    }

}
