package com.example.skanda.schoolmanagement;

import android.content.DialogInterface;
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

public class DeleteStudent extends AppCompatActivity {

    SQLiteDatabase db;
    EditText id;
    Button s;
    ArrayList<String> student=new ArrayList<>();
    ArrayAdapter<String> ob;
    ListView lv;
    int tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        db=openOrCreateDatabase("Schoolmanagment",MODE_PRIVATE,null);
       id=findViewById(R.id.searchstudentid);
       s=findViewById(R.id.search);
       lv=findViewById(R.id.searchstudent);


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tid= Integer.parseInt(id.getText().toString());
                SearchStudent(tid);



            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeleteStudent(tid);
            }
        });


    }
    public void SearchStudent(int i){
        String query="select * from Student where id='"+i+"'";
        Cursor c=db.rawQuery(query,null);
        Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        if(c.moveToNext())
        {
            String tid=c.getString(0);
            String name=c.getString(1);
            student.add("StudentID  :   "+tid+"\nName      :    "+name);
            ob=new ArrayAdapter<>(DeleteStudent.this,android.R.layout.simple_list_item_1,student);
            lv.setAdapter(ob);
        }
        else{
            Toast.makeText(this, "No Student Exist with this ID", Toast.LENGTH_SHORT).show();
        }
    }


    public void DeleteStudent(int i){
        final String query="delete from Student where id='"+i+"'";

        android.support.v7.app.AlertDialog.Builder builder;

        builder = new android.support.v7.app.AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth);


        builder.setTitle("Confirmation !!")
                .setMessage("Are you sure you want to Delete Student?" + "\n" + student  )
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

