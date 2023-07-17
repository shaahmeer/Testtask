package com.example.testtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.testtask.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int colorSelected = Color.GRAY;
    private int colorUnselected = Color.BLACK;
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        loadFragment(new BlankFragment2());


        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.person) {
                    loadFragment(new BlankFragment2());
                    binding.bottomNavigationView.setItemIconTintList(createColorStateList(colorUnselected, colorSelected));
                } else if (item.getItemId() == R.id.referrals) {
                    loadFragment(new BlankFragment2());
                    binding.bottomNavigationView.setItemIconTintList(createColorStateList(colorSelected, colorUnselected));
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


    private ColorStateList createColorStateList(int selectedColor, int unselectedColor) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_selected},
                new int[]{-android.R.attr.state_selected}
        };

        int[] colors = new int[]{
                selectedColor,
                unselectedColor
        };

        return new ColorStateList(states, colors);
    }
}
