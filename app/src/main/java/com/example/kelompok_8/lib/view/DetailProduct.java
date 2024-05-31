package com.example.kelompok_8.lib.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok_8.R;
import com.example.kelompok_8.lib.Model.Product;
import com.google.android.material.button.MaterialButton;

public class DetailProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        FrameLayout topPanel = findViewById(R.id.topPanel);
        ScrollView kontenWisata = findViewById(R.id.kontenWisata);
        ProgressBar loadingMain = findViewById(R.id.loadingMain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Hide loadingMain and show kontenMain
                loadingMain.setVisibility(View.GONE);
                topPanel.setVisibility(View.VISIBLE);
                kontenWisata.setVisibility(View.VISIBLE);
            }
        }, 1500);

        // Ambil data produk dari intent
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        // Set judul
        TextView judulTextView = findViewById(R.id.judul_item);
        judulTextView.setText(product.getJudul());

        // Set deskripsi
        TextView deskripsiTextView = findViewById(R.id.deskripsi_item);
        deskripsiTextView.setText(product.getDeskripsi());

        // Set harga
        TextView hargaTextView = findViewById(R.id.harga_item);
        hargaTextView.setText(product.getHarga());

        // Set Lokasi wisata
        TextView lokasi_text = findViewById(R.id.lokasi_text);
        lokasi_text.setText(product.getAlamat());

        // Set gambar
        ImageView gambarImageView = findViewById(R.id.gambar_item);
        gambarImageView.setImageResource(product.getGambarResourceId());

        // Icon share
        ImageView shareImage = findViewById(R.id.buttonShare);

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(intent.EXTRA_TEXT, "Bagikan wisata " + product.getJudul() + " dengan teman kalian atau keluarga kalian \n https://wa.me/+62895377490806");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share to :"));
            }
        });

        MaterialButton buttonMessage = findViewById(R.id.buttonCall);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+62895377490806";
                String message = "Selamat pagi kak, saya mau booking homestaynya selama 2 hari ya kak, buat 2 orang.";

                // Format URL untuk mengirim pesan langsung di WhatsApp dengan pesan tertentu
                String url = "https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message);

                // Membuat dan memulai Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        MaterialButton buttonLokasi = findViewById(R.id.buttonLokasi);
        buttonLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMaps = new Intent(DetailProduct.this, MapsWebView.class);
                // Tambahkan putExtra dengan data yang ingin dikirim
                intentMaps.putExtra("productJudul", product.getJudul());
                startActivity(intentMaps);
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
