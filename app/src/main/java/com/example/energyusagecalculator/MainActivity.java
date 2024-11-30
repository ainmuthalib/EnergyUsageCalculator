package com.example.energyusagecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.energyusagecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        ImageView imageInfo = findViewById(R.id.imageInfo);

        // Set click listener for the ImageView
        imageInfo.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_help, null); // This should match the filename
            builder.setView(dialogView);

            TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
            textViewMessage.setText(
                    "\n\n1. Enter the total number of kilowatt-hours (kWh) consumed in the month.\n\n" +
                            "2. Enter the rebate percentage you are eligible for, which should be a value between 0 and 5.\n\n" +
                            "3. Tap the \"Calculate\" button.\n\n" +
                            "4. Once the calculation is complete, the app will display:\n\n" +
                            "      • Total charges before rebate.\n\n" +
                            "      • Total rebate.\n\n" +
                            "      • Final cost after applying the rebate percentage."
            );

            builder.setPositiveButton("Got it", (dialog, which) ->
                    Toast.makeText(MainActivity.this, "Information acknowledged", Toast.LENGTH_SHORT).show()
            );

            // Show the dialog
            builder.create().show();
        });
    }
}