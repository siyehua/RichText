package com.tencent.testrichtext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        findViewById<View>(R.id.btSee).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<View>(R.id.btWeb).setOnClickListener {
            val intent = Intent(this, CommonH5Activity::class.java)
            intent.putExtra("url", "http://www.mafengwo.cn/i/7426580.html")
            startActivity(intent)
        }
    }
}