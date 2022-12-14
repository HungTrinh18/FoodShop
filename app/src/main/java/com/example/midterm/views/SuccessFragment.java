package com.example.midterm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.midterm.R;
import com.example.midterm.databinding.FragmentSuccessBinding;
import com.example.midterm.viewmodels.HomeViewModel;

public class SuccessFragment extends Fragment {
    FragmentSuccessBinding fragmentSuccessBinding;

    public SuccessFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSuccessBinding = FragmentSuccessBinding.inflate(inflater, container, false);
        return fragmentSuccessBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        NavController navController = Navigation.findNavController(view);
        fragmentSuccessBinding.continueShoppingButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_successFragment_to_homeFragment);
            homeViewModel.resetPrice();
        });
    }
}