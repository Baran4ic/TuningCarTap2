package com.example.tuningcartap2;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CarTuning extends AppCompatActivity {
    int counter = 0;
    int maxcolor = 0;
    int maxwheels = 0;
    int maxwindow = 0;
    int maxfars = 0;
    int maxlivr = 0;
    int color = 0;
    int wheels = 0;
    int window = 0;
    int fars = 0;
    int livr = 0;
    int n=10;
    TextView textTitle;
    TextView textCount;
    ImageView imgCar;
    ImageView imgKorpus;
    ImageView imgWheels;
    ImageView imgWindow;
    ImageView imgFars;
    ImageView imgLivr;

    private static final String PREFS_NAME = "CarTuningPrefs";
    private static final String KEY_COUNTER = "counter";
    private static final String KEY_COLOR = "color";
    private static final String KEY_WHEELS = "wheels";
    private static final String KEY_WINDOW = "window";
    private static final String KEY_FARS = "fars";
    private static final String KEY_LIVR = "livr";
    private static final String KEY_MAX_COLOR = "maxcolor";
    private static final String KEY_MAX_WHEELS = "maxwheels";
    private static final String KEY_MAX_WINDOW = "maxwindow";
    private static final String KEY_MAX_FARS = "maxfars";
    private static final String KEY_MAX_LIVR = "maxlivr";
    int type;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_tuning);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        type = getIntent().getIntExtra("IMAGE_TYPE", 0);
        textTitle = findViewById(R.id.text1);
        textCount = findViewById(R.id.text2);
        imgCar = findViewById(R.id.imgCar);
        imgKorpus = findViewById(R.id.imgKorpus);
        imgWheels = findViewById(R.id.imgWheels);
        imgWindow = findViewById(R.id.imgWindow);
        imgFars = findViewById(R.id.imgFars);
        imgLivr = findViewById(R.id.imgLivr);

        loadCarState();
        updateUI();
    }

    protected void onPause(){
        super.onPause();
        saveCarState();
    }

    private void saveCarState() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME + type, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(KEY_COUNTER, counter);
        editor.putInt(KEY_COLOR, color);
        editor.putInt(KEY_WHEELS, wheels);
        editor.putInt(KEY_WINDOW, window);
        editor.putInt(KEY_FARS, fars);
        editor.putInt(KEY_LIVR, livr);
        editor.putInt(KEY_MAX_COLOR, maxcolor);
        editor.putInt(KEY_MAX_WHEELS, maxwheels);
        editor.putInt(KEY_MAX_WINDOW, maxwindow);
        editor.putInt(KEY_MAX_FARS, maxfars);
        editor.putInt(KEY_MAX_LIVR, maxlivr);

        editor.apply();
    }

    private void updateUI() {
        // Обновляем счетчик пробега
        String s = "Пробег: " + counter + ((counter%10>1 && counter%10<5 && counter/10%10!=1)?" км":" км");
        textCount.setText(s);

        // Обновляем заголовок в зависимости от прогресса
        if (counter >= 30) {
            textTitle.setText("Разблокировано всё!");
        } else if (counter >= 20) {
            textTitle.setText("Разблокированы синие колеса!");
        } else if (counter >= 10) {
            textTitle.setText("Разблокирован черный цвет!");
        } else {
            textTitle.setText("Проехать " + n + " км");
        }

        // Обновляем изображения
        updateCarImages();
    }

    private void updateCarImages() {
        switch (color) {
            case 0: imgKorpus.setImageResource(R.drawable.korpus_skyline); break;
            case 1: imgKorpus.setImageResource(R.drawable.korpus_black); break;
            case 2: imgKorpus.setImageResource(R.drawable.korpus_blue); break;
            case 3: imgKorpus.setImageResource(R.drawable.korpus_yellow); break;
            case 4: imgKorpus.setImageResource(R.drawable.korpus_orange); break;
            case 5: imgKorpus.setImageResource(R.drawable.korpus_pink); break;
            case 6: imgKorpus.setImageResource(R.drawable.korpus_green); break;
            case 7: imgKorpus.setImageResource(R.drawable.korpus_light_blue); break;
            case 8: imgKorpus.setImageResource(R.drawable.korpus_purple); break;
            case 9: imgKorpus.setImageResource(R.drawable.korpus_red); break;
            case 10: imgKorpus.setImageResource(R.drawable.korpus_dark_green); break;
        }
        switch (wheels) {
            case 0:imgWheels.setImageResource(R.drawable.wheels_skyline);break;
            case 1:imgWheels.setImageResource(R.drawable.wheels_blue);break;
            case 2:imgWheels.setImageResource(R.drawable.wheels_yellow);break;
            case 3:imgWheels.setImageResource(R.drawable.wheels_orange);break;
            case 4:imgWheels.setImageResource(R.drawable.wheels_pink);break;
            case 5:imgWheels.setImageResource(R.drawable.wheels_green);break;
            case 6:imgWheels.setImageResource(R.drawable.wheels_light_blue);break;
            case 7:imgWheels.setImageResource(R.drawable.wheels_purple);break;
            case 8:imgWheels.setImageResource(R.drawable.wheels_red);break;
            case 9:imgWheels.setImageResource(R.drawable.wheels_dark_green);break;
        }
        switch (window){
            case 0:imgWindow.setImageResource(R.drawable.window_skyline);break;
            case 1:imgWindow.setImageResource(R.drawable.window_black);break;
            case 2:imgWindow.setImageResource(R.drawable.window_blue);break;
            case 3:imgWindow.setImageResource(R.drawable.window_yellow);break;
            case 4:imgWindow.setImageResource(R.drawable.window_orange);break;
            case 5:imgWindow.setImageResource(R.drawable.window_pink);break;
            case 6:imgWindow.setImageResource(R.drawable.window_green);break;
            case 7:imgWindow.setImageResource(R.drawable.window_light_blue);break;
            case 8:imgWindow.setImageResource(R.drawable.window_purple);break;
            case 9:imgWindow.setImageResource(R.drawable.window_red);break;
            case 10:imgWindow.setImageResource(R.drawable.window_dark_green);break;
        }
        switch (fars){
            case 0:imgFars.setImageResource(R.drawable.fars_skyline);break;
            case 1:imgFars.setImageResource(R.drawable.fars_blue);break;
            case 2:imgFars.setImageResource(R.drawable.fars_yellow);break;
            case 3:imgFars.setImageResource(R.drawable.fars_orange);break;
            case 4:imgFars.setImageResource(R.drawable.fars_pink);break;
            case 5:imgFars.setImageResource(R.drawable.fars_green);break;
            case 6:imgFars.setImageResource(R.drawable.fars_light_blue);break;
            case 7:imgFars.setImageResource(R.drawable.fars_purple);break;
            case 8:imgFars.setImageResource(R.drawable.fars_red);break;
            case 9:imgFars.setImageResource(R.drawable.fars_dark_green);break;
        }
        switch (livr){
            case 0:imgLivr.setImageResource(R.drawable.livr_skyline);break;
            case 1:imgLivr.setImageResource(R.drawable.livr_lines);break;
            case 2:imgLivr.setImageResource(R.drawable.livr_butterfly);break;
            case 3:imgLivr.setImageResource(R.drawable.livr_fire);break;
            case 4:imgLivr.setImageResource(R.drawable.livr_breath);break;
            case 5:imgLivr.setImageResource(R.drawable.livr_music);break;
        }
    }

    private void loadCarState() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME + type, MODE_PRIVATE);

        counter = prefs.getInt(KEY_COUNTER, 0);
        color = prefs.getInt(KEY_COLOR, 0);
        wheels = prefs.getInt(KEY_WHEELS, 0);
        window = prefs.getInt(KEY_WINDOW, 0);
        fars = prefs.getInt(KEY_FARS, 0);
        livr = prefs.getInt(KEY_LIVR, 0);
        maxcolor = prefs.getInt(KEY_MAX_COLOR, 0);
        maxwheels = prefs.getInt(KEY_MAX_WHEELS, 0);
        maxwindow = prefs.getInt(KEY_MAX_WINDOW, 0);
        maxfars = prefs.getInt(KEY_MAX_FARS, 0);
        maxlivr = prefs.getInt(KEY_MAX_LIVR, 0);
    }

    public void tapCar(View view) {
        counter +=1;
        String s="Пробег: " + counter + ((counter%10>1 && counter%10<5 && counter/10%10!=1)?" км":" км");
        textCount.setText(s);
        switch (counter){
            case 10: textTitle.setText("Разблокирован черный цвет!"); maxcolor++; n=20; break;
            case 20: textTitle.setText("Разблокированы синие колеса!"); maxwheels++; n=30; break;
            case 30: textTitle.setText("Разблокировано всё!");
                maxcolor=10;
                maxwheels=9;
                maxwindow=10;
                maxfars=9;
                maxlivr=5;
                n=100000000; break;
            default: textTitle.setText("Проехать "+n+" км"); break;
        }
    }

    public void tapBacket(View view) {
        color = (color<maxcolor && color<10)? color+1:0;
        updateCarImages();
        saveCarState();
    }

    public void tapWheel(View view) {
        wheels = (wheels<maxwheels && wheels<9)? wheels+1:0;
        updateCarImages();
        saveCarState();
    }

    public void tapWindow(View view) {
        window = (window<maxwindow && window<10)? window+1:0;
        updateCarImages();
        saveCarState();
    }

    public void tapFars(View view) {
        fars = (fars<maxfars && fars<9)? fars+1:0;
        updateCarImages();
        saveCarState();
    }

    public void tapLivr(View view) {
        livr = (livr<maxlivr && livr<5)? livr+1:0;
        updateCarImages();
        saveCarState();
    }
}