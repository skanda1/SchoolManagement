package com.example.skanda.schoolmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText name,password,phone,email,address,fname,mname;
    Spinner std,sec,batch;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname="Student";
    String student,pass,mail,stand,sect,add,call,Fname,Mname,bat;
    ArrayAdapter<String> ob;
    Button hide,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name = findViewById(R.id.editText2);
        password = findViewById(R.id.editText4);
        hide = findViewById(R.id.button2);
        register=findViewById(R.id.button4);
        std=findViewById(R.id.spinner);
        sec=findViewById(R.id.spinner2);
        phone=findViewById(R.id.editText6);
        email=findViewById(R.id.editText7);
        address=findViewById(R.id.editText8);
        fname=findViewById(R.id.editText5);
        mname=findViewById(R.id.editText9);
        batch=findViewById(R.id.stdbatch);
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        ob=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,AdminHome.batch);
        batch.setAdapter(ob);
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";





        if (db != null)

        {
            //Toast.makeText(this, "Database is created ", Toast.LENGTH_SHORT).show();
            CreateTable();
        }
        else
        {
            //Toast.makeText(this, "not created ", Toast.LENGTH_SHORT).show();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student=name.getText().toString().trim();
                pass=password.getText().toString().trim();
                call=phone.getText().toString().trim();
                Mname=mname.getText().toString().trim();
                Fname=fname.getText().toString().trim();
                bat=batch.getSelectedItem().toString();

                mail=email.getText().toString().trim();
                add=address.getText().toString().trim();
                stand=std.getSelectedItem().toString();
                sect=sec.getSelectedItem().toString();

                if(call.length()!=10)
                {
                    Toast.makeText(AddStudent.this, "Enter a valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }


                else
                {
                    if(mail.matches(emailPattern))
                    {
                        AlertDialog.Builder builder;

                        builder = new AlertDialog.Builder(AddStudent.this, android.R.style.Theme_Material_Light_DarkActionBar);


                        builder.setTitle("Confirmation !!")
                                .setMessage("Are you sure you want to Confirm this entry?" + "\n" + student + "\n" + stand + "\n" + bat + "\n" + sect + "\n"+ call+ "\n" )
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        addStudent();
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
                    else {

                        Toast.makeText(AddStudent.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                    }
                }





            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
    }

    void CreateTable()
    {
        String query="create table if not exists "+tname+" (id INTEGER PRIMARY KEY AUTOINCREMENT  ,name varchar(20) PRIMARY KEY,password varchar(20),batch varchar(10),standard varchar(30),section varchar(30),fathername varchar(40),mothername varchar(40),phone int,email varchar(30),address varchar(40))";
        db.execSQL(query);
        Toast.makeText(this, "Table Created ", Toast.LENGTH_SHORT).show();
    }

    public void addStudent()
    {

        String insertQuery="insert into "+tname+" (name,password,batch,standard,section,fathername,mothername,phone,email,address) values('"+student+"','"+pass+"','"+bat+"','"+stand+"','"+sect+"','"+Fname+"','"+Mname+"','"+call+"','"+mail+"','"+add+"')";
        db.execSQL(insertQuery);
        Intent i=new Intent(AddStudent.this,AdminHome.class);
        startActivity(i);
        Toast.makeText(this, "Data inserted ", Toast.LENGTH_SHORT).show();

    }

   /* public void createchattables()
    {
        student=name.getText().toString().trim();
        String query="create table if not exists"+student+"(user varchar(10),comments varchar(50))";
        db.execSQL(query);
    }*/

 }
