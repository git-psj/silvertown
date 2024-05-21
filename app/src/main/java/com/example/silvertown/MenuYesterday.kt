package com.example.silvertown

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.toObject

class MenuYesterday : Fragment() {

    // 이 함수는 프래그먼트를 생성할 때 자동으로 호출
    // onAttach() -> onCreate() -> onCreateView() -> onViewCreated() 순서대로 호출
    override fun onCreateView(
        inflater: LayoutInflater,      // 프래그먼트 레이아웃을 인플레이트 시키기 위해
        container: ViewGroup?,         // 프래그먼트를 가지는 액티비티의 뷰그룹(fragment)
        savedInstanceState: Bundle?    // 임시값 저장 객체
    ): View? {
        var view = inflater.inflate(R.layout.menu_fragyesterday, container, false)
        var menu_b = view.findViewById<TextView>(R.id.menu_breakfast)
        menu_b.text = arguments?.getString("breakfast")
        var menu_l = view.findViewById<TextView>(R.id.menu_lunch)
        menu_l.text = arguments?.getString("lunch")
        var menu_d = view.findViewById<TextView>(R.id.menu_dinner)
        menu_d.text = arguments?.getString("dinner")

    return view
}
}