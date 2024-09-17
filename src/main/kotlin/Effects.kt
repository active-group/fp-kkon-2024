// Ein Effekt ist eins der folgenden:
// - Ein Lesen von stdin
// - Ein Schreiben nach stdout

sealed interface Effect<A>

data class Read<A>(val prompt: String): Effect<A>
data class Write(val s: String): Effect<Unit>

// Ein Programm ist eins der folgenden:
// - Ein Leseprogramm
// - Ein Schreibprogramm

sealed interface Code<A> {
    fun <B> append(k: (A) -> Code<B>): Code<B>
}

fun <A, B> append1(code1: Code<A>, weiter: (A) -> Code<B>): Code<B> = code1.append(weiter)

data class Read2<A>(val prompt: String,
                    val restCode: (String) -> Code<A>): Code<A> {
    override fun <B> append(k: (A) -> Code<B>): Code<B> =
        Read2(prompt) { value: String -> append1(this.restCode(value), k) }
    }

data class Write2<A>(val s: String,
                     val restCode: (Unit) -> Code<A>): Code<A> {
    override fun <B> append(k: (A) -> Code<B>): Code<B> =
        Write2(s) { u: Unit -> append1(this.restCode(u), k) }
}

data class Return<A>(val result: A): Code<A> {
    override fun <B> append(k: (A) -> Code<B>): Code<B> =
        k(result)
}

val lesen: Code<Int> = Read2("A") { a: String -> Return(a.toInt()) }
val lesen2: Code<Int> = Read2("A") { a: String ->
    Read2("B") { b ->
        Return(a.toInt() + b.toInt())
    } }

val schreibenLesen: Code<String> = Read2("A") { a: String ->
    Write2(a) {
        _ -> Return("Herzlichen Glückwunsch")
    }}

val readPair: Code<Pair<String, String>> = Read2("Left") {
    left -> Read2("Right") {
        right -> Return(Pair(left, right))
}
}

fun printPair(pair: Pair<String, String>): Code<Unit> = Write2(pair.toString()) { Return(Unit) }

val echoPair: Code<Unit> = append1(readPair, ::printPair)

// Ein Leseprogramm besteht aus:
// - Ein Prompt (String)

fun business(x: Int): Int = x + 1

fun taschenrechner() {
    val len: String = readln()
    val leni: Int = len.toInt()
    println(business(leni))
}

fun <A> run(code: Code<A>): A =
    when (code) {
        is Return -> code.result
        is Write2 -> {
            println(code.s)
            run(code.restCode(Unit))
        }
        is Read2 -> {
            println(code.prompt + ": ")
            val res: String = readln()
            run(code.restCode(res))
        }
    }

fun <A> runListAcc(code: Code<A>, inputs: List<String>, acc: List<String>): Pair<A, List<String>> =
    when (code) {
        is Return -> Pair(code.result, acc)
        is Write2 -> {
            // println(code.s)
            runListAcc(code.restCode(Unit), inputs, Cons(code.s, acc))
        }
        is Read2 -> {
            // println(code.prompt + ": ")
            when (inputs) {
                is Nil -> error("You're wrong")
                is Cons -> {
                    val res = inputs.head
                    runListAcc(code.restCode(res), inputs.tail, acc)
                }
            }
        }
    }

fun <A> runList(code: Code<A>, inputs: List<String>): Pair<A, List<String>> =
    runListAcc(code, inputs, Nil)