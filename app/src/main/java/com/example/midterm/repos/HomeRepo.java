package com.example.midterm.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.midterm.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeRepo {
    private MutableLiveData<List<Product>> mutableProductList;
    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(UUID.randomUUID().toString(),
                "Pizza",
                10,
                true,
                "pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot.",
                "https://thumbs.dreamstime.com/b/pepperoni-pizza-separated-slice-whole-pepperoni-pizza-single-separated-slice-isolated-vector-illustration-110262843.jpg"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Burger",
                4.2,
                false,
                "Burger, is a food consisting of fillings —usually a patty of ground meat, typically beef—placed inside a sliced bun or bread roll.",
                "https://img.freepik.com/free-vector/cheeseburg-vector-illustration-isolated-white-background_505557-782.jpg?w=2000"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Fries",
                2.3,
                true,
                "Fries, these are deep-fried, very thin, salted slices of potato that are usually served at room temperature.",
                "https://img.freepik.com/free-vector/cartoon-french-fries-fast-food_354412-9.jpg?w=360"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Hot Dog",
                5,
                true,
                "Hot dog, is a food consisting of a grilled or steamed sausage served in the slit of a partially sliced bun.",
                "http://clipart-library.com/images/ziXeXgM5T.jpg"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Croissant",
                9,
                false,
                "Croissants, are named for their historical crescent shape, the dough is layered with butter, rolled and folded several times in succession, then rolled into a thin sheet, in a technique called laminating.",
                "https://thumbs.dreamstime.com/b/croissant-icon-cartoon-style-isolated-illustration-127023208.jpg"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Taco",
                6.2,
                true,
                "Taco, is a traditional Mexican food consisting of a small hand-sized corn- or wheat-based tortilla topped with a filling.",
                "http://clipart-library.com/images/6TyXKnaLc.png"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Sandwich",
                9,
                true,
                "Sandwich, in its basic form, slices of meat, cheese, or other food placed between two slices of bread, or more generally any dish wherein bread serves as a container or wrapper for another food type.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwEN7OgxTTgk8q850ytr80dMVQEiwjn1XCUg&usqp=CAU"));

        productList.add(new Product(UUID.randomUUID().toString(),
                "Ice Cream",
                2,
                true,
                "Ice cream, is a sweetened frozen food typically eaten as a snack or dessert.",
                "https://thumbs.dreamstime.com/b/melting-ice-cream-cone-balls-waffle-vector-flat-illustration-isolated-white-background-146246646.jpg"));

        mutableProductList.setValue(productList);
    }
}
