package com.foodona.foodona;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.foodona.foodona.Restarant.Navigation.BottomNavigationActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView cabbooking;
    ImageView fooddelivery;
    ImageView freshProducts;
    ImageView utilityservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
    }

    public void find() {
        cabbooking = findViewById(R.id.cabbooking);
        fooddelivery = findViewById(R.id.fooddelivery);
        freshProducts = findViewById(R.id.freshProducts);
        utilityservice = findViewById(R.id.utilityservice);
        cabbooking.setOnClickListener(this);
        fooddelivery.setOnClickListener(this);
        freshProducts.setOnClickListener(this);
        utilityservice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == fooddelivery) {
            startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
            finish();
        }
    }
}
