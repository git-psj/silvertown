package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var pwd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        email = findViewById(R.id.et_id)
        pwd = findViewById(R.id.et_pw)
        //로그인
        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            signin()
        }
    }
    //로그인 확인
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            GetUser()
        }
    }
    //로그인
    private fun signin() {
        auth?.signInWithEmailAndPassword(email.text.toString(), pwd.text.toString())
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    GetUser()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    //사용자 정보 가져오기
    private fun GetUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            // 사용자 이름 가져오기
            val email = it.email
            val docRef = db.collection("user").document(email.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val userdata = documentSnapshot.toObject<Signup.User>()
                if (userdata != null) {
                    Toast.makeText(this, userdata.name, Toast.LENGTH_LONG).show()
                    if (userdata.name == "관리자") {
                        val intent = Intent(applicationContext, Manager::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}