package com.example.nobelabookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nobelabookreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //login
        binding.loginBtn.setOnClickListener {
            //will do later
        }
        //continue without login
        binding.skipBtn.setOnClickListener {
            //will do later
        }
    }
}