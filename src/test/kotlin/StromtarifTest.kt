import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals
import kotlin.test.Test

class StromtarifTest {
    @Test
    fun billigStromTest() {
        assertEquals(4.9, billigStrom(0), 0.01)
        assertEquals(6.8, billigStrom(10), 0.01)
        assertEquals(8.7, billigStrom(20), 0.01)
        assertEquals(10.6, billigStrom(30), 0.01)
    }

    @Test
    fun wattFuerrWenig() {
        assertEquals(8.2, wattFuerWenig(0), 0.01)
        assertEquals(9.8, wattFuerWenig(10), 0.01)
    }
}