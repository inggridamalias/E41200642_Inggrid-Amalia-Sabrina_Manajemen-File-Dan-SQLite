package com.example.manajemenfile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    TextView showText;
    Button buttonPrivate, buttonPublic, buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showText = (TextView) findViewById(R.id.getText);
        buttonPrivate = findViewById(R.id.buttonPrivate);
        buttonPublic = findViewById(R.id.buttonPublic);
        buttonBack = findViewById(R.id.buttonback);

        buttonPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPrivate(view);
            }
        });

        buttonPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPublic(view);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back(view);
            }
        });
    }

    public void back(View view){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void getPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //Folder Name //Folder Name
        File myFile = new File(folder, "myData1.txt"); // Filename
        String text = getdata(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("No Data");
        }
    }

    public void getPrivate(View view){
        File folder = getExternalFilesDir("inggrid"); //Folder Name
        File myFile = new File(folder, "myData2.txt"); // Filename
        String text = getdata(myFile);
        if (text != null){
            showText.setText(text);
        } else {
            showText.setText("No Data");
        }
    }

    private String getdata(File myFile){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1){
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
