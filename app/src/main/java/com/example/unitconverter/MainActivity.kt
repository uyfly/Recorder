package com.example.unitconverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.unitconverter.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber: BigDecimal = BigDecimal.ZERO
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LifeCycle - onCreate", "onCreate 실행")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        inputEditText.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                BigDecimal.ZERO
            } else {
                text.toString().toBigDecimal()
            }

            if (cmToM) {
                outputTextView.text = inputNumber.times(BigDecimal("0.01")).toString()
            } else {
                outputTextView.text = inputNumber.times(BigDecimal("100")).toString()
            }
        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(BigDecimal("0.01")).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(BigDecimal("100")).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
        Log.d("MainActivity - onSaveInstanceState", "onSaveInstanceState 실행")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MainActivity - onRestoreInstanceState", "onRestoreInstanceState 실행")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle - onStart", "onStart 실행")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle - onResume", "onResume 실행")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LifeCycle - onRestart", "onRestart 실행")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle - onPause", "onPause 실행")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle - onStop", "onStop 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle - onDestroy", "onDestroy 실행")
    }
}