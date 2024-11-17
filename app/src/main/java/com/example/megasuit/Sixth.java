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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
/*For offline register*/
public class Sixth extends AppCompatActivity {
    EditText e5, e6, e7;
    Button b13, b14;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sixth);
        b13 = (Button) findViewById(R.id.button13);
        b14 = (Button) findViewById(R.id.button14);
        e5 = (EditText) findViewById(R.id.editTextText5);
        e6 = (EditText) findViewById(R.id.editTextText6);
        e7 = (EditText) findViewById(R.id.editTextText7);
        e7.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            @Override
            public void onClick(View view) {
                String s1 = e5.getText().toString();
                String s2 = e6.getText().toString().trim();
                String s3 = e7.getText().toString();
                if (s1.equals("") | s2.equals("") | s3.equals("")) {
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                    ts.speak("Please fill all the above",TextToSpeech.QUEUE_FLUSH,null);
                    Toast.makeText(Sixth.this, "Please fill all.", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase data = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    data.execSQL("create table if not exists tab(name varchar, email varchar, password varchar)");
                    String query = "select * from tab where(name='" + s1 + "' and email='" + s2 + "')";
                    Cursor cursor = data.rawQuery(query, null);
                    if (cursor.getCount() > 0) {
                        if (vibrator != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(500); // For older devices
                            }
                        }
                        ts.speak("your id exist",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Sixth.this, "User already registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        data.execSQL("insert into tab values('" + s1 + "', '" + s2 + "', '" + s3 +"')");
                        ts.speak("registration successful",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(Sixth.this, "Database updated.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Sixth.this, Second.class);
                        startActivity(i);
                        finish();
                    }

                }
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("back to register page",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Sixth.this, Third.class);
                startActivity(i);
                finish();
            }
        });
    }
}