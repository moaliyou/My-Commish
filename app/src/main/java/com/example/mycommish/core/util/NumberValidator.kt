package com.example.mycommish.core.util

import java.text.DecimalFormatSymbols

class NumberValidator(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {

    private val decimalSeparator = symbols.decimalSeparator
    fun validateNumberInput(input: String): String {

        // If the input something non-digit
        // then just return empty string
        if (input.matches("\\D".toRegex())) return ""

        // If the input is consists of
        // consecutive zeros then return single zero
        if (input.matches("0+".toRegex())) return ""

        val inputResult = StringBuilder()

        for (char in input) {
            if (char.isDigit() && char != decimalSeparator) {
                inputResult.append(char)
                continue
            }
        }

        return inputResult.toString()
    }
}