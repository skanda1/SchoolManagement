package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TeacherOptions extends AppCompatActivity {

    Spinner sp;
    Button p,e,r,l,c;
    ArrayAdapter<String> ob;
    SharedPreferences s;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_options);
        sp=findViewById(R.id.projectbatch);
        p=findViewById(R.id.projectwork);
        e=findViewById(R.id.evaluate);
        r=findViewById(R.id.reports);
        c=findViewById(R.id.chat);
        s=getSharedPreferences("Option",0);
        ed=s.edit();
        l=findViewById(R.id.lo);



        ob=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Options.batch);
        sp.setAdapter(ob);


        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(TeacherOptions.this,Reports.class);
                startActivity(i);
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String batch=sp.getSelectedItem().toString();
                ed.putString("batch",batch);
                ed.commit();
                Toast.makeText(TeacherOptions.this, ""+s.getString("batch",""), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherOptions.this,AllotWork.class);
                startActivity(i);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TeacherOptions.this,Options.class);
                startActivity(i);
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String batch=sp.getSelectedItem().toString();
                ed.putString("batch",batch);
                ed.commit();
                Toast.makeText(TeacherOptions.this, ""+s.getString("batch",""), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherOptions.this,Evaluate.class);
                startActivity(i);

            }
        });

      c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TeacherOptions.this,Chat.class);
                startActivity(i);
            }
        });

    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,TeacherOptions.class);
        startActivity(i);
    }
}
