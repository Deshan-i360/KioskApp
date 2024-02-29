package com.kioskapp

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import android.content.IntentFilter
import android.content.Intent
import android.content.ComponentName
import android.content.Context
import android.app.admin.DevicePolicyManager
import android.app.Activity
import android.os.Bundle

class MainActivity : ReactActivity() {


  companion object {
    private var instance: MainActivity? = null

    fun getInstance(): MainActivity? {
        return instance
    }
}
  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  override fun getMainComponentName(): String = "KioskApp"

  /**
   * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
   * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
   */
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        instance = this
    }

    fun enterLockTask() {
      instance?.startLockTask()
    }

    fun exitLockTask() {
        try {
          instance?.startLockTask()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}





fun enterLockTask( context: MainActivity, dpm: DevicePolicyManager) {

   var texto = if (dpm.isLockTaskPermitted(context.packageName)) "app is allowed to lock"
    else "app is not allowed to lock"
   
            context.startLockTask()
    
}


fun exitLockTask( context: MainActivity) {
   
        try {
            context.stopLockTask()
        }
        catch (e: Exception) {
        }
}






