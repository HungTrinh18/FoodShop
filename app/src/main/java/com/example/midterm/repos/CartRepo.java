package com.example.midterm.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.midterm.models.CartProduct;
import com.example.midterm.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private final MutableLiveData<List<CartProduct>> mutableCart = new MutableLiveData<>();
    private final MutableLiveData<Double> mutablePrice = new MutableLiveData<>();

    public LiveData<List<CartProduct>> getCart() {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<>());
        totalPrice();
    }

    public boolean addProductToCart(Product product) {
        if (mutableCart.getValue() == null) {
            initCart();
        }

        List<CartProduct> cartProductList = new ArrayList<>(mutableCart.getValue());
        for(CartProduct cartProduct: cartProductList){
            if(cartProduct.getProduct().getID().equals(product.getID())){
                if(cartProduct.getQuantity() == 10) {
                    return false;
                }
                int i = cartProductList.indexOf(cartProduct);
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                cartProductList.set(i, cartProduct);
                mutableCart.setValue(cartProductList);
                totalPrice();
                return true;
            }
        }

        CartProduct cartProduct = new CartProduct(product, 1);
        cartProductList.add(cartProduct);
        mutableCart.setValue(cartProductList);
        totalPrice();
        return true;
    }

    public void removeItemFromCart(CartProduct cartProduct){
        if (mutableCart.getValue() == null) return;
        List<CartProduct> cartProductList = new ArrayList<>(mutableCart.getValue());
        cartProductList.remove(cartProduct);
        mutableCart.setValue(cartProductList);
        totalPrice();
    }

    public void updateAmount(CartProduct cartProduct, int total){
        if (mutableCart.getValue() == null) return;
        List<CartProduct> cartProductList;
        cartProductList = new ArrayList<>(mutableCart.getValue());
        CartProduct updatedProduct = new CartProduct(cartProduct.getProduct(), total);
        cartProductList.set(cartProductList.indexOf(cartProduct), updatedProduct);
        mutableCart.setValue(cartProductList);

        totalPrice();
    }

    private void totalPrice(){
        if (mutableCart.getValue() == null)
            return;
        double total = 0.0;
        List<CartProduct> cartProductList = mutableCart.getValue();
        for(CartProduct cartProduct: cartProductList){
            total = total + cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
        }
        mutablePrice.setValue(total);
    }

    public LiveData<Double> getPrice(){
        if (mutablePrice.getValue() == null){
            mutablePrice.setValue(0.0);
        }
        return mutablePrice;
    }
}
