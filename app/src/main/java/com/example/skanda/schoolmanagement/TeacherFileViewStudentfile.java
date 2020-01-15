package com.example.skanda.schoolmanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherFileViewStudentfile extends AppCompatActivity {
    SharedPreferences sp;
    SQLiteDatabase db;
    TextView des,fsname,sid,stle,fname;
    EditText here;

    FilesAdapter adapter;

    Button vs;
    String stdid,sname,stitle,sbatch,path,filename;
    SharedPreferences.Editor ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_file_view_studentfile);
        sp = getSharedPreferences("Option", MODE_PRIVATE);
        des = findViewById(R.id.fdes);
        fsname = findViewById(R.id.fsname);
        sid = findViewById(R.id.fsid);
        vs = findViewById(R.id.view);

        db = openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        stle = findViewById(R.id.fstle);
        stdid = sp.getString("id", "");
        sname = sp.getString("name", "");
        sbatch = sp.getString("batch", "");
        stitle = sp.getString("Title", "");
        sid.setText(stdid);
        stle.setText(stitle);
        fsname.setText(sname);
        getdescription(stitle);
        final String mainpath= Environment.getExternalStorageDirectory().getAbsolutePath();
        //Toast.makeText(this, ""+stitle, Toast.LENGTH_SHORT).show();
        getPath(stle.getText().toString());
        fname=findViewById(R.id.filenameview);
        filename = getfile(stitle);
        fname.setText(filename);


        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int count = 0;
                    String temp = "";

                   // Toast.makeText(TeacherFileViewStudentfile.this, ""+path, Toast.LENGTH_SHORT).show();

             Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);
             i.addCategory(Intent.CATEGORY_OPENABLE);
             i.setDataAndNormalize(Uri.parse(path));
             i.setType("*/"+filename);
             startActivity(Intent.createChooser(i,""));
            }
        });


    }




        public String getfile(String n)
        {
        String query = "select filename from task where title='" + n + "'";

        Cursor c = db.rawQuery(query, null);
        String fname ="";
        while (c.moveToNext()) {

              fname = c.getString(0);
               }
        return fname;

    }

        void getdescription (String d)
        {
            String query = "select description from task where title='" + d + "'";
            Cursor c = db.rawQuery(query, null);
            String dquest = "";
            while (c.moveToNext()) {
                dquest = c.getString(0).toString();

            }
            des.setText(dquest);
        }

        public void getPath(String t)
        {
            String q="select filepath from task where title='"+t+"'";
            Cursor c=db.rawQuery(q,null);
            if(c.moveToNext())
            {
                path=c.getString(0);
                Toast.makeText(this, ""+path, Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(this, "file not yet uploaded", Toast.LENGTH_SHORT).show();
            }
        }




    }


