package com.android.lyc.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.lyc.R
import com.android.lyc.databinding.MyBinding
import com.android.lyc.ui.chronometer.ChronometerActivity
import com.android.lyc.ui.databind.DataBindingActivity

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MyBinding
    private lateinit var viewModel: MainViewModel

    /**
     *  if not use DataBinding return inflater.inflate(R.layout.main_fragment, container, false)
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.setDataBindingBtn("Go DataBindingActivity")
        binding.nextBtn = "LifeCycle ViewModel"
        binding.onClickListener = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.data_binding_btn -> startActivity(
                Intent(activity, DataBindingActivity::class.java)
            )
            R.id.next_func_btn -> {
                startActivity(
                    Intent(activity, ChronometerActivity::class.java))
            }

        }
    }

}
