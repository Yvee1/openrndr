package org.openrndr.color

import kotlin.math.min

data class ColorXYZa(val x: Double, val y: Double, val z: Double, val a: Double = 1.0) :
        ConvertibleToColorRGBa,
        OpacifiableColor<ColorXYZa>,
        AlgebraicColor<ColorXYZa> {

    companion object {
        val SO2_A = ColorXYZa(109.83, 100.0, 35.55)
        val SO2_C = ColorXYZa(98.04, 100.0, 118.11)
        val SO2_D65 = ColorXYZa(95.02, 100.0, 108.82)
        val SO2_F2 = ColorXYZa(98.09, 100.0, 67.53)
        val SO2_TL4 = ColorXYZa(101.40, 100.0, 65.90)
        val SO2_UL3000 = ColorXYZa(107.99, 100.0, 33.91)
        val SO2_D50 = ColorXYZa(107.99, 100.0, 82.45)
        val SO2_D60 = ColorXYZa(107.99, 100.0, 100.86)
        val SO2_D75 = ColorXYZa(107.99, 100.0, 122.53)

        val SO10_A = ColorXYZa(111.16, 100.0, 35.19)
        val SO10_C = ColorXYZa(97.30, 100.0, 116.14)
        val SO10_D65 = ColorXYZa(94.83, 100.0, 107.38)
        val SO10_F2 = ColorXYZa(102.12, 100.0, 69.37)
        val SO10_TL4 = ColorXYZa(103.82, 100.0, 66.90)
        val SO10_UL3000 = ColorXYZa(111.12, 100.0, 35.21)
        val SO10_D50 = ColorXYZa(96.72, 100.0, 81.45)
        val SO10_D60 = ColorXYZa(95.21, 100.0, 99.60)
        val SO10_D75 = ColorXYZa(94.45, 100.0, 120.70)

        val NEUTRAL = ColorXYZa.fromRGBa(ColorRGBa(1.0, 1.0, 1.0))

        fun fromRGBa(rgba: ColorRGBa): ColorXYZa {
            val x = 0.4124 * rgba.r + 0.3576 * rgba.g + 0.1805 * rgba.b
            val y = 0.2126 * rgba.r + 0.7152 * rgba.g + 0.0722 * rgba.b
            val z = 0.0193 * rgba.r + 0.1192 * rgba.g + 0.9505 * rgba.b
            return ColorXYZa(x, y, z, rgba.a)
        }
    }

    val minValue get() = min(min(x, y), z)

    fun toLABa(ref: ColorXYZa) = ColorLABa.fromXYZa(this, ref)
    fun toLUVa(ref: ColorXYZa) = ColorLUVa.fromXYZa(this, ref)
    override fun toRGBa(): ColorRGBa {
        val r = 3.2406 * x - 1.5372 * y - 0.4986 * z
        val g = -0.9689 * x + 1.8758 * y + 0.0415 * z
        val b = 0.0557 * x - 0.2040 * y + 1.0570 * z
        return ColorRGBa(r, g, b, a, Linearity.LINEAR)
    }

    fun toHSVa(): ColorHSVa = toRGBa().toHSVa()
    fun toHSLa(): ColorHSLa = toRGBa().toHSLa()
    override fun plus(other: ColorXYZa) = copy(x = x + other.x, y = y + other.y, z = z + other.z, a = a + other.a)
    override fun minus(other: ColorXYZa) = copy(x = x - other.x, y = y - other.y, z = z - other.z, a = a - other.a)
    override fun times(factor: Double): ColorXYZa = copy(x = x * factor, y = y * factor, z = z * factor, a = a * factor)
    override fun opacify(factor: Double) = copy(a = a * factor)
}