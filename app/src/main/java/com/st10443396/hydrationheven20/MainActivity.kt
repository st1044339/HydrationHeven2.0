package com.st10443396.hydrationheven20

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var tvErrors: EditText
    private lateinit var etDate: EditText
    private lateinit var etMorning: EditText
    private lateinit var etAfternoon: EditText
    private lateinit var etNotes: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
         etDate =   findViewById(R.id.etDate)
         etMorning = findViewById(R.id.etMorning)
         etAfternoon = findViewById(R.id.etAfternoon)
         etNotes = findViewById(R.id.etNotes)
         tvErrors = findViewById(R.id.tvErrors)

        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            saveData()
        }

        val btnClear: Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            clearWallpaper()
        }
    }
    private fun saveData() {
         val date = etDate.text.toString()
         val morningStr = etMorning.text.toString()
         val afternoonStr = etAfternoon.text.toString()
         val notes = etNotes.text.toString()

         val errors = mutableListOf<String>()

         if (DataManager.getNumberOfDays() <= 7) {
         }

        // Validate date format
         try {
             val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
             dateFormat.parse(date) // Check if parsing succeeds
             } catch (e: ParseException) {
             errors.add("Invalid date format. Use format: 6 June 2024")
             }

         // Validate morning and afternoon screen time
         val morning = morningStr.toIntOrNull()
         val afternoon = afternoonStr.toIntOrNull()

         if (morning == null || afternoon == null) {
             errors.add("Morning and afternoon cups of water must be numeric.")
             } else {
             if (morning < 0 || morning > 7) {
             errors.add("Morning must drink two-three cups of water.")
             }
             if (afternoon < 0 || afternoon > 7) {
                errors.add("Afternoon must drink two-three cups of water .")
               }
             }
            // Display error messages (if any)
            if (errors.isNotEmpty()) {
                val errorMessage = errors.joinToString("\n")
                tvErrors.text = errorMessage
                return
             }

           // Save data to parallel arrays
           if (morning != null && afternoon != null) {
               DataManager.saveData(date, morning, afternoon, notes)
             }
        // Clear input fields for next input
         etDate.text.clear()
         etMorning.text.clear()
         etAfternoon.text.clear()
         etNotes.text.clear()
    } else {
         tvErrors.text = "You have already saved 7 days of information"}
