package com.android.lyc.ui.chronometer

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.lyc.R
import com.android.lyc.viewmodel.ChronometerViewModel
import com.android.lyc.viewmodel.LiveDataTimerViewModel
import kotlinx.android.synthetic.main.chronometer_activity.*


/**
 * @author rosetta
 * @date 2020/02/19
 */
class ChronometerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var chronometer: Chronometer
    lateinit var liveDataTimerViewModel : LiveDataTimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chronometer_activity)

        chronometer = findViewById(R.id.chronometer_test)
        chronometer_start.setOnClickListener(this)
        chronometer_end.setOnClickListener { chronometer.stop() }

        val chronometerViewModel: ChronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)

        if (chronometerViewModel.getStartTime() == null) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.setStartTime(startTime)
            chronometer.base = startTime
        } else {
            chronometer.base = SystemClock.elapsedRealtime()
        }

        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)

        val elapsedTimeObserver: Observer<Long> = Observer { aLong ->
            val newText: String = this@ChronometerActivity.resources.getString(R.string.seconds, aLong)
            println(newText)
            live_data_tv.text = newText
        }
        liveDataTimerViewModel.getElapsedTime()?.observe(this, elapsedTimeObserver)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.chronometer_start -> chronometer.start()
            R.id.chronometer_end -> chronometer.stop()
        }
    }
}