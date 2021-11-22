package com.example.shoestoreapp.Adapter;

import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestoreapp.CartActivity;
import com.example.shoestoreapp.Controller.CartController;
import com.example.shoestoreapp.Helper.ImageConverter;
import com.example.shoestoreapp.Models.CartItem;
import com.example.shoestoreapp.R;
import com.example.shoestoreapp.Repository.CartRepository;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private List<CartItem> cartItems;
    private Context context;
    private CartController cartController;
    private CartActivity activity;

    public CartItemAdapter(List<CartItem> cartItems, Context context, CartActivity activity) {
        this.cartItems = cartItems;
        this.context = context;
        cartController = new CartController(new CartRepository(context));
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        CartItemAdapter.ViewHolder viewHolder = new CartItemAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_cartItemName.setText(cartItems.get(position).getShoe().getName());
        holder.tv_cartItemPrice.setText(String.format("$%s", cartItems.get(position).getShoe().getPrice()));
        holder.tv_cartItemQuantity.setText(String.format("%s", cartItems.get(position).getQuantity()));
        holder.iv_cartItemImage.setImageBitmap(ImageConverter.toBitmap(cartItems.get(position).getShoe().getImage()));

        holder.btnDecreaseItemQuantity.setEnabled(cartItems.get(position).getQuantity() > 1);

        holder.btnRemoveCartItem.setOnClickListener(view -> {

            boolean isSuccessful = cartController.removeItemFromCart(cartItems.get(position).getId());
            if (isSuccessful) {
                activity.reloadData();

                Toast.makeText(context, "Cart Item removed!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            }

        });

        holder.btnIncreaseItemQuantity.setOnClickListener(view -> {
            boolean isSuccessful = cartController.increaseCartItemQuantity(cartItems.get(position));
            if (isSuccessful) {
                activity.reloadData();
            }
        });
        holder.btnDecreaseItemQuantity.setOnClickListener(view -> {
            boolean isSuccessful = cartController.decreaseCartItemQuantity(cartItems.get(position));
            if (isSuccessful) {
                activity.reloadData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cartItemName, tv_cartItemPrice, tv_cartItemQuantity;
        ImageView iv_cartItemImage;

        ImageButton btnIncreaseItemQuantity, btnRemoveCartItem, btnDecreaseItemQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cartItemName = itemView.findViewById(R.id.tv_cartItemName);
            tv_cartItemPrice = itemView.findViewById(R.id.tv_cartItemPrice);
            tv_cartItemQuantity = itemView.findViewById(R.id.tv_cartItemQuantity);
            iv_cartItemImage = itemView.findViewById(R.id.iv_cartItemImage);

            btnRemoveCartItem = itemView.findViewById(R.id.btn_removeCartItem);
            btnIncreaseItemQuantity = itemView.findViewById(R.id.btn_increaseQuantity);
            btnDecreaseItemQuantity = itemView.findViewById(R.id.btn_decreaseQuantity);
        }
    }

}
