package com.android.quickdial.ui.dial

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R
import com.android.quickdial.database.User

/**
 * @author rosetta
 * @date 2021/01/13
 */
class UserAdapter(private val activity: Activity,
                  private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.VH>() {

    var colors :Array<Int> = arrayOf(android.R.color.holo_blue_light
    , android.R.color.holo_green_light, android.R.color.holo_blue_bright,
        android.R.color.holo_orange_light, android.R.color.holo_red_light,
        android.R.color.holo_blue_light)
    
    class VH(view: View) : RecyclerView.ViewHolder(view) {
        var nameTv: TextView? = null
        var phoneTv: TextView? = null
        var addIv: ImageView? = null
        var cardView: CardView? = null
        init {
            cardView = view.findViewById(R.id.dial_card_view) as CardView
            nameTv = view.findViewById<View>(R.id.name) as TextView
            addIv = view.findViewById<View>(R.id.dial_add) as ImageView
            phoneTv = view.findViewById<View>(R.id.phone) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.dial_list_item_layout  , parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (TextUtils.isEmpty(users[position].name)) {
            holder.addIv?.visibility = View.VISIBLE
            holder.nameTv?.visibility = View.GONE
            holder.phoneTv?.visibility = View.GONE
            holder.addIv?.setOnClickListener {
                var intent = Intent(activity, AddDialerActivity::class.java)
                activity.startActivity(intent)
            }
        } else {
            holder.addIv?.visibility = View.GONE
            holder.nameTv?.visibility = View.VISIBLE
            holder.phoneTv?.visibility = View.VISIBLE
            holder.nameTv?.text = users[position].name
            holder.phoneTv?.text = users[position].phone
            holder.cardView?.setOnClickListener {
                var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + users[position].phone))
                activity.startActivityForResult(intent, 1000);
            }
        }

        holder.cardView?.setCardBackgroundColor(getColor(activity, colors[position]))
    }

    override fun getItemCount(): Int {
        return users.size
    }
}