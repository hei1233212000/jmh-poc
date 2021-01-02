package poc.jmh.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import poc.jmh.modulusByCustomMethod
import poc.jmh.modulusByDefaultOperator
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 5)
open class ModulusBenchmark {
    @Benchmark
    fun calculateModulusByDefaultOperator(state: MyState, blackHole: Blackhole) {
        val result = modulusByDefaultOperator(state.number, state.divisor)
        blackHole.consume(result)
    }

    @Benchmark
    fun calculateModulusByCustomMethod(state: MyState, blackHole: Blackhole) {
        val result = modulusByCustomMethod(state.number, state.divisor)
        blackHole.consume(result)
    }

    @State(Scope.Benchmark)
    class MyState {
        val number = Integer.MAX_VALUE / 2
        val divisor = 1024
    }
}