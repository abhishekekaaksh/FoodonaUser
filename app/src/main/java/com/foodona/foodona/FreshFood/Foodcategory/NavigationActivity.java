package com.foodona.foodona.FreshFood.Foodcategory;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.foodona.foodona.R;

public class NavigationActivity extends AppCompatActivity {
    Fragment fragment;
    ImageView rightimage, imageleft, closeimage, imageright;
    DrawerLayout main_drawer_layout;
    LinearLayout left_drawer_left, right_drawer_right;
    ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        find();
        main_drawer_layout.setDrawerListener(setUpDrawerToggle());

    }


    public void find() {
        main_drawer_layout = findViewById(R.id.main_drawer_layout);
        closeimage = findViewById(R.id.on_layout_menubtn);
        left_drawer_left = findViewById(R.id.left_drawer_layout);
        imageleft = findViewById(R.id.imageleft);
        imageright = findViewById(R.id.imageright);

    }

    private DrawerLayout.DrawerListener setUpDrawerToggle() {

        mtoggle = new ActionBarDrawerToggle(this, main_drawer_layout, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Utils.printLog("onDrawerOpened");
                // UserModal modal=AppPreferences.getSavedUser(MapActivity2.this);
                //  menu_username.setText("Hello "+" "+modal.getName());
            }
        };
        return mtoggle;
    }




    public void open_mannually() {

        if (!main_drawer_layout.isDrawerOpen(left_drawer_left)) {
            main_drawer_layout.openDrawer(Gravity.LEFT);
        } else {
            main_drawer_layout.closeDrawer(Gravity.LEFT);
        }

    }
}
