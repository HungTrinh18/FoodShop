package com.example.midterm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midterm.databinding.CartProductBinding;
import com.example.midterm.models.CartProduct;

public class CartListAdapter extends ListAdapter<CartProduct, CartListAdapter.CartViewHolder> {
    private final cartInterface cartInterface;
    public CartListAdapter(cartInterface cartInterface) {
        super(CartProduct.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartProductBinding cartProductBinding = CartProductBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(cartProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartProductBinding.setCartProduct(getItem(position));
        holder.cartProductBinding.executePendingBindings();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        CartProductBinding cartProductBinding;
        public CartViewHolder(CartProductBinding cartProductBinding){
            super(cartProductBinding.getRoot());
            this.cartProductBinding = cartProductBinding;
            cartProductBinding.deleteProductButton.setOnClickListener(v -> cartInterface.deleteProduct(getItem(getBindingAdapterPosition())));
            cartProductBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int total = position + 1;
                    if (total == getItem(getBindingAdapterPosition()).getQuantity()) return;
                    cartInterface.updateAmount(getItem(getBindingAdapterPosition()), total);

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
    public interface cartInterface {
        void deleteProduct(CartProduct cartProduct);
        void updateAmount(CartProduct cartProduct, int total);
    }
}
