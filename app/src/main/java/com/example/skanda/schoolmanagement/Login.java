package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor ob;
    String item,ename,epass;
    EditText name,password;
    SQLiteDatabase db;
    String db_name="Schoolmanagment";
    String tname="Teacher";
    String tname1="Student";
    Button login,hide;
    String dname,dpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("Option",0);
        item=sp.getString("User","");
        ob=sp.edit();
        Toast.makeText(this, ""+item, Toast.LENGTH_SHORT).show();

        login=findViewById(R.id.button);
        name=findViewById(R.id.editText);
        password=findViewById(R.id.editText3);
        hide=findViewById(R.id.button3);
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ename = name.getText().toString();
                epass = password.getText().toString();
                if(ename.equals("") && epass.equals(""))
                {
                    Toast.makeText(Login.this, "Username and Password cannot be null", Toast.LENGTH_SHORT).show();
                }
                else if(ename.equals(""))
                {
                    Toast.makeText(Login.this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (item.equals("admin")) {

                        if (ename.equals("admin") && epass.equals("admin")) {
                            Intent i = new Intent(Login.this, AdminHome.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please RE-Check Your Credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (item.equals("teacher")) {
                        validateData(ename, epass);

                        if (dname.equals(ename) && dpass.equals(epass)) {
                            ob.putString("teachername", ename);
                            ob.commit();
                            Intent intent = new Intent(Login.this, TeacherOptions.class);
                            startActivity(intent);
                            //Toast.makeText(Login.this, "Teacher login entered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Please RE-Check Your Credentials..", Toast.LENGTH_SHORT).show();
                        }
                    } else if (item.equals("student")) {
                        validateSData(ename, epass);
                        if (dname.equals(ename) && dpass.equals(epass)) {
                            ob.putString("studentname", ename);
                            ob.commit();
                            //Toast.makeText(Login.this, "Student login entered", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, StudentOptions.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please RE-Check Your Credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        }



    void validateData(String n,String p)  {


        String Displayproduct = "select name,password from " + tname +" where name='"+ n+"' and password='"+p+"'";

        Cursor c = db.rawQuery(Displayproduct, null);


        if(c.moveToNext()) {

             dname = c.getString(0);
             dpass=c.getString(1);

        }
        else{
            Toast.makeText(this, "User does not exit", Toast.LENGTH_SHORT).show();
        }



    }

    void validateSData(String n,String p)
    {
        String Displaystudent = "select * from " + tname1 +" where name='"+ n +"' and password='"+p+"'";

        Cursor c = db.rawQuery(Displaystudent, null);

       if(c.moveToNext())
        {


            dname = c.getString(1);
            dpass=c.getString(2);


        }
        else{
           Toast.makeText(this, "User does not exit", Toast.LENGTH_SHORT).show();
       }


        Toast.makeText(this, "Student Data present ", Toast.LENGTH_SHORT).show();
    }
}


