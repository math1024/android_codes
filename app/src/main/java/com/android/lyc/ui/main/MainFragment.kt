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
import com.android.lyc.ui.LongTextActivity
import com.android.lyc.ui.chronometer.ChronometerActivity
import com.android.lyc.ui.databind.DataBindingActivity
import com.android.lyc.ui.lifecycle.LifeCycleActivity
import com.android.lyc.ui.navi.NavigationActivity
import com.android.lyc.ui.paging.SearchRepoActivity
import com.android.lyc.ui.vmsync.VmSyncActivity

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
        binding.nextBtn = "live Data"
        binding.liveDataBtn.text = "Chronometer"
        binding.lifeCycleBtn.text = "Life Cycle"
        binding.syncViewModelBtn.text = "Sync ViewModel"
        binding.navigationBtn.text = "Navigation"
        binding.pagingBtn.text = "Paging"
        binding.longTextBtn.text = "Long Text"
        binding.onClickListener = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.data_binding_btn -> startActivity(Intent(activity, DataBindingActivity::class.java)
            )
            R.id.live_data_btn -> {
                startActivity(Intent(activity, ChronometerActivity::class.java))
            }
            R.id.life_cycle_btn -> {
                startActivity(Intent(activity, LifeCycleActivity::class.java))
            }
            R.id.sync_view_model_btn -> {
                startActivity(Intent(activity, VmSyncActivity::class.java))
            }
            R.id.navigation_btn -> {
                startActivity(Intent(activity, NavigationActivity::class.java))
            }
            R.id.paging_btn -> startActivity(Intent(activity, SearchRepoActivity::class.java))
            R.id.long_text_btn -> {
                startActivity(Intent(activity, LongTextActivity::class.java))
            }

        }
    }

}
