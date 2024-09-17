// Flüsse -- Daten mit Selbstbezug (Rekursion!)

// Ein Fluss ist eines der folgenden
// - ein Bach aus einer Quelle
// - ein Zusammentreffen von einem Hauptfluss und einem Nebenfluss
sealed interface River

// Ein Bach besteht aus
// - Ursprungsort
data class Creek(
    val origin: String
): River

// Ein Zusammentreffen besteht aus:
// - Name des Ortes
// - Hauptfluss
// - Nebenfluss
data class Confluence(
    val location: String,
    val hauptfluss: River,
    val nebenfluss: River
): River

// Fließt Wasser von einem Ort in den Fluss?
fun flowsFrom(location: String, river: River): Boolean =
    when (river) {
        is Creek -> river.origin == location
        is Confluence ->
            river.location == location
                    || flowsFrom(location, river.hauptfluss)
                    || flowsFrom(location, river.nebenfluss)
    }