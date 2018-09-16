package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ClassesTest {
    @Test
    fun `data classes`() {
        data class Customer(val name: String, var email: String)
        val harry1 = Customer("Harry", "harry@example.org")
        val harry2 = Customer("Harry", "hazza@example.com")

        // Provides getters (for val/var) and setters (for var)
        assertEquals("Harry", harry1.name)
        harry2.email = "harry@example.org"
        assertEquals("harry@example.org", harry2.email)

        // Implements equals, hashCode, and toString
        assertTrue(harry1 == harry2)
        assertTrue(harry1.hashCode() == harry2.hashCode())
        assertEquals("Customer(name=Harry, email=harry@example.org)", harry1.toString())

        // Provides copy function
        assertEquals(Customer("Harry", "harry@example.org"), harry1.copy())
        assertEquals(Customer("Harry", "hazza@example.com"), harry1.copy(email = "hazza@example.com"))
    }
}