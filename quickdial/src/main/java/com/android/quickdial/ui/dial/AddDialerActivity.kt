package com.android.quickdial.ui.dial

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.quickdial.R
import com.android.quickdial.database.User
import com.android.quickdial.database.UserDatabase
import kotlinx.android.synthetic.main.activity_add_dialer.*

class AddDialerActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val USER_ID = "userId"
    }

    private var userId = -1
    private var currentUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dialer)
        setTitle(R.string.contact_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()

        userId = intent.getIntExtra(USER_ID, -1)
        if (userId != -1) {
            currentUser = UserDatabase.getDatabase(this).userDao().findUser(userId)
            edit_name_et?.setText(currentUser?.name, TextView.BufferType.EDITABLE);
            edit_phone_et?.setText(currentUser?.phone, TextView.BufferType.EDITABLE);
        }
    }

    private fun initView() {
        save_contact.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.save_contact -> saveContact()
        }
    }

    private fun saveContact() {
        val name = edit_name_et.text.toString()
        val phone = edit_phone_et.text.toString()
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(phone)) {
            if (userId != -1) {
                currentUser?.name = name
                currentUser?.phone = phone
                UserDatabase.getDatabase(this).userDao().updateUser(currentUser!!)
            } else {
                var user = User(name, phone)
                UserDatabase.getDatabase(this).userDao().insertUser(user)
            }
        }
        setResult(RESULT_OK)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_dialer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_import -> selectContact()
            R.id.action_del -> delQuickDial()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delQuickDial() {
        if (currentUser != null) {
            UserDatabase.getDatabase(this).userDao().delete(currentUser)
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun selectContact() {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1001 -> queryContactData(data)
            }
        }
    }

    private fun queryContactData(data: Intent?) {
        var uri: Uri = Uri.parse(data?.data.toString())
        var cursor = contentResolver.query(
            uri, arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, "sort_key", "contact_id",
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), null, null, null
        )

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
            edit_name_et?.setText(name, TextView.BufferType.EDITABLE);
            edit_phone_et?.setText(number, TextView.BufferType.EDITABLE);
        }
    }
}