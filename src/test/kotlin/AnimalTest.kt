import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AnimalTest {
    val dillo1 = Dillo(55000, true) // 55kg, lebendig
    val dillo2 = Dillo(58000, false) // 58kb, tot

    @Test
    fun feedDilloTest() {
        assertEquals(Dillo(55050, true), feedDillo(dillo1))
        assertEquals(dillo2, feedDillo(dillo2))
    }

    val parrot1 = Parrot(1000, "I love you") // 1kg, verliebt
    val parrot2 = Parrot(800, "") // 800g, tot

    @Test
    fun feedAnimalTest() {
        assertEquals(feedDillo(dillo1), feedAnimal(dillo1))
        assertEquals(feedDillo(dillo2), feedAnimal(dillo2))
        assertEquals(feedParrot(parrot1), feedAnimal(parrot1))
        assertEquals(feedParrot(parrot2), feedAnimal(parrot2))
    }
}