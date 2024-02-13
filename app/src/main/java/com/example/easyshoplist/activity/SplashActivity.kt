//package com.example.easyshoplist.activity
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.view.WindowManager
//import com.example.easyshoplist.Data.AuthViewModel
//import com.example.easyshoplist.R
//
//class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
//        window.setFlags(
//            WindowManager.LayoutParams.FLAGS_CHANGED,
//            WindowManager.LayoutParams.FLAGS_CHANGED
//        )
//        Handler().postDelayed({
//            startActivity(Intent(this, Loginscreen::class.java))
//            finish()
//        }, 2500)
//    }
//}