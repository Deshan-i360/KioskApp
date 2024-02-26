// package com.kioskapp

// import android.app.Activity
// import android.app.admin.DeviceAdminReceiver
// import android.app.admin.DevicePolicyManager
// import android.app.ActivityOptions
// import android.content.ComponentName
// import android.content.Context
// import android.content.Intent
// import android.os.Build
// import android.widget.Toast
// import com.facebook.react.bridge.ReactApplicationContext
// import com.facebook.react.bridge.ReactContextBaseJavaModule
// import com.facebook.react.bridge.ReactMethod
// import android.util.Log
// import DeviceManager

// class KioskAppModel(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
//     override fun getName() = "KioskAppModel"

//     @ReactMethod
//     fun createCalendarEvent(name: String, location: String) {
//         Log.d("CalendarModule", "Create event called with name: $name and location: $location")
//     }

//     @ReactMethod
//     fun startLockTask(packageName: String) {
//         val activity = currentActivity
//         if (activity != null) {
//             val dpm = activity.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
//             val adminName = ComponentName(activity, DeviceManager::class.java)
//             if (dpm.isDeviceOwnerApp(activity.packageName)) {
//                 dpm.setLockTaskPackages(adminName, arrayOf(packageName))
//                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                     val intent = activity.packageManager.getLaunchIntentForPackage(packageName)
//                     if (intent != null) {
//                         val options = ActivityOptions.makeBasic()
//                         options.setLockTaskEnabled(true)
//                         activity.startActivity(intent, options.toBundle())
//                     }
//                 } else {
//                     if (dpm.isLockTaskPermitted(packageName)) {
//                         activity.startLockTask()
//                     } else {
// //                        Toast.makeText(reactContext, "App is not allowlisted for lock task mode.", Toast.LENGTH_SHORT).show()
//                     }
//                 }
//             } else {
// //                Toast.makeText(reactContext, "App is not the device owner.", Toast.LENGTH_SHORT).show()
//             }
//         }
//     }

//     @ReactMethod
//     fun stopLockTask() {
//         val activity = currentActivity
//         if (activity != null) {
//             activity.stopLockTask()
//         }
//     }
// }

