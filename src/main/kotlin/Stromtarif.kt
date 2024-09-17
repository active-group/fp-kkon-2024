// Was ist eine Konstruktionsaneleitung

// 1. Kurzbeschreibung
// 2. Datenanalyse
// 3. Signatur-Deklaration
// 4. Tests
// 5. Gerüst (TODO())
// 6. Schablonen
// 7. Rumpf

/**
 * | Tarif              | Grundgebühr pro Monat | Verbrauchspreis pro kWh |
 * |--------------------+-----------------------+-------------------------|
 * | "Billig-Strom"     | € 4.90                | € 0.19                  |
 * | "Watt fuer Wenig"  | € 8.20                | € 0.16                  |
 */

// Gesamtkosten für billig-strom berechnen pro verbrauch
fun billigStrom(kWh: Int): Double {
    // return 4.90 + kWh * 0.19
    return stromtarifRechnung(4.90,
        0.19,
        kWh)
}

// Gesamtkosten für watt-fuer-wenig berechnen pro verbrauch
fun wattFuerWenig(kWh: Int): Double {
    // return 8.20 + kWh * 0.16
    return stromtarifRechnung(8.20,
        0.16,
        kWh)
}

// Gesamtkosten für einen bestimmten Vertrag berechnen
fun stromtarifRechnung(grundgebuehr: Double,
                       preisProKWh: Double,
                       kWh: Int): Double {
    return grundgebuehr + kWh * preisProKWh
}