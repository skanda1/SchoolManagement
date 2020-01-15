package com.example.skanda.schoolmanagement;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.internal.Utility;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import static com.idescout.sqlite.xray.protocol.response.TableInfo.f;

public class Upload extends AppCompatActivity {
SharedPreferences sp;
    SQLiteDatabase db;
    String tname="task";
    TextView question;
    Button upld,vw;
    String check;
    EditText fname,message;
    String subdates,criteriachoice;
    RadioButton rca,rcb,rcc;
    String displayName = null;
    String path=null;
    private static final int FILE_SELECT_CODE = 0;
    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);



        question = findViewById(R.id.description);
        upld = findViewById(R.id.upload);
        vw = findViewById(R.id.view);
        message=findViewById(R.id.msg);
        fname = findViewById(R.id.filename);
        rca=findViewById(R.id.Ca);
        rcb=findViewById(R.id.Cb);
        rcc=findViewById(R.id.Cc);

        sp = getSharedPreferences("Option", 0);
        final String title = sp.getString("Title", "");
        db = openOrCreateDatabase("Schoolmanagment", MODE_PRIVATE, null);
        getdescription(title);



        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    performFileSearch();
                }});


        upld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = fname.getText().toString();
                subdates= DateFormat.getDateTimeInstance().format(new Date());
                if(fileName.equals(""))
                {
                    Toast.makeText(Upload.this, "Please select a file to upload!!", Toast.LENGTH_SHORT).show();
                }

                else{

                    if(rca.isChecked())
                    {
                        criteriachoice="criteria A";
                        updatefilename(fileName, title, subdates, criteriachoice);
                        Toast.makeText(Upload.this, "file uploaded", Toast.LENGTH_SHORT).show();
                        fname.setText("");
                        rca.setChecked(false);


                    }
                    else if (rcb.isChecked())
                    {
                        criteriachoice="criteria B";

                        updatefilename(fileName, title, subdates, criteriachoice);
                        Toast.makeText(Upload.this, "file uploaded ", Toast.LENGTH_SHORT).show();
                        fname.setText("");
                        rcb.setChecked(false);

                    }
                    else if (rcc.isChecked())
                    {
                        criteriachoice="criteria C";
                        updatefilename(fileName, title, subdates, criteriachoice);
                        Toast.makeText(Upload.this, "file uploded", Toast.LENGTH_SHORT).show();
                        fname.setText("");
                        rcc.setChecked(false);

                    }
                    else {
                        Toast.makeText(Upload.this, "!! Criteria cannot be Blank.", Toast.LENGTH_SHORT).show();
                    }

                }
                insertPath(path,title);

            }


        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;

            if (resultData != null) {
                uri = resultData.getData();
                path=uri.getPath();
                Toast.makeText(this, ""+path, Toast.LENGTH_SHORT).show();
                dumpImageMetaData(uri);
                }}

        }


    public void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }


    public void dumpImageMetaData(Uri uri)
    {
        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        Cursor cursor = getApplicationContext().getContentResolver()
                .query(uri, null, null, null, null, null);

        try {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                //Toast.makeText(this, ""+displayName, Toast.LENGTH_SHORT).show();
                fname.setText(displayName);
            }

    } finally {
        cursor.close();
    }
}
    void updatefilename(String f,String ttl,String subdate,String cri)
    {
        String uploadquery="update task set filename='"+f+"',submissiondate='"+subdate+"',criteria='"+cri+"' where title='"+ttl+"' ";
        //Toast.makeText(this, "filename :"+f, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "subdate :"+subdate, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "title :"+ttl, Toast.LENGTH_SHORT).show();
       db.execSQL(uploadquery);

    }
    void getdescription(String d)
    {
       String query="select description from task where title='"+d+"'";
        Cursor c=db.rawQuery(query,null);
        String dquest="";
        while(c.moveToNext())
        {
            dquest=c.getString(0).toString();

        }
        question.setText(dquest);
    }

    public void insertPath(String p,String t)
    {
        String q="update task set filepath='"+p+"' where title='"+t+"'";
        db.execSQL(q);
    }


}
