package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Options extends AppCompatActivity implements View.OnClickListener {
    TextView admin,teacher,student,a,t,s;
    SharedPreferences sp;
    SharedPreferences.Editor ob;

    public static ArrayList<String> batch;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        admin=findViewById(R.id.admin);
        teacher=findViewById(R.id.teacher);
        student=findViewById(R.id.student);
        a=findViewById(R.id.textView);
        t=findViewById(R.id.textView2);
        s=findViewById(R.id.textView3);
        sp=getSharedPreferences("Option",MODE_PRIVATE);
        ob=sp.edit();
       admin.setOnClickListener(this);
       teacher.setOnClickListener(this);
       student.setOnClickListener(this);
        db=openOrCreateDatabase("Schoolmanagment",MODE_PRIVATE,null);
        batch=new ArrayList<>();
        createBatch();
        selectBatch();

       //dropTable();
        createTask();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
       // Toast.makeText(this, ""+root, Toast.LENGTH_SHORT).show();

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ob.putString("User","admin");
                ob.commit();
                Intent i = new Intent(Options.this, Login.class);
                startActivity(i);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ob.putString("User","teacher");
                ob.commit();
                Intent i1 = new Intent(Options.this, Login.class);
                startActivity(i1);
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ob.putString("User","student");
                ob.commit();
                Intent i3 = new Intent(Options.this, Login.class);
                startActivity(i3);
                            }
        });


    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.admin:
                ob.putString("User","admin");
                ob.commit();
                Intent i = new Intent(Options.this, Login.class);
                startActivity(i);
                break;

            case R.id.teacher:
                ob.putString("User","teacher");
                ob.commit();
                Intent i1 = new Intent(Options.this, Login.class);
                startActivity(i1);
                break;

            case R.id.student:
                ob.putString("User","student");
                ob.commit();
                Intent i3 = new Intent(Options.this, Login.class);
                startActivity(i3);
                break;
        }
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
    public void createBatch()
    {
        String query="create table if not exists batch (name varchar(10) PRIMARY KEY)";
        db.execSQL(query);
        //Toast.makeText(this, "Batch table created", Toast.LENGTH_SHORT).show();
    }
    public void dropTable()
    {
        String query="drop table task";
        db.execSQL(query);
        //Toast.makeText(this, "Table dropped", Toast.LENGTH_SHORT).show();
    }
    public void createTask()
    {

        String query="create table if not exists task (id int,name varchar(10),batch varchar(10),title varchar(20),startdate varchar(10),enddate varchar(10),submissiondate varchar(10),description varchar(50),comments varchar(50),filename varchar(50),remarks verchar(10),criteria varchar(10),filepath varchar(500))";
        db.execSQL(query);

    }


}
