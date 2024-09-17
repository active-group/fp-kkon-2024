// Composing Financial Contracts (Mark-Eber, Peyton Jones)
// markus.schlegel@active-group.de

// Wir definieren eine Syntax von Finanzverträgen
sealed interface Contract

enum class Currency { EUR, USD, GBP }

typealias Date = String
typealias Amount = Double

// data class ZeroCouponBond(val date: Date,
//                          val amount: Amount,
//                          val currency: Currency): Contract

// Keine Rechte, keine Pflichten
data object Zero: Contract

// Morgen bekomme ich 100 EUR
val c0 = zeroCouponBond("2024-09-18", 100.0, Currency.EUR)

data class One(val currency: Currency): Contract
val c1 = One(Currency.EUR)

data class Multiple(val amount: Amount, val contract: Contract): Contract
val c2 = Multiple(100.0, c1)

data class Later(val date: Date, val contract: Contract): Contract

val c3 = Later("2024-09-18", Multiple(100.0, One(Currency.EUR)))

fun zeroCouponBond(date: Date,
                   amount: Amount,
                   currency: Currency): Contract
    = Later(date, Multiple(amount, One(currency)))

// Monoid: A mit folgender Operation: plus: A -> A -> A
// plus(a, plus(b, c)) == plus(plus(a, b), c)
// empty element e: A
// plus(a, e) = a
// plus(e, a) = a

// Monoid
data class Plus(val c1: Contract, val c2: Contract): Contract

val c4 = Plus(c3, c1)

// Rechte und Pflichten umkehren:
data class Reverse(val contract: Contract): Contract

val c5 = Reverse(c0)

val c6 = zeroCouponBond("2025-09-18", 111.0, Currency.USD)
val c7 = zeroCouponBond("2025-09-18", 100.0, Currency.EUR)

val c8 = Plus(c6, Reverse(c7))

// Bilanz = Summe von Contracts

enum class Direction { LONG, SHORT }

// Eine Zahlung besteht aus:
// - Menge an
// - Currency
// - Datum
// - Richtung

data class Payment(val amount: Amount,
                   val currency: Currency,
                   val date: Date,
                   val direction: Direction)

// Denotation (Semantik):
fun payments(c: Contract, date: Date): Pair<List<Payment>, Contract> =
    when (c) {
        is Zero -> Pair(Nil, Zero)
        is One ->
            Pair(Cons(Payment(1.0, c.currency, date, Direction.LONG), Nil),
            Zero)
        is Later -> TODO()
        is Multiple -> TODO()
        is Plus -> {
            val (ps1, nc1)  = payments(c.c1, date)
            val (ps2, nc2) = payments(c.c2, date)
            Pair(append(ps1, ps2), Plus(nc1, nc2))
        }
        is Reverse -> TODO()
    }
