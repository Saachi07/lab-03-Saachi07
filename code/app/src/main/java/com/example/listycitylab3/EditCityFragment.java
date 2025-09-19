package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    private City city;
    public static EditCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable("city");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_city, null);

        EditText cityNameInput = view.findViewById(R.id.edit_city_name);
        EditText provinceInput = view.findViewById(R.id.edit_province);

        if (city != null) {
            cityNameInput.setText(city.getName());
            provinceInput.setText(city.getProvince());
        }

        builder.setView(view)
                .setTitle("Add/Edit City")
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Save", (dialog, which) -> {
                    city.setName(cityNameInput.getText().toString());
                    city.setProvince(provinceInput.getText().toString());
                    ((MainActivity) requireActivity()).refreshCityList();
                });

        return builder.create();
    }
}
