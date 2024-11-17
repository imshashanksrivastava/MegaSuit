package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class TwentyTwo extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private EditText editText;
    private Button buttonSpeak;
    Button back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_two);

        // Initializing the TextToSpeech instance
        textToSpeech = new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int languageResult = textToSpeech.setLanguage(Locale.US);
                    if (languageResult == TextToSpeech.LANG_MISSING_DATA |
                            languageResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle the error if the language is not supported
                    }
                }
            }
        });

        // Initialize Views
        TextView textView = findViewById(R.id.textView6tv);
        editText = findViewById(R.id.editTextText14tv);
        buttonSpeak = findViewById(R.id.button51tv);
        back = (Button) findViewById(R.id.button52tv);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TwentyTwo.this, Eleven.class);
                startActivity(i);
                finish();
                return;
            }
        });

        // Button Click Listener to trigger text-to-speech
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!text.isEmpty()) {
                    // Speak the text entered
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    // Optional: Handle case if no text is entered
                    editText.setError("Please enter text to speak!");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
