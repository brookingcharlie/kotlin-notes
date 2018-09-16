package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RangesTest {
    @Test
    fun `progressions`() {
        assertEquals(listOf(1, 2, 3), (1..3).toList())
        assertEquals(listOf(1, 2, 3), (1 until 4).toList())
        assertEquals(listOf(1, 3, 5), (1..5 step 2).toList())
        assertEquals(listOf(6, 3, 0), (6 downTo 0 step 3).toList())
    }

    @Test
    fun `membership`() {
        assertTrue(1 in 1..3)
        assertTrue(2 in 1..3)
        assertTrue(3 in 1..3)
        assertTrue(4 !in 1..3)
    }

    @Test
    fun `iterating`() {
        fun fact(n: Int): Int {
            var acc = 1
            for (i in 1..n) {
                acc *= i
            }
            return acc
        }
        assertEquals(6, fact(3))
    }
}
