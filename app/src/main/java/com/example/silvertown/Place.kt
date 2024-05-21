package com.example.silvertown

import java.io.Serializable
// Serializable 인터페이스를 구현하는 클래스. 객체를 직렬화된 포맷으로 저장할 수 있음
data class Place(var title: String, var addr: String,
                 var desc: String) : Serializable