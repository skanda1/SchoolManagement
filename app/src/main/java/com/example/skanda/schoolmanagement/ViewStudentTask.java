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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStudentTask extends AppCompatActivity {
    ListView lst;
    //ArrayList<String> stdtasks=new ArrayList<>();
    String n;
    SharedPreferences sp;
    SharedPreferences.Editor ob;

    SQLiteDatabase db;
    String tname="tasks";
    Task ts;

    ArrayList<Task> tasks = new ArrayList<>();
    TaskAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_task);
        lst=findViewById(R.id.lv2);
        db=openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        sp=getSharedPreferences("Option",MODE_PRIVATE);

        ob=sp.edit();
        String name=sp.getString("studentname","");
        getTask(name);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=tasks.get(i).getTitle();
                ob.putString("Title",title);
                ob.commit();
                Toast.makeText(ViewStudentTask.this, ""+title, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ViewStudentTask.this,Upload.class);
                startActivity(intent);
            }
        });

        adapter = new TaskAdapter(this, R.layout.tasklist,tasks );

        lst.setAdapter(adapter);

        /*ArrayAdapter<String> adapter=new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,stdtasks);
        lst.setAdapter(adapter);
*/
    }




    void getTask(String n)
    {
        String query = "select * from task where name='"+n+"'";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {

            String title=c.getString(3);
            String startdate=c.getString(4);
            String enddate=c.getString(5);
            String des=c.getString(7);
           // String all="Title :" +title+"  startDate  :"+startdate+"\n EndDate  :"+enddate;


            tasks.add(new Task(title,startdate,enddate));

        }
        Toast.makeText(this, "tasks displayed ", Toast.LENGTH_SHORT).show();
    }
}
