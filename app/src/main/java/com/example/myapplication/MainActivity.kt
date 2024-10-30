package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.mylibrary.Main

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val res = Main.start()
        Log.e("TAG", "onCreateActivity: $res")
    }
}