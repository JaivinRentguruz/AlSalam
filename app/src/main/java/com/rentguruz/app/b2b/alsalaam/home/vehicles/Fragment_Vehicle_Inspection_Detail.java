package com.rentguruz.app.b2b.alsalaam.home.vehicles;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.rentguruz.app.b2b.alsalaam.R;
import com.rentguruz.app.b2b.alsalaam.adapters.CustomBindingAdapter;
import com.rentguruz.app.b2b.alsalaam.base.BaseFragment;
import com.rentguruz.app.b2b.alsalaam.databinding.FragmentVehicleInspectionDetailedBinding;
import com.rentguruz.app.b2b.alsalaam.model.response.VehicleModel;


public class Fragment_Vehicle_Inspection_Detail extends BaseFragment {

    FragmentVehicleInspectionDetailedBinding binding;
    VehicleModel vehicleModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVehicleInspectionDetailedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUiColor(UiColor);
        binding.header.screenHeader.setText("Vehicle Inspection");
        // binding.basic.setTextColor(getResources().getColor(R.color.green));
        binding.next.setOnClickListener(this);
        binding.header.back.setOnClickListener(this);
        binding.header.discard.setOnClickListener(this);

        binding.detailed.setTextColor(Color.parseColor(UiColor.primary));
        binding.basic.setOnClickListener(this);
        binding.detailed.setOnClickListener(this);
        binding.damage.setOnClickListener(this);

        vehicleModel = new VehicleModel();
        vehicleModel  = (VehicleModel) getArguments().getSerializable("Model");
        bundle.putSerializable("Model", vehicleModel);

        CustomBindingAdapter.loadImage(binding.car.VehImage, vehicleModel.DefaultImagePath);
        binding.car.txtvehicleName.setText(vehicleModel.VehicleName);
        binding.car.txtVehicleNumber.setText(vehicleModel.VinNumber);

    }

    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                /*NavHostFragment.findNavController(Fragment_Vehicle_Features.this)
                        .navigate(R.id.action_VehiclesFeature_to_VehiclesLicenceCerti);*/
                break;

            case R.id.discard:
            case R.id.back:
                NavHostFragment.findNavController(Fragment_Vehicle_Inspection_Detail.this).popBackStack();
                break;

            case R.id.basic:
                NavHostFragment.findNavController(Fragment_Vehicle_Inspection_Detail.this).navigate(R.id.action_vehicle_master_to_vehicle_inspection_basic,bundle);
                break;

            case R.id.detailed:

                break;
        }
    }
}
