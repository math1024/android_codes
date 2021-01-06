package com.android.quickdial.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R

/**
 * @author rosetta
 * @date 2021/01/06
 */
class HomeAdapter(private val phoneList: Array<String>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list_item_layout, parent, false) as TextView

        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = phoneList[position]
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }


}