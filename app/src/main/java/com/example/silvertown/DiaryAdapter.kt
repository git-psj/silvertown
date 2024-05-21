package com.example.silvertown

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: Context, val data: MutableList<DiaryData>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        // 뷰홀더 클래스 정의
    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.d_title)
        val text: TextView = view.findViewById(R.id.d_text)
        val date: TextView = view.findViewById(R.id.d_date)
    }
    // 뷰홀더 클래스를 가지고 뷰 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemlayout =
            LayoutInflater.from(parent.context).inflate(R.layout.diary_view, parent, false)
        return ItemViewHolder(itemlayout)
    }
    // 아이템뷰 객체에 데이터를 결합해서 아이템뷰를 생성
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val diary = data.get(position)
        holder.title.text = diary.title.toString()
        holder.text.text = diary.text.toString()
        holder.date.text = diary.date.toString()
        // 아이템뷰에 클릭이벤트 리스너(콜백함수 : 이벤트가 발생하면 호출)를 등록하기
        if(position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                // 인텐트 객체 생성하고 액티비티 실행하기
                val intent = Intent(context, PlaceActivity::class.java)
                intent.putExtra("title", diary.title.toString())
                intent.putExtra("text", diary.text.toString())
                intent.putExtra("date", diary.date.toString())
                startActivity(context, intent, null)
            }
        }
    }
    // 생성할 아이템뷰 객체의 갯수(데이터의 갯수)
    override fun getItemCount() = data.size
}