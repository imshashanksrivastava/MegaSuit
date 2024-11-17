package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
/*For offline login*/
public class Eight extends AppCompatActivity {
    EditText e10,e11;
    Button b17,b18;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eight);
        e10 = (EditText) findViewById(R.id.editTextText10);
        e11 = (EditText) findViewById(R.id.editTextText11);
        e11.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b17 = (Button) findViewById(R.id.button17);
        b18 = (Button) findViewById(R.id.button18);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Back to login page",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Eight.this, Fourth.class);
                startActivity(i);
                finish();
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            @Override
            public void onClick(View view) {
                String s1 = e10.getText().toString().trim();
                String s2 = e11.getText().toString();
                if (s1.equals("") | s2.equals("")) {
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                    Toast.makeText(Eight.this, "Fill all.", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase data = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    data.execSQL("create table if not exists tab(name varchar, email varchar, password varchar)");
                    String query = "select * from tab where(email='" + s1 + "' and password='" + s2 + "')";
                    Cursor cursor = data.rawQuery(query, null);
                    if (cursor.getCount() > 0) {
                        ts.speak("Welcome to home page",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Eight.this, "Login successful.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Eight.this, Eleven.class);
                        startActivity(i);
                        finish();
                    } else {
                        if (vibrator != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(500); // For older devices
                            }
                        }
                        ts.speak("data mismatched,try again",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Eight.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}