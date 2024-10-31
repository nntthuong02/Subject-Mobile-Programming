package com.app.b1danhsach.util

import com.app.b1danhsach.models.Student

class StudentDataSource {
    private val studentData = mutableListOf(
        Student("20120001", "Nguyen Van A"),
        Student("20120002", "Tran Thi B"),
        Student("20120003", "Le Van C"),
        Student("20120004", "Hoang Thi D"),
        Student("20120005", "Pham Van E"),
        Student("20120006", "Nguyen Thi F"),
        Student("20120007", "Vo Van G"),
        Student("20120008", "Ngo Thi H"),
        Student("20120009", "Bui Van I"),
        Student("20120010", "Tran Van J"),
        Student("20120011", "Phan Thi K"),
        Student("20120012", "Nguyen Van L"),
        Student("20120013", "Vo Thi M"),
        Student("20120014", "Le Van N"),
        Student("20120015", "Do Thi O"),
        Student("20120016", "Hoang Van P"),
        Student("20120017", "Phan Thi Q"),
        Student("20120018", "Vu Van R"),
        Student("20120019", "Tran Thi S"),
        Student("20120020", "Nguyen Van T"),
        Student("20120021", "Pham Thi U"),
        Student("20120022", "Bui Van V"),
        Student("20120023", "Nguyen Thi W"),
        Student("20120024", "Le Van X"),
        Student("20120025", "Do Van Y"),
        Student("20120026", "Hoang Thi Z"),
        Student("20120027", "Nguyen Van AA"),
        Student("20120028", "Pham Thi BB"),
        Student("20120029", "Bui Van CC"),
        Student("20120030", "Tran Thi DD"),
        Student("20120031", "Vo Van EE"),
        Student("20120032", "Do Thi FF"),
        Student("20120033", "Nguyen Van GG"),
        Student("20120034", "Le Thi HH"),
        Student("20120035", "Hoang Van II"),
        Student("20120036", "Pham Thi JJ"),
        Student("20120037", "Nguyen Van KK"),
        Student("20120038", "Vo Thi LL"),
        Student("20120039", "Ngo Van MM"),
        Student("20120040", "Bui Thi NN"),
        Student("20120041", "Tran Van OO"),
        Student("20120042", "Phan Thi PP"),
        Student("20120043", "Nguyen Van QQ"),
        Student("20120044", "Pham Van RR"),
        Student("20120045", "Vu Thi SS"),
        Student("20120046", "Do Van TT"),
        Student("20120047", "Hoang Thi UU"),
        Student("20120048", "Phan Van VV"),
        Student("20120049", "Nguyen Thi WW"),
        Student("20120050", "Tran Van XX")
    )

    fun getStudentData(): List<Student> {
        return studentData
    }
}
