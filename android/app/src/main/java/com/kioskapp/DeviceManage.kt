package com.kioskapp


import android.app.admin.DevicePolicyManager
import android.app.admin.DeviceAdminReceiver
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.content.Intent

class DeviceManage : DeviceAdminReceiver() {
  companion object {
        fun getComponentName(context: Context): ComponentName {
            return ComponentName(context.applicationContext, DeviceManage::class.java)
        }

        private val TAG = DeviceManage::class.java.simpleName
    }

    override fun onLockTaskModeEntering(context: Context, intent: Intent, pkg: String) {
        super.onLockTaskModeEntering(context, intent, pkg)
//        val dpm = getManager(context)
//        val admin = getWho(context)
//        dpm.addUserRestriction(admin, UserManager.DISALLOW_CREATE_WINDOWS)
        Log.d(TAG, "onLockTaskModeEntering")
    }

    override fun onLockTaskModeExiting(context: Context, intent: Intent) {
        super.onLockTaskModeExiting(context, intent)
        Log.d(TAG, "onLockTaskModeExiting")
    }
}