package com.android.lyc.ui

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.lyc.R
import kotlinx.android.synthetic.main.long_text_activity.*
import java.io.*


/**
 * @author rosetta
 * @date 2020/08/04
 */

class LongTextActivity : AppCompatActivity(), View.OnClickListener {
    var buffer = StringBuffer();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.long_text_activity)
        initBuffer()
        long_text.movementMethod = ScrollingMovementMethod()
        long_text.text = "max length"
        load_text.setOnClickListener(this)
        write_text.setOnClickListener(this)
        read_text.setOnClickListener(this)
    }

    private fun initBuffer() {
        buffer.delete(0, buffer.length);
        for (i in 1..100) {
            buffer.append(i);
        }
    }

    private fun loadText() {
        print("loadText");


        long_text.text = (buffer.length).toString() + " : " + buffer;
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.load_text -> loadText()
            R.id.write_text -> saveFile()
            R.id.read_text -> readFile()
        }
    }

    private fun readFile() {
        Log.w("long", "readFile")
        var result: String? = ""
        val fileName = File(Environment.DIRECTORY_DOWNLOADS,"long.txt")
        var fileReader: FileReader? = null
        var bufferedReader: BufferedReader? = null
        try {
            fileReader = FileReader(fileName)
            bufferedReader = BufferedReader(fileReader)
            try {
                var read: String? = null
                while ({ read = bufferedReader.readLine();read }() != null) {
                    result = result + read + "\r\n"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            bufferedReader?.close()
            fileReader?.close()
        }
        Log.w("long", "读取出来的文件内容是：\r\n$result")
    }

    private fun saveFile() {
        var path = Environment.DIRECTORY_DOWNLOADS
        Log.w("long", path)
        Log.w("long", "external " + Environment.getExternalStorageDirectory())
//        File file=new File(Environment.getExternalStorageDirectory(), filename)
        var file = File(Environment.DIRECTORY_DOWNLOADS,"long.txt")
        if(!file.exists()){
            file.mkdirs()
        }

        Log.w("long", file.absolutePath);

//        try {
////            val outputStream = FileOutputStream(file)
//            val outputStream: FileOutputStream =
//                this.openFileOutput(file.name, Context.MODE_PRIVATE)
//            Log.w("long", "array: " + buffer.toString().toByteArray());
//            outputStream.write(buffer.toString().toByteArray())
//            outputStream.flush()
//            outputStream.close()
//            Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show()
//            Log.e("long", "Successful")
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
        try {
//            if (!file.parentFile.exists()) {
//                thisFile.parentFile.mkdirs()
//            }
            val fw = FileWriter(file.absolutePath, true)
            fw.write(buffer.toString())
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}