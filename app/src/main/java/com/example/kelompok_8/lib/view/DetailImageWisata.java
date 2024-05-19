package com.example.kelompok_8.lib.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.kelompok_8.R;
import com.example.kelompok_8.lib.adapter.ProductAllAdapter;

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

        // Set spacing in dp
        int spacingInPixels = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        List<Integer> productList = new ArrayList<>();
        productList.add(R.drawable.slider2);
        productList.add(R.drawable.slider3);
        productList.add(R.drawable.slider5);
        productList.add(R.drawable.galeri1);
        productList.add(R.drawable.galeri2);
        productList.add(R.drawable.galeri3);
        productList.add(R.drawable.galeri4);
        productList.add(R.drawable.galeri5);
        productList.add(R.drawable.galeri6);
        productList.add(R.drawable.galeri7);
        productList.add(R.drawable.galeri8);
        productList.add(R.drawable.galeri9);
        // Tambahkan produk lainnya sesuai kebutuhan

        ProductAllAdapter adapter = new ProductAllAdapter(this, productList);
        recyclerView.setAdapter(adapter);
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

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}