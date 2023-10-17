package com.example.sales_tax_calculator

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount : EditText
    private lateinit var etStateAbbrv : EditText
    private lateinit var tvTaxAmount : TextView
    private lateinit var tvTotalAmount : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        etStateAbbrv = findViewById(R.id.etStateAbbrv)
        tvTaxAmount = findViewById(R.id.tvTaxAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        etBaseAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")

            }

        })

    }

}