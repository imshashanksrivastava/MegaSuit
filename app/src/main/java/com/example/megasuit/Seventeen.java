package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Seventeen extends AppCompatActivity {
    ImageButton ib1, ib2, ib3, ib4, ib5;
    Button b19, b20, b21, b22, b23, b24;
    MediaPlayer mp1, mp2, mp3, mp4, mp5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seventeen);

        // Initialize UI components
        ib1 = findViewById(R.id.imageButton2s);
        ib2 = findViewById(R.id.imageButton3s);
        ib3 = findViewById(R.id.imageButton4s);
        ib4 = findViewById(R.id.imageButton5s);
        ib5 = findViewById(R.id.imageButton6s);
        b19 = findViewById(R.id.button53s);
        b20 = findViewById(R.id.button54s);
        b21 = findViewById(R.id.button55s);
        b22 = findViewById(R.id.button56s);
        b23 = findViewById(R.id.button57s);
        b24 = findViewById(R.id.button58s);

        // Initialize MediaPlayers
        mp1 = MediaPlayer.create(this, R.raw.ma);
        mp2 = MediaPlayer.create(this, R.raw.mc);
        mp3 = MediaPlayer.create(this, R.raw.me);
        mp4 = MediaPlayer.create(this, R.raw.md);
        mp5 = MediaPlayer.create(this, R.raw.mb);

        // Set button listeners
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        b19.setOnClickListener(view -> toggleMediaPlayer(mp1, ib1));
        b20.setOnClickListener(view -> toggleMediaPlayer(mp2, ib2));
        b21.setOnClickListener(view -> toggleMediaPlayer(mp3, ib3));
        b22.setOnClickListener(view -> toggleMediaPlayer(mp4, ib4));
        b23.setOnClickListener(view -> toggleMediaPlayer(mp5, ib5));

        // Stop all players and navigate to another activity
        b24.setOnClickListener(view -> {
            stopAllMediaPlayers();
            Intent intent = new Intent(Seventeen.this, Eleven.class);
            startActivity(intent);
            finish();
        });
    }

    private void toggleMediaPlayer(MediaPlayer mediaPlayer, ImageButton imageButton) {
        if (mediaPlayer.isPlaying()) {
            // If the media player is playing, stop it and reset the image button
            mediaPlayer.pause();
            imageButton.setImageResource(R.drawable.off);
        } else {
            // Otherwise, stop all players and start the selected media player
            stopAllMediaPlayers();
            mediaPlayer.start();
            imageButton.setImageResource(R.drawable.on);
        }
    }

    private void stopAllMediaPlayers() {
        // Pause all media players
        if (mp1.isPlaying()) mp1.pause();
        if (mp2.isPlaying()) mp2.pause();
        if (mp3.isPlaying()) mp3.pause();
        if (mp4.isPlaying()) mp4.pause();
        if (mp5.isPlaying()) mp5.pause();

        // Reset all image button icons to "off"
        ib1.setImageResource(R.drawable.off);
        ib2.setImageResource(R.drawable.off);
        ib3.setImageResource(R.drawable.off);
        ib4.setImageResource(R.drawable.off);
        ib5.setImageResource(R.drawable.off);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release media player resources
        if (mp1 != null) mp1.release();
        if (mp2 != null) mp2.release();
        if (mp3 != null) mp3.release();
        if (mp4 != null) mp4.release();
        if (mp5 != null) mp5.release();
    }
}
