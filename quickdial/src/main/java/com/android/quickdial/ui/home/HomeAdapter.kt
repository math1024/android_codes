package com.android.quickdial.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R


/**
 * @author rosetta
 * @date 2021/01/06
 */
class HomeAdapter(private val context: Context, private val phoneList: Array<String>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list_item_layout, parent, false) as View
        view.setOnClickListener {
//            print(textView.text)
            var intent= Intent()
//            intent.action = (Intent.ACTION_DIAL)
//            intent.data = (Uri.parse("tel:" + textView.text))
//            context.startActivity(intent)
//            intent = Intent(Intent.ACTION_CALL,Uri.parse("tel:" + ((TextView)view.findViewById(R.id.name)).text))
//            context.startActivity(intent);


        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.name).text = phoneList[position]
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }


}