package com.example.tuningcartap2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class Garage extends AppCompatActivity {

    private Button btnAdd;
    private GridView gridView;
    private ArrayList<CarImage> carImages = new ArrayList<>();
    private ImageAdapter adapter;
    private Button btnSell;

    private static final String PREFS_NAME = "GaragePrefs";
    private static final String KEY_CAR_IMAGES = "saved_car_images";
    private static final int REQUEST_CODE_ORDER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        btnAdd = findViewById(R.id.btnAdd);
        gridView = findViewById(R.id.gridView);
        btnSell = findViewById(R.id.btnSell);
        btnSell.setVisibility(View.GONE);

        adapter = new ImageAdapter(this, carImages);
        gridView.setAdapter(adapter);

        loadImages();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Garage.this, Order.class);
            startActivityForResult(intent, REQUEST_CODE_ORDER);
        });

        btnSell.setOnClickListener(v -> {
            int position = (int) btnSell.getTag();
            carImages.remove(position);
            adapter.notifyDataSetChanged();
            saveImages();
            btnSell.setVisibility(View.GONE);
        });

        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            btnSell.setTag(position);
            btnSell.setVisibility(View.VISIBLE);
            return true;
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDoubleClick()) {
                    CarImage selected = carImages.get(position);
                    if (selected.getType() == 3) {
                        Intent intent = new Intent(Garage.this, CarTuning.class);
                        intent.putExtra("IMAGE_TYPE", selected.getType());
                        startActivity(intent);
                    } else if (selected.getType() == 2) {
                        Intent intent = new Intent(Garage.this, CarTuning.class);
                        intent.putExtra("IMAGE_TYPE", selected.getType());
                        startActivity(intent);
                    } else if (selected.getType() == 1) {
                        Intent intent = new Intent(Garage.this, CarTuning.class);
                        intent.putExtra("IMAGE_TYPE", selected.getType());
                        startActivity(intent);
                    }
                }
            }

            private long lastClickTime = 0;
            private boolean isDoubleClick() {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < 300) {
                    return true;
                }
                lastClickTime = clickTime;
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ORDER && resultCode == RESULT_OK) {
            int imageType = data.getIntExtra("IMAGE_TYPE", 0);
            int imageResId = getImageResIdByType(imageType);
            if (imageResId != 0) {
                addImage(imageResId, imageType);
            }
        }
    }

    private int getImageResIdByType(int type) {
        switch (type) {
            case 1: return R.drawable.icon_tuning;
            case 2: return R.drawable.icone_lanser_evo7;
            case 3: return R.drawable.icone_toyota_supra;
            default: return 0;
        }
    }

    private void addImage(int imageResId, int type) {
        carImages.add(new CarImage(imageResId, type));
        adapter.notifyDataSetChanged();
        saveImages();
    }
    private void saveImages() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();
        for (CarImage carImage : carImages) {
            JSONArray item = new JSONArray();
            item.put(carImage.getImageResId());
            item.put(carImage.getType());
            jsonArray.put(item);
        }
        editor.putString(KEY_CAR_IMAGES, jsonArray.toString());
        editor.apply();
    }

    private void loadImages() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_CAR_IMAGES, null);
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                carImages.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray item = jsonArray.getJSONArray(i);
                    carImages.add(new CarImage(item.getInt(0), item.getInt(1)));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static class CarImage {
        private final int imageResId;
        private final int type;

        public CarImage(int imageResId, int type) {
            this.imageResId = imageResId;
            this.type = type;
        }

        public int getImageResId() {
            return imageResId;
        }

        public int getType() {
            return type;
        }
    }
}