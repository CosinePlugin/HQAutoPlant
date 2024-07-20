package kr.cosine.autoplant.extension

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("#,###.###")

fun Double.format(): String = decimalFormat.format(this)

fun Int.format(): String = decimalFormat.format(this)

fun Long.format(): String = decimalFormat.format(this)