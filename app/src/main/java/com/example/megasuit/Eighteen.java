package com.example.megasuit;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.os.Build;
import android.provider.Settings;

import java.util.Locale;

public class Eighteen extends AppCompatActivity {
    ImageButton ib7, ib8, ib9;
    Button b26;
    TextToSpeech ts;
    WifiManager wm;
    BluetoothAdapter ba;
    CameraManager cm;
    private static final int REQUEST_ENABLE_BT = 1; // Declare as a constant
    private boolean wifi = false, blue = false, camera = false;

    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eighteen);

        ib7 = findViewById(R.id.imageButton7qs);
        ib8 = findViewById(R.id.imageButton8qs);
        ib9 = findViewById(R.id.imageButton9qs);
        b26 = findViewById(R.id.button59qs);

        wm = (WifiManager) getSystemService(WIFI_SERVICE);
        ba = BluetoothAdapter.getDefaultAdapter();
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);

        ts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.7f);
            }
        });

        // Wi-Fi Toggle Button
        ib7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleWifi();
            }
        });

        // Bluetooth Toggle Button
        ib8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBluetooth();
            }
        });

        // Camera (Torch) Toggle Button
        ib9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCamera();
            }
        });

        // Back to Index Button
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ts.speak("Back to index", TextToSpeech.QUEUE_FLUSH, null);
                Intent i = new Intent(Eighteen.this, Eleven.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void toggleWifi() {
        if (!wifi) {
            ts.speak("Wifi turned on", TextToSpeech.QUEUE_FLUSH, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Intent panelIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivityForResult(panelIntent, 0);
            } else {
                wm.setWifiEnabled(true);
            }
            wifi = true;
            ib7.setImageResource(R.drawable.wn);
            Toast.makeText(Eighteen.this, "Wifi turned on", Toast.LENGTH_SHORT).show();
        } else {
            ts.speak("Wifi turned off", TextToSpeech.QUEUE_FLUSH, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Intent panelIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivityForResult(panelIntent, 0);
            } else {
                wm.setWifiEnabled(false);
            }
            wifi = false;
            ib7.setImageResource(R.drawable.wo);
            Toast.makeText(Eighteen.this, "Wifi turned off", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleBluetooth() {
        if (!blue) {
            ts.speak("Bluetooth turned on", TextToSpeech.QUEUE_FLUSH, null);
            handleBluetoothEnable();
            blue = true;
            ib8.setImageResource(R.drawable.bo);
            Toast.makeText(Eighteen.this, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
        } else {
            ts.speak("Bluetooth turned off", TextToSpeech.QUEUE_FLUSH, null);
            handleBluetoothDisable();
            blue = false;
            ib8.setImageResource(R.drawable.bf);
            Toast.makeText(Eighteen.this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }


    private void toggleCamera() {
        try {
            if (!camera) {
                ts.speak("Torch turned on", TextToSpeech.QUEUE_FLUSH, null);
                String id = cm.getCameraIdList()[0];
                cm.setTorchMode(id, true);
                camera = true;
                ib9.setImageResource(R.drawable.to);
                Toast.makeText(Eighteen.this, "Torch turned on", Toast.LENGTH_SHORT).show();
            } else {
                ts.speak("Torch turned off", TextToSpeech.QUEUE_FLUSH, null);
                String id = cm.getCameraIdList()[0];
                cm.setTorchMode(id, false);
                camera = false;
                ib9.setImageResource(R.drawable.tf);
                Toast.makeText(Eighteen.this, "Torch turned off", Toast.LENGTH_SHORT).show();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Toast.makeText(Eighteen.this, "Error accessing camera", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleBluetoothEnable() {
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        if (bluetoothManager == null) {
            showToast("Device doesn't support Bluetooth");
            return;
        }

        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            showToast("Device doesn't have Bluetooth capability");
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.BLUETOOTH_CONNECT},
                        REQUEST_ENABLE_BT
                );
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            showToast("Bluetooth is already enabled");
        }
    }
    private void handleBluetoothDisable() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            // Redirect the user to Bluetooth settings
            Intent disableBtIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(disableBtIntent);
            showToast("Redirecting to Bluetooth settings to turn off");
        } else {
            showToast("Bluetooth is already disabled");
        }
    }

    /**
     * Handles app close functionality.
     */
    private void handleAppClose() {
        showToast("Closing the app...");
        finish();
        // Optionally: Use System.exit(0) if you want to terminate the entire process.
        // System.exit(0);
    }

    /**
     * Displays a Toast message.
     * @param message The message to display.
     */
    private void showToast(String message) {
        Toast.makeText(Eighteen.this, message, Toast.LENGTH_SHORT).show();
    }
}
