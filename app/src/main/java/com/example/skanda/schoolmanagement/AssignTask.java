package com.example.skanda.schoolmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AssignTask extends AppCompatActivity {

    EditText ssd,eed,tle,desc,comm;

    TextView batch,name,id;
    Button addtask,up;
    DatePickerDialog.OnDateSetListener ob;
    Calendar myCalendar = Calendar.getInstance();
    SharedPreferences sp;
    SQLiteDatabase db;
    String i,n,b,t,s,e,d,c;
    String title,bat,nam,sid;
    int j=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        ssd=findViewById(R.id.start);
        eed=findViewById(R.id.end);
        up=findViewById(R.id.update);
        addtask=findViewById(R.id.addtask);
        batch=findViewById(R.id.taskbatch);
        name=findViewById(R.id.getname);
        id=findViewById(R.id.stuid);
        tle=findViewById(R.id.title);
        desc=findViewById(R.id.description);
        comm=findViewById(R.id.comments);


        sp=getSharedPreferences("Option",0);
        db=openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        batch.setText(sp.getString("batch","batch not found"));
        name.setText(sp.getString("name","not found"));
        selectID(sp.getString("name",null),sp.getString("batch","batch not found"));

        up.setVisibility(View.INVISIBLE);
        //dropTable();
        //createTask();

        j= getIntent().getIntExtra("flag",0);
        if(j==1)
        {
            up.setVisibility(View.VISIBLE);
            addtask.setVisibility(View.INVISIBLE);
            title = sp.getString("Title", "");
            bat=sp.getString("batch","");
            sid=sp.getString("id","");
            nam=sp.getString("name","");

            id.setText(sid);
            name.setText(nam);
            batch.setText(bat);
            tle.setText(title);
            tle.setEnabled(false);
            getdescription(title,nam,sid,bat);



        }





        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateTask(bat,nam,sid,title,ssd.getText().toString(),eed.getText().toString(),desc.getText().toString(),comm.getText().toString());
                Intent i=new Intent(AssignTask.this,AllotWork.class);
                startActivity(i);

            }
        });








        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=id.getText().toString();
                n=name.getText().toString();
                b=batch.getText().toString();
                t=tle.getText().toString();

                s=ssd.getText().toString();
                e=eed.getText().toString();
                d=desc.getText().toString();
                c=comm.getText().toString();
                addTask(i,n,b,t,s,e,d,c);
                Intent i=new Intent(AssignTask.this,AllotWork.class);
                startActivity(i);
                            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatesd();
            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateed();
            }

        };



        ssd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AssignTask.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        eed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AssignTask.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AllotWork.class);
        startActivity(i);
    }

    void updateed() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eed.setText(sdf.format(myCalendar.getTime()));
    }
    void updatesd() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ssd.setText(sdf.format(myCalendar.getTime()));
    }
    public void selectID(String name,String batch)
    {
        String query="select * from Student where name='"+ name +"' and batch='"+ batch +"'";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToNext())
        {
            String stdid=c.getString(0);
            id.setText(stdid);
        }
    }
    public void createTask()
    {

        String query="create table if not exists task (id int,name varchar(10),batch varchar(10),title varchar(20),startdate varchar(10),enddate varchar(10),submissiondate varchar(10),description varchar(50),comments varchar(50),filename varchar(50),remarks verchar(10),criteria varchar(10))";
        db.execSQL(query);

    }
    public void addTask(String id,String n,String b,String t,String sd,String ed,String d,String c)
    {
        Toast.makeText(this, ""+t, Toast.LENGTH_SHORT).show();
        String insertQuery="insert into task (id,name,batch,title,startdate,enddate,description,comments) values('"+id+"','"+n+"','"+b+"','"+t+"','"+sd+"','"+ed+"','"+d+"','"+c+"')";
        db.execSQL(insertQuery);
        Toast.makeText(this, "Task assigned", Toast.LENGTH_SHORT).show();
    }
    public void dropTable()
    {
        String query="drop table task";
        db.execSQL(query);
        Toast.makeText(this, "Table dropped", Toast.LENGTH_SHORT).show();
    }
    void getdescription(String t,String n,String i,String b)
    {
        String query="select * from task where title='"+t+"' and name='"+n+"' and id='"+i+"' and batch='"+b+"'";
        Cursor c=db.rawQuery(query,null);
        String sd="",ed="",des="",com="";
        while(c.moveToNext())
        {

            sd=c.getString(4).toString();
            ed=c.getString(5).toString();
            des=c.getString(7).toString();
            com=c.getString(8).toString();

        }
       ssd.setText(sd);
        eed.setText(ed);
        desc.setText(des);
        comm.setText(com);

    }
    public  void updateTask(String b,String n,String i,String t,String s,String e,String d,String c){


        String query="update task set startdate='"+s+"',enddate='"+e+"',description='"+d+"',comments='"+c+"' where id='"+i+"' and name='"+n+"' and batch='"+b+"' and title='"+t+"'";
        db.execSQL(query);
        Toast.makeText(this, "Task Updated sucessfully", Toast.LENGTH_SHORT).show();
    }


}
