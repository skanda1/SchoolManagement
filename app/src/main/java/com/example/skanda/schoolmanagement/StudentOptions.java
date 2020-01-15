package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentOptions extends AppCompatActivity {

    Button task,comment,chats,logout;
    SharedPreferences sp;
    SharedPreferences.Editor ob;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_options);
        task=findViewById(R.id.ViewTasks);
        comment=findViewById(R.id.comments);
        chats=findViewById(R.id.chats);
        logout=findViewById(R.id.lgot);
        sp=getSharedPreferences("Option",0);

        name=findViewById(R.id.textView18);
         String ename=sp.getString("studentname","");
         name.setText(ename);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentOptions.this,ViewStudentTask.class);
                startActivity(i);
            }
        });

        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentOptions.this,ViewChatRooms.class);
                i.putExtra("page","student");
                startActivity(i);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentOptions.this,StudentComments.class);
                startActivity(i);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentOptions.this,Options.class));
                finish();
            }
        });
    }
}
