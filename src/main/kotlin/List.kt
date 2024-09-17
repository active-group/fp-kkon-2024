// Eine Liste ist eines der folgenden
// - Eine Cons-Zelle
// - Eine leere Liste

sealed interface List<out A> {
    //fun atIndex(i: Int): A
}


// Eine Cons-Zelle besteht aus ...
// - Einem Wert
// - Einer Liste

data class Cons<A>(val head: A, val tail: List<A>): List<A>

// Eine leere Liste ist eine leere Liste

data object Nil: List<Nothing>

// Gesucht: Ein konkreter Typ List<T>, sodass List<T> <: List<A> für alle A
// Idee: Finde einen konkreten Typ T, sodass T <: A für alle A
// Antwort: Nothing <: A für alle A
// Und: Zeige, dass gilt: für alle Typen A und B: wenn A <: B, dann gilt auch: List<A> <: List<B>

val someNil: List<Int> = Nil
val otherNil: List<Boolean> = Nil

val intList: List<Int> = Cons(1, Cons(2, Nil)) // [1, 2]

// 1 5 99

// Eine Ganzzahl ist eins der folgenden:
// - Die Null
// - Ein Nachfolger

// Ein Nachfolger besteht aus:
// - einem Vorgänger (Ganzzahl)

// Wir brauchen: Konstruktoren, Prädikate, und Accessors

val zero: Int = 0
fun isZero(x: Int): Boolean = x == 0

fun makeSucc(pred: Int): Int = pred + 1
fun isSucc(x: Int): Boolean = x > 0
fun succPred(x: Int): Int = x - 1

tailrec fun <A> repeatAcc(a: A, n: Int, acc: List<A>): List<A> =
    when {
        isZero(n) -> acc
        isSucc(n) -> repeatAcc(a, succPred(n), Cons(a, acc))
        else -> error("Unreachable")
    }

fun <A> repeat(a: A, n: Int): List<A> = repeatAcc(a, n, Nil)



tailrec fun <A> lengthAcc(l: List<A>, acc: Int): Int =
    when (l) {
        is Cons -> // l.head, l.tail lengthAcc()
            lengthAcc(l.tail, acc + 1)
        is Nil -> acc
    }

fun <A> length(l: List<A>): Int = lengthAcc(l, 0)

// `map` ist eine Funktion höherer Ordnung,
// die eine Funktion (A) -> B nimmt und eine
// List<A> und eine List<B> zurückgibt

// map(Cons(1, Cons(2, Nil))) { x -> x + 1 }
// Cons(2, Cons(3, Nil))

fun <A, B> map(l: List<A>, f: (A) -> B): List<B> =
    when (l) {
        is Nil -> Nil
        is Cons -> Cons(f(l.head), map(l.tail, f)) // l.head ... l.tail  Cons ... map()
    }

fun <A> append(l1: List<A>, l2: List<A>): List<A> =
    when (l1) {
        is Nil -> l2
        is Cons -> Cons(l1.head, append(l1.tail, l2)) // l1.head l1.tail Cons() append(...)
    }