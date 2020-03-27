package org.example

import org.junit.Test
import kotlin.test.assertEquals

class ObjectTests {
    object Singleton {
        val name = "Name"
    }

    @Test
    fun `singleton`() {
        assertEquals("Name", Singleton.name)
    }

    @Test
    fun `inheritance`() {
        open class A(val x: Int)

        val obj: A = object : A(1) {}
        assertEquals(1, obj.x)
    }

    class Person private constructor(val name: String) {
        companion object {
            fun create(name: String): Person = Person(name)
        }
    }

    @Test
    fun `companion object`() {
        val person = Person.create("Fred")
        assertEquals("Fred", person.name)
    }
}