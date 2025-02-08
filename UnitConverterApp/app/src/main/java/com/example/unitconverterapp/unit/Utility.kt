package com.example.unitconverterapp.unit

object Utility {

    val categoryList = arrayOf(Unit.LENGTH.name to false, Unit.AREA.name to false, Unit.TIME.name to false, Unit.WEIGHT.name to false)

    val lengthList = arrayOf(Unit.MILI_METER, Unit.CENTI_METER, Unit.FOOT, Unit.METER)
    val areaList = arrayOf(Unit.SQUARE_METER, Unit.SQUARE_FOOT, Unit.SQUARE_INCH, Unit.ACRE)
    val timeList = arrayOf(Unit.MINUTE, Unit.SECOND, Unit.HOUR)
    val weightList = arrayOf(Unit.GRAM, Unit.KILOGRAM, Unit.MILI_GRAM)
}