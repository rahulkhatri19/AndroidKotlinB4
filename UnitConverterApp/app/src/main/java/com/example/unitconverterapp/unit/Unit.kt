package com.example.unitconverterapp.unit

enum class Unit(val unitFactor: Double, val unitName: String) {
    // Unit
    LENGTH(0.0, "Length"),
    AREA(0.0, "Area"),
    TIME(0.0, "Time"),
    WEIGHT(0.0, "Weight"),

    // Length
    METER(1.0, "Meter"),
    MILI_METER(1000.0, "Mili Meter"),
    CENTI_METER(100.0, "Centimeter"),
    FOOT(3.28, "Foot"),

    // Area
    SQUARE_METER(1.0, "Square Meter"),
    SQUARE_FOOT(10.7639, "Square Foot"),
    SQUARE_INCH(1550.0, "Square inch"),
    ACRE(0.000247, "Acre"),

    // Time
    MINUTE(1.0, "Minute"),
    SECOND(60.0, "Second"),
    HOUR(0.0167, "Hour"),

    // Weight
    GRAM(1.0, "Gram"),
    KILOGRAM(0.001, "Kilo Gram"),
    MILI_GRAM(1000.0, "Mili Gram"),

}