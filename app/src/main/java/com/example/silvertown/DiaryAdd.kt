package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DiaryAdd : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var et_title : EditText
    private lateinit var et_date : EditText
    private lateinit var et_text : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diary_add)

        et_title = findViewById(R.id.sd_title)
        et_date = findViewById(R.id.sd_date)
        et_text = findViewById(R.id.sd_text)
        val button = findViewById<Button>(R.id.btn_diarylist)

        //일기 저장
        button.setOnClickListener{
            val add_diary = DiaryData(et_title.text.toString(),
                et_text.text.toString(), et_date.text.toString())

            db.collection("diary")
                .add(add_diary)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, Diary::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "저장 실패.", Toast.LENGTH_LONG).show()
                }
        }
    }
}