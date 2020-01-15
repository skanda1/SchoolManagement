package com.example.skanda.schoolmanagement;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

public class TeacherViewFiles extends AppCompatActivity {
    SharedPreferences sp,my;
    SharedPreferences.Editor ob;

    String teacher,std,sec,dname,id,bat;
    Spinner sp1,sp2;
    ListView ls;
    Button viewstudents;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname1="Student";
    ArrayList<String> student = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_files);
        viewstudents = findViewById(R.id.v1);
        sp1 = findViewById(R.id.s1);
        sp2 = findViewById(R.id.s2);
        ls=findViewById(R.id.l1);


        //sp = getSharedPreferences("login", 0);
        my=getSharedPreferences("Option",0);
        ob=my.edit();
        //teacher = sp.getString("teachername", "");


        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        viewstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter =new ArrayAdapter<String>(TeacherViewFiles.this,R.layout.layout,R.id.textView32,student);

                adapter.clear();

                std = sp1.getSelectedItem().toString();
                sec = sp2.getSelectedItem().toString();
                bat=my.getString("batch","");
                getSData(std, sec,bat);
                ls.setAdapter(adapter);
            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=  ls.getItemAtPosition(i).toString();
                ob.putString("name",name);
                ob.commit();
                //Toast.makeText(TeacherViewFiles.this, ""+my.getString("name",""), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(TeacherViewFiles.this,Files.class);
                startActivity(intent);
            }
        });



    }
    void getSData(String n,String p,String b)
    {
        String Displayproduct = "select * from " + tname1 +" where standard='"+ n +"' and section='"+p+"' and batch='"+b+"'";


        Cursor c = db.rawQuery(Displayproduct, null);

        if(c.getCount()==0)
        {
            Toast.makeText(this, "No students", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();
        while (c.moveToNext()) {
            id=c.getString(0);
            dname = c.getString(1);
            student.add(dname);
        }
    }}

