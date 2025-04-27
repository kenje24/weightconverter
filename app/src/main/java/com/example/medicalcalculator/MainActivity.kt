package com.example.medicalcalculator

import android.os.Bundle
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtWeight: EditText
    private lateinit var radLbToKilo: RadioButton
    private lateinit var radKiloToLb: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnConvert: Button
    private lateinit var txtResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Customize the Action Bar
        supportActionBar?.apply {
            setTitle(R.string.app_name) // Sets app name from strings.xml
            setIcon(R.mipmap.ic_launcher) // Sets the launcher icon
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
        }

        // Bind views
        txtWeight = findViewById(R.id.txtWeight)
        radLbToKilo = findViewById(R.id.radLbToKilo)
        radKiloToLb = findViewById(R.id.radKiloToLb)
        radioGroup = findViewById(R.id.radioGroup)
        btnConvert = findViewById(R.id.btnConvert)
        txtResult = findViewById(R.id.txtResult)

        btnConvert.setOnClickListener {
            convertWeight()
        }
    }

    private fun convertWeight() {
        val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imm.hideSoftInputFromWindow(txtWeight.windowToken, 0)

        val inputText = txtWeight.text.toString()

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a weight", Toast.LENGTH_SHORT).show()
            txtResult.text = ""
            return
        }

        val weight = inputText.toDouble()

        if (radKiloToLb.isChecked) {
            // Kilos to Pounds
            if (weight > 225) {
                Toast.makeText(this, "Weight exceeds 225 kg!", Toast.LENGTH_LONG).show()
                txtResult.text = ""
                return
            }
            val pounds = weight * 2.2
            txtResult.text = String.format("%.2f lbs", pounds)
        } else if (radLbToKilo.isChecked) {
            // Pounds to Kilos
            if (weight > 500) {
                Toast.makeText(this, "Weight exceeds 500 lbs!", Toast.LENGTH_LONG).show()
                txtResult.text = ""
                return
            }
            val kilos = weight / 2.2
            txtResult.text = String.format("%.2f kg", kilos)
        } else {
            Toast.makeText(this, "Please select a conversion type", Toast.LENGTH_SHORT).show()
            txtResult.text = ""
        }
    }


}
