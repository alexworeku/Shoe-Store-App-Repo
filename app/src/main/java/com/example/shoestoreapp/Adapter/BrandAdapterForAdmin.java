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
import com.example.shoestoreapp.Controller.BrandController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.R;
import com.example.shoestoreapp.Repository.BrandRepository;
import java.util.List;

public class BrandAdapterForAdmin extends RecyclerView.Adapter<BrandAdapterForAdmin.ViewHolder> {
    private List<Brand> brandsList;
    private Context context;

    public BrandAdapterForAdmin(List<Brand> brands, Context context) {
        this.brandsList = brands;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_for_admin, parent, false);
        BrandAdapterForAdmin.ViewHolder viewHolder = new BrandAdapterForAdmin.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brand currentBrand = brandsList.get(position);
        holder.tv_brandName.setText(currentBrand.getBrandName());
        holder.iv_BrandImageForAdmin.setImageBitmap(ImageConverter.toBitmap(brandsList.get(position).getBrandImage()));
        holder.btn_deleteBrand.setOnClickListener(v -> {
            BrandController controller = new BrandController(new BrandRepository(context));
            boolean isSuccessful = controller.removeBrandById(brandsList.get(position).getBrandId());
            if (isSuccessful) {
                Toast.makeText(context, "Brand Deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_brandName;
        ImageButton btn_deleteBrand;
        ImageView iv_BrandImageForAdmin;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_brandName = itemView.findViewById(R.id.tv_brandNameForAdmin);
            btn_deleteBrand = itemView.findViewById(R.id.btn_removeBrand);
            iv_BrandImageForAdmin = itemView.findViewById(R.id.iv_brandImageForAdmin);


        }
    }
}
