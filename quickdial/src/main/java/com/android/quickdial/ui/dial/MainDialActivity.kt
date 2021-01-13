package com.android.quickdial.ui.dial

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R
import com.android.quickdial.database.User
import com.android.quickdial.ui.home.GridItemDecoration

class MainDialActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dial)

        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PermissionChecker.PERMISSION_GRANTED ||
            PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CONTACTS
                ), 100
            )
        }

        initView()
    }

    private fun initView() {
        setTitle(R.string.app_name)
        recyclerView = findViewById(R.id.dial_recycler_view)
        var arrayList: ArrayList<User> = ArrayList()
//        for (index in 1..6){
//            var user: User = User("name$index", "phone$index")
//            arrayList.add(user)
//        }
        var adapter = UserAdapter(this, arrayList)
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }
}