package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LazyEvaluationTests {
    @Test
    fun `lazy evaluation`() {
        var evaluated = false
        val s: String by lazy {
            evaluated = true
            "expensive string"
        }
        assertFalse(evaluated)
        assertEquals("expensive string", s)
        assertTrue(evaluated)
    }
}
