package poc.jmh

import java.math.BigDecimal

fun sumInteger(a: Int, b: Int): Int = a + b

fun sumBigDecimal(a: BigDecimal, b: BigDecimal): BigDecimal = a.add(b)

/**
 * @param divisor it should be power of 2
 */
fun modulusByDefaultOperator(anyNumber: Int, divisor: Int) = anyNumber % divisor

/**
 * @param divisor it should be power of 2
 */
fun modulusByCustomMethod(anyNumber: Int, divisor: Int) = anyNumber and (divisor - 1)
