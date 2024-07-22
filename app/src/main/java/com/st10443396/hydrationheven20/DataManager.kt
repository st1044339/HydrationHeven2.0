package com.st10443396.hydrationheven20

class DataManager { companion object{
    var strDate = mutableListOf<String>()
    var intMorning = mutableListOf<Int>()
    var intAfternoon = mutableListOf<Int>()
    var strNotes = mutableListOf<String>()

    fun saveData(date: String, morning: Int, afternoon: Int, notes: String) {
        strDate.add(date)
        intMorning.add(morning)
        intAfternoon.add(afternoon)
        strNotes.add(notes)
    }

    fun calculateAverageScreenTime(): Double {
        val totalDays = strDate.size
        val totalScreenTime = intMorning.sum() + intAfternoon.sum()
        return if (totalDays > 0) totalScreenTime.toDouble() / totalDays else 0.0
    }

    fun getNumberOfDays(): Int {
        return strDate.size
    }
}

}
}