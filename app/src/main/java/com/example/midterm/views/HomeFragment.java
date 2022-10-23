package com.example.midterm.views;

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
import com.example.midterm.adapters.HomeListAdapter;
import com.example.midterm.databinding.FragmentHomeBinding;
import com.example.midterm.models.Product;
import com.example.midterm.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment implements HomeListAdapter.HomeInterface {
    FragmentHomeBinding fragmentHomeBinding;
    private HomeListAdapter homeListAdapter;
    private HomeViewModel homeViewModel;
    private NavController navController;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeListAdapter = new HomeListAdapter(this);
        fragmentHomeBinding.homeRecyclerView.setAdapter(homeListAdapter);
        fragmentHomeBinding.homeRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentHomeBinding.homeRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getProducts().observe(getViewLifecycleOwner(), products -> homeListAdapter.submitList(products));

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
       homeViewModel.addProductToCart(product);
    }

    @Override
    public void onItemClick(Product product) {
        homeViewModel.setProduct(product);
        navController.navigate(R.id.action_homeFragment_to_detailsFragment);
    }
}