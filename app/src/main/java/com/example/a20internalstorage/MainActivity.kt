package com.example.a20internalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val viewButton = findViewById<Button>(R.id.viewButton)

        saveButton.setOnClickListener(View.OnClickListener {
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            val fileOutputStream:FileOutputStream

            try {
                fileOutputStream = openFileOutput(file,Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            }catch (e:FileNotFoundException){
                e.printStackTrace()
            }catch (e:NumberFormatException){
                e.printStackTrace()
            }catch (e:IOException){
                e.printStackTrace()
            }catch (e:Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"Data Saved...",Toast.LENGTH_SHORT).show()
            fileName.text.clear()
            fileData.text.clear()
        })

         viewButton.setOnClickListener(View.OnClickListener {
             val filename = fileName.text.toString()
             if(filename.toString()!=null&&filename.toString().trim()!="" ){
                 var fileInputStream:FileInputStream?=null
                 fileInputStream = openFileInput(filename)
                 var inputStreamReader:InputStreamReader = InputStreamReader(fileInputStream)
                 val bufferedReader:BufferedReader = BufferedReader(inputStreamReader)
                 val stringBuilder:StringBuilder = StringBuilder()
                 var text:String?=null
                 while ({text=bufferedReader.readLine();text} ()!=null){
                     stringBuilder.append(text)
                 }
                 fileData.setText(stringBuilder.toString()).toString()
             }else{
                    Toast.makeText(applicationContext,"File name can't be Blank",Toast.LENGTH_SHORT).show()
             }
         })

    }
}