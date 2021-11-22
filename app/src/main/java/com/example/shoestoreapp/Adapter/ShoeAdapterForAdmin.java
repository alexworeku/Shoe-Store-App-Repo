package com.example.shoestoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestoreapp.Controller.ShoeController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.R;
import com.example.shoestoreapp.Repository.ShoeRepository;

import java.util.List;

public class ShoeAdapterForAdmin extends RecyclerView.Adapter<ShoeAdapterForAdmin.ViewHolder> {

    private List<Shoe> shoeList;
    private Context context;

    public ShoeAdapterForAdmin(List<Shoe> shoesList, Context context) {
        shoeList = shoesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item_for_admin, parent, false);
        ShoeAdapterForAdmin.ViewHolder viewHolder = new ShoeAdapterForAdmin.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shoe selectedShoe = shoeList.get(position);
        holder.tv_shoeName.setText(selectedShoe.getName());
        holder.tv_shoePrice.setText("$" + selectedShoe.getPrice());
        holder.iv_shoeImage.setImageBitmap(ImageConverter.toBitmap(selectedShoe.getImage()));
//        holder.btn_edit.setOnClickListener(v -> {
//
//            //Todo:write update code here
//            Toast.makeText(context, "Shoe Edited", Toast.LENGTH_SHORT).show();
//        });
        holder.btn_delete.setOnClickListener(v -> {
            ShoeController controller = new ShoeController(new ShoeRepository(context));
            boolean isSuccessful = controller.removeShoeById(shoeList.get(position).getId());
            if (isSuccessful) {
                Toast.makeText(context, "Shoe Deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            }
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
//        ImageButton btn_edit;
        ImageButton btn_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_shoeImage = itemView.findViewById(R.id.iv_shoeImageForAdmin);
            tv_shoeName = itemView.findViewById(R.id.tv_shoeNameForAdmin);
            tv_shoePrice = itemView.findViewById(R.id.tv_shoePriceForAdmin);
//            btn_edit = itemView.findViewById(R.id.btn_editShoe);
            btn_delete = itemView.findViewById(R.id.btn_removeShoe);
        }
    }

}
