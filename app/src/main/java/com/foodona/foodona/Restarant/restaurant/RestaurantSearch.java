package com.foodona.foodona.Restarant.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.foodona.foodona.R;

public class RestaurantSearch extends Fragment {

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_restaurant_search, container, false);


        return rootView;

    }
}
