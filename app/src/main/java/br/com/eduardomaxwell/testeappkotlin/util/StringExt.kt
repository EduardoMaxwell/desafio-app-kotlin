package br.com.eduardomaxwell.testeappkotlin.util

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    val isEmail = pattern.matcher(this).matches()

    return this.isNotEmpty() && isEmail
}


private val weightCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

fun String.isValidCPF() : Boolean {
    if( this.length != 11 )
        return false

    val digit1 = digitCalc( this.substring(0, 9), weightCPF )
    val digit2 = digitCalc(this.substring(0, 9) + digit1, weightCPF )

    return this == (this.substring(0, 9) + digit1.toString() + digit2.toString())
}

private fun digitCalc(
    str: String,
    weight: IntArray ): Int {

    var sum = 0
    var index = str.length - 1
    var digit: Int

    while (index >= 0) {
        digit = Integer.parseInt( str.substring(index, index + 1) )
        sum += digit * weight[ weight.size - str.length + index ]
        index--
    }

    sum = 11 - sum % 11

    return if(sum > 9)
        0
    else
        sum
}