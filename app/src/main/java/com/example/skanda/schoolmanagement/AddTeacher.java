package com.example.skanda.schoolmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddTeacher extends AppCompatActivity {

    EditText name,password,phone,email,address;
    Spinner std,sec,bat;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname="Teacher";
    String teacher,pass,mail,stand,sect,add,call,Batch;

    Button hide,register;
    //ArrayList<String> batch;
    ArrayAdapter<String> ob;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        name = findViewById(R.id.editText2);
        password = findViewById(R.id.editText4);
        hide = findViewById(R.id.button2);
        register=findViewById(R.id.button4);
        std=findViewById(R.id.spinner);
        sec=findViewById(R.id.spinner2);
        phone=findViewById(R.id.editText6);
        email=findViewById(R.id.editText7);
        address=findViewById(R.id.editText8);
        bat=findViewById(R.id.tbatch);
        ob=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,AdminHome.batch);
        bat.setAdapter(ob);
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";






        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacher=name.getText().toString().trim();
                pass=password.getText().toString().trim();
                call=phone.getText().toString().trim();
                Batch=bat.getSelectedItem().toString();
                mail=email.getText().toString().trim();
                add=address.getText().toString().trim();
                sect=sec.getSelectedItem().toString();
                stand=std.getSelectedItem().toString();
                if(call.length()!=10)
                {
                    Toast.makeText(AddTeacher.this, "Enter a valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if(mail.matches(emailPattern)) {


                        AlertDialog.Builder builder;

                        builder = new AlertDialog.Builder(AddTeacher.this, android.R.style.Theme_Material_Light_DarkActionBar);


                        builder.setTitle("Confirmation !!")
                                .setMessage("Are you sure you want to Confirm this entry?" + "\n" + teacher + "\n" + stand + "\n" + sect + "\n" + call + "\n")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        addTeacher();
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
                        Toast.makeText(AddTeacher.this, "Enter a valid Email Address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
    }



    public void addTeacher()
    {

        String insertQuery="insert into "+tname+" (name,password,batch,standard,section,phone,email,address) values('"+teacher+"','"+pass+"','"+Batch+"','"+stand+"','"+sect+"','"+call+"','"+mail+"','"+add+"')";
        db.execSQL(insertQuery);
        Intent i=new Intent(AddTeacher.this,AdminHome.class);
        startActivity(i);
        Toast.makeText(this, "Data Saved ", Toast.LENGTH_SHORT).show();

    }

}
