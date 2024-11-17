package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class TwentyOne extends AppCompatActivity {
    Button b7, b8, b9, b10, b11;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_twenty_one);
        b7 = (Button) findViewById(R.id.button46mm);
        b8 = (Button) findViewById(R.id.button47mm);
        b9 = (Button) findViewById(R.id.button48mm);
        b10 = (Button) findViewById(R.id.button49mm);
        b11 = (Button) findViewById(R.id.button50mm);
        ts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.9f);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Gave E equals m c square";
                ts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(TwentyOne.this, "E = mc^2", Toast.LENGTH_SHORT).show();
                return ;
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Pioneer of Artificial Intelligence";
                ts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(TwentyOne.this, s, Toast.LENGTH_SHORT).show();
                return;
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Founding father of USA";
                ts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(TwentyOne.this, s, Toast.LENGTH_SHORT).show();
                return;
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Reason behind Nobel Prizes";
                ts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(TwentyOne.this, s, Toast.LENGTH_SHORT).show();
                return;
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Back to index", TextToSpeech.QUEUE_FLUSH, null);
                Intent i = new Intent(TwentyOne.this, Eleven.class);
                startActivity(i);
                finish();
                return;
            }
        });
    }
}