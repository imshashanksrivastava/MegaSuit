package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
/*For online login*/
public class Seven extends AppCompatActivity {
    EditText e8,e9;
    Button b15,b16;
    FirebaseAuth firebaseAuth;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seven);
        b15 = (Button) findViewById(R.id.button15);
        b16 = (Button) findViewById(R.id.button16);
        e8 = (EditText) findViewById(R.id.editTextText8);
        e9 = (EditText) findViewById(R.id.editTextText9);
        e9.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        firebaseAuth = FirebaseAuth.getInstance();
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("back to login page",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Seven.this, Fourth.class);
                startActivity(i);
                finish();
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            @Override
            public void onClick(View view) {
                String s1 = e8.getText().toString().trim();
                String s2 = e9.getText().toString();
                if (s1.isEmpty()) {
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                    e8.setError("Fill email");
                    return;
                } else {
                    if (s2.isEmpty()) {
                        if (vibrator != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(500); // For older devices
                            }
                        }
                        e9.setError("Fill password");
                        return;
                    } else {
                        firebaseAuth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    ts.speak("successfully logged In",TextToSpeech.QUEUE_FLUSH,null);
                                    Toast.makeText(Seven.this, "Logged in", Toast.LENGTH_SHORT).show();
                                    Intent k = new Intent(Seven.this, Eleven.class);
                                    startActivity(k);
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
                                    Toast.makeText(Seven.this, "Mismatched", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }

            }
        });
    }
}