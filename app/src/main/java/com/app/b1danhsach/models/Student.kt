package com.app.b1danhsach.models

data class Student(
    val mssv: String,
    val name: String,
    val gender: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    val province: String,
    val district: String,
    val ward: String,
    val hobbies: List<String>
)
