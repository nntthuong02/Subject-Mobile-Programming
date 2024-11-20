package vn.edu.hust.studentman
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    private val _students = MutableLiveData<List<StudentModel>>()
    val students: LiveData<List<StudentModel>> get() = _students

    private val studentList = mutableListOf<StudentModel>()

    init {
        studentList.addAll(StudentDataSource().getStudentData())
        _students.value = studentList
    }

    fun addStudent(student: StudentModel) {
        studentList.add(student)
        _students.value = studentList
    }

    fun editStudent(editedStudent: StudentModel) {
        val index = studentList.indexOfFirst { it.studentId == editedStudent.studentId }
        if (index != -1) {
            studentList[index] = editedStudent
            _students.value = studentList
        }
    }

    fun deleteStudent(student: StudentModel) {
        studentList.remove(student)
        _students.value = studentList
    }

    fun undoDelete(student: StudentModel, position: Int) {
        studentList.add(position, student)
        _students.value = studentList
    }
}
