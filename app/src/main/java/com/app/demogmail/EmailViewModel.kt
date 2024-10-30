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

    init {
        // Khởi tạo danh sách email mẫu
        _emails.value = EmailDataSource().getEmailData()
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
}
