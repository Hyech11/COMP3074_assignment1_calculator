package  ca.gbc.comp3074.paycalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.PersistableBundle
import java.text.NumberFormat
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputHours = findViewById<EditText>(R.id.inputHours)
        val inputRate = findViewById<EditText>(R.id.inputRate)
        val inputTax = findViewById<EditText>(R.id.inputTax)
        val btnCalc = findViewById<Button>(R.id.btnCalculate)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        val txtPay = findViewById<TextView>(R.id.txtPay)
        val txtOvertime = findViewById<TextView>(R.id.txtOvertime)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtTax = findViewById<TextView>(R.id.txtTax)

        val currency = NumberFormat.getCurrencyInstance()

        btnCalc.setOnClickListener {
            val hours = inputHours.text.toString().toDoubleOrNull()
            val rate = inputRate.text.toString().toDoubleOrNull()
            val taxRate = inputTax.text.toString().toDoubleOrNull()

            if (hours == null || rate == null || taxRate == null){
                Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val baseHours = max(0.0, minOf(40.0, hours))
            val overtimeHours = max(0.0, hours - 40.0)

            val pay = baseHours * rate
            val overtimePay = overtimeHours * rate * 1.5
            val totalPay = pay + overtimePay
            val tax = pay * taxRate

            txtPay.text = "Pay: ${currency.format(pay)}"
            txtOvertime.text = "Overtime Pay: ${currency.format(overtimePay)}"
            txtTotal.text = "Total Pay: ${currency.format(totalPay)}"
            txtTax.text = "Tax: ${currency.format(tax)}"
        }

        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}