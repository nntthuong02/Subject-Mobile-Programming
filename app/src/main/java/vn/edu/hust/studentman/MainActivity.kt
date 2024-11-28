package vn.edu.hust.studentman

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import vn.edu.hust.studentman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }
  private val viewModel: StudentViewModel by viewModels()
  private lateinit var studentAdapter: ArrayAdapter<StudentModel>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    setupListView()
    observeViewModel()

    // Thêm sự kiện khi click vào nút Add New
    binding.btnAddNew.setOnClickListener {
      val intent = Intent(this, AddStudentActivity::class.java)
      startActivityForResult(intent, ADD_STUDENT_REQUEST)
    }
  }

  private fun setupListView() {
    studentAdapter = StudentAdapter2(this, R.layout.layout_student_item, mutableListOf())
    binding.listViewStudents.adapter = studentAdapter

    // Đăng ký ContextMenu cho ListView
    registerForContextMenu(binding.listViewStudents)
  }

  private fun observeViewModel() {
    viewModel.students.observe(this) { students ->
      studentAdapter.clear()
      studentAdapter.addAll(students)
      studentAdapter.notifyDataSetChanged()
    }
  }

  // Tạo Option Menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.add_new_student -> {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivityForResult(intent, ADD_STUDENT_REQUEST)
        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }

  // Tạo Context Menu cho từng item trong ListView
  override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
    super.onCreateContextMenu(menu, v, menuInfo)
    val inflater = menuInflater
    inflater.inflate(R.menu.context_menu_student, menu)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
    val selectedStudent = studentAdapter.getItem(info.position)

    when (item.itemId) {
      R.id.context_edit -> {
        selectedStudent?.let { showEditStudentActivity(it) }
        return true
      }
      R.id.context_remove -> {
        selectedStudent?.let { showDeleteConfirmation(it) }
        return true
      }
      else -> return super.onContextItemSelected(item)
    }
  }

  // Hàm mở Activity Edit để sửa thông tin sinh viên
  private fun showEditStudentActivity(student: StudentModel) {
    val intent = Intent(this, EditStudentActivity::class.java)
    intent.putExtra("student_id", student.studentId)
    intent.putExtra("student_name", student.studentName)
    startActivityForResult(intent, EDIT_STUDENT_REQUEST)
  }

  // Hàm xác nhận xóa sinh viên
  private fun showDeleteConfirmation(student: StudentModel) {
    val position = viewModel.students.value?.indexOf(student) ?: -1
    if (position != -1) {
      viewModel.deleteStudent(student)

      Snackbar.make(binding.root, "Student deleted", Snackbar.LENGTH_LONG)
        .setAction("Undo") {
          viewModel.undoDelete(student, position)
        }
        .show()
    } else {
      Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
    }
  }

  // Xử lý kết quả từ Activity thêm hoặc sửa sinh viên
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      when (requestCode) {
        ADD_STUDENT_REQUEST -> {
          // Lấy giá trị từ Intent
          val studentName = data?.getStringExtra("student_name")
          val studentId = data?.getStringExtra("student_id")

          // Tạo đối tượng StudentModel
          if (studentName != null && studentId != null) {
            val student = StudentModel(studentName, studentId)
            viewModel.addStudent(student) // Lưu sinh viên vào ViewModel
          }
        }
        EDIT_STUDENT_REQUEST -> {
          val updatedStudentName = data?.getStringExtra("updated_student_name")
          val updatedStudentId = data?.getStringExtra("updated_student_id")

          // Tạo đối tượng StudentModel
          if (updatedStudentName != null && updatedStudentId != null) {
            val updatedStudent = StudentModel(updatedStudentName, updatedStudentId)
            viewModel.editStudent(updatedStudent) // Cập nhật sinh viên vào ViewModel
          }
        }
      }
    }
  }


  companion object {
    const val ADD_STUDENT_REQUEST = 1
    const val EDIT_STUDENT_REQUEST = 2
  }
}
