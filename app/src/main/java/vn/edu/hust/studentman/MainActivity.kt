package vn.edu.hust.studentman

import android.os.Bundle
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
  private lateinit var studentAdapter: StudentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    setupRecyclerView()
    observeViewModel()
    binding.btnAddNew.setOnClickListener {
      showAddStudentDialog()
    }
  }

  private fun setupRecyclerView() {
    studentAdapter = StudentAdapter(
      students = emptyList(),
      onEdit = { showEditStudentDialog(it) },
      onDelete = { showDeleteConfirmation(it) }
    )
    binding.recyclerViewStudents.apply {
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = studentAdapter
    }
  }

  private fun observeViewModel() {
    viewModel.students.observe(this) { students ->
      studentAdapter = StudentAdapter(students, { showEditStudentDialog(it) }, { showDeleteConfirmation(it) })
      binding.recyclerViewStudents.adapter = studentAdapter
    }
  }

  private fun showAddStudentDialog() {
    val dialog = StudentDialogFragment.newInstance(null)
    dialog.setOnStudentAdded { newStudent ->
      viewModel.addStudent(newStudent)
    }
    dialog.show(supportFragmentManager, "AddStudentDialog")
  }

  private fun showEditStudentDialog(student: StudentModel) {
    val dialog = StudentDialogFragment.newInstance(student)
    dialog.setOnStudentEdited { updatedStudent ->
      viewModel.editStudent(updatedStudent)
    }
    dialog.show(supportFragmentManager, "EditStudentDialog")
  }

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
}
