package com.example.mycommish.core.util

import java.text.DecimalFormatSymbols

class DecimalFormatter(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {
    private val decimalSeparator = symbols.decimalSeparator

    fun cleanup(input: String): String {
        val inputResult = StringBuilder()
        var decimalPointCounter = 0
        var hasDecimalSeparator = false

        // If the input something non-digit
        // then just return empty string
        if (input.matches("\\D".toRegex())) return ""

        // If the input is consists of
        // consecutive zeros then return single zero
        if (input.matches("0+".toRegex())) return "0"

        for (char in input) {
            if (char.isDigit()) {
                if (hasDecimalSeparator && inputResult.isNotEmpty())
                    decimalPointCounter++
                if (decimalPointCounter <= 2) inputResult.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSeparator && inputResult.isNotEmpty()) {
                inputResult.append(char)
                hasDecimalSeparator = true
            }
        }

        return inputResult.toString()
    }
}