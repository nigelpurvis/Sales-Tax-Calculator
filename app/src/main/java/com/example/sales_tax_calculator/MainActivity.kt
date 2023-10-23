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
    private val stateSalesTax = mapOf("CA" to 0.0725, "AK" to 0.0, "AL" to 0.04, "AZ" to 0.056, "AR" to 0.065, "CO" to 0.029,"CT" to 0.0635,"DE" to 0.00,"FL" to 0.06,"GA" to 0.04, "HI" to 0.04, "ID" to 0.06, "IL" to 0.0625, "IN" to 0.07, "IA" to 0.06, "KS" to 0.065, "KY" to 0.06, "LA" to 0.0445, "ME" to 0.055, "MD" to 0.06, "MA" to 0.0625, "MI" to 0.06, "MN" to 0.06875, "MS" to 0.07, "MO" to 0.04225,"MT" to 0.00, "NE" to 0.055, "NV" to 0.0685, "NH" to 0.00,"NJ" to 0.06625, "NM" to 0.05125, "NY" to 0.04, "NC" to 0.0475, "ND" to 0.05, "OH" to 0.0575, "OK" to 0.045, "OR" to 0.00,"PA" to 0.06, "RI" to 0.07, "SC" to 0.06, "SD" to 0.045, "TN" to 0.07, "TX" to 0.0625, "UT" to 0.047, "VT" to 0.06, "VA" to 0.053, "WA" to 0.065, "WV" to 0.06, "WI" to 0.05, "WY" to 0.04)
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
        if (etBaseAmount.text.toString().length > 0){
            baseAmount = etBaseAmount.text.toString().toDouble()
            currSalesTax = (baseAmount * currSalesTaxPercent).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            tvTaxAmount.text = currSalesTax.toString()
            tvTotalAmount.text = (baseAmount + currSalesTax).toString()
        }
        if (etBaseAmount.text.toString().length == 0 || etStateAbbrv.text.toString().length == 0){
            tvTaxAmount.text = "0.00"
            tvTotalAmount.text = "0.00"
        }
    }
    private fun setSalesTax(){
        if(etStateAbbrv.text.toString().length > 0) {
            var currState = etStateAbbrv.text.toString()
            if (currState in stateSalesTax) {
                currSalesTaxPercent = stateSalesTax.getValue(currState) as Double
            }
        }
    }

}