package com.example.skanda.schoolmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteTeacher extends AppCompatActivity {

    SQLiteDatabase db;
    EditText id;
    Button s;
    ArrayList<String> teacher;
    ArrayAdapter<String> ob;
    ListView lv;
    int tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_teacher);
        db=openOrCreateDatabase("Schoolmanagment",MODE_PRIVATE,null);
        id=findViewById(R.id.searchstudentid);
        s=findViewById(R.id.search1);
        lv=findViewById(R.id.searchteacher);
        teacher=new ArrayList<>();


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tid= Integer.parseInt(id.getText().toString());
               searchTeacher(tid);
            }
        });




       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              DeleteTeacher(tid);
            }
        });


    }



    void searchTeacher(int i)
    {
        String Displayteacher = "select * from Teacher where id='"+ i+"'"  ;
        Cursor c = db.rawQuery(Displayteacher, null);
        Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        while (c.moveToNext()) {
            String tid=c.getString(0);
            String name=c.getString(1);
            teacher.add("TeacherID  :   "+tid+"\nName      :    "+name);

            Toast.makeText(this, "teacher Data present "+teacher, Toast.LENGTH_SHORT).show();
        }
        ob=new ArrayAdapter<>(DeleteTeacher.this,android.R.layout.simple_list_item_1,teacher);
        lv.setAdapter(ob);
    }


    public void DeleteTeacher(int i){
        final String query="delete from Teacher where id='"+i+"'";

        android.support.v7.app.AlertDialog.Builder builder;

        builder = new android.support.v7.app.AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth);


        builder.setTitle("Confirmation !!")
                .setMessage("Are you sure you want to Delete Teacher?" + "\n" + teacher  )
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.execSQL(query);
                        ob.clear();
                        lv.setAdapter(ob);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    }


