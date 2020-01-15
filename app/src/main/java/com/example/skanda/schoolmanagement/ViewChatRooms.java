package com.example.skanda.schoolmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ViewChatRooms extends AppCompatActivity {



    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list_of_rooms = new ArrayList<>();
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    SharedPreferences sp;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chat_rooms);
        listView = (ListView) findViewById(R.id.viewrooms);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());

                }

                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter=new ArrayAdapter<>(ViewChatRooms.this,android.R.layout.simple_list_item_1,list_of_rooms);
                //Toast.makeText(ViewChatRooms.this, ""+list_of_rooms, Toast.LENGTH_SHORT).show();
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        sp=getSharedPreferences("Option",0);
        name=sp.getString("studentname","");

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),ChatRoom.class);
                intent.putExtra("room_name",((TextView)view).getText().toString() );
                intent.putExtra("user_name",name);
                intent.putExtra("page","student");
                startActivity(intent);
            }
        });

    }


}
