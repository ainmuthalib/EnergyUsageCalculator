package com.example.energyusagecalculator.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mResult;
    private final MutableLiveData<String> mBreakdown;

    public HomeViewModel() {
        mResult = new MutableLiveData<>();
        mResult.setValue(" ");
        mBreakdown = new MutableLiveData<>();
        mBreakdown.setValue(" ");
    }

    public LiveData<String> getResult() {
        return mResult;
    }

    public LiveData<String> getBreakdown() {
        return mBreakdown;
    }

    public void calculateBill(float unitsUsed, float rebatePercentage) {
        double finalBill = 0.0;
        double rebateAmount = 0.0;
        double totalBeforeRebate = 0.0;
        StringBuilder breakdownBuilder = new StringBuilder();

        // Calculate the total bill based on usage blocks
        if (unitsUsed <= 200) {
            totalBeforeRebate = unitsUsed * 0.218;
            breakdownBuilder.append(String.format("1 block of %.0f kWh x RM0.218 : RM%.2f\n", unitsUsed, totalBeforeRebate));
        } else if (unitsUsed <= 300) {
            totalBeforeRebate = (200 * 0.218) + ((unitsUsed - 200) * 0.334);
            breakdownBuilder.append("1 block of 200 kWh x RM0.218 : RM43.60 \n");
            breakdownBuilder.append(String.format("Next %.0f kWh x RM0.334 : RM%.2f \n", unitsUsed - 200, (unitsUsed - 200) * 0.334));
        } else if (unitsUsed <= 600) {
            totalBeforeRebate = (200 * 0.218) + (100 * 0.334) + ((unitsUsed - 300) * 0.516);
            breakdownBuilder.append("1 block of 200 kWh x RM0.218 : RM43.60 \n");
            breakdownBuilder.append("Next 100 kWh x RM0.334 : RM33.40 \n");
            breakdownBuilder.append(String.format("Next %.0f kWh x RM0.516 : RM%.2f \n", unitsUsed - 300, (unitsUsed - 300) * 0.516));
        } else {
            totalBeforeRebate = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((unitsUsed - 600) * 0.546);
            breakdownBuilder.append("1 block of 200 kWh x RM0.218 : RM43.60 \n");
            breakdownBuilder.append("Next 100 kWh x RM0.334 : RM33.40 \n");
            breakdownBuilder.append("Next 300 kWh x RM0.516 : RM154.80 \n");
            breakdownBuilder.append(String.format("Remaining %.0f kWh  x RM0.546 : RM%.2f \n", unitsUsed - 600, (unitsUsed - 600) * 0.546));
        }

        // Calculate rebate
        rebateAmount = totalBeforeRebate * (rebatePercentage / 100);
        finalBill = totalBeforeRebate - rebateAmount;

        // Update LiveData
        mResult.setValue(String.format("Total before rebate: RM%.2f \nRebate: RM%.2f \nFinal Bill: RM%.2f ", totalBeforeRebate, rebateAmount, finalBill));
        mBreakdown.setValue(breakdownBuilder.toString());
    }
}
