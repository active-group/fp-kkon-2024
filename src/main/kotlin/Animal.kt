// Produkte (X besteht aus)

// Ein Tier auf dem texanischen Highway ist eines der folgenden
// - Gürteltiere (Dillos)
// - Papagei (Parrot)
// gemischte Daten
// mixed data
// Sum type/Summentyp
sealed interface Animal {
    fun feed(): Animal = feedAnimal(this)
}

// Ein Gürteltier besteht aus
// - einem Gewicht
// - lebendig oder tot?

data class Dillo (
    val weight: Int,
    val isAlive: Boolean
): Animal

// 1. Kurzbeschreibung
// 2. Datenanalyse
// 3. Signatur-Deklaration
// 4. Tests
// 5. Gerüst (TODO())
// 6. Schablonen
// 7. Rumpf

// Ein Gürteltier mit 50g Futter füttern.
fun feedDillo(dillo: Dillo): Dillo {
    if (dillo.isAlive) {
        return Dillo(dillo.weight + 50, dillo.isAlive)
    } else {
        return dillo
    }
}

// Ein Papagei besteht aus
// - ein Gewicht
// - einem Satz, den er sagen kann
data class Parrot(
    val weight: Int,
    val sentence: String
): Animal

// Einen Papagei mit 5g Futter füttern.
fun feedParrot(parrot: Parrot): Parrot {
    if (parrot.sentence == "") {
        return parrot
    } else {
        return Parrot(
            parrot.weight + 5,
            parrot.sentence
        )
    }
}

// Ein Tier füttern.
fun feedAnimal(animal: Animal): Animal =
    when (animal) {
        is Dillo -> feedDillo(animal)
        is Parrot -> feedParrot(animal)
}