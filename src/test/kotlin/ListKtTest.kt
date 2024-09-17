import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ListKtTest {

    @Test
    fun repeat() {
        assertEquals(Cons(true, Cons(true, Nil)), repeat(true, 2))
        assertEquals(Nil, repeat(true, 0))
    }

    @Test
    fun repeatAndLength() {
        assertEquals(length(repeat(true, 99999)),99999)
    }

    @Test
    fun length() {

    }

    @Test
    fun map() {
        assertEquals(Nil, map(Nil as List<Int>) {x -> x + 1})
        assertEquals(Cons(2, Nil), map(Cons(1, Nil)) {x -> x + 1})
        assertEquals(
            Cons(33, Cons(2, Nil)),
            map(Cons(32, Cons(1, Nil))) {x -> x + 1})
    }

    @Test
    fun append() {
        assertEquals(Nil, append(Nil, Nil))
        assertEquals(Cons(1, Nil), append(Nil, Cons(1, Nil)))
        assertEquals(Cons(1, Cons(2, Nil)), append(Cons(1, Nil), Cons(2, Nil)))
        assertEquals(
            Cons(1, Cons(2, Cons(3, Nil))),
            append(
                Cons(1, Cons(2, Nil)),
                Cons(3, Nil)))
    }
}
