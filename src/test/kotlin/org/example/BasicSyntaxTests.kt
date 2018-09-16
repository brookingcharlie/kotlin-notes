package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BasicSyntaxTests {
    @Test
    fun `functions`() {
        fun addA(a: Int, b: Int): Int {
            return a + b
        }
        fun addB(a: Int, b: Int) = a + b
        assertEquals(addA(2, 3), addB(2, 3))
    }

    @Test
    fun `unit`() {
        fun doSomethingA(): Unit {}
        fun doSomethingB() = doSomethingA()
        assertEquals(Unit, doSomethingA())
        assertEquals(Unit, doSomethingB())
    }

    @Test
    fun `val and var`() {
        val a: Int = 1
        val b = 2
        assertTrue(b is Int)
        // this would give a compile error "val cannot be reassigned"
        //
        //     b = 2
        //
        // to reassign, you need to use the var keyword instead
        var c = 3
        c = 4
        assertEquals(4, c)
    }

    @Test
    fun `string template`() {
        val x = 3
        val s = "result: $x is half of ${x * 2}"
        assertEquals("result: 3 is half of 6", s)
    }

    @Test
    fun `if expression`() {
        fun maxOf(a: Int, b: Int) = if (a > b) a else b
        assertEquals(3, maxOf(2, 3))
    }

    @Test
    fun `for loop`() {
        fun sum(ns: List<Int>): Int {
            var acc = 0
            for (n in ns) {
                acc += n
            }
            return acc
        }
        assertEquals(6, sum(listOf(1, 2, 3)))
    }

    @Test
    fun `while loop`() {
        fun fact(n: Int): Int {
            var acc = 1
            var i = n
            while (i > 1) {
                acc *= i
                i--
            }
            return acc
        }
        assertEquals(6, fact(3))
    }

    @Test
    fun `pattern matching`() {
        fun fact(obj: Any): Int =
            when (obj) {
                0 -> 1
                1 -> 1
                is Int -> obj * fact(obj - 1)
                else -> throw UnsupportedOperationException()
            }
        assertEquals(1, fact(1))
        assertEquals(6, fact(3))
    }

    @Test
    fun `type checks`() {
        fun getLengthOfAny(x: Any): Int {
            // this would give "error: unresolved reference: length"
            //
            //   return x.length
            //
            // but the compiler automatically casts to String when it sees this if-stmt
            if (x is String) {
                return x.length
            }
            throw UnsupportedOperationException()
        }
        assertEquals(4, getLengthOfAny("asdf"))
    }
}
