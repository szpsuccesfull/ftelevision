package com.example.ftelevision.models;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ftelevision.R;
import com.example.ftelevision.models.TelevisionModel;

public class DataDetailFragment extends Fragment {

    private static  String serial,brand,description;
    private boolean active;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_datail_serial, tv_data_datail_brand,tv_data_datail_description;

        tv_data_datail_serial = view.findViewById(R.id.tv_data_datail_serial);
        tv_data_datail_brand = view.findViewById(R.id.tv_data_datail_brand );
        tv_data_datail_description = view.findViewById(R.id.tv_data_datail_description);

        tv_data_datail_serial.setText(serial);
        tv_data_datail_brand.setText(brand);
        tv_data_datail_description.setText(description);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(com.example.ftelevision.models.DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    static void receiveData(Bundle bundle){
        TelevisionModel model = (TelevisionModel) bundle.getSerializable("model");
        if(model != null){
            serial = model.getSerial();
            description = model.getDescription();
            brand= model.getBrand();
        }
    }
}