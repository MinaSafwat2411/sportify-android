package com.project.sportify.utils.connection_utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionUtils(private var context: Context) : IConnectionUtils {
    override val isConnected: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
}