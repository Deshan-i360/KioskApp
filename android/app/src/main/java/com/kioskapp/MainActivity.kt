package com.kioskapp

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import android.content.IntentFilter
import android.content.Intent
import android.content.ComponentName
import android.content.Context
import android.content.IntentSender
import android.app.admin.DevicePolicyManager
import android.app.admin.SystemUpdatePolicy
import android.app.Activity
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.os.Bundle
import android.os.Build
import android.os.UserManager
import android.os.BatteryManager
import android.view.View
import android.provider.Settings



class MainActivity : ReactActivity() {

  private lateinit var mAdminComponentName: ComponentName
  private lateinit var mDevicePolicyManager: DevicePolicyManager
  


  companion object {
//    const val LOCK_ACTIVITY_KEY = "com.kioskapp.MainActivity"
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
        mAdminComponentName = DeviceManage.getComponentName(this)
        mDevicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        mDevicePolicyManager.removeActiveAdmin(mAdminComponentName)


       }

       public fun isAdmin() = mDevicePolicyManager.isDeviceOwnerApp(packageName)

       public fun setKioskPolicies(enable: Boolean) {
        val isAdmin = isAdmin()
           if (isAdmin) {
               setRestrictions(enable)
               enableStayOnWhilePluggedIn(enable)
               setUpdatePolicy(enable)
               setAsHomeApp(enable)
               setKeyGuardEnabled(enable)
           }
           setLockTask(enable, isAdmin)
           setImmersiveMode(enable)
       }
   
       // region restrictions
        fun setRestrictions(disallow: Boolean) {
           setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, disallow)
           setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, disallow)
           setUserRestriction(UserManager.DISALLOW_ADD_USER, disallow)
           setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, disallow)
           setUserRestriction(UserManager.DISALLOW_ADJUST_VOLUME, disallow)
           mDevicePolicyManager.setStatusBarDisabled(mAdminComponentName, disallow)
       }
   
        fun setUserRestriction(restriction: String, disallow: Boolean) = if (disallow) {
           mDevicePolicyManager.addUserRestriction(mAdminComponentName, restriction)
       } else {
           mDevicePolicyManager.clearUserRestriction(mAdminComponentName, restriction)
       }
       // endregion
   
        fun enableStayOnWhilePluggedIn(active: Boolean) = if (active) {
           mDevicePolicyManager.setGlobalSetting(
               mAdminComponentName,
               Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
               (BatteryManager.BATTERY_PLUGGED_AC
                       or BatteryManager.BATTERY_PLUGGED_USB
                       or BatteryManager.BATTERY_PLUGGED_WIRELESS).toString()
           )
       } else {
           mDevicePolicyManager.setGlobalSetting(mAdminComponentName, Settings.Global.STAY_ON_WHILE_PLUGGED_IN, "0")
       }
   
       private fun setLockTask(start: Boolean, isAdmin: Boolean) {
           if (isAdmin) {
               mDevicePolicyManager.setLockTaskPackages(
                   mAdminComponentName, if (start) arrayOf(packageName) else arrayOf()
               )
           }
           if (start) {
               startLockTask()
           } else {
               stopLockTask()
           }
       }
   
       private fun setUpdatePolicy(enable: Boolean) {
           if (enable) {
               mDevicePolicyManager.setSystemUpdatePolicy(
                   mAdminComponentName,
                   SystemUpdatePolicy.createWindowedInstallPolicy(60, 120)
               )
           } else {
               mDevicePolicyManager.setSystemUpdatePolicy(mAdminComponentName, null)
           }
       }
   
       private fun setAsHomeApp(enable: Boolean) {
           if (enable) {
               val intentFilter = IntentFilter(Intent.ACTION_MAIN).apply {
                   addCategory(Intent.CATEGORY_HOME)
                   addCategory(Intent.CATEGORY_DEFAULT)
               }
               mDevicePolicyManager.addPersistentPreferredActivity(
                   mAdminComponentName, intentFilter, ComponentName(packageName, MainActivity::class.java.name)
               )
           } else {
               mDevicePolicyManager.clearPackagePersistentPreferredActivities(
                   mAdminComponentName, packageName
               )
           }
       }
   
       private fun setKeyGuardEnabled(enable: Boolean) {
           mDevicePolicyManager.setKeyguardDisabled(mAdminComponentName, !enable)
       }

    @Suppress("DEPRECATION")
    private fun setImmersiveMode(enable: Boolean) {
        runOnUiThread {
            if (enable) {
                val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                window.decorView.systemUiVisibility = flags
            } else {
                val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.decorView.systemUiVisibility = flags
            }
        }
    }


//       private fun createIntentSender(context: Context?, sessionId: Int, packageName: String?): IntentSender {
//           val intent = Intent("INSTALL_COMPLETE")
//           if (packageName != null) {
//               intent.putExtra("PACKAGE_NAME", packageName)
//           }
//           val pendingIntent = PendingIntent.getBroadcast(
//               context,
//               sessionId,
//               intent,
//               FLAG_IMMUTABLE
//           )
//           return pendingIntent.intentSender
//       }

    

    // fun enterLockTask() {
    //   instance?.startLockTask()
    // }

    // fun exitLockTask() {
    //     try {
    //       instance?.startLockTask()
    //     } catch (e: Exception) {
    //         println(e.message)
    //     }
    // }

    // override fun onDestroy() {
    //     super.onDestroy()
    //     instance = null
    // }


    



}







