package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddBatch extends AppCompatActivity {

    EditText ed;
    Button b;
    ArrayList<String> batch=new ArrayList<>();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);
        ed=findViewById(R.id.batchname);
        b=findViewById(R.id.ab);
        db=openOrCreateDatabase("Schoolmanagment",MODE_PRIVATE,null);

        createBatch();
        selectBatch();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bat=ed.getText().toString();
                if(batch.contains(bat))
                {
                    Toast.makeText(AddBatch.this, "Batch name already exists!!", Toast.LENGTH_SHORT).show();
                }
                else if(bat.equals(""))
                {
                    Toast.makeText(AddBatch.this, "Batch name cannot be null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //AdminHome.batch.add(bat);
                    addBatch(bat);
                    ed.setText("");
                    Toast.makeText(AddBatch.this, ""+Options.batch, Toast.LENGTH_SHORT).show();
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

    public void addBatch(String bat)
    {
        String query="insert into batch values ('"+bat+"')";
        db.execSQL(query);
        Toast.makeText(this, "batch inserted", Toast.LENGTH_SHORT).show();
    }

    public void createBatch()
    {
        String query="create table if not exists batch (name varchar(10) PRIMARY KEY)";
        db.execSQL(query);
        Toast.makeText(this, "Batch table created", Toast.LENGTH_SHORT).show();
    }

    public void selectBatch()
    {
        String query="select * from batch";
        Cursor c=db.rawQuery(query,null);
        while(c.moveToNext())
        {
            batch.add(c.getString(0))  ;
        }
    }
}
