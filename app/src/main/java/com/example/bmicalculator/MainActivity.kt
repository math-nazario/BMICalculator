package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtWeight: EditText = findViewById(R.id.edtWeight)
        val edtHeight: EditText = findViewById(R.id.edtHeight)
        val btnReset: Button = findViewById(R.id.btnReset)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)

        btnReset.setOnClickListener {
            if (edtWeight.text.isNotEmpty() || edtHeight.text.isNotEmpty()) {
                edtWeight.text.clear()
                edtHeight.text.clear()
            }
        }

        btnCalculate.setOnClickListener {
            val weightStr: String = edtWeight.text.toString()
            val heightStr: String = edtHeight.text.toString()
            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                Snackbar.make(edtWeight, "Preencha todos os campos", Snackbar.LENGTH_LONG).show()
            } else {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat()
                val heightSquared = height * height
                val result = weight / heightSquared
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(KEY_RESULT_BMI, result)
                startActivity(intent)
                finish()
            }
        }
    }
}