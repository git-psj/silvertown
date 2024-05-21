package com.example.silvertown

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class Menu: AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var date: TextView
    private lateinit var menudate: String

    data class Menus(
        val breakfast: String? = null,
        val lunch: String? = null,
        val dinner: String? = null,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_frag)

        //홈으로
        val btn_mhome = findViewById<Button>(R.id.btn_mhome)
        btn_mhome.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }
        //fragment
        //기본
        MenuData(Date(), MenuToday())
        //어제
        findViewById<Button>(R.id.btn_menuy).setOnClickListener {
            MenuData((Date().toInt() - 1).toString(), MenuYesterday())
        }
        //오늘
        findViewById<TextView>(R.id.date).setOnClickListener {
            MenuData(Date(), MenuToday())
        }
        //내일
        findViewById<Button>(R.id.btn_menut).setOnClickListener {
            MenuData((Date().toInt() + 1).toString(), MenuTomorrow())
        }

    }
    private fun Date() : String{
        val localDate: LocalDate = LocalDate.now()
        val form = DateTimeFormatter.BASIC_ISO_DATE
        val current = LocalDateTime.now()
        val dateform = current.format(form)

        return dateform
    }
    private fun MenuData(menudate : String, fragment: Fragment) {
        var bundle = Bundle()
        date = findViewById(R.id.date)
        date.text = menudate

        val menu = db.collection("menu").document(menudate)
        Log.d(ContentValues.TAG, "menudate : ${menudate}")
        menu.get().addOnSuccessListener { documentSnapshot ->
            val menus = documentSnapshot.toObject<Menus>()
            Log.d(ContentValues.TAG, "menus : ${menus}")
            if (menus != null) {
                bundle.putString("breakfast", menus.breakfast.toString())
                bundle.putString("lunch", menus.lunch.toString())
                bundle.putString("dinner", menus.dinner.toString())
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().
                replace(R.id.fragment_view, fragment).commit()
            }
            else {
                bundle.putString("breakfast", "등록된 정보가 없습니다.")
                bundle.putString("lunch", "등록된 정보가 없습니다.")
                bundle.putString("dinner", "등록된 정보가 없습니다.")
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().
                replace(R.id.fragment_view, fragment).commit()
            }
        }
    }
}