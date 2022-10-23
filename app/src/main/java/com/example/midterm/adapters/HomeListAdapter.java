package com.example.midterm.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midterm.databinding.HomeRowBinding;
import com.example.midterm.models.Product;

public class HomeListAdapter extends ListAdapter<Product, HomeListAdapter.HomeViewHolder> {
    HomeInterface homeInterface;
    public HomeListAdapter(HomeInterface homeInterface) {
        super(Product.itemCallback);
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HomeRowBinding homeRowBinding = HomeRowBinding.inflate(layoutInflater, parent, false);
        homeRowBinding.setHomeInterface(homeInterface);
        return new HomeViewHolder(homeRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product = getItem(position);
        holder.homeRowBinding.setProduct(product);
        holder.homeRowBinding.executePendingBindings();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {
        HomeRowBinding homeRowBinding;

        public HomeViewHolder(HomeRowBinding binding) {
            super(binding.getRoot());
            this.homeRowBinding = binding;
        }
    }

    public interface HomeInterface {
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
