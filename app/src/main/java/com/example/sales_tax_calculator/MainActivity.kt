package com.example.sales_tax_calculator

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount : EditText
    private lateinit var etStateAbbrv : EditText
    private lateinit var tvTaxAmount : TextView
    private lateinit var tvTotalAmount : TextView
    private val stateSalesTax = mapOf("CA" to 0.0725, "AK" to 0)
    private var currSalesTaxPercent = 0.0
    private var currSalesTax = 0.0
    private var baseAmount = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        etStateAbbrv = findViewById(R.id.etStateAbbrv)
        tvTaxAmount = findViewById(R.id.tvTaxAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvTaxAmount.text = (0.00).toString()
        tvTotalAmount.text = (0.00).toString()


        etBaseAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeSalesTax()
            }

        })

        etStateAbbrv.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                setSalesTax()
                computeSalesTax()
            }

        })

    }

    private fun computeSalesTax() {
        if (etBaseAmount != null){
            baseAmount = etBaseAmount.text.toString().toDouble()
            currSalesTax = (baseAmount * currSalesTaxPercent).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            tvTaxAmount.text = currSalesTax.toString()
            tvTotalAmount.text = (baseAmount + currSalesTax).toString()
        }
    }
    private fun setSalesTax(){
        if(etStateAbbrv != null) {
            var currState = etStateAbbrv.text.toString()
            if (currState in stateSalesTax) {
                currSalesTaxPercent = stateSalesTax.getValue(currState) as Double
            }
        }
    }

}