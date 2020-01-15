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

public class CustomAdapter extends ArrayAdapter<Task>{




        private Context mContext;
        private List<Task> taskList = new ArrayList<>();

        public CustomAdapter(@NonNull Context context, int tasklist, ArrayList<Task> list) {
            super(context, 0 , list);
            mContext = context;
            taskList = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;

            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.layout2,parent,false);

            Task currentStudent = taskList.get(position);


            TextView title = (TextView) listItem.findViewById(R.id.tle);
            title.setText(currentStudent.getTitle());
            TextView startdate=listItem.findViewById(R.id.srtdte);
            startdate.setText(currentStudent.getStartdate());
            TextView enddate=listItem.findViewById(R.id.endte);
            enddate.setText(currentStudent.getEnddate());

            TextView submissiondate=listItem.findViewById(R.id.subdte);
            submissiondate.setText(currentStudent.getSubmissiondate());


            return listItem;
        }
    }


