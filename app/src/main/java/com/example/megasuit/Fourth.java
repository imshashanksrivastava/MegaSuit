package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
/*For online offline and otp login*/
public class Fourth extends AppCompatActivity {
    Button b7,b8,b10;
    TextToSpeech ts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);
        b7=(Button) findViewById(R.id.button7);
        b8=(Button) findViewById(R.id.button8);
        b10=(Button) findViewById(R.id.button10);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("For online login",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Fourth.this, Seven.class);
                startActivity(i);
                finish();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("For offline login",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Fourth.this, Eight.class);
                startActivity(i);
                finish();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("back to login page",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Fourth.this, Second.class);
                startActivity(i);
                finish();
            }
        });
    }
}