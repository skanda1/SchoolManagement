package com.example.skanda.schoolmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilesAdapter extends ArrayAdapter<Task>{
    private Context mContext;
    private List<Task> taskList = new ArrayList<>();

    public FilesAdapter(@NonNull Context context, int tasklist, ArrayList<Task> list) {
        super(context, 0 , list);
        mContext = context;
        taskList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.files,parent,false);

        Task currentStudent = taskList.get(position);


        TextView title = (TextView) listItem.findViewById(R.id.textView42);
        title.setText(currentStudent.getTitle());
        TextView submitteddate=listItem.findViewById(R.id.textView44);
        submitteddate.setText(currentStudent.getSubmissiondate());





        return listItem;
    }
}
