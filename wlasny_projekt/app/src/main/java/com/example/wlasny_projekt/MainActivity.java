package com.example.wlasny_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView displayInteger = (TextView) findViewById(R.id.textViewPoints);
        Button save = (Button) findViewById(R.id.buttonSave);
        Button load = (Button) findViewById(R.id.buttonLoad);
        Button add = (Button) findViewById(R.id.buttonAdd);
        Context context = this;
        MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        add.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               model.addNumber();
               displayInteger.setText(model.number);
           }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "file.txt";
                File file = new File(context.getFilesDir(), filename);
                int score = number;
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(score);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int number = 0;
                    String filename = "file.txt";
                    File file = new File(context.getFilesDir(), filename);
                    Scanner sc = new Scanner(file);
                    while(sc.hasNext()){
                        number += sc.nextInt();
                    }
                    displayInteger.setText(""+number);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}