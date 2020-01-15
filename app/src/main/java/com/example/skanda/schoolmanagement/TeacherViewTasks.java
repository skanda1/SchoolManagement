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

public class TeacherViewTasks extends AppCompatActivity {
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
        setContentView(R.layout.activity_teacher_view_tasks);
        sp1=findViewById(R.id.st);
        sp2=findViewById(R.id.se);
        ls=findViewById(R.id.lvtvt);
        viewstudents=findViewById(R.id.tvtview);
        my=getSharedPreferences("Option",0);
        ob=my.edit();
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        viewstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter =new ArrayAdapter<String>(TeacherViewTasks.this,R.layout.studentlist,R.id.name,student);

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
               // Toast.makeText(TeacherViewTasks.this, ""+my.getString("name",""), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(TeacherViewTasks.this,ViewTask.class);
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


        // Toast.makeText(this, "Student Data present ", Toast.LENGTH_SHORT).show();
    }
}
