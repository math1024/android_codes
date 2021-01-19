package com.android.quickdial.ui.dial

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.quickdial.R
import com.android.quickdial.database.User
import com.android.quickdial.database.UserDatabase
import kotlinx.android.synthetic.main.activity_add_dialer.*

class AddDialerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dialer)
        setTitle(R.string.contact_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
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
            var user = User(name, phone)
            UserDatabase.getDatabase(this).userDao().insertUser(user);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_dialer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_import -> selectContact()
            R.id.action_del -> Toast.makeText(this, "del", Toast.LENGTH_SHORT).show()
            R.id.action_modify -> Toast.makeText(this, "modify", Toast.LENGTH_SHORT).show()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun selectContact() {
        var intent =  Intent(Intent.ACTION_GET_CONTENT)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when(requestCode) {
                1000 -> queryContactData(data)
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