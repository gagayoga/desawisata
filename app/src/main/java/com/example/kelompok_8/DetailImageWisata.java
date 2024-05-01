package com.example.kelompok_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kelompok_8.Adapter.ProductAdapter;
import com.example.kelompok_8.Adapter.ProductAllAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailImageWisata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image_wisata);

        // Image Slider
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // Set jumlah kolom menjadi dua
        recyclerView.setLayoutManager(layoutManager);

        List<Integer> productList = new ArrayList<>();
        productList.add(R.drawable.slider1);
        productList.add(R.drawable.slider2);
        productList.add(R.drawable.slider3);
        productList.add(R.drawable.slider4);
        productList.add(R.drawable.slider5);
        productList.add(R.drawable.slider6);
        // Tambahkan produk lainnya sesuai kebutuhan

        ProductAllAdapter adapter = new ProductAllAdapter(this, productList);
        recyclerView.setAdapter(adapter);


        // Tambahkan listener ke adapter
        adapter.setOnItemClickListener(new ProductAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int imageResId) {
                // Navigasi ke halaman detail gambar dengan membawa parameter gambar yang sesuai
                Intent intent = new Intent(DetailImageWisata.this, DetailImage.class);
                intent.putExtra("imageResId", imageResId);
                startActivity(intent);
            }
        });

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