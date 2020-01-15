package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Chat extends AppCompatActivity {

     Button  addroom;
     EditText room_name;

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list_of_rooms = new ArrayList<>();
    String name;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        addroom = findViewById(R.id.addroom);
        room_name = findViewById(R.id.room_name_edittext);
        listView =  findViewById(R.id.chatroomsview);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                    //Toast.makeText(Chat.this, ""+set, Toast.LENGTH_SHORT).show();
                }

                list_of_rooms.clear();
                list_of_rooms.addAll(set);
                arrayAdapter = new ArrayAdapter<String>(Chat.this,android.R.layout.simple_list_item_1,list_of_rooms);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        sp=getSharedPreferences("Option",0);
        name=sp.getString("teachername","");


addroom.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(room_name.getText().toString(),"");
        root.updateChildren(map);
    }
});

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),ChatRoom.class);
                intent.putExtra("room_name",((TextView)view).getText().toString() );
                intent.putExtra("user_name",name);
                intent.putExtra("page","teacher");
                startActivity(intent);
            }
        });


    }




}