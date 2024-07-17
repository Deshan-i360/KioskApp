package com.kioskapp

import android.app.Activity
import android.app.admin.DeviceAdminReceiver
import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.kioskapp.MainActivity
import android.util.Log
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity

class KioskAppModel(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "KioskAppModel"

    @ReactMethod
    fun createCalendarEvent(name: String, location: String) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")
    }


    @ReactMethod
    fun enterLockTask(name: String, location: String) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")
        val mainActivity = currentActivity as? MainActivity
        mainActivity?.setKioskPolicies(true)
    }

    @ReactMethod
    fun exitLockTask(name: String, location: String) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")
        val mainActivity = currentActivity as? MainActivity
        mainActivity?.setKioskPolicies(false)

    }
        
}

