package com.ramos.ics115_quiz2_ramos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText comment;
    private CheckBox CB1,CB2,CB3,CB4,CB5,CB6,CB7,CB8;
    private Button save, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CB1 = findViewById(R.id.checkBox);
        CB2 = findViewById(R.id.checkBox2);
        CB3 = findViewById(R.id.checkBox3);
        CB4 = findViewById(R.id.checkBox4);
        CB5 = findViewById(R.id.checkBox5);
        CB6 = findViewById(R.id.checkBox6);
        CB7 = findViewById(R.id.checkBox7);
        CB8 = findViewById(R.id.checkBox8);

        final CheckBox[] checkBoxes = {CB1,CB2,CB3,CB4,CB5,CB6,CB7,CB8};

        comment = findViewById(R.id.comment);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer data = new StringBuffer();

                for (CheckBox chkbx: checkBoxes){
                    if (chkbx.isChecked()){
                        data.append(chkbx.getText().toString() + ",");
                    }
                }

                data.append("comment:"+comment.getText().toString());

                FileOutputStream writer = null;
                try {
                    writer = openFileOutput("data1.txt", MODE_PRIVATE);
                    writer.write(data.toString().getBytes());
                    Log.d("sad",data.toString());
                    Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this, "File not found.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "IO Exception", Toast.LENGTH_SHORT).show();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    public void nextActivity(){
        Intent intent = new Intent(this, SUMMARY.class);
        startActivity(intent);
    }}
