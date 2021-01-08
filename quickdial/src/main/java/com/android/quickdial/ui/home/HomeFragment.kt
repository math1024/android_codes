package com.android.quickdial.ui.home

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.dial_tips)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        viewManager = GridLayoutManager(activity, 2)

        var datas = arrayOf("114", "1141", "1142","1143", "12345678909", "18522167325")
        viewAdapter = HomeAdapter(this.requireContext(), datas);

        var divider = DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL)
        divider.setDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.black)))
        recyclerView = root.findViewById<RecyclerView>(R.id.dial_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            divider = divider
        }
        return root
    }
}