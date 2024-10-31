package com.app.b1danhsach.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.b1danhsach.databinding.ItemStudentBinding
import com.app.b1danhsach.models.Student

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.binding.tvMssv.text = student.mssv
        holder.binding.tvName.text = student.name
    }

    override fun getItemCount(): Int = students.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}
