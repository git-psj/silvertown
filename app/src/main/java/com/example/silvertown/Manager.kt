package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Manager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager)
        //로그아웃
        val btn_msignout = findViewById<Button>(R.id.btn_msignout)
        btn_msignout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        //회원가입
        val btn_menusignup = findViewById<Button>(R.id.btn_menusignup)
        btn_menusignup.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
        //식단 추가
        val btn_menuadd = findViewById<Button>(R.id.btn_menuadd)
        btn_menuadd.setOnClickListener {
            val intent = Intent(applicationContext, MenuAdd::class.java)
            startActivity(intent)
        }
    }
}