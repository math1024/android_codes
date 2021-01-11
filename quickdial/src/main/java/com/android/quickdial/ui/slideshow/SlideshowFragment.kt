package com.android.quickdial.ui.slideshow

import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R
import com.android.quickdial.ui.database.User


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var recycleView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        recycleView = root.findViewById(R.id.system_contact_list);
        return root
    }

    override fun onStart() {
        super.onStart()
        queryContacts()
    }

    private val users: ArrayList<User> = arrayListOf()
    private fun queryContacts() {
        val resolver: ContentResolver = requireActivity().contentResolver
        val cursor: Cursor? = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, "sort_key", "contact_id",
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), null, null, null
        )
        users.clear()
        while (cursor?.moveToNext() == true) {
            val name = cursor.getString(
                cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )
            )
            val number = cursor.getString(
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            )
            println("name:$name,number:$number")

            var user:User = User(name, number)
            users.add(user)
        }
        println("==========")
        createList()
    }

    private fun createList() {
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(requireContext())
        var adapter: MyAdapter = MyAdapter(users)
        var dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recycleView.adapter = adapter
        recycleView.layoutManager = linearLayoutManager
        recycleView.addItemDecoration(dividerItemDecoration)
    }

    class MyAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<MyAdapter.VH>() {
        class VH(v: View) : RecyclerView.ViewHolder(v) {
            var name: TextView? = null
            var phone: TextView? = null
            init {
                name = v.findViewById<View>(android.R.id.text1) as TextView
                phone = v.findViewById<View>(android.R.id.text2) as TextView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v: View = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_2, parent, false)
            return VH(v)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.name?.text = users[position].name
            holder.phone?.text = users[position].phone
        }

        override fun getItemCount(): Int {
            return users.size
        }
    }
}