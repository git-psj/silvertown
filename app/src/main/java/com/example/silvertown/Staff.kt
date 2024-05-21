package com.example.silvertown

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged

class Staff: AppCompatActivity() {
    private lateinit var spw : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.staff)

        spw = findViewById(R.id.etp_sok)

        spw.addTextChangedListener {
            val sok = spw.text.toString()
            val button = findViewById<Button>(R.id.btn_sok)
            button.setOnClickListener {
                if(sok == "1234") {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }




    }
}