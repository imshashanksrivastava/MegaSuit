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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
/*for online register*/
public class Fifth extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    Button b11, b12;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fifth);
        b11=(Button) findViewById(R.id.button11);
        b12=(Button) findViewById(R.id.button12);
        e1 = (EditText) findViewById(R.id.editTextText);
        e2 = (EditText) findViewById(R.id.editTextText2);
        e3 = (EditText) findViewById(R.id.editTextText3);
        e4 = (EditText) findViewById(R.id.editTextText4);
        e3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });
        b12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ts.speak("back to register page",TextToSpeech.QUEUE_FLUSH,null);
                Intent i = new Intent(Fifth.this, Third.class);
                startActivity(i);
                finish();
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            @Override
            public void onClick(View view) {
                databaseReference = firebaseDatabase.getReference("Users");
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                if (s2.isEmpty()) {
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                    e2.setError("fill email");
                    return;
                } else {
                    if (s3.isEmpty()) {
                        if (vibrator != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(500); // For older devices
                            }
                        }
                        e3.setError("fill password");
                    } else {
                        firebaseAuth.createUserWithEmailAndPassword(s2, s3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (s4.length() != 10) {
                                        if (vibrator != null) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                                            } else {
                                                vibrator.vibrate(500); // For older devices
                                            }
                                        }
                                        ts.speak("Enter the correct number",TextToSpeech.QUEUE_FLUSH,null);
                                        Toast.makeText(Fifth.this, "Wrong number", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Users users = new Users(s1, s2, s3, s4);
                                        databaseReference.child(s4).setValue(users);
                                    }
                                    ts.speak("Successfully registered",TextToSpeech.QUEUE_FLUSH,null);
                                    Toast.makeText(Fifth.this, "database updated", Toast.LENGTH_SHORT).show();
                                    Intent j = new Intent(Fifth.this, Second.class);
                                    startActivity(j);
                                    finish();
                                } else {
                                    if (vibrator != null) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                                        } else {
                                            vibrator.vibrate(500); // For older devices
                                        }
                                    }
                                    ts.speak("Registration denied",TextToSpeech.QUEUE_FLUSH,null);
                                    Toast.makeText(Fifth.this, "database not updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}