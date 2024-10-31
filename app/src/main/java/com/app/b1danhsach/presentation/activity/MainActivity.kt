package com.app.b1danhsach.presentation.activity

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.app.b1danhsach.databinding.ActivityMainBinding
import com.app.b1danhsach.models.Student
import com.app.b1danhsach.util.AddressHelper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addressHelper = AddressHelper(resources)

        setupProvinceSpinner()
        setupCalendarView()
        setupSubmitButton()
    }

    private fun setupProvinceSpinner() {
        val provinces = addressHelper.getProvinces()
        binding.spinnerProvince.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, provinces)

        binding.spinnerProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                val districts = addressHelper.getDistricts(selectedProvince)
                binding.spinnerDistrict.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, districts)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinnerDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDistrict = binding.spinnerDistrict.selectedItem.toString()
                val wards = addressHelper.getWards(binding.spinnerProvince.selectedItem.toString(), selectedDistrict)
                binding.spinnerWard.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, wards)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupCalendarView() {
        binding.btnShowCalendar.setOnClickListener {
            binding.calendarView.visibility = if (binding.calendarView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            binding.btnShowCalendar.text = selectedDate
            binding.calendarView.visibility = View.GONE
        }
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            if (validateForm()) {
                val gender = if (binding.rbMale.isChecked) "Nam" else "Nữ"
                val hobbies = mutableListOf<String>()
                if (binding.cbSports.isChecked) hobbies.add("Thể thao")
                if (binding.cbMovies.isChecked) hobbies.add("Điện ảnh")
                if (binding.cbMusic.isChecked) hobbies.add("Âm nhạc")

//                val student = Student(
//                    mssv = binding.etMSSV.text.toString(),
//                    name = binding.etName.text.toString(),
//                    gender = gender,
//                    email = binding.etEmail.text.toString(),
//                    phone = binding.etPhone.text.toString(),
//                    birthDate = binding.btnShowCalendar.text.toString(),
//                    province = binding.spinnerProvince.selectedItem.toString(),
//                    district = binding.spinnerDistrict.selectedItem.toString(),
//                    ward = binding.spinnerWard.selectedItem.toString(),
//                    hobbies = hobbies
//                )
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        with(binding) {
            if (etMSSV.text.isEmpty()) return showError("MSSV không được để trống")
            if (etName.text.isEmpty()) return showError("Họ tên không được để trống")
            if (!rbMale.isChecked && !rbFemale.isChecked) return showError("Vui lòng chọn giới tính")
            if (etEmail.text.isEmpty()) return showError("Email không được để trống")
            if (etPhone.text.isEmpty()) return showError("Số điện thoại không được để trống")
            if (btnShowCalendar.text == "Chọn ngày sinh") return showError("Vui lòng chọn ngày sinh")
            if (spinnerProvince.selectedItem == null) return showError("Vui lòng chọn tỉnh/thành")
            if (spinnerDistrict.selectedItem == null) return showError("Vui lòng chọn quận/huyện")
            if (spinnerWard.selectedItem == null) return showError("Vui lòng chọn phường/xã")
            if (!cbAgreeTerms.isChecked) return showError("Vui lòng đồng ý với các điều khoản")
        }
        return true
    }

    private fun showError(message: String): Boolean {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return false
    }
}
