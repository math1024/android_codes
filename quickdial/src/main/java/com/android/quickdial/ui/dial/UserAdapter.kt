package com.android.quickdial.ui.dial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R
import com.android.quickdial.database.User

/**
 * @author rosetta
 * @date 2021/01/13
 */
class UserAdapter(private val context: Context,
                  private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.VH>() {
    
    var colors :Array<Int> = arrayOf(android.R.color.holo_blue_light
    , android.R.color.holo_green_light, android.R.color.holo_blue_bright,
        android.R.color.holo_orange_light, android.R.color.holo_red_light,
        android.R.color.holo_blue_light)
    
    class VH(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView? = null
        var phone: TextView? = null
        var cardView: CardView? = null
        init {
            cardView = view.findViewById(R.id.dial_card_view) as CardView
            name = view.findViewById<View>(R.id.name) as TextView
            phone = view.findViewById<View>(R.id.phone) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.dial_list_item_layout  , parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name?.text = users[position].name
        holder.phone?.text = users[position].phone
        holder.cardView?.setCardBackgroundColor(getColor(context, colors[position]))
    }

    override fun getItemCount(): Int {
        return users.size
    }
    
    
}