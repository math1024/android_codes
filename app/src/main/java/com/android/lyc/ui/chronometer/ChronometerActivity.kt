package com.android.lyc.ui.chronometer

import android.os.Bundle
import android.view.View
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.android.lyc.R
import kotlinx.android.synthetic.main.chronometer_activity.*;


/**
 * @author rosetta
 * @date 2020/02/19
 */
class ChronometerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chronometer_activity)

        chronometer = findViewById(R.id.chronometer_test)
chronometer.isCountDown
        chronometer_start.setOnClickListener(this)
        chronometer_end.setOnClickListener { chronometer.stop() }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.chronometer_start -> chronometer.start()
//            R.id.chronometer_end -> chronometer.stop()

        }
    }
}