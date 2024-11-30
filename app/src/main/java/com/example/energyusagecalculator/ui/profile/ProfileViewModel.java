package com.example.energyusagecalculator.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mName;
    private final MutableLiveData<String> mEmail;
    private final MutableLiveData<String> mGroup;
    private final MutableLiveData<String> mStudentNumber;
    private final MutableLiveData<String> mProgrammeCode;
    private final MutableLiveData<String> mGitHub;
    private final MutableLiveData<String> mAppInfo;
    private final MutableLiveData<String> mCopyright;

    public ProfileViewModel() {
        mName = new MutableLiveData<>();
        mEmail = new MutableLiveData<>();
        mGroup = new MutableLiveData<>();
        mStudentNumber = new MutableLiveData<>();
        mProgrammeCode = new MutableLiveData<>();
        mGitHub = new MutableLiveData<>();
        mAppInfo = new MutableLiveData<>();
        mCopyright = new MutableLiveData<>();

        // Initialize with default values
        mName.setValue("Ain Binti Abd Muthalib");
        mGroup.setValue("RCDCS2405A");
        mStudentNumber.setValue("2022646248");
        mProgrammeCode.setValue("CDCS240");
        mGitHub.setValue("https://github.com/ainmuthalib");  // Link for GitHub
        mEmail.setValue("mailto:2022646248@student.uitm.edu.my");
        mAppInfo.setValue("Energy Usage Calculator is designed to help you estimate your monthly electricity bills effortlessly. By inputting the number of electricity units consumed and any applicable rebate percentage, you can quickly calculate your total charges and final costs.");
        mCopyright.setValue("© 2024 Ain Binti Abd Muthalib. All Rights Reserved.");
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getGroup() {
        return mGroup;
    }

    public LiveData<String> getStudentNumber() {
        return mStudentNumber;
    }

    public LiveData<String> getProgrammeCode() {
        return mProgrammeCode;
    }

    public LiveData<String> getGitHub() {
        return mGitHub;
    }

    public LiveData<String> getAppInfo() {
        return mAppInfo;
    }

    public LiveData<String> getCopyright() {
        return mCopyright;
    }
}
