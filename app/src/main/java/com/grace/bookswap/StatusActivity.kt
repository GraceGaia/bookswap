package com.grace.bookswap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StatusActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newarrayList: ArrayList<item>
    lateinit var ImageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var item: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        ImageId = arrayOf(
        )

        heading = arrayOf()

        item = arrayOf()

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newarrayList = arrayListOf<item>()
        getuserdata()
    }

    private fun getuserdata() {
        for (i in ImageId.indices){
            val item = item(ImageId[i],heading[i])
            newarrayList.add(item)
        }

        var adapter = CustomerAdapter(newarrayList)
        newRecyclerView.adapter = adapter
//        adapter.setOnItemClickListener(object : CustomerAdapter.onItemClickListener{
//            override fun onItemClick(position: Int){
//
//               // Toast.makeText(this@StatusActivity,"You clicked on item no. $position",Toast.LENGTH_SHORT).show()
//
//                val intent = Intent(this@StatusActivity, LIstActivity ::class.java)
//                intent.putExtra("heading", newarrayList[position].heading)
//                intent.putExtra("ImageId", newarrayList[position].titleImage)
//                startActivity(intent)
//
//            }
//        })

    }
}

private fun CustomerAdapter.setOnItemClickListener(any: Any) {

}
