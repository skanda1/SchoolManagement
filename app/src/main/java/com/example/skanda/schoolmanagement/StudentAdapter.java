package com.example.skanda.schoolmanagement;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skanda.schoolmanagement.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    private List<Student> studentList = new ArrayList<>();

    public StudentAdapter(@NonNull Context context,  ArrayList<Student> list) {
        super(context, 0 , list);
        mContext = context;
        studentList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.studentlist,parent,false);

        Student currentStudent = studentList.get(position);



       // TextView id = (TextView) listItem.findViewById(R.id.id);
        //id.setText(currentStudent.getId());

        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentStudent.getName());

        return listItem;
    }
}