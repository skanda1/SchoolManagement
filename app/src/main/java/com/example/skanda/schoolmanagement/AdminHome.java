package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {
    TextView at,as,vt,vs,ab,lo,dt,ds;
    SQLiteDatabase db;
    public static ArrayList<String> batch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        as=findViewById(R.id.addStudent);
        at=findViewById(R.id.addTeacher);
        vt=findViewById(R.id.viewteacher);
        vs=findViewById(R.id.viewstudent);
        dt=findViewById(R.id.deleteteacher);
        ds=findViewById(R.id.deletestudent);
        ab=findViewById(R.id.addbatch);
        lo=findViewById(R.id.logout);
        db=openOrCreateDatabase("Schoolmanagment",0,null);
        batch=new ArrayList<>();
        selectBatch();


        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,AddStudent.class);
                startActivity(i);
            }
        });

        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,AddTeacher.class);
                startActivity(i);
            }
        });

        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,ViewTeacher.class);
                startActivity(i);
            }
        });

        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,ViewStudent.class);
                startActivity(i);
            }
        });

        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,DeleteTeacher.class);
                startActivity(i);
            }
        });

        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,DeleteStudent.class);
                startActivity(i);
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,AddBatch.class);
                startActivity(i);

            }
        });
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHome.this,Options.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
    }
    public void selectBatch()
    {
        String query="select * from batch";
        Cursor c=db.rawQuery(query,null);
        while(c.moveToNext())
        {
            batch.add(c.getString(0))  ;
            //Toast.makeText(this, ""+batch, Toast.LENGTH_SHORT).show();
        }
    }

}
