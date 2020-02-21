package com.android.lyc.ui.vmsync

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.lyc.R
import com.android.lyc.viewmodel.SeekBarViewModel


/**
 * @author rosetta
 * @date 2020/02/21
 */
class VmSyncFragment : Fragment(){
    lateinit var seekBarViewModel: SeekBarViewModel
    lateinit var seekBar: SeekBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root:View = inflater.inflate(R.layout.vm_sync_fragment, container, false)
        seekBarViewModel = ViewModelProviders.of(requireActivity()).get(SeekBarViewModel::class.java)
        seekBar = root.findViewById(R.id.seekBar)
        subscribeSeekBar()
        return root
    }

    private fun subscribeSeekBar() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    println("Progress changed!")
                    seekBarViewModel.seekBarValue.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        seekBarViewModel.seekBarValue.observe(
            requireActivity(), Observer<Int> { value ->
                if (value != null) {
                    println("value---$value")
                    seekBar.progress = value
                }
            })
    }
}