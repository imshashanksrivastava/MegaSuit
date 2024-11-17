package com.example.megasuit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/*Browse*/
public class Thirteen extends AppCompatActivity {
    EditText e14;
    Button b35, b36;
    WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirteen);
        e14 = findViewById(R.id.editTextText14);
        w1 = findViewById(R.id.web);
        b35 = findViewById(R.id.button35);
        b36 = findViewById(R.id.button36);

        b35.setOnClickListener(view -> {
            String url = e14.getText().toString();
            if (!url.isEmpty()) {
                w1.loadUrl(url);
            } else {
                e14.setError("Please enter a URL");
            }
        });

        b36.setOnClickListener(view -> {
            Intent i = new Intent(Thirteen.this, Eleven.class);
            startActivity(i);
            finish();
        });
    }
}
