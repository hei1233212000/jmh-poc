package poc.jmh.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Mode.All
import org.openjdk.jmh.infra.Blackhole
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import poc.jmh.sumBigDecimal
import poc.jmh.sumInteger
import java.math.BigDecimal
import java.util.concurrent.TimeUnit.NANOSECONDS

@BenchmarkMode(All)
@OutputTimeUnit(NANOSECONDS)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
open class CalculationsBenchmark {
    @Benchmark
    fun sumIntegerBenchmark(state: MyState, blackHole: Blackhole) {
        val result = sumInteger(state.firstInteger, state.secondInteger)
        blackHole.consume(result)
    }

    @Benchmark
    fun sumBigDecimalBenchmark(state: MyState, blackHole: Blackhole) {
        val result = sumBigDecimal(state.firstBigDecimal, state.secondBigDecimal)
        blackHole.consume(result)
    }

    @State(Scope.Benchmark)
    class MyState {
        val firstInteger = 1
        val secondInteger = 2

        val firstBigDecimal: BigDecimal = BigDecimal.ONE
        val secondBigDecimal: BigDecimal = BigDecimal.valueOf(2)

        @Setup
        fun setup() {
            logger.info("setup")
        }

        @TearDown
        fun tearDown() {
            logger.info("tearDown")
        }

        private companion object {
            val logger: Logger = LoggerFactory.getLogger(MyState::class.java)
        }
    }
}
