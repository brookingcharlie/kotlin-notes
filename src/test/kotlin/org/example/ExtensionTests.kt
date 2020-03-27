package org.example

import org.junit.Test
import kotlin.test.assertEquals

class ExtensionTests {
    @Test
    fun `extension method`() {
        fun String.isThreeLetters() = this.length == 3
        assertEquals(false, "xx".isThreeLetters())
        assertEquals(true, "xxx".isThreeLetters())
    }

    @Test
    fun `static resolution`() {
        open class Shape
        class Circle: Shape()

        fun Shape.getTooltip() = "Any kind of shape"
        fun Circle.getTooltip() = "A circle"

        fun getTooltipForShape(c: Shape): String {
            return c.getTooltip()
        }

        val circle = Circle()
        assertEquals("A circle", circle.getTooltip())
        assertEquals("Any kind of shape", getTooltipForShape(circle))
    }

    @Test
    @Suppress("EXTENSION_SHADOWED_BY_MEMBER")
    fun `member precedence`() {
        class C {
            fun getFoo() = "member"
        }
        fun C.getFoo() = "shadowed"
        fun C.getFoo(i: Int) = "extension $i"
        assertEquals("member", C().getFoo())
        assertEquals("extension 1", C().getFoo(1))
    }

    @Test
    fun `nullable receiver`() {
        fun String?.provided() = (this != null) && this.length > 0
        val s: String? = "asdf"
        val t: String? = null
        assertEquals(true, s.provided())
        assertEquals(false, t.provided())
    }

    class ClassWithCompanion {
        companion object {
            fun getFoo(): String = "foo"
        }
    }

    @Test
    fun `companion object extensions`() {
        fun ClassWithCompanion.Companion.getBar() = "bar"
        assertEquals("foo", ClassWithCompanion.getFoo())
        assertEquals("bar", ClassWithCompanion.getBar())
    }
}