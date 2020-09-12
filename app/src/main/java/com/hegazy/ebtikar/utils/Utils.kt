package com.hegazy.ebtikar.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.NavDestination
import com.hegazy.ebtikar.R
import dmax.dialog.SpotsDialog

fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}


fun doToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}


fun checkInternetConnection(
    activity: Activity?,
    action: (() -> Unit)? = null,
    onDisconnected: (() -> Unit)? = null
) {
    activity?.let {
        it.runOnUiThread {
            checkInternetConnection2(activity, action = action, onDisconnected = onDisconnected)
        }
    }
}

fun checkInternetConnection2(
    context: Context?,
    action: (() -> Unit)?,
    onDisconnected: (() -> Unit)?
) {
    if (!checkInternet(context!!)) {
        doToast(context, context.getString(R.string.no_internet_connection))
        onDisconnected?.invoke()
        return
    }
    action?.invoke()
}

fun checkInternet(context: Context): Boolean {
    if (!isInternetConnected(context)) {
        doToast(context, context.getString(R.string.no_internet_connection))
        return false
    }
    return true
}

@SuppressLint("MissingPermission")
fun isInternetConnected(context: Context): Boolean {

    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun setupDialog(activity: Activity?): AlertDialog {
    return SpotsDialog.Builder()
        .setCancelable(false)
        .setTheme(R.style.CustomSpotDialog)
        .setContext(activity).build()
}


fun enableFullScreen(activity: androidx.fragment.app.FragmentActivity?) {
    //show system UI
    activity?.window?.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    //show navigation bar
    activity?.window!!.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}