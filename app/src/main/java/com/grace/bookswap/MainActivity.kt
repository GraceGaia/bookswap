package com.grace.bookswap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var webView:WebView? = null
    var buttonSignUp:Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.BookWeb)
        var mySettings = webView!!.settings
        mySettings.javaScriptEnabled = true
        webView!!.loadUrl("file:///android_asset/index.html")

        buttonSignUp = findViewById(R.id.btnSignUp)

        buttonSignUp!!.setOnClickListener {
            var usersIntent = Intent(this,SignInActivity::class.java)
            startActivity(usersIntent)
        }







    }
}