package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// Based on https://kotlinlang.org/docs/reference/basic-syntax.html
class BasicSyntaxTest {
    @Test
    fun `function notation`() {
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
    fun `string templates`() {
        val x = 3
        val s = "result: $x is half of ${x * 2}"
        assertEquals("result: 3 is half of 6", s)
    }

    @Test
    fun `inline if`() {
        fun maxOf(a: Int, b: Int) = if (a > b) a else b
        assertEquals(3, maxOf(2, 3))
    }

    @Test
    fun `nullable checks`() {
        fun addToNullable(d: Int?): Int {
            // this would give "error: operator call ... not allowed on a nullable receiver 'd'"
            //
            //     return d + 5
            //
            // but the compiler automatically casts to non-nullable when it sees this if-stmt
            if (d != null) {
                return d + 5
            }
            throw UnsupportedOperationException()
        }
        assertEquals(9, addToNullable(4))
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
