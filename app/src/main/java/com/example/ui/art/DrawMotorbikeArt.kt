package com.example.ui.art

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

private val BIKE_INK = Color(0xFF1D2127)

internal fun DrawScope.drawMotorbikeShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height
    val line = (w * 0.014f).coerceAtLeast(1.1f)

    // Soft studio ground contact shadow
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0x45000000), Color(0x16000000), Color.Transparent),
            center = Offset(w * 0.5f, h * 0.77f),
            radius = w * 0.45f,
        ),
        topLeft = Offset(w * 0.08f, h * 0.69f),
        size = Size(w * 0.84f, h * 0.16f),
    )

    val wheelR = when (spec.shape) {
        CollectableShape.MINI_MOTO -> w * 0.118f
        CollectableShape.SCOOTER -> w * 0.108f
        else -> w * 0.142f
    }
    val rearX = w * 0.26f
    val frontX = w * 0.74f
    val wheelY = h * 0.64f

    // Rear swingarm & chain guard
    drawLine(Color(0xFF3E4550), Offset(rearX, wheelY), Offset(w * 0.47f, h * 0.61f), w * 0.04f, StrokeCap.Round)
    drawLine(BIKE_INK, Offset(rearX, wheelY), Offset(w * 0.47f, h * 0.61f), line)

    // Swept exhaust header & titanium muffler canister
    val pipe = Path().apply {
        moveTo(w * 0.56f, h * 0.58f)
        cubicTo(w * 0.50f, h * 0.69f, w * 0.36f, h * 0.68f, w * 0.16f, h * 0.57f)
    }
    drawPath(pipe, Color(0xFFC5B38E), style = Stroke(w * 0.044f, cap = StrokeCap.Round))
    drawPath(pipe, BIKE_INK, style = Stroke(line))
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFFCFD8DC), Color(0xFF607D8B))),
        topLeft = Offset(w * 0.13f, h * 0.53f),
        size = Size(w * 0.18f, h * 0.075f),
        cornerRadius = CornerRadius(w * 0.03f),
    )
    drawRoundRect(BIKE_INK, Offset(w * 0.13f, h * 0.53f), Size(w * 0.18f, h * 0.075f), CornerRadius(w * 0.03f), style = Stroke(line))

    // Realistic wheels with brake rotors & calipers
    drawDetailedBikeWheel(rearX, wheelY, wheelR, spec.secondary, spec.accent, line, isFront = false)
    drawDetailedBikeWheel(frontX, wheelY, wheelR, spec.secondary, spec.accent, line, isFront = true)

    // Sculpted engine crankcase, radiator & frame
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF54606E), Color(0xFF282E36))),
        topLeft = Offset(w * 0.37f, h * 0.48f),
        size = Size(w * 0.24f, h * 0.17f),
        cornerRadius = CornerRadius(w * 0.035f),
    )
    drawRoundRect(BIKE_INK, Offset(w * 0.37f, h * 0.48f), Size(w * 0.24f, h * 0.17f), CornerRadius(w * 0.035f), style = Stroke(line))
    drawCircle(Color(0xFF3E4652), w * 0.048f, Offset(w * 0.45f, h * 0.57f))
    drawCircle(BIKE_INK, w * 0.048f, Offset(w * 0.45f, h * 0.57f), style = Stroke(line * 0.85f))

    val bodyGradient = Brush.verticalGradient(
        listOf(spec.primary.lighten(0.24f), spec.primary, spec.primary.darken(0.28f)),
    )

    when (spec.shape) {
        CollectableShape.MINI_MOTO -> {
            drawForkLeg(w * 0.59f, h * 0.35f, frontX, wheelY, Color(0xFFD4AF37), w, line)
            drawRoundRect(bodyGradient, Offset(w * 0.25f, h * 0.41f), Size(w * 0.40f, h * 0.14f), CornerRadius(w * 0.06f))
            drawRoundRect(BIKE_INK, Offset(w * 0.25f, h * 0.41f), Size(w * 0.40f, h * 0.14f), CornerRadius(w * 0.06f), style = Stroke(line))
            drawBikeHeadlight(w * 0.65f, h * 0.40f, w, line)
        }

        CollectableShape.SCOOTER -> {
            val apron = Path().apply {
                moveTo(w * 0.20f, h * 0.45f)
                cubicTo(w * 0.32f, h * 0.46f, w * 0.44f, h * 0.48f, w * 0.52f, h * 0.56f)
                lineTo(w * 0.56f, h * 0.34f)
                lineTo(w * 0.66f, h * 0.34f)
                cubicTo(w * 0.72f, h * 0.48f, w * 0.75f, h * 0.56f, w * 0.73f, h * 0.63f)
                lineTo(w * 0.56f, h * 0.63f)
                lineTo(w * 0.42f, h * 0.60f)
                lineTo(w * 0.22f, h * 0.56f)
                close()
            }
            drawPath(apron, bodyGradient)
            drawPath(apron, BIKE_INK, style = Stroke(line, join = StrokeJoin.Round))
            drawRoundRect(spec.secondary, Offset(w * 0.24f, h * 0.40f), Size(w * 0.24f, h * 0.065f), CornerRadius(w * 0.03f))
            drawRoundRect(BIKE_INK, Offset(w * 0.24f, h * 0.40f), Size(w * 0.24f, h * 0.065f), CornerRadius(w * 0.03f), style = Stroke(line))
            drawBikeHeadlight(w * 0.67f, h * 0.38f, w, line)
        }

        CollectableShape.RETRO_ROADSTER -> {
            drawForkLeg(w * 0.59f, h * 0.34f, frontX, wheelY, Color(0xFFCFD8DC), w, line)
            drawOval(bodyGradient, Offset(w * 0.41f, h * 0.35f), Size(w * 0.23f, h * 0.14f))
            drawOval(BIKE_INK, Offset(w * 0.41f, h * 0.35f), Size(w * 0.23f, h * 0.14f), style = Stroke(line))
            drawRoundRect(Color(0xFF5D4037), Offset(w * 0.21f, h * 0.42f), Size(w * 0.24f, h * 0.065f), CornerRadius(w * 0.03f))
            drawRoundRect(BIKE_INK, Offset(w * 0.21f, h * 0.42f), Size(w * 0.24f, h * 0.065f), CornerRadius(w * 0.03f), style = Stroke(line))
            drawBikeHeadlight(w * 0.66f, h * 0.39f, w, line)
        }

        CollectableShape.CRUISER_CHOPPER -> {
            drawForkLeg(w * 0.55f, h * 0.30f, frontX, wheelY, Color(0xFFE0E5EA), w, line)
            drawOval(bodyGradient, Offset(w * 0.37f, h * 0.37f), Size(w * 0.24f, h * 0.13f))
            drawOval(BIKE_INK, Offset(w * 0.37f, h * 0.37f), Size(w * 0.24f, h * 0.13f), style = Stroke(line))
            drawRoundRect(Color(0xFF1F2227), Offset(w * 0.20f, h * 0.45f), Size(w * 0.22f, h * 0.075f), CornerRadius(w * 0.035f))
            drawBikeHeadlight(w * 0.62f, h * 0.37f, w, line)
        }

        CollectableShape.BAGGER_TOURER -> {
            drawForkLeg(w * 0.60f, h * 0.35f, frontX, wheelY, Color(0xFFCFD8DC), w, line)
            drawRoundRect(bodyGradient, Offset(w * 0.15f, h * 0.45f), Size(w * 0.23f, h * 0.16f), CornerRadius(w * 0.04f))
            drawRoundRect(BIKE_INK, Offset(w * 0.15f, h * 0.45f), Size(w * 0.23f, h * 0.16f), CornerRadius(w * 0.04f), style = Stroke(line))
            val fairing = Path().apply {
                moveTo(w * 0.48f, h * 0.36f)
                cubicTo(w * 0.60f, h * 0.34f, w * 0.72f, h * 0.38f, w * 0.74f, h * 0.48f)
                lineTo(w * 0.64f, h * 0.58f)
                lineTo(w * 0.40f, h * 0.52f)
                close()
            }
            drawPath(fairing, bodyGradient)
            drawPath(fairing, BIKE_INK, style = Stroke(line, join = StrokeJoin.Round))
            drawLine(Color(0xFF81D4FA).copy(alpha = 0.72f), Offset(w * 0.56f, h * 0.35f), Offset(w * 0.64f, h * 0.23f), w * 0.034f, StrokeCap.Round)
        }

        CollectableShape.ADVENTURE_ENDURO -> {
            drawForkLeg(w * 0.59f, h * 0.29f, frontX, wheelY, Color(0xFFD4AF37), w, line)
            val body = Path().apply {
                moveTo(w * 0.18f, h * 0.44f)
                lineTo(w * 0.46f, h * 0.34f)
                lineTo(w * 0.75f, h * 0.42f)
                lineTo(w * 0.58f, h * 0.51f)
                lineTo(w * 0.23f, h * 0.51f)
                close()
            }
            drawPath(body, bodyGradient)
            drawPath(body, BIKE_INK, style = Stroke(line, join = StrokeJoin.Round))
            drawLine(Color(0xFF90CAF9).copy(alpha = 0.7f), Offset(w * 0.56f, h * 0.35f), Offset(w * 0.64f, h * 0.23f), w * 0.03f, StrokeCap.Round)
        }

        CollectableShape.NAKED_STREETFIGHTER -> {
            drawForkLeg(w * 0.58f, h * 0.35f, frontX, wheelY, Color(0xFFD4AF37), w, line)
            // Trellis frame tubes
            drawLine(spec.secondary, Offset(w * 0.32f, h * 0.47f), Offset(w * 0.54f, h * 0.41f), w * 0.028f, StrokeCap.Round)
            drawLine(spec.secondary, Offset(w * 0.36f, h * 0.51f), Offset(w * 0.52f, h * 0.46f), w * 0.024f, StrokeCap.Round)
            val tank = Path().apply {
                moveTo(w * 0.18f, h * 0.39f)
                lineTo(w * 0.40f, h * 0.44f)
                cubicTo(w * 0.48f, h * 0.34f, w * 0.60f, h * 0.34f, w * 0.67f, h * 0.44f)
                lineTo(w * 0.35f, h * 0.50f)
                close()
            }
            drawPath(tank, bodyGradient)
            drawPath(tank, BIKE_INK, style = Stroke(line, join = StrokeJoin.Round))
            drawBikeHeadlight(w * 0.67f, h * 0.43f, w, line)
        }

        CollectableShape.SUPERSPORT_FAIRING,
        CollectableShape.MOTOGP_PROTOTYPE,
        CollectableShape.CUSTOM_TURBINE -> {
            drawForkLeg(w * 0.59f, h * 0.35f, frontX, wheelY, Color(0xFFD4AF37), w, line)
            val fairing = Path().apply {
                moveTo(w * 0.15f, h * 0.34f)
                lineTo(w * 0.38f, h * 0.44f)
                cubicTo(w * 0.48f, h * 0.34f, w * 0.62f, h * 0.34f, w * 0.78f, h * 0.46f)
                lineTo(w * 0.63f, h * 0.60f)
                lineTo(w * 0.35f, h * 0.58f)
                close()
            }
            drawPath(fairing, bodyGradient)
            drawPath(fairing, BIKE_INK, style = Stroke(line, join = StrokeJoin.Round))
            // Windscreen + glossy fairing stripe + air duct
            drawLine(Color(0xFF90CAF9).copy(alpha = 0.72f), Offset(w * 0.57f, h * 0.36f), Offset(w * 0.68f, h * 0.30f), w * 0.032f, StrokeCap.Round)
            drawLine(spec.secondary, Offset(w * 0.44f, h * 0.48f), Offset(w * 0.71f, h * 0.48f), w * 0.034f, StrokeCap.Round)
            drawOval(Color(0xFF1A1E24), Offset(w * 0.52f, h * 0.49f), Size(w * 0.11f, h * 0.06f))
            if (spec.shape == CollectableShape.MOTOGP_PROTOTYPE) {
                drawLine(spec.accent, Offset(w * 0.69f, h * 0.43f), Offset(w * 0.79f, h * 0.40f), w * 0.032f, StrokeCap.Round)
            }
            drawBikeHeadlight(w * 0.74f, h * 0.45f, w, line)
        }

        else -> Unit
    }
}

private fun DrawScope.drawForkLeg(x1: Float, y1: Float, x2: Float, y2: Float, forkColor: Color, w: Float, line: Float) {
    drawLine(BIKE_INK, Offset(x1, y1), Offset(x2, y2), w * 0.048f, StrokeCap.Round)
    drawLine(forkColor, Offset(x1, y1), Offset(x2, y2), w * 0.030f, StrokeCap.Round)
}

private fun DrawScope.drawBikeHeadlight(x: Float, y: Float, w: Float, line: Float) {
    drawCircle(Color(0xFFFFF59D), w * 0.036f, Offset(x, y))
    drawCircle(BIKE_INK, w * 0.036f, Offset(x, y), style = Stroke(line * 0.8f))
}

private fun DrawScope.drawDetailedBikeWheel(
    cx: Float,
    cy: Float,
    r: Float,
    rimAccent: Color,
    caliperColor: Color,
    line: Float,
    isFront: Boolean,
) {
    drawCircle(Color(0xFF181B20), r, Offset(cx, cy))
    drawCircle(BIKE_INK, r, Offset(cx, cy), style = Stroke(line))
    drawCircle(rimAccent, r * 0.70f, Offset(cx, cy), style = Stroke(r * 0.09f))
    // 5 alloy spokes
    for (i in 0 until 5) {
        val rad = Math.toRadians((i * 72).toDouble())
        drawLine(
            color = Color(0xFFCFD8DC),
            start = Offset(cx, cy),
            end = Offset(cx + (r * 0.64f * cos(rad)).toFloat(), cy + (r * 0.64f * sin(rad)).toFloat()),
            strokeWidth = r * 0.11f,
            cap = StrokeCap.Round,
        )
    }
    if (isFront) {
        drawCircle(Color(0xFFB0BEC5), r * 0.46f, Offset(cx, cy), style = Stroke(r * 0.14f))
        drawCircle(caliperColor, r * 0.22f, Offset(cx - r * 0.36f, cy + r * 0.16f))
    }
    drawCircle(Color(0xFF263238), r * 0.18f, Offset(cx, cy))
}
