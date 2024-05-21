package com.example.silvertown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var name : TextView
    private lateinit var h_date : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        //오늘 날짜
        val localDate: LocalDate = LocalDate.now()
        val form = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일 ")
            .withLocale(Locale.forLanguageTag("ko"))
        val current = LocalDateTime.now()
        val dateform = current.format(form)

        name = findViewById(R.id.name)
        h_date = findViewById(R.id.h_date)
        h_date.text = dateform

        val user = Firebase.auth.currentUser
        user?.let {
            // 사용자 이름 가져오기
            val email = it.email
            val docRef = db.collection("user").document(email.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val userdate = documentSnapshot.toObject<Signup.User>()
                if (userdate != null) {
                    name.text = userdate.name
                }
            }
        }
        //로그아웃
        val btn_signout = findViewById<Button>(R.id.btn_signout)
        btn_signout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        //안내
        val btn_info = findViewById<ImageButton>(R.id.btn_info)
        btn_info.setOnClickListener {
            val intent = Intent(applicationContext, Information::class.java)
            startActivity(intent)
        }
        //식단
        val btn_menu = findViewById<ImageButton>(R.id.btn_menu)
        btn_menu.setOnClickListener {
            val intent = Intent(applicationContext, Menu::class.java)
            startActivity(intent)
        }
        //일기
        val btn_diary = findViewById<ImageButton>(R.id.btn_diary)
        btn_diary.setOnClickListener {
            val intent = Intent(applicationContext, Diary::class.java)
            startActivity(intent)
        }
        //직원호출
        val btn_staff = findViewById<ImageButton>(R.id.btn_staff)
        btn_staff.setOnClickListener {
            val intent = Intent(applicationContext, Staff::class.java)
            startActivity(intent)
        }

    }
}