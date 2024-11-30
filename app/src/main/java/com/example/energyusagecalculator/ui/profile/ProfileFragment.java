package com.example.energyusagecalculator.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.energyusagecalculator.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Binding the views
        final TextView textViewName = binding.textViewName;
        final ImageView imageViewEmail = binding.imageViewEmail; // Use ImageView for Email
        final TextView textViewGroup = binding.textViewGroup;
        final TextView textViewStudentNumber = binding.textViewStudentNumber;
        final TextView textViewProgrammeCode = binding.textViewProgrammeCode;
        final ImageView imageViewGitHub = binding.imageViewGitHub; // Use ImageView for GitHub link
        final TextView textViewAppInfo = binding.textViewAppInfo;
        final TextView textViewCopyright = binding.textViewCopyright;

        // Observing the ViewModel LiveData
        profileViewModel.getName().observe(getViewLifecycleOwner(), name -> {
            textViewName.setText("Name: " + name);
        });

        profileViewModel.getEmail().observe(getViewLifecycleOwner(), email -> {
            // Assuming imageViewEmail is used to open email app
            imageViewEmail.setContentDescription(email); // Set content description to email
            imageViewEmail.setOnClickListener(v -> {
                // Open email app with the email address from the ViewModel
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(email));
                startActivity(emailIntent);
            });
        });

        profileViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            textViewGroup.setText("Group: " + group);
        });

        profileViewModel.getStudentNumber().observe(getViewLifecycleOwner(), studentNumber -> {
            textViewStudentNumber.setText("Student Number: " + studentNumber);
        });

        profileViewModel.getProgrammeCode().observe(getViewLifecycleOwner(), programmeCode -> {
            textViewProgrammeCode.setText("Programme Code: " + programmeCode);
        });

        profileViewModel.getGitHub().observe(getViewLifecycleOwner(), gitHub -> {
            // Assuming imageViewGitHub is used to link to GitHub profile
            imageViewGitHub.setContentDescription(gitHub); // Set content description to GitHub link
            imageViewGitHub.setOnClickListener(v -> {
                // Open GitHub profile in the browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gitHub));
                startActivity(browserIntent);
            });
        });

        profileViewModel.getAppInfo().observe(getViewLifecycleOwner(), appInfo -> {
            textViewAppInfo.setText(appInfo);
        });

        profileViewModel.getCopyright().observe(getViewLifecycleOwner(), copyright -> {
            textViewCopyright.setText(copyright);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
