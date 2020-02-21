package com.android.lyc.ui.lifecycle

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 * @author rosetta
 * @date 2020/02/20
 */
class BindLocationManager {
    companion object {
        fun bindLocationListenerIn(
            lifecycleOwner: LifecycleOwner?,
            listener: LocationListener,
            context: Context
        ) {
            BoundLocationListener(lifecycleOwner, listener, context)
        }


    }

    @SuppressLint("MissingPermission")
    class BoundLocationListener(
        lifecycleOwner: LifecycleOwner?,
        private val mListener: LocationListener,
        private val mContext: Context
    ) : LifecycleObserver {

        init {
            lifecycleOwner?.lifecycle?.addObserver(this)
        }

        private var mLocationManager: LocationManager? = null

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            mLocationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                mListener
            )
            println("BoundLocationMgr Listener added")
            // Force an update with the last location, if available.
            val lastLocation: Location? =getLastKnownLocation()
            if (lastLocation != null) {
                mListener.onLocationChanged(lastLocation)
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            if (mLocationManager == null) {
                return
            }
            mLocationManager!!.removeUpdates(mListener)
            mLocationManager = null
            println("BoundLocationMgr Listener removed")
        }

        private fun getLastKnownLocation(): Location? {
            val providers = mLocationManager?.allProviders
            var bestLocation: Location? = null
            for (provider in providers!!) {
                val l = mLocationManager?.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) { // Found best last known location: %s", l);
                    bestLocation = l
                }
            }
            return bestLocation
        }
    }


}