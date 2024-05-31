package com.example.kelompok_8.lib.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kelompok_8.R;

public class MapsWebView extends AppCompatActivity {

    ImageView btnBack;

    private WebView webView;

    private ProgressBar progressBar;

    private TextView textNavbar;

    private String urlMaps = "https://www.google.com/maps/place/Muntuk,+Kec.+Dlingo,+Kabupaten+Bantul,+Daerah+Istimewa+Yogyakarta/@-7.9185809,110.3713961,13z/data=!3m1!4b1!4m6!3m5!1s0x2e7a5375c359c933:0x5027a76e3568c00!8m2!3d-7.910537!4d110.4440838!16s%2Fg%2F1235kpl3?hl=id&entry=ttu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_web_view);

        // Tombol kembali
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Setting webview maps desawisata
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        // Dapatkan judul produk dari intent
        String productJudul = getIntent().getStringExtra("productJudul");

        // Tentukan URL lokasi berdasarkan judul produk
        String url = getMapUrlForProduct(productJudul);
        webView.loadUrl(url);

        // Setting judul navbar
        textNavbar = findViewById(R.id.textNavbar);
        textNavbar.setText(productJudul);
    }

    private String getMapUrlForProduct(String productJudul) {
        switch (productJudul) {
            case "Rosin Hotel Resort":
               return urlMaps;
            case "Homestay Kawasan Deswita Karang Asem":
                return urlMaps;
            case "Almiya Homestay":
                return urlMaps;
            case "Wisata Menganyam Bambu":
                return "https://www.google.com/maps/place/KARYA+MANDIRI+%22Bamboo+Handicraft%22/@-7.9193339,110.4425319,18.78z/data=!4m6!3m5!1s0x2e7a5376999426fb:0x7f8edbbad1f789dd!8m2!3d-7.919143!4d110.4433627!16s%2Fg%2F11bxgn32m0?entry=ttu";
            default:
                return "https://www.google.com/maps";
        }
    }
}