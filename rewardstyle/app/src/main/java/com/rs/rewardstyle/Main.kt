package com.rs.rewardstyle

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rs.rewardstyle.model.Ltk
import java.util.*

class Main: AppCompatActivity(){


    private var curIndex = 0
    private var adapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: MutableList<Ltk> = ArrayList()
        list.addAll(Utility.getArray(Utility.getJsonFromAssets(this, "storage.json")))
        val next = findViewById<Button>(R.id.next)
        val prev = findViewById<Button>(R.id.prev)
        val id = findViewById<TextView>(R.id.lkt_id)
        val img = findViewById<ImageView>(R.id.hero_img)
        val listView = findViewById<ListView>(R.id.products_list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list[curIndex].product_ids)
        listView.adapter = adapter
        SetNewData(list, id, img)
        next.setOnClickListener {
            curIndex++
            SetNewData(list, id, img)
        }
        prev.setOnClickListener {
            curIndex--
            SetNewData(list, id, img)
        }
    }

    private fun SetNewData(list: List<Ltk>, id: TextView, img: ImageView) {
        if (!list.isEmpty() && curIndex < list.size && curIndex >= 0) {
            id.text = list[curIndex].id
            Glide.with(this)
                    .load(list[curIndex].hero_image)
                    .into(img)
            adapter!!.clear()
            adapter!!.addAll(list[curIndex].product_ids)
            adapter!!.notifyDataSetChanged()
        }
    }
}
