package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_BMI = "ResultActivity.KEY_BMI"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getFloatExtra(KEY_RESULT_BMI, 0f)
        val tvResult: TextView = findViewById(R.id.tvResult)
        val tvResultClassification: TextView = findViewById(R.id.tvResultClassification)
        tvResult.text = result.toString()

        val classification: String = if (result < 18.5f) {
            "ABAIXO DO PESO"
        } else if (result in 18.5f..24.99f) {
            "NORMAL"
        } else if (result in 25f..29.99f) {
            "SOBREPESO"
        } else if (result in 30f..34.99f) {
            "OBESIDADE I"
        } else if (result in 35f..39.99f) {
            "OBESIDADE II"
        } else {
            "OBESIDADE III"
        }
        tvResultClassification.text = classification

        val btnRecalculate: Button = findViewById(R.id.btnRecalculate)

        btnRecalculate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}