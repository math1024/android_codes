package com.android.lyc.ui.lifecycle

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.lyc.R
import kotlinx.android.synthetic.main.life_cycle_activity.*


/**
 * @author rosetta
 * @date 2020/02/20
 */
class LifeCycleActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    private val mGpsListener: LocationListener = MyLocationListener()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            BindLocationManager.bindLocationListenerIn(this, mGpsListener, applicationContext)
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.life_cycle_activity)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
            BindLocationManager.bindLocationListenerIn(this, mGpsListener, applicationContext)
        }
    }

    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            println(location.latitude.toString() + ", " + location.longitude)
            life_cycle_tv.text = location.latitude.toString() + ", " + location.longitude
        }

        override fun onStatusChanged(
            provider: String,
            status: Int,
            extras: Bundle
        ) {
        }

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(
                this@LifeCycleActivity,
                "Provider enabled: $provider",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }

}