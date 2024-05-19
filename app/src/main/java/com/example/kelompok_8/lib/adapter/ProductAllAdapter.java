package com.example.kelompok_8.lib.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelompok_8.R;

import java.util.List;

public class ProductAllAdapter extends RecyclerView.Adapter<ProductAllAdapter.ProductViewHolder> {

    private Context context;
    private List<Integer> productList;
    private OnItemClickListener listener; // Tambahkan listener

    public interface OnItemClickListener {
        void onItemClick(int imageResId);
    }

    public ProductAllAdapter(Context context, List<Integer> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        int productImage = productList.get(position);
        holder.imageViewProduct.setImageResource(productImage);

        // Mengatur lebar gambar menjadi setengah dari lebar layar
        ViewGroup.LayoutParams layoutParams = holder.imageViewProduct.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels / 2;
        holder.imageViewProduct.setLayoutParams(layoutParams);

        // Mengatur tinggi gambar menjadi 250dp
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 225, context.getResources().getDisplayMetrics());
        holder.imageViewProduct.setLayoutParams(layoutParams);

        // Menambahkan margin ke ImageView
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) holder.imageViewProduct.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, 5); // Atur margin kanan menjadi 10dp
        holder.imageViewProduct.setLayoutParams(marginLayoutParams);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);

            // Tambahkan onClickListener ke itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(productList.get(position));
                        }
                    }
                }
            });
        }
    }
}
