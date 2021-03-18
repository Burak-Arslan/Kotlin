package com.example.acase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize(){
        try{
            supportActionBar!!.title  = "Maçkolik"
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            findNavController(R.id.nav_host_fragment).navigate(R.id.splashFragment)
        }catch (ex:Exception){
            Toast.makeText(applicationContext,"Beklenmedik bir hata oluştu",Toast.LENGTH_SHORT).show()
            Log.e("MainActivity-initilaze",ex.message.toString())
        }
    }
}