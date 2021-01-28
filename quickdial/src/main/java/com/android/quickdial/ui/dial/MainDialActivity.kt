package com.android.quickdial.ui.dial

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.quickdial.R
import com.android.quickdial.database.User
import com.android.quickdial.database.UserDatabase
import com.android.quickdial.ui.home.GridItemDecoration
import com.umeng.analytics.MobclickAgent

class MainDialActivity : AppCompatActivity(), UserAdapter.UserCallBack {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dial)

        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PermissionChecker.PERMISSION_GRANTED ||
            PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PermissionChecker.PERMISSION_GRANTED ||
            PermissionChecker.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PermissionChecker.PERMISSION_GRANTED ||
            PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PermissionChecker.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.INTERNET
                ), 100
            )
        }
        initData()
        initView()
    }

    private fun initData() {
        val users = UserDatabase.getDatabase(this).userDao().all
        arrayList.clear()
        if (users != null) {
            for (user in users) {
                println(user)
                user?.let { arrayList.add(it) }
            }
        }

        val size = arrayList.size
        for (index in 1..(8 - size)) {
            var user = User("", "phone$index")
            arrayList.add(user)
        }
    }

    var arrayList: ArrayList<User> = ArrayList(6)
    private fun initView() {
        setTitle(R.string.quick_dial)
        recyclerView = findViewById(R.id.dial_recycler_view)
        adapter = UserAdapter(this, arrayList)
        adapter.userCallBack = this
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            initData()
            adapter.notifyDataSetChanged()
        }
    }

    override fun callback(userId: Int) {
        var intent = Intent(this, AddDialerActivity::class.java)
        intent.putExtra(AddDialerActivity.USER_ID, userId)
        startActivityForResult(intent, 1000);
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}