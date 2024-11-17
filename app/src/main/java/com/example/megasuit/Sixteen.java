package com.example.megasuit;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sixteen extends AppCompatActivity {
    EditText e17,e18;
    TextView t5;
    Button b43,b44,b45,b46,b47;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sixteen);
        e17=(EditText)findViewById(R.id.editTextText17);
        e18=(EditText)findViewById(R.id.editTextText18);
        t5=(TextView)findViewById(R.id.textView5);
        b43=(Button)findViewById(R.id.button43);
        b44=(Button)findViewById(R.id.button44);
        b45=(Button)findViewById(R.id.button45);
        b46=(Button)findViewById(R.id.button46);
        b47=(Button)findViewById(R.id.button47);
        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= e17.getText().toString();
                String s2= e18.getText().toString();
                Float f1=Float.parseFloat(s1);
                Float f2=Float.parseFloat(s2);
                Float f3=f1+f2;
                String s3=Float.toString(f3);
                t5.setText(s3);
                Toast.makeText(Sixteen.this, "Addition is-"+s3, Toast.LENGTH_SHORT).show();
            }
        });
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= e17.getText().toString();
                String s2= e18.getText().toString();
                Float f1=Float.parseFloat(s1);
                Float f2=Float.parseFloat(s2);
                Float f3=f1-f2;
                String s3=Float.toString(f3);
                t5.setText(s3);
                Toast.makeText(Sixteen.this, "Subtraction is-"+s3, Toast.LENGTH_SHORT).show();
            }
        });
        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= e17.getText().toString();
                String s2= e18.getText().toString();
                Float f1=Float.parseFloat(s1);
                Float f2=Float.parseFloat(s2);
                Float f3=f1*f2;
                String s3=Float.toString(f3);
                t5.setText(s3);
                Toast.makeText(Sixteen.this, "Multiplication is-"+s3, Toast.LENGTH_SHORT).show();
            }
        });
        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= e17.getText().toString();
                String s2= e18.getText().toString();
                Float f1=Float.parseFloat(s1);
                Float f2=Float.parseFloat(s2);
                Float f3=f1/f2;
                String s3=Float.toString(f3);
                t5.setText(s3);
                Toast.makeText(Sixteen.this, "Division is-"+s3, Toast.LENGTH_SHORT).show();
            }
        });
        b47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Sixteen.this,Eleven.class);
                startActivity(i);
                finish();
            }
        });
    }
}