package com.example.acase.ui.splash.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.acase.R
import java.util.*
import kotlin.concurrent.timerTask

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialize()
    }

    private fun initialize(){
        try{
            Timer().schedule(timerTask {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }, 3500)
        }catch (ex:Exception){
            Toast.makeText(requireContext(),"Beklenmedik bir hata oluştu", Toast.LENGTH_SHORT).show()
            Log.e("SplashFragme initilaze",ex.message.toString())
        }
    }
}