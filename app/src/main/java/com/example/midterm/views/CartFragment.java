package com.example.midterm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.midterm.R;
import com.example.midterm.adapters.CartListAdapter;
import com.example.midterm.databinding.FragmentCartBinding;
import com.example.midterm.models.CartProduct;
import com.example.midterm.viewmodels.HomeViewModel;

public class CartFragment extends Fragment implements CartListAdapter.cartInterface{
    HomeViewModel homeViewModel;
    FragmentCartBinding fragmentCartBinding;

    public CartFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return  fragmentCartBinding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getCart().observe(getViewLifecycleOwner(), cartProducts -> {
            cartListAdapter.submitList(cartProducts);
            fragmentCartBinding.placeOrderButton.setEnabled(cartProducts.size() > 0);
        });
        homeViewModel.getPrice().observe(getViewLifecycleOwner(), aDouble -> fragmentCartBinding.orderTotalTextView.setText("Total: " + aDouble.toString()));
        fragmentCartBinding.placeOrderButton.setOnClickListener(v -> navController.navigate(R.id.action_cartFragment_to_successFragment));
    }

    @Override
    public void deleteProduct(CartProduct cartProduct) {
        homeViewModel.removeProductFromCart(cartProduct);
    }

    @Override
    public void updateAmount(CartProduct cartProduct, int total) {
        homeViewModel.updateAmount(cartProduct, total);
    }
}