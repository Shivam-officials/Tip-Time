package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate the layout xml file and return the object instance named binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // onClickListener of calculateButton
        binding.calculateButton.setOnClickListener { calculateTip() }

        //onKeyListener for hiding the keyboard after hitting enter on costOfServiceEditText view
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view,keyCode) }
    }

    /**
    function for calculating the tip
     */
    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        // if cost is 0 or null, exit from calculateTip() after displaying 0.0 in tip result
        if (cost == null || cost == 0.0 ){
            displayTip(0.0)
            return
        }

        //storing tip percentage acc to selected radioButton in a tipPercentage variable
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //calculating tip
        var tip = cost * tipPercentage

        //rounding up the tip
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    /**
    function for displaying test after formatting the tip in currency format
     */
    private fun displayTip(tip:Double){

        //formatting the tip in currency format
        val formattedTip: String = NumberFormat.getCurrencyInstance().format(tip)

        //assigning the formatted tip in tipResult TextView text
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }

    /**
    * helper function for hiding the keyboard when the "Enter Key" is Entered
     */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}
