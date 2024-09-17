import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RiverTest() {

    val schlichem = Creek("Tieringen")
    val eschach = Creek("Heimliswald")
    val prim = Creek("Dreifaltigkeitsberg")
    val neckar1 = Confluence(
        "Rottweil",
        eschach,
        prim
    )
    val neckar2 = Confluence(
        "Epfendorf",
        neckar1,
        schlichem
    )

    @Test
    fun flowsFromTest() {
        assert(flowsFrom("Heimliswald", eschach))
        assert(flowsFrom("Dreifaltigkeitsberg", prim))
        assert(flowsFrom("Rottweil", neckar2))
        assert(!flowsFrom("Berlin", neckar2))
    }
}