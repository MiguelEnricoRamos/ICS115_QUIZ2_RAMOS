package com.ramos.ics115_quiz2_ramos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SUMMARY extends AppCompatActivity {

    private TextView sbj, comments;
    private Button send, prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        StringBuffer sbjs = new StringBuffer();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = openFileInput("data1.txt");
            int token;

            while ((token = fileInputStream.read()) != -1) {
                sbjs.append((char) token);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "IOException found", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sbj = findViewById(R.id.subjects);
        String str[] = sbjs.toString().split(",");
        for (int i = 0; i < str.length-1; i++) {
            sbj.append(str[i] + "\n");

        }

        comments = findViewById(R.id.comments);
        comments.append(str[str.length-1].replace("comment:",""));
        prev = findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevActivity();
            }
        });

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_LONG).show();
            }

        });



    }

    public void prevActivity() {
        Intent intent = new Intent(SUMMARY.this, MainActivity.class);
        startActivity(intent);
    }
}
