# KKon 2024: Workshop "Funktionale Programmierung – Deep Dive"

Code für den Workshop [Funktionale Programmierung – Deep
Dive"](https://rheinwerk-kkon.de/workshops/schlegel-deep-dive-funktionale-programmierung/)
auf der KKon 2024.

# Konstruktionsanleitungen

## Abstraktion

Wenn Du zwei Definitionen geschrieben hast, die inhaltlich verwandt sind und
viele Ähnlichkeiten aufweisen, abstrahiere wie folgt:

1. Kopiere eine der beiden Definitionen und gib ihr einen neuen Namen.
2. Ersetze die Stellen, bei denen sich die beiden Definitionen unterscheiden,
   jeweils durch eine neue Variable.
3. Füge die neuen Variablen als Parameter zum lambda der Definition hin zu oder
   füge ein neues lambda mit diesen Parametern ein. Du musst gegebenenfalls
   rekursive Aufrufe der Funktion anpassen.
4. Schreibe eine Signatur für die neue Funktion.
5. Ersetze die beiden alten Definitionen durch Aufrufe der neuen Definition.

## Zusammengesetzte Daten: Datenanalyse

Zusammengesetzte Daten kannst Du an Formulierungen wie "ein X besteht aus ...",
"ein X ist charakterisiert durch ..." oder "ein X hat ..." erkennen. Manchmal
lautet die Formulierung etwas anders. Die daraus resultierende Datendefinition
ist ein Kommentar im Programm in folgender Form:

```kotlin
// Ein X hat / besteht aus / ist charakterisiert durch:
// - Bestandteil / Eigenschaft 1
// - Bestandteil / Eigenschaft 2
// ...
// - Bestandteil / Eigenschaft n
```

Darauf folgt eine entsprechende Record-Definition. Dafür überlege Dir Namen für
den Record-Typ `T` und für die Felder, `f_1. .... f_n` .Zu jedem Feld gehört eine Signatur

## Zusammengesetzte Daten als Eingabe: Schablone

Wenn Deine Funktion zusammengesetzte Daten als Eingabe akzeptiert (das ergibt
sich aus der Signatur), gehe nach Schreiben des Gerüstes folgendermaßen vor:

1. Für jede Komponente, schreibe `r.sel` in die Schablone, wobei sel der
   Selektor der Komponente und r der Name des Record-Parameters ist, also zum
   Beispiel: `wt.hour`
2. Vervollständige die Schablone, indem Du einen Ausdruck konstruierst, indem
   die Selektor-Anwendungen vorkommen.
3. Es ist möglich, dass nicht alle Selektor-Anwendungen im Rumpf verwendet
   werden: In diesem Fall lösche die Selektor-Anwendung wieder.

## Zusammengesetzte Daten als Ausgabe: Schablone

Wenn Deine Funktion zusammengesetzte Daten als Ausgabe hat, schreibe einen
Aufruf des passenden Record-Konstruktors in den Rumpf, zunächst mit einer
Ellipse für jedes Feld des Records, also zum Beispiel:

```kotlin
Dillo(...)
```

## Gemische Daten: Datenanalyse

Gemischte Daten sind Fallunterscheidungen, bei denen jeder Fall eine eigene
Klasse von Daten mit eigener Signatur ist. Schreibe bei gemischten Daten eine
Signatur-Definition der folgenden Form unter die Datendefinition:

```kotlin
// ein Sig ist eines der folgenden
// - C1
// - C2
// - ...
// - Cn
sealed interface Sig

data class C1: Sig
data class C2: Sig
...
data class Cn: Sig
```

Sig ist die Signatur für die neue Datensorte; `C1` bis `Cn` sind die Signaturen,
aus denen die neue Datensorte zusammengemischt ist.

## Gemische Daten: Schablone

Eine Schablone für eine Funktion und deren Testfälle, die gemischte Daten
akzeptiert, kannst Du folgendermaßen konstruieren:

- Schreibe Tests für jeden der Fälle.
- Schreibe eine `when`-Verzweigung als Rumpf in die Schablone, die genau *n*
  Zweige hat -- also genau soviele Zweige, wie es Fälle in der Datendefinition
  beziehungsweise der Signatur gibt.
- Schreibe für jeden Zweig eine Bedingung, der den entsprechenden Fall identifiziert.
- Vervollständige die Zweige, indem Du eine Datenanalyse für jeden einzelnen
  Fall vornimmst und ensprechende Hilfsfunktionen und Konstruktionsanleitungen
  benutzt. Die übersichtlichsten Programme entstehen meist, wenn für jeden Fall
  sparate Hilfsfunktionen definiert sind.

