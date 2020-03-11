package com.erman.percentagecalculator

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        fun hideKeyboard() {
            val inputManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.SHOW_FORCED
            )
        }

        clearButton.setOnClickListener {
            radioGroup.clearCheck()
            numberAEditText.text.clear()
            numberBEditText.text.clear()
            resultTextView.text = ""
            hideKeyboard()
        }

        radioGroup.setOnCheckedChangeListener { _, _ ->
            hideKeyboard()

            if (numberAEditText.text.toString() != "" && numberAEditText.text.toString() != "") {
                val numberA: Double = if (!numberAEditText.text.isEmpty()) {
                    numberAEditText.text.toString().toDouble()
                } else {
                    0.0
                }
                val numberB: Double = if (!numberBEditText.text.isEmpty()) {
                    numberBEditText.text.toString().toDouble()
                } else {
                    0.0
                }

                if (PercentageBofNumberARadioButton1.isChecked)
                    resultTextView.text = String.format("%.1f", ((numberA / 100) * numberB))

                if (percentageOfBRadioButton2.isChecked)
                    resultTextView.text = "%" + String.format("%.1f", ((numberA / numberB) * 100))

                if (changeAtoBRadioButton3.isChecked)
                    resultTextView.text =
                        "%" + String.format("%.1f", (((numberB - numberA) / numberA) * 100))

                if (increaseByPercentBRadioButton4.isChecked)
                    resultTextView.text =
                        String.format("%.1f", (numberA + ((numberA / 100) * numberB)))

                if (decreaseAbyPercentBRadioButton5.isChecked)
                    resultTextView.text =
                        String.format("%.1f", (numberA - ((numberA / 100) * numberB)))
            }
        }
    }
}