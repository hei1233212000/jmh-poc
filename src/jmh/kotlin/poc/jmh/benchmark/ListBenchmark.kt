package poc.jmh.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Mode.AverageTime
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit.NANOSECONDS
import kotlin.collections.ArrayList

@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
open class ListBenchmark {
    @Benchmark
    fun insertIntoArrayList(state: ListBenchmarkState, blackHole: Blackhole) {
        val list = ArrayList<Int>()
        (1..state.numberOfItems).forEach {
            list.add(it)
        }
        blackHole.consume(list)
    }

    @Benchmark
    fun insertIntoArrayListWithInitSize(state: ListBenchmarkState, blackHole: Blackhole) {
        val list = ArrayList<Int>(state.numberOfItems)
        (1..state.numberOfItems).forEach {
            list.add(it)
        }
        blackHole.consume(list)
    }

    @Benchmark
    fun insertIntoLinkedList(state: ListBenchmarkState, blackHole: Blackhole) {
        val list = LinkedList<Int>()
        (1..state.numberOfItems).forEach {
            list.add(it)
        }
        blackHole.consume(list)
    }

    @Benchmark
    fun accessRandomItemFromArrayList(state: ListBenchmarkState, blackHole: Blackhole) {
        val result = state.arrayList[state.randomIndex]
        blackHole.consume(result)
    }

    @Benchmark
    fun accessRandomItemFromLinkedList(state: ListBenchmarkState, blackHole: Blackhole) {
        val result = state.linkedList[state.randomIndex]
        blackHole.consume(result)
    }

    @State(Scope.Benchmark)
    class ListBenchmarkState {
        final val numberOfItems = 1000
        val arrayList = ArrayList<Int>(numberOfItems)
        val linkedList = LinkedList<Int>()
        val randomIndex = numberOfItems / 2

        @Setup(Level.Trial)
        fun setup() {
            (1..numberOfItems).forEach {
                arrayList.add(it)
                linkedList.add(it)
            }
        }
    }
}