package org.example

import org.junit.Test
import kotlin.test.assertEquals

class NullSafetyTests {
    @Test
    fun `non-nullable types`() {
        fun addToNonNullable(d: Int): Int {
            return d + 5
        }
        val n: Int = 4
        assertEquals(9, addToNonNullable(n))

        // This would cause a compiler error since a non-nullable type is required
        //
        //     val m: Int? = 5
        //     addToNonNullable(m)
        //
    }

    @Test
    fun `casting of nullable types`() {
        fun addToNullable(d: Int?): Int? {
            // this would give "error: operator call ... not allowed on a nullable receiver 'd'"
            //
            //     return d + 5
            //
            // but the compiler automatically casts to non-nullable when it sees this if-stmt
            if (d != null) {
                return d + 5
            }
            return null
        }
        assertEquals(9, addToNullable(4))
        assertEquals(null, addToNullable(null))
    }

    @Test
    fun `if not null`() {
        fun len(s: String?): Int? = s?.length
        assertEquals(4, len("asdf"))
        assertEquals(null, len(null))
    }

    @Test
    fun `if not null else`() {
        fun len(s: String?): Int = s?.length ?: 0
        assertEquals(4, len("asdf"))
        assertEquals(0, len(null))
    }
}