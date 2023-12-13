package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et1,et2,et3;
    Button bt1,bt2,bt3,bt4;
    SQLiteDatabase db;
    String rollno,name,dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        bt3=findViewById(R.id.bt3);
        bt4=findViewById(R.id.bt4);
        DB dbHelp=new DB(this);
        db=dbHelp.getReadableDatabase();
        db=dbHelp.getWritableDatabase();
    }

    public void insert(View view) {
        rollno=et1.getText().toString();
        name=et2.getText().toString();
        dept=et3.getText().toString();
        if (rollno.isEmpty() || name.isEmpty() || dept.isEmpty()){
            Toast.makeText(this,"Cannot be empty",Toast.LENGTH_LONG).show();
        }
        else {
            ContentValues values=new ContentValues();
            values.put("rollno",rollno);
            values.put("name",name);
            values.put("dept",dept);
            db.insert("student",null,values);
            Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show();
        }
    }

    public void read(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor c=db.rawQuery("select * from student",null);
        while (c.moveToNext()){
            buffer.append("\n\n\n" +c.getString(0));
            buffer.append("\n\n\n"+c.getString(1));
            buffer.append("\n\n\n"+c.getString(2));
        }
        Toast.makeText(this,buffer.toString(),Toast.LENGTH_LONG).show();

    }

    public void update(View view) {
        rollno=et1.getText().toString();dept=et3.getText().toString();
        name=et2.getText().toString();

        ContentValues values=new ContentValues();
        values.put("rollno",rollno);
        values.put("name",name);
        values.put("dept",dept);
        db.update("student",values,"rollno="+rollno,null);
        Toast.makeText(this,"Updated Succesfully for roll no ="+rollno,Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {
        rollno=et1.getText().toString();
        db.delete("student","rollno="+rollno,null);
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }
}