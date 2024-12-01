package com.example.energyusagecalculator.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.energyusagecalculator.R;
import com.example.energyusagecalculator.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editTextUnits = binding.editTextUnits;
        final EditText editTextRebate = binding.editTextRebate;
        final Button buttonCalculate = binding.buttonCalculate;
        final Button buttonClear = binding.buttonClear;
        final TextView textViewResult = binding.textViewResult;
        final TextView textViewBreakdown = binding.textViewBreakdown;

        // Observe LiveData from ViewModel
        homeViewModel.getResult().observe(getViewLifecycleOwner(), textViewResult::setText);
        homeViewModel.getBreakdown().observe(getViewLifecycleOwner(), textViewBreakdown::setText);

        // Button click listeners
        buttonCalculate.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

            String unitsUsedStr = editTextUnits.getText().toString();
            String rebatePercentageStr = editTextRebate.getText().toString();

            if (TextUtils.isEmpty(unitsUsedStr) && TextUtils.isEmpty(rebatePercentageStr)) {
                Toast.makeText(getContext(), "Please enter both values.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(unitsUsedStr)) {
                Toast.makeText(getContext(), "Please enter electricity unit used.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(rebatePercentageStr)) {
                Toast.makeText(getContext(), "Please enter rebate percentage.", Toast.LENGTH_SHORT).show();
                return;
            }

            float unitsUsed = Float.parseFloat(unitsUsedStr);
            float rebatePercentage = Float.parseFloat(rebatePercentageStr);
            if (rebatePercentage < 0 || rebatePercentage > 5) {
                Toast.makeText(getContext(), "Rebate percentage must be between 0 and 5.", Toast.LENGTH_SHORT).show();
                return;
            }

            homeViewModel.calculateBill(unitsUsed, rebatePercentage);

            // Request focus on the units EditText
            editTextUnits.requestFocus();
        });

        buttonClear.setOnClickListener(v -> {
            editTextUnits.setText(""); // Clear the units input
            editTextRebate.setText(""); // Clear the rebate input
            textViewResult.setText(""); // Clear the result display
            textViewBreakdown.setText(""); // Clear the breakdown display

            // Hide the keyboard when clearing the fields
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
