package org.example

import org.junit.Test
import kotlin.test.assertEquals

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
        assertEquals(doSomethingA(), Unit)
        assertEquals(doSomethingB(), Unit)
    }

    @Test
    fun `val and var`() {
        val a: Int = 1
        val b = 2
        assert(b is Int)
        // this would give a compile error "val cannot be reassigned"
        //
        //     b = 2
        //
        // to reassign, you need to use the var keyword instead
        var c = 3
        c = 4
        assertEquals(c, 4)
    }

    @Test
    fun `inline if`() {
        fun maxOf(a: Int, b: Int) = if (a > b) a else b
        assertEquals(maxOf(2, 3), 3)
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
        assertEquals(addToNullable(4), 9)
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
        assertEquals(getLengthOfAny("asdf"), 4)
    }
}
