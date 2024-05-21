package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlaceActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        // 인텐트에서 전달받은 데이터를 가져오기
        val title= intent.getStringExtra("title")
        val text= intent.getStringExtra("text")
        val date= intent.getStringExtra("date")
        val button = findViewById<Button>(R.id.btn_diarylist)

        val d_title = findViewById<TextView>(R.id.sd_title)
        d_title.text = title
        val d_text = findViewById<TextView>(R.id.sd_text)
        d_text.text = text
        val d_date = findViewById<TextView>(R.id.sd_date)
        d_date.text = date

        button.setOnClickListener {
            val intent = Intent(applicationContext, Diary::class.java)
            startActivity(intent)
        }



    }
}