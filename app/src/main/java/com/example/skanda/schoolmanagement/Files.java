package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Files extends AppCompatActivity {
    SharedPreferences sp;
    SQLiteDatabase db;
    TextView batch,name,id;
    ArrayList<Task> tasks = new ArrayList<>();
    FilesAdapter adapter;
    ListView lst;
    String stdid;
    SharedPreferences.Editor ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        batch=findViewById(R.id.taskbatch2);
        name=findViewById(R.id.getname2);
        id=findViewById(R.id.stuid2);
        lst=findViewById(R.id.tsklst);


        sp=getSharedPreferences("Option",MODE_PRIVATE);

        ob=sp.edit();

        db=openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        batch.setText(sp.getString("batch","batch not found"));
        name.setText(sp.getString("name","not found"));
        selectID(sp.getString("name",null),sp.getString("batch","batch not found"));

        getTask(name.getText().toString());

        adapter = new FilesAdapter(this, R.layout.files,tasks );

        lst.setAdapter(adapter);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=tasks.get(i).getTitle();
                ob.putString("Title",title);
                ob.putString("name",name.getText().toString());
                ob.putString("batch",batch.getText().toString());
                ob.putString("id",id.getText().toString());
                ob.commit();
                Intent intent=new Intent(Files.this,TeacherFileViewStudentfile.class);
                intent.putExtra("flag",1);
                startActivity(intent);

            }
        });


    }


    public void selectID(String name,String batch)
    {
        String query="select * from Student where name='"+ name +"' and batch='"+ batch +"'";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToNext())
        {
            stdid=c.getString(0);
            id.setText(stdid);
        }
    }

    void getTask(String n)
    {
        String query = "select * from task where name='"+n+"'";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {

            String title=c.getString(3);
            //String startdate=c.getString(4);
            //String enddate=c.getString(5);
            String sub=c.getString(6);

            // String all="Title :" +title+"  startDate  :"+startdate+"\n EndDate  :"+enddate;


            tasks.add(new Task(title,sub));

        }


        //Toast.makeText(this, "tasks displayed ", Toast.LENGTH_SHORT).show();


    }

}
