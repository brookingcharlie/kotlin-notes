package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ClassTests {
    @Test
    @Suppress("USELESS_IS_CHECK")
    fun `empty class`() {
        class Empty
        assertTrue(Empty() is Empty)
    }

    @Test
    fun `primary constructor`() {
        // Explicit primary constructor (needed if you have modifiers)
        class Person0 constructor(nameParam: String) {
            val name = nameParam
        }
        assertEquals("Fred", Person0("Fred").name)

        // Implicit primary constructor
        class Person1(nameParam: String) {
            val name = nameParam
        }
        assertEquals("Fred", Person1("Fred").name)

        // Automatic property using val keyword
        class Person2(val name: String)
        assertEquals("Fred", Person2("Fred").name)
    }

    @Test
    fun `init block`() {
        class Person(val name: String) {
            var properName: String? = null;

            init {
                properName = name.capitalize()
            }
        }
        assertEquals("Fred", Person("fred").properName)
    }

    @Test
    fun `secondary constructor`() {
        class Person(val name: String) {
            var mood: String? = null

            constructor(name: String, mood: String) : this(name) {
                this.mood = mood
            }
        }

        val person = Person("Fred", "happy")
        assertEquals("Fred", person.name)
        assertEquals("happy", person.mood)
    }

    @Test
    fun `properties`() {
        // Using explicit backing for var property
        class Person(val name: String) {
            val properName: String
                get() = name.capitalize()
            var preferredName
                get() = _preferredName
                set(value) {
                    _preferredName = value
                }
            private var _preferredName: String? = null
        }

        val person = Person("fred")
        person.preferredName = "Freddie"
        assertEquals("Fred", person.properName)
        assertEquals("Freddie", person.preferredName)

        // Using implicit backing field
        class Person2(val name: String) {
            var preferredName: String? = null
                set(value) {
                    field = value
                }
        }

        val person2 = Person2("fred")
        person2.preferredName = "Freddie"
        assertEquals("Freddie", person2.preferredName)
    }

    @Test
    @Suppress("USELESS_IS_CHECK")
    fun `inheritance`() {
        open class Base(val p: Int) {
            open val foo: Int get() = p + 1
            open fun getBar(): Int = p * 2
        }

        class Derived(p: Int) : Base(p) {
            override val foo: Int get() = p + 2
            override fun getBar(): Int = super.getBar() * 3
        }

        val derived = Derived(5)
        assertTrue(derived is Derived)
        assertTrue(derived is Base)
        assertEquals(7, derived.foo)
        assertEquals(30, derived.getBar())
    }

    interface Interface {
        val x: Int
        fun getFoo(): String
    }

    @Test
    fun `interfaces`() {
        class Implementation : Interface {
            override val x: Int
                get() = 123

            override fun getFoo(): String {
                return "Hello"
            }
        }

        val implementation = Implementation()
        assertEquals(123, implementation.x)
        assertEquals("Hello", implementation.getFoo())
    }

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