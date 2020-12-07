package poc.jmh

import java.math.BigDecimal

fun sumInteger(a: Int, b: Int): Int = a + b

fun sumBigDecimal(a: BigDecimal, b: BigDecimal): BigDecimal = a.add(b)
