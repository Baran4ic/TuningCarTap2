package com.example.tuningcartap2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    ImageView imgVolume;
    int volume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgVolume = findViewById(R.id.volume);
    }

    public void tapGarage(View view) {
        Intent intent=new Intent(MainActivity.this,Garage.class);
        startActivity(intent);
    }

    public void tapSettings(View view) {
        Intent intent=new Intent(MainActivity.this,Settings.class);
        startActivity(intent);
    }

    public void tapVolume(View view) {
        volume = (volume <1)? volume +1:0;
        switch (volume){
            case 0:
                imgVolume.setImageResource(R.drawable.volume_off);
                music.stop();
                break;
            case 1:
                imgVolume.setImageResource(R.drawable.volume_on);
                music = MediaPlayer.create(MainActivity.this, R.raw.song1);
                music.stop();
                music = MediaPlayer.create(MainActivity.this, R.raw.song1);
                music.start();
                break;
        }
    }
}