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

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;
/*Home page*/
public class Eleven extends AppCompatActivity {
    Button b22,b23,b25,b26;
    Button b28,b29,b31,b32, b34;
    FirebaseAuth firebaseAuth;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eleven);
        b22=(Button) findViewById(R.id.button22);
        b23=(Button) findViewById(R.id.button23);
        b25=(Button) findViewById(R.id.button25);
        b26=(Button) findViewById(R.id.button26);

        b28=(Button) findViewById(R.id.button28);
        b29=(Button) findViewById(R.id.button29);
        b31=(Button) findViewById(R.id.button31);
        b32=(Button) findViewById(R.id.button32);
        b34=(Button) findViewById(R.id.button34);
        firebaseAuth=FirebaseAuth.getInstance();
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Browse",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Thirteen.class);
                startActivity(i);
                finish();
            }
        });
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Text and call",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Fourteen.class);
                startActivity(i);
                finish();
            }
        });
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Calculate",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Sixteen.class);
                startActivity(i);
                finish();
            }
        });
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("music here",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Seventeen.class);
                startActivity(i);
                finish();
            }
        });
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Quick accessories",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Eighteen.class);
                startActivity(i);
                finish();
            }
        });
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Quiz",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,Ninteen.class);
                startActivity(i);
                finish();
            }
        });
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Showcase of our society...",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,TwentyOne.class);
                startActivity(i);
                finish();
            }
        });
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Manifest your text into voice",TextToSpeech.QUEUE_FLUSH,null);
                Intent i=new Intent(Eleven.this,TwentyTwo.class);
                startActivity(i);
                finish();
            }
        });

        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent i=new Intent(Eleven.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}