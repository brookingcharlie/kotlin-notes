package org.example

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CollectionTests {
    @Test
    fun `lists`() {
        val immutableList = listOf(1, 2, 3)
        assertTrue(2 in immutableList)

        val mutableList = mutableListOf(1, 2, 3)
        mutableList.add(4)
        assertTrue(4 in mutableList)

        for (n in immutableList) {
            // doSomething(n)
        }
    }

    @Test
    fun maps() {
        val immutableMap = mapOf('a' to 1, 'b' to 2, 'c' to 3)
        assertEquals(1, immutableMap['a'])

        val mutableMap = mutableMapOf('a' to 1, 'b' to 2, 'c' to 3)
        mutableMap['d'] = 4
        assertEquals(4, mutableMap['d'])

        for ((k, v) in immutableMap) {
            // doSomething(k, v)
        }
    }

    @Test
    fun `sets`() {
        val immutableSet = setOf(1, 2, 3)
        assertTrue(2 in immutableSet)

        val mutableSet = mutableSetOf(1, 2, 3)
        mutableSet.add(4)
        assertTrue(4 in mutableSet)

        for (n in immutableSet) {
            // doSomething(n)
        }
    }

    @Test
    fun `arrays`() {
        val xs = arrayOf(1, 2, 3)
        xs[2] = 4
        assertEquals(2, xs[1])
        assertEquals(4, xs[2])

        val ys: Array<Int?> = arrayOfNulls(4)
        assertEquals(4, ys.size)
        assertEquals(null, ys[3])

        val zs: IntArray = intArrayOf(1, 2, 3)
        assertEquals(2, zs[1])

        for (x in xs) {
            // doSomething(x)
        }
    }

    @Test
    fun `copying lists`() {
        val xs = mutableListOf(1, 2, 3)
        val ys = xs.toList()
        xs.add(4)
        assertEquals(listOf(1, 2, 3, 4), xs)
        assertEquals(listOf(1, 2, 3), ys)
    }

    @Test
    fun `covariance`() {
        open class Shape
        class Circle(r: Int) : Shape()

        val rectangles: List<Circle> = listOf(Circle(2), Circle(3))
        val shapes: List<Shape> = rectangles
        assertTrue(true, "Successfully assigned immutable List<Rect> to List<Shape>!")
        assertEquals(rectangles, shapes)

        // Trying the same with mutable lists would give a type-mismatch compile error.
        // This is to prevent runtime error, e.g. a Rectangle being added using mutableShapes.
        //
        //     val mutableRectangles: MutableList<Circle> = mutableListOf(Circle(2), Circle(3))
        //     val mutableShapes: MutableList<Shape> = mutableRectangles
        //
    }
}