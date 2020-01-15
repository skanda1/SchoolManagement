package com.example.skanda.schoolmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class CorrectionAdapter extends ArrayAdapter<Task> {
    private Context mContext;
    private List<Task> taskList = new ArrayList<>();
    public static String remarks="",fname="";
    public static int pos;
SQLiteDatabase db;


    public CorrectionAdapter(@NonNull Context context, int tasklist, ArrayList<Task> list) {
        super(context, 0 , list);
        mContext = context;
        taskList = list;
        db=context.openOrCreateDatabase("Schoolmanagment", context.MODE_PRIVATE, null);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;


        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout3,parent,false);


        final Task currentStudent = taskList.get(position);




        final TextView filename = (TextView) listItem.findViewById(R.id.fname);
        filename.setText(currentStudent.getTitle());

        Button correct=(Button) listItem.findViewById(R.id.button8);

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                remarks="correct";
                String fname=filename.getText().toString();
updateRemarks(fname);
               Toast.makeText(mContext, "remarks updated at position :"+fname, Toast.LENGTH_SHORT).show();

            }
        });

        Button wrong=(Button) listItem.findViewById(R.id.button9);
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remarks="wrong";
                String fname=filename.getText().toString();
                updateRemarks(fname);
                Toast.makeText(mContext, "remarks updated at position :"+fname, Toast.LENGTH_SHORT).show();


            }
        });



        return listItem;
    }


    void updateRemarks(String fname)
    {
        String query="update task set remarks='"+CorrectionAdapter.remarks+"' where filename='"+fname+"'";
        db.execSQL(query);
        String g="";
        String q="select remarks from task where filename='"+fname+"'";
        Cursor c=db.rawQuery(q,null);
        while(c.moveToNext())
        {
            g=c.getString(0);
        }
        Log.i("remarks from database",g);
    }


}
