// import android.app.admin.DevicePolicyManager
// import android.content.ComponentName
// import android.content.Context

// class DeviceManager(private val context: Context) {

//     // Allowlist two apps.
//     private val KIOSK_PACKAGE = "com.kioskapp"
//     private val PLAYER_PACKAGE = "com.example.player"
//     private val APP_PACKAGES = arrayOf(KIOSK_PACKAGE, PLAYER_PACKAGE)

//     private val dpm: DevicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
//     private val adminName: ComponentName = getComponentName(context)

//     // Function to allowlist apps for lock task mode
//     fun allowlistAppsForLockTask() {
//         dpm.setLockTaskPackages(adminName, APP_PACKAGES)
//     }

//     // Other methods in your DeviceManager class

//     // Function to check if an app is allowlisted for lock task mode
//     fun isAppAllowlistedForLockTask(appPackage: String): Boolean {
//         return dpm.isLockTaskPermitted(appPackage)
//     }

//     // Function to retrieve the list of allowlisted apps for lock task mode
//     fun getAllowlistedAppsForLockTask(): Array<String> {
//         return dpm.getLockTaskPackages(adminName)
//     }
// }
