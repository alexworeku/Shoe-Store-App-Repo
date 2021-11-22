package com.example.shoestoreapp.Adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.MainActivity;
import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private List<Brand> brandsList;
    private int selectedIndex = 0;
    private MainActivity activity;

    public BrandAdapter(List<Brand> brands,MainActivity activity) {
        brandsList = brands;
        this.activity=activity;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_brandName;
        ImageView iv_brandImage;
        LinearLayout ll_brandItemArea;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_brandName = itemView.findViewById(R.id.tv_brandName);
            iv_brandImage = itemView.findViewById(R.id.iv_brandImageForFilter);
        ll_brandItemArea=itemView.findViewById(R.id.ll_brandItemArea);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brand currentItem = brandsList.get(position);
        holder.tv_brandName.setText(currentItem.getBrandName());
        holder.iv_brandImage.setImageBitmap(ImageConverter.toBitmap(brandsList.get(position).getBrandImage()));
       if(position==selectedIndex){
           holder.ll_brandItemArea.setBackgroundColor(0xFCC81B);
       }else{
           holder.ll_brandItemArea.setBackgroundColor(0xFFFFFF);

       }

        holder.ll_brandItemArea.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
              selectedIndex=position;
              activity.filterByBrand(brandsList.get(position).getBrandName());
            }
            return  false;
        });
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }


}
