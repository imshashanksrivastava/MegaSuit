package com.example.megasuit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/*Call & Text*/
public class Fourteen extends AppCompatActivity {
    EditText e15,e16;
    Button b37,b38,b39;
    private int phoneCall=1;
    private int REQUEST_SMS_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourteen);
        b37=(Button)findViewById(R.id.button37);
        b38=(Button)findViewById(R.id.button38);
        b39=(Button)findViewById(R.id.button39);
        e15=(EditText)findViewById(R.id.editTextText15);
        e16=(EditText)findViewById(R.id.editTextText16);
        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                String phoneNo=e15.getText().toString().trim();
                if(phoneNo.equals("")){
                    Toast.makeText(Fourteen.this, "Enter a phone number.", Toast.LENGTH_SHORT).show();
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                }else{
                    makePhoneCall(phoneNo);
                }
            }
        });
        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                String phoneNo= e15.getText().toString().trim();
                String msg=e16.getText().toString().trim();
                if(phoneNo.equals("")|| msg.equals("")){
                    Toast.makeText(Fourteen.this, "Fill both the sections.", Toast.LENGTH_SHORT).show();
                    if (vibrator != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(500); // For older devices
                        }
                    }
                }else{
                    sendSMS(phoneNo,msg);
                }
            }
        });
        if(ContextCompat.checkSelfPermission(Fourteen.this, Manifest.permission.SEND_SMS)!= getPackageManager().PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Fourteen.this,new String[]{Manifest.permission.SEND_SMS},REQUEST_SMS_PERMISSION);
        }
        b39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Fourteen.this,Eleven.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void makePhoneCall(String phoneNo){
        Intent i=new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+phoneNo));
        if(ActivityCompat.checkSelfPermission(Fourteen.this, android.Manifest.permission.CALL_PHONE) != getPackageManager().PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Fourteen.this,new String[]{Manifest.permission.CALL_PHONE},phoneCall);
        }
        else{
            startActivity(i);
        }
    }
    private void sendSMS(String phoneNo, String msg){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        try{
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(phoneNo,null,msg,null,null);
            Toast.makeText(Fourteen.this, "Message sent!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(Fourteen.this, "Failed to send a message", Toast.LENGTH_SHORT).show();
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(500); // For older devices
                }
            }
            e.printStackTrace();
        }
    }
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode==phoneCall){
            if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
                String phoneNumber = e15.getText().toString().trim();
                makePhoneCall(phoneNumber);
            } else {
                Toast.makeText(Fourteen.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode==REQUEST_SMS_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(Fourteen.this, "SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Fourteen.this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}