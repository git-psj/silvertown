package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuAdd : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var menu_date : EditText
    private lateinit var add_breakfast : EditText
    private lateinit var add_lunch : EditText
    private lateinit var add_dinner : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_add)

        menu_date = findViewById(R.id.menu_date)
        add_breakfast = findViewById(R.id.add_breakfast)
        add_lunch = findViewById(R.id.add_lunch)
        add_dinner = findViewById(R.id.add_dinner)

        val btn_mmanager = findViewById<Button>(R.id.btn_mmanager)
        btn_mmanager.setOnClickListener {
            val intent = Intent(applicationContext, Manager::class.java)
            startActivity(intent)
        }

        val button = findViewById<Button>(R.id.btn_addmenu)

        button.setOnClickListener{
            val menu = Menu.Menus(add_breakfast.text.toString(),
                add_lunch.text.toString(), add_dinner.text.toString())
            db.collection("menu").document(menu_date.text.toString()).set(menu)
            val intent = Intent(applicationContext, Manager::class.java)
            startActivity(intent)

        }

    }
}