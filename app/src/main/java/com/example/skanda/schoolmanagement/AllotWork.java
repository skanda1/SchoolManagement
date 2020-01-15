package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllotWork extends AppCompatActivity {

    Button at,vt,cmts,files,back;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allot_work);
        at=findViewById(R.id.at);
        vt=findViewById(R.id.vt);
        cmts=findViewById(R.id.comments);
        sp=getSharedPreferences("Option",0);
        back=findViewById(R.id.bck);
        files=findViewById(R.id.files);


        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AllotWork.this,TeacherViewFiles.class);
                startActivity(i);
            }
        });


        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob=new Intent(AllotWork.this,ProjectWork.class);
                startActivity(ob);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AllotWork.this,TeacherOptions.class);
                startActivity(i);
            }
        });

        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(AllotWork.this, "View Tasks clicked", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(AllotWork.this,TeacherViewTaskList.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,TeacherOptions.class);
        startActivity(i);
    }
}
