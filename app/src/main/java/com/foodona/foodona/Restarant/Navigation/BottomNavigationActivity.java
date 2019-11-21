package com.foodona.foodona.Restarant.Navigation;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.restaurant.RestaurantSearch;
import com.foodona.foodona.Restarant.restaurant.RestaurentActivity;
import com.foodona.foodona.Restarant.restaurant.RestaurentCart;
import com.foodona.foodona.Restarant.restaurant.UserProfileFragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationActivity extends AppCompatActivity {
    private TextView mTextMessage;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


        loadFragment(new RestaurentActivity());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new RestaurentActivity();
                    break;
                case R.id.navigation_search:
                    fragment = new RestaurantSearch();
                    break;
                case R.id.navigation_user:
                    fragment = new UserProfileFragmentActivity();
                    break;
                case R.id.navigation_shooping:
                    fragment = new RestaurentCart();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
