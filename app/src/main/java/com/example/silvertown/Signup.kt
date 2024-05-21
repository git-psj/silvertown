package com.example.silvertown

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Signup : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var store: FirebaseFirestore
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var pwd: EditText
    private lateinit var birth: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        auth = Firebase.auth
        name = findViewById(R.id.et_sname)
        email = findViewById(R.id.et_sid)
        pwd = findViewById(R.id.et_spw)
        birth = findViewById(R.id.et_sbirth)

        val button = findViewById<Button>(R.id.btn_signup)

        button.setOnClickListener {
            signUp()
        }
        val btn_smanager = findViewById<Button>(R.id.btn_smanager)
        btn_smanager.setOnClickListener {
            val intent = Intent(applicationContext, Manager::class.java)
            startActivity(intent)
        }
    }

    data class User (var name : String? = null, var birth : String? = null)

    //회원가입 함수
    private fun signUp() {
        auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Firebase DB에 저장 되어 있는 계정이 아닐 경우
                    //입력한 계정을 새로 등록한다
                    val user = User(name.text.toString(), birth.text.toString())
                    db.collection("user").document(email.text.toString()).set(user)
                    Toast.makeText(this, "완료", Toast.LENGTH_LONG).show()
                    //goToMainActivity(task.result?.user)
                    val intent = Intent(applicationContext, Manager::class.java)
                    startActivity(intent)
                } else if (task.exception?.message.isNullOrEmpty()) {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                } else {
                    //입력한 계정 정보가 이미 Firebase DB에 있는 경우
                    //signIn(email, password)
                }
            }
    }
}