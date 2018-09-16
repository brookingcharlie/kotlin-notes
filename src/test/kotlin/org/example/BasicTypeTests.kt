package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BasicTypeTests {
    @Test
    fun `basic types`() {
        val d: Double = 3.14
        val f: Float = 6.28F
        val l: Long = 2L
        val i: Int = 1_000_000
        val s: Short = 0x00EF
        val b: Byte = 0b00000001
    }

    @Test
    fun `boxed vs non-boxed`() {
        // On the Java platform, numbers are stored as JVM primitive types,
        // unless we need a nullable number reference, in which case they're boxed.
        val n: Int = 10_000

        val a: Int = n
        val b: Int = n
        assertTrue(a == b)
        assertTrue(a === b)

        val nullableA: Int? = n
        val nullableB: Int? = n
        assertTrue(nullableA == nullableB)
        assertFalse(nullableA === nullableB)
    }

    @Test
    fun `type conversion`() {
        val b: Byte = 1
        // This statement won't compile since there's no automatic type conversion
        //
        //     val i: Int = b
        //
        // Instead, you need to call explicit conversation functions. Every number type
        // supports the following conversions: toByte(), toShort(), toInt(), toLong(),
        // toFloat(), toDouble(), toChar().
        val i: Int = b.toInt()
        assertEquals(1, i)
    }
}