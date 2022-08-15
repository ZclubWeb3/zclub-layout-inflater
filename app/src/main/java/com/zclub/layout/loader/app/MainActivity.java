package com.zclub.layout.loader.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mrcd.inflater.AsyncLayoutInflater;
import com.zclub.layout.loader.app.databinding.ActivityMainBinding;

/**
 * layout inflater demo app
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AsyncLayoutInflater mHighPerformanceInflater ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        mHighPerformanceInflater = new AsyncLayoutInflater(this);
        // load and cache the will_inflate_layout.xml
        mHighPerformanceInflater.inflateAndCache(R.layout.will_inflate_layout, binding.getRoot());
        // show the loaded views
        binding.loadLayoutBtn.setOnClickListener((v) -> {
            // take cached view from ViewCache with specified view id
            final View cachedView = mHighPerformanceInflater.getViewCache().take(R.layout.will_inflate_layout);
            if ( cachedView != null ) {
                binding.viewContainer.addView(cachedView);
            }
        });
    }
}