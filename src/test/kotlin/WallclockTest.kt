import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WallclockTest {
    val wc1 = Wallclock(11, 55)  // 11:55 Uhr
    val wc2 = Wallclock(0, 0) // Mitternacht

    @Test
    fun minutesSinceMidnight() {
        assertEquals(715, minutesSinceMidnight(wc1))
        assertEquals(0, minutesSinceMidnight(wc2))
    }
}