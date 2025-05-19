package com.example.tuningcartap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button btnSky = findViewById(R.id.btnSky);
        Button btnLancer = findViewById(R.id.btnLancer);
        Button btnToyota = findViewById(R.id.btnToyota);

        btnSky.setOnClickListener(v -> returnResult(1));
        btnLancer.setOnClickListener(v -> returnResult(2));
        btnToyota.setOnClickListener(v -> returnResult(3));
    }

    private void returnResult(int imageType) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("IMAGE_TYPE", imageType);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}