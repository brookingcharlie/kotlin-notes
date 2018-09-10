// notes on <https://kotlinlang.org/docs/reference/basic-syntax.html>

fun sum1(a: Int, b: Int): Int {return a + b}
fun sum2(a: Int, b: Int) = a + b
assert(sum1(2, 3) == 5)
assert(sum2(2, 3) == 5)

fun printSum1(a: Int, b: Int): Unit {println("$a + $b = ${a + b}")}
fun printSum2(a: Int, b: Int) = println("$a + $b = ${a + b}")
assert(printSum1(2, 3) == Unit)
assert(printSum2(2, 3) == Unit)

val a: Int = 1
val b = 2
assert(b is Int)
// this would give "error: val cannot be reassigned"
//   b = 2
// to reassign, you need to use the var keyword instead
var c = 3
c = 4
assert(c == 4)

fun maxOf(a: Int, b: Int) = if (a > b) a else b
assert(maxOf(2, 3) == 3)

fun showNullableCheck(d: Int?) {
  // this would give "error: operator call ... not allowed on a nullable receiver 'd'"
  //   println(d + 5)
  // but the compiler automatically casts to non-nullable when it sees this if-stmt
  if (d != null) {
    println(d + 5)
  }
}

fun showTypeCheck(x: Any) {
  // this would give "error: unresolved reference: length"
  //   println(x.length)
  // but the compiler automatically casts to String when it sees this if-stmt
  if (x is String) {
    println(x.length)
  }
}
