package com.android.quickdial.ui.dial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.quickdial.R

class AddDialerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dialer)
        setTitle(R.string.app_name)
    }
}