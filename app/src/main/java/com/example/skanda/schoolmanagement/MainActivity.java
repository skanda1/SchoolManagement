package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.idescout.sql.SqlScoutServer;

public class MainActivity extends AppCompatActivity {
    ImageView sm;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname="Student";
    String tname1="Teacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SqlScoutServer.create(this, getPackageName());
        setContentView(R.layout.activity_main);
        sm=findViewById(R.id.imageView);
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Options.class);
                startActivity(i);
                finish();
            }
        });

        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        if (db != null)

        {
            //Toast.makeText(this, "Database is created ", Toast.LENGTH_SHORT).show();
           // DropTable();
            CreateStudentTable();
            CreateTeacherTable();
        }
        else
        {
            //Toast.makeText(this, "not created ", Toast.LENGTH_SHORT).show();
        }



    }

    void DropTable(){
        String query="drop table if exists "+tname1;
        db.execSQL(query);
        //Toast.makeText(this, "Teacher Table Dropped", Toast.LENGTH_SHORT).show();

    }

    void CreateStudentTable()
    {
        String query="create table if not exists "+tname+" (id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20) UNIQUE,password varchar(20),batch varchar(10),standard varchar(30),section varchar(30),fathername varchar(40),mothername varchar(40),phone int,email varchar(30),address varchar(40))";
        db.execSQL(query);
        //Toast.makeText(this, "Table Created ", Toast.LENGTH_SHORT).show();
    }

    void CreateTeacherTable()
    {
        String query="create table if not exists "+tname1+" (id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(20) UNIQUE ,password varchar(20),batch varchar(10),standard varchar(30),section varchar(30),phone int,email varchar(30),address varchar(40))";
        db.execSQL(query);
        //Toast.makeText(this, "Teacher Table Created ", Toast.LENGTH_SHORT).show();
    }


}
