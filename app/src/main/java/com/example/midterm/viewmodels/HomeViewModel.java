package com.example.midterm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.midterm.models.CartProduct;
import com.example.midterm.models.Product;
import com.example.midterm.repos.CartRepo;
import com.example.midterm.repos.HomeRepo;

import java.util.List;

public class HomeViewModel extends ViewModel {
    HomeRepo homeRepo = new HomeRepo();
    CartRepo cartRepo = new CartRepo();

    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts() {
        return homeRepo.getProducts();
    }

    public void setProduct(Product product){
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct(){
        return mutableProduct;
    }

    public LiveData<List<CartProduct>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addProductToCart(Product product){
        return cartRepo.addProductToCart(product);
    }

    public void removeProductFromCart(CartProduct cartProduct){
        cartRepo.removeItemFromCart(cartProduct);
    }

    public void updateAmount(CartProduct cartProduct, int total) {
        cartRepo.updateAmount(cartProduct, total);
    }

    public LiveData<Double> getPrice(){
        return cartRepo.getPrice();
    }

    public void resetPrice() {
        cartRepo.initCart();
    }
}
