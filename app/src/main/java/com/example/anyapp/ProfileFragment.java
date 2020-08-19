package com.example.anyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Joppe
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView textAccountUsername, textAccountEmail;

    private Button buttonChangeDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_profile, container, false);

        textAccountUsername = view.findViewById(R.id.up_textAccountUsername);
        textAccountEmail = view.findViewById(R.id.up_textAccountEmail);

        buttonChangeDetails = view.findViewById(R.id.up_buttonChangeDetails);

        textAccountUsername.setText(SharedPrefManager.getInstance(getActivity()).getUsername());
        textAccountEmail.setText(SharedPrefManager.getInstance(getActivity()).getEmail());

        buttonChangeDetails.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.up_buttonChangeDetails:
                fragment = new ChangeDetailsFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack("tag");
        transaction.commit();
    }
}
