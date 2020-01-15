package com.example.skanda.schoolmanagement;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ChatRoom  extends AppCompatActivity{

     Button btn_send_msg;
     EditText input_msg;
     TextView teachermsg;
     TextView studentmsg;
     String user_name,room_name,page;
     DatabaseReference root ;
    private String temp_key;
    SharedPreferences sp;
    ArrayList<String> names=new ArrayList();
    String db_name="Schoolmanagment";
    String tname="Teacher",dname;
    SQLiteDatabase db;
    LinearLayout sv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        sp=getSharedPreferences("Option",0);

        btn_send_msg = findViewById(R.id.btn_send);
        input_msg =  findViewById(R.id.msg_input);
        sv=findViewById(R.id.lchat);

        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);

        if(getIntent().getStringExtra("page").equals("teacher"))
        {

            user_name = sp.getString("teachername","");
            //Toast.makeText(this, ""+user_name, Toast.LENGTH_SHORT).show();
        }
        else
        {

            user_name = sp.getString("studentname","");
            //Toast.makeText(this, ""+user_name, Toast.LENGTH_SHORT).show();
        }

        room_name = getIntent().getExtras().get("room_name").toString();
        page=getIntent().getStringExtra("page");
        setTitle(" Room - "+room_name);

       root = FirebaseDatabase.getInstance().getReference().child(room_name);



        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(page.equals("teacher"))
                {
                    Map<String,Object> map = new HashMap<String, Object>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);

                    DatabaseReference message_root = root.child(temp_key);
                    Map<String,Object> map2 = new HashMap<String, Object>();
                    map2.put("name",user_name);
                    map2.put("msg",input_msg.getText().toString());

                    message_root.updateChildren(map2);
                    input_msg.setText("");
                }
                else
                {
                    Map<String,Object> map = new HashMap<String, Object>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);

                    DatabaseReference message_root = root.child(temp_key);
                    Map<String,Object> map2 = new HashMap<String, Object>();
                    map2.put("name",user_name);
                    map2.put("msg",input_msg.getText().toString());

                    message_root.updateChildren(map2);
                    input_msg.setText("");
                }


            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private String chat_msg,chat_user_name;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){

            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            getTdata();

          if (names.contains(chat_user_name))
            {
                ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
              TextView tv=new TextView(getApplicationContext());

              tv.setLayoutParams(lpView);


                LinearLayout.LayoutParams leftgravity = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                leftgravity.gravity=Gravity.LEFT;

                tv.setLayoutParams(leftgravity);
                tv.setTextSize(20);
                tv.setElegantTextHeight(true);
                tv.setPaddingRelative(4,2,4,2);
                tv.setBackgroundResource(R.drawable.wbutton);
                tv.setTextColor(Color.BLACK);
              tv.setText(chat_user_name +" : "+chat_msg);
              sv.addView(tv);


            }
            else
            {
                ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView tv=new TextView(getApplicationContext());

                tv.setLayoutParams(lpView);


                LinearLayout.LayoutParams rightgravity = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rightgravity.gravity=Gravity.RIGHT;

                tv.setLayoutParams(rightgravity);
                tv.setElegantTextHeight(true);
                tv.setTextSize(20);
                tv.setPaddingRelative(4,2,4,2);
                tv.setBackgroundResource(R.drawable.wbutton);

                tv.setText(chat_user_name +" : "+chat_msg);
                tv.setTextColor(Color.BLACK);
                sv.addView(tv);


                //studentmsg.append(chat_user_name+" : "+chat_msg +"\n");
            }

        }


    }

    void getTdata()
    {
        String Displayteacher = "select * from Teacher"  ;
        Cursor c = db.rawQuery(Displayteacher, null);
       // Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        while (c.moveToNext()) {
            dname=c.getString(1);
            names.add(dname);

        }
    }

    void getSdata()
    {
        String Displayteacher = "select * from Student"  ;
        Cursor c = db.rawQuery(Displayteacher, null);
        Toast.makeText(this, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        while (c.moveToNext()) {
            dname=c.getString(1);

            names.add(dname);
            //Toast.makeText(this, "teacher Data present "+teachers, Toast.LENGTH_SHORT).show();
        }
    }
}