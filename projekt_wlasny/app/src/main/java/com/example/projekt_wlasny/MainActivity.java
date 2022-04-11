package com.example.projekt_wlasny;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
//    MainActivityViewModel model;
    double kg = 0;
    double cm = 0;
    double bmi = 0;
    double bmi_show = 0;
    int obraz = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
//        model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textViewBMI = (TextView) findViewById(R.id.textViewBMI);
        TextView textViewBMIresult = (TextView) findViewById(R.id.textViewBMIresult);
        TextView textViewCM = (TextView) findViewById(R.id.textViewSeekBarCM);
        TextView textViewKG = (TextView) findViewById(R.id.textViewSeekBarKG);
        SeekBar seekBarCM = (SeekBar) findViewById(R.id.seekBarCM);
        SeekBar seekBarKG = (SeekBar) findViewById(R.id.seekBarKG);
        Button buttonOblicz = (Button) findViewById(R.id.buttonOblicz);
        Button buttonWczyt = (Button) findViewById(R.id.buttonWczyt);
        Button buttonZapis = (Button) findViewById(R.id.buttonZapis);

        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmi = (double) (kg / ((cm * cm) / 100) * 100);
                bmi_show = Math.round(bmi * 100.0) / 100.0;
                textViewBMIresult.setText("" + bmi_show);

                if (bmi_show <= 18) {
                    textViewBMI.setText("Niedowaga");
                    imageView.setImageResource(R.drawable.jasnyzielen);
                    obraz = 0;
                } else if (bmi_show <= 23) {
                    textViewBMI.setText("Waga prawidlowa");
                    imageView.setImageResource(R.drawable.zielony);
                    obraz = 1;
                } else if (bmi_show <= 27) {
                    textViewBMI.setText("Nadwaga");
                    imageView.setImageResource(R.drawable.morski);
                    obraz = 2;
                } else if (bmi_show <= 32) {
                    textViewBMI.setText("Otylosc I stopnia");
                    imageView.setImageResource(R.drawable.szary);
                    obraz = 3;
                } else if (bmi_show <= 37) {
                    textViewBMI.setText("Otylosc II stopnia");
                    imageView.setImageResource(R.drawable.brazowy);
                    obraz = 4;
                } else {
                    textViewBMI.setText("Otylosc skrajna");
                    imageView.setImageResource(R.drawable.czerwony);
                    obraz = 5;
                }
            }
        });
        buttonZapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Zapisano do pliku", Toast.LENGTH_SHORT).show();
                try {
                    FileWriter fw = new FileWriter("data");
                    fw.write(textViewBMI + ";" + textViewBMIresult + ";" + obraz);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonWczyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Wczytano z pliku", Toast.LENGTH_SHORT).show();
                try {
                    String text = "";
                    String filename = "data";
                    File file = new File(context.getFilesDir(), filename);
                    Scanner sc = new Scanner(file);
                    while(sc.hasNext()){
                        text += sc.nextLine();
                    }
                    String[] dane = text.split(";");
                    textViewBMI.setText(dane[0]);
                    textViewBMIresult.setText(dane[1]);
                    switch (dane[2]){
                        case "0":
                            imageView.setImageResource(R.drawable.jasnyzielen);
                            break;
                        case "1":
                            imageView.setImageResource(R.drawable.zielony);
                            break;
                        case "2":
                            imageView.setImageResource(R.drawable.morski);
                            break;
                        case "3":
                            imageView.setImageResource(R.drawable.szary);
                            break;
                        case "4":
                            imageView.setImageResource(R.drawable.brazowy);
                            break;
                        case "5":
                            imageView.setImageResource(R.drawable.czerwony);
                            break;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        seekBarCM.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textViewCM.setText("" + progress);
                cm = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarKG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textViewKG.setText("" + progress);
                kg = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}