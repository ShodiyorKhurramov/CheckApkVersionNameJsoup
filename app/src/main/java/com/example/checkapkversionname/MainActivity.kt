package com.example.checkapkversionname

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews(){
        val tv_update=findViewById<TextView>(R.id.tv_update)
        val tv_newVersion=findViewById<TextView>(R.id.tv_newVersion)
        val tv_currentVersion=findViewById<TextView>(R.id.tv_currentVersion)
        tv_newVersion.text=newVersion()
        tv_currentVersion.text=curentNmae()

       val bt_check=findViewById<Button>(R.id.bt_check)
        bt_check.setOnClickListener{
            tv_update.text=updateCheck(newVersion(),curentNmae())
        }

    }

    fun newVersion():String{
        val newVersion=NewVersion().execute().get()

        return newVersion
    }

    fun curentNmae():String{
        var currentVeersion :String ?= null
        try {

            currentVeersion=this.packageManager
                .getPackageInfo(this.packageName, 0).versionName

        } catch (e: PackageManager.NameNotFoundException) {

        }
        return currentVeersion !!
    }

    fun updateCheck(newVersion: String, currentVersion: String): String {
        val newVersion = newVersion.split(".")
        val currentVersion = currentVersion.split(".")
        if (newVersion[0] > currentVersion[0] || newVersion[1] > currentVersion[1]) {
            return "Update"
        }
        return "No Update"
    }
}