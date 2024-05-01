package com.example.kelompok_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetailImage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        // Mengambil parameter gambar dari Intent
        int imageResId = getIntent().getIntExtra("imageResId", 0);

        // Menetapkan gambar ke ImageView
        ImageView imageView = findViewById(R.id.gambar_item);
        imageView.setImageResource(imageResId);

        // Tambahkan onClickListener pada ImageView back
        ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke MainActivity
                finish();
            }
        });
    }
}