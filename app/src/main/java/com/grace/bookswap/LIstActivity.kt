package com.grace.bookswap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LIstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val headingList : TextView = findViewById(R.id.mDetails)
        val bookTitle : TextView = findViewById(R.id.mTitle)
        val bookAuthor : TextView = findViewById(R.id.mAuthor)
        val bookGenre : TextView = findViewById(R.id.mGenre)
        val userContacts : TextView = findViewById(R.id.mUserNumber)



    }
}