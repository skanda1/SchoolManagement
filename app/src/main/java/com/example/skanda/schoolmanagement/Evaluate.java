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

public class Evaluate extends AppCompatActivity {
    Spinner s1,s2;
    Button b;
    ListView lv;
    SharedPreferences sp,my;
    SharedPreferences.Editor ob;
    ArrayList<String> student = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String teacher,std,sec,dname,id,bat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        s1=findViewById(R.id.spinner3);
        s2=findViewById(R.id.spinner4);
        b=findViewById(R.id.button7);
        lv=findViewById(R.id.elv);

        my=getSharedPreferences("Option",0);
        ob=my.edit();
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter =new ArrayAdapter<String>(Evaluate.this,R.layout.eval,R.id.textView51,student);

                adapter.clear();

                std = s1.getSelectedItem().toString();
                sec = s2.getSelectedItem().toString();
                bat=my.getString("batch","");
                getSData(std, sec,bat);
                lv.setAdapter(adapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=  lv.getItemAtPosition(i).toString();
                ob.putString("name",name);
                ob.commit();
                Toast.makeText(Evaluate.this, ""+my.getString("name",""), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Evaluate.this,Correction.class);
                startActivity(intent);
            }
        });


    }

    void getSData(String n,String p,String b)
    {
        String Displayproduct = "select * from Student where standard='"+ n +"' and section='"+p+"' and batch='"+b+"'";


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
