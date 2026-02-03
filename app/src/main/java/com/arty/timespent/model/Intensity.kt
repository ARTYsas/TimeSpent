package com.arty.timespent.model

enum class Intensity(val multiplier: Float, val label: String) {
    CHILL(0.5f, "На чилле"),
    BASE(1.0f, "База"),
    FLEX(1.5f, "Флексим"),
    BERSERK(2.0f, "Берсерк")
}