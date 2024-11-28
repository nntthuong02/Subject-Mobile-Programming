package vn.edu.hust.studentman

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter2(
    context: Context,
    private val resource: Int,
    private val students: MutableList<StudentModel>
) : ArrayAdapter<StudentModel>(context, resource, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        // Lấy thông tin sinh viên
        val student = getItem(position)

        // Ánh xạ các View
        val textName = view.findViewById<TextView>(R.id.text_student_name)
        val textId = view.findViewById<TextView>(R.id.text_student_id)

        // Gán dữ liệu
        textName.text = student?.studentName
        textId.text = student?.studentId

        return view
    }
}
