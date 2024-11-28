package vn.edu.hust.studentman

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Giả sử bạn có EditText cho tên và mã sinh viên
        val studentNameEditText = findViewById<EditText>(R.id.etStudentName)
        val studentIdEditText = findViewById<EditText>(R.id.etStudentId)

        // Giả sử bạn có một Button để thêm sinh viên
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val studentName = studentNameEditText.text.toString()
            val studentId = studentIdEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("student_name", studentName)
            resultIntent.putExtra("student_id", studentId)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()  // Kết thúc Activity và trả kết quả về MainActivity
        }
    }
}
