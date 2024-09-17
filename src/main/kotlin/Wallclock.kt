// Eine Wallclock besteht aus
// - stunden (int)
// - minuten (int)
data class Wallclock(
    val hours: Int,
    val minutes: Int
)

// Zusammengesetzte Daten
// bzw. Produkte (Int x Int)

// 1. Kurzbeschreibung
// 2. Datenanalyse
// 3. Signatur-Deklaration
// 4. Tests
// 5. Gerüst (TODO())
// 6. Schablonen
// 7. Rumpf

// Wie viele Minuten sind seit Mitternacht vergangen?
fun minutesSinceMidnight(wallclock: Wallclock): Int {
    return 60 * wallclock.hours + wallclock.minutes
}