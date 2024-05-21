package com.example.silvertown

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Diary : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var date: TextView
    private lateinit var d_title: TextView
    private lateinit var d_text: TextView
    private lateinit var d_date: TextView
    private val data = mutableListOf<DiaryData>()

    data class Diaries(
        val title: String? = null,
        val text: String? = null,
        val date: String? = null
    ) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diary)

        //오늘 날짜 가져오기
        val localDate: LocalDate = LocalDate.now()
        val form = DateTimeFormatter.BASIC_ISO_DATE
        val current = LocalDateTime.now()
        val dateform = current.format(form)
        val button = findViewById<ImageButton>(R.id.add_diary)

        date = findViewById(R.id.d_nowdate)
        date.text = dateform

        //홈으로
        val btn_dhome = findViewById<Button>(R.id.btn_dhome)
        btn_dhome.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }

        //일기 작성 페이지로 이동
        button.setOnClickListener {
            val intent = Intent(applicationContext, DiaryAdd::class.java)
            startActivity(intent)
        }
        db.collection("diary").orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val diary = document.toObject<DiaryData>()
                    data.add(DiaryData(diary.title as String, diary.text as String,
                        diary.date as String))
                    Log.d(TAG, "저장완료")
                    Log.d(TAG, "${data}")
                }
                val adapter = ItemAdapter(this, data) // adapter 객체 생성
                val recyclerView = findViewById<RecyclerView>(R.id.diary_recycler)
                val manager = LinearLayoutManager(this)
                manager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = manager
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

}