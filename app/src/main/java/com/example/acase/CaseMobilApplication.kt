package com.example.acase

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CaseMobilApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        Stetho.initializeWithDefaults(this)
    }
}
