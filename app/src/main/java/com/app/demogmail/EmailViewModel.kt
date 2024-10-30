import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.demogmail.Email
import com.app.demogmail.EmailDataSource
import java.lang.reflect.Array.set

class EmailViewModel : ViewModel() {
    private val _emails = MutableLiveData<List<Email>>()
    val emails: LiveData<List<Email>> get() = _emails

    private val starredEmails = mutableMapOf<Int, Boolean>()

    private val _emailsCheck = MutableLiveData<List<Email>>()
    val emailsCheck: LiveData<List<Email>> = _emailsCheck

    // Danh sách để lưu trạng thái được chọn của từng email
    private val selectedStates: MutableList<Boolean> = mutableListOf()

    init {
        // Khởi tạo danh sách email mẫu
        _emails.value = EmailDataSource().getEmailData()



        val emailList = EmailDataSource().getEmailData() // Lấy danh sách email
        selectedStates.clear() // Đảm bảo danh sách trạng thái rỗng trước khi thêm phần tử mới
        selectedStates.addAll(List(emailList.size) { false }) //

    }

    fun toggleStar(position: Int) {
        // Đảo ngược trạng thái ngôi sao
        val isStarred = starredEmails[position] ?: false
        starredEmails[position] = !isStarred
        _emails.value?.let { emails ->
            _emails.value = emails // Cập nhật lại LiveData để kích hoạt UI
        }
    }

    fun isEmailStarred(position: Int): Boolean {
        return starredEmails[position] ?: false
    }

    fun toggleSelection(position: Int) {
        selectedStates[position] = !selectedStates[position]
    }

    fun isEmailSelected(position: Int): Boolean {
        return selectedStates.getOrElse(position) { false }
    }

    fun getSelectedEmailCount(): Int {
        return selectedStates.count { it }
    }
    fun getFavoriteEmailCount(): Int {
        return starredEmails.count { it.value }
    }
}
