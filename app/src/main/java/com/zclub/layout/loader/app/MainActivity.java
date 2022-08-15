package com.zclub.layout.loader.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.zclub.ws.message.databinding.ActivityMainBinding;

/**
 * layout inflater demo app
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }

}