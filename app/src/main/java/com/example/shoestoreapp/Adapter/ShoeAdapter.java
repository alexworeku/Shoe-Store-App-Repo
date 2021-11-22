package com.example.shoestoreapp.Adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.R;
import com.example.shoestoreapp.ShoeDetailActivity;

import java.io.Serializable;
import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ViewHolder> {
    private List<Shoe> shoeList;
    private Context context;

    public ShoeAdapter(List<Shoe> shoesList, Context context) {
        shoeList = shoesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_card_item, parent, false);
        ShoeAdapter.ViewHolder viewHolder = new ShoeAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shoe selectedShoe = shoeList.get(position);
        holder.tv_shoeName.setText(selectedShoe.getName());
        holder.tv_shoePrice.setText("$" + selectedShoe.getPrice());
        holder.iv_shoeImage.setImageBitmap(ImageConverter.toBitmap(selectedShoe.getImage()));
        holder.btn_viewDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShoeDetailActivity.class);
            intent.putExtra("selectedShoeId",shoeList.get(position).getId() );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shoeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_shoeImage;
        TextView tv_shoeName;
        TextView tv_shoePrice;
        ImageButton btn_viewDetail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_shoeImage = itemView.findViewById(R.id.iv_shoeImage);
            tv_shoeName = itemView.findViewById(R.id.tv_shoeName);
            tv_shoePrice = itemView.findViewById(R.id.tv_shoePrice);
            btn_viewDetail = itemView.findViewById(R.id.btn_viewDetail);

        }
    }

}
