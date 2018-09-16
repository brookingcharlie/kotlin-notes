package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FunctionsTest {
    @Test
    fun `named arguments`() {
        fun addA(a: Int, b: Int) = a + b
        assertEquals(addA(2, 3), addA(b = 3, a = 2))
    }

    @Test
    fun `optional arguments`() {
        fun addB(a: Int, b: Int = 1) = a + b
        assertEquals(addB(2, 1), addB(2))

        fun addC(a: Int, b: Int = 1, c: Int) = a + b + c
        assertEquals(addC(2, 1, 3), addC(2, c = 3))
    }

    @Test
    fun `anonymous functions and lambdas`() {
        val addAnon = fun(a: Int, b: Int): Int {return a + b}
        assertEquals(5, addAnon(2, 3))
        assertEquals(5, addAnon.invoke(2, 3))

        val add: (Int, Int) -> Int = {a, b -> a + b}
        assertEquals(5, add(2, 3))

        val compute: () -> Int = {5}
        assertEquals(5, compute())
    }

    @Test
    fun `higher-order functions`() {
        fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
            var accumulator: R = initial
            for (element: T in this) {
                accumulator = combine(accumulator, element)
            }
            return accumulator
        }
        assertEquals(6, listOf(1, 2, 3).fold(0, {a, b -> a + b}))
    }

    @Test
    fun `generic functions`() {
        fun <T> singletonList(item: T): List<T> = listOf(item)
        assertEquals(listOf(1), singletonList(1))
    }

    @Test
    fun `varargs`() {
        fun asList(vararg ts: Int): List<Int> {
            val result = ArrayList<Int>()
            for (t in ts) {
                result.add(t)
            }
            return result
        }
        assertEquals(listOf(1, 2, 3), asList(1, 2, 3))
    }

    @Test
    fun `infix`() {
        infix fun Int.add(x: Int): Int = this + x
        assertEquals(1.add(2), 1 add 2)
    }

    @Test
    fun `tail recursion`() {
        val epsilon = 1E-10
        tailrec fun findFixPoint(x: Double = 1.0): Double =
            if (Math.abs(x - Math.cos(x)) < epsilon) x else findFixPoint(Math.cos(x))
        assertTrue(Math.abs(findFixPoint() - 0.7390851332151611) < epsilon)
    }
}
