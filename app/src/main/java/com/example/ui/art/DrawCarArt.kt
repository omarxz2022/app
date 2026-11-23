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

private val CAR_INK = Color(0xFF1D2127)

internal fun DrawScope.drawCarShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height
    val line = (w * 0.014f).coerceAtLeast(1.1f)

    // Studio contact ground shadow
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0x45000000), Color(0x18000000), Color.Transparent),
            center = Offset(w * 0.5f, h * 0.75f),
            radius = w * 0.47f,
        ),
        topLeft = Offset(w * 0.05f, h * 0.67f),
        size = Size(w * 0.90f, h * 0.16f),
    )

    val rearX = w * 0.255f
    val frontX = w * 0.745f
    val wheelY = h * 0.65f
    val wheelR = if (spec.shape == CollectableShape.SUV_OFFROADER || spec.shape == CollectableShape.PICKUP_TRUCK) {
        w * 0.112f
    } else {
        w * 0.098f
    }

    val paintBrush = Brush.verticalGradient(
        colors = listOf(
            spec.primary.lighten(0.32f),
            spec.primary,
            spec.primary.darken(0.28f),
        ),
        startY = h * 0.28f,
        endY = h * 0.66f,
    )

    when (spec.shape) {
        CollectableShape.CITY_HATCH -> {
            val body = Path().apply {
                moveTo(w * 0.13f, h * 0.63f)
                cubicTo(w * 0.12f, h * 0.50f, w * 0.15f, h * 0.39f, w * 0.26f, h * 0.30f)
                lineTo(w * 0.56f, h * 0.30f)
                cubicTo(w * 0.65f, h * 0.34f, w * 0.72f, h * 0.42f, w * 0.76f, h * 0.45f)
                cubicTo(w * 0.85f, h * 0.47f, w * 0.88f, h * 0.54f, w * 0.87f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.18f, beltEnd = 0.82f, beltY = 0.49f)
            drawCabinGlassWithInterior(w, h, 0.26f, 0.55f, 0.71f, 0.33f, 0.45f, line)
        }

        CollectableShape.SEDAN_SALOON -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.63f)
                lineTo(w * 0.13f, h * 0.45f)
                lineTo(w * 0.27f, h * 0.42f)
                cubicTo(w * 0.32f, h * 0.35f, w * 0.36f, h * 0.31f, w * 0.42f, h * 0.31f)
                lineTo(w * 0.60f, h * 0.31f)
                cubicTo(w * 0.67f, h * 0.35f, w * 0.73f, h * 0.41f, w * 0.76f, h * 0.44f)
                cubicTo(w * 0.86f, h * 0.46f, w * 0.89f, h * 0.53f, w * 0.88f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.48f)
            drawCabinGlassWithInterior(w, h, 0.33f, 0.59f, 0.72f, 0.34f, 0.44f, line)
            // B-pillar & door handle detail
            drawLine(CAR_INK, Offset(w * 0.50f, h * 0.34f), Offset(w * 0.50f, h * 0.62f), line * 0.8f)
        }

        CollectableShape.WAGON_ESTATE -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.63f)
                lineTo(w * 0.14f, h * 0.34f)
                lineTo(w * 0.61f, h * 0.32f)
                cubicTo(w * 0.69f, h * 0.37f, w * 0.74f, h * 0.43f, w * 0.77f, h * 0.45f)
                cubicTo(w * 0.86f, h * 0.47f, w * 0.89f, h * 0.54f, w * 0.88f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.48f)
            drawCabinGlassWithInterior(w, h, 0.19f, 0.59f, 0.73f, 0.35f, 0.45f, line)
        }

        CollectableShape.SUV_OFFROADER -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.60f)
                lineTo(w * 0.14f, h * 0.29f)
                lineTo(w * 0.66f, h * 0.29f)
                lineTo(w * 0.76f, h * 0.42f)
                lineTo(w * 0.88f, h * 0.45f)
                lineTo(w * 0.88f, h * 0.61f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.46f)
            drawCabinGlassWithInterior(w, h, 0.19f, 0.62f, 0.73f, 0.33f, 0.43f, line)
            drawLine(spec.secondary, Offset(w * 0.20f, h * 0.27f), Offset(w * 0.60f, h * 0.27f), w * 0.025f, StrokeCap.Round)
        }

        CollectableShape.PICKUP_TRUCK -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.60f)
                lineTo(w * 0.11f, h * 0.44f)
                lineTo(w * 0.44f, h * 0.44f)
                lineTo(w * 0.46f, h * 0.30f)
                lineTo(w * 0.68f, h * 0.30f)
                lineTo(w * 0.76f, h * 0.43f)
                lineTo(w * 0.89f, h * 0.45f)
                lineTo(w * 0.89f, h * 0.61f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.15f, beltEnd = 0.84f, beltY = 0.47f)
            drawCabinGlassWithInterior(w, h, 0.48f, 0.66f, 0.73f, 0.34f, 0.43f, line)
            drawLine(CAR_INK, Offset(w * 0.44f, h * 0.44f), Offset(w * 0.44f, h * 0.60f), line)
        }

        CollectableShape.ROADSTER_CONVERTIBLE -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.64f)
                lineTo(w * 0.14f, h * 0.47f)
                lineTo(w * 0.43f, h * 0.47f)
                lineTo(w * 0.69f, h * 0.45f)
                cubicTo(w * 0.82f, h * 0.47f, w * 0.89f, h * 0.52f, w * 0.89f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.50f)
            drawLine(Color(0xFF90CAF9), Offset(w * 0.57f, h * 0.46f), Offset(w * 0.49f, h * 0.35f), w * 0.032f, StrokeCap.Round)
            drawCircle(spec.secondary, w * 0.042f, Offset(w * 0.36f, h * 0.44f))
            drawCircle(CAR_INK, w * 0.042f, Offset(w * 0.36f, h * 0.44f), style = Stroke(line * 0.8f))
        }

        CollectableShape.MUSCLE_COUPE -> {
            val body = Path().apply {
                moveTo(w * 0.10f, h * 0.64f)
                lineTo(w * 0.13f, h * 0.46f)
                lineTo(w * 0.27f, h * 0.43f)
                cubicTo(w * 0.34f, h * 0.37f, w * 0.38f, h * 0.34f, w * 0.44f, h * 0.34f)
                lineTo(w * 0.58f, h * 0.34f)
                lineTo(w * 0.74f, h * 0.45f)
                cubicTo(w * 0.84f, h * 0.48f, w * 0.90f, h * 0.53f, w * 0.89f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.49f)
            drawCabinGlassWithInterior(w, h, 0.34f, 0.56f, 0.70f, 0.37f, 0.45f, line)
        }

        CollectableShape.MID_ENGINE_SUPERCAR -> {
            val body = Path().apply {
                moveTo(w * 0.09f, h * 0.64f)
                lineTo(w * 0.12f, h * 0.47f)
                cubicTo(w * 0.22f, h * 0.42f, w * 0.30f, h * 0.37f, w * 0.36f, h * 0.36f)
                lineTo(w * 0.56f, h * 0.36f)
                cubicTo(w * 0.66f, h * 0.41f, w * 0.76f, h * 0.46f, w * 0.90f, h * 0.54f)
                lineTo(w * 0.90f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.51f)
            drawCabinGlassWithInterior(w, h, 0.35f, 0.55f, 0.71f, 0.39f, 0.46f, line)
            drawRoundRect(Color(0xFF1A1E24), Offset(w * 0.41f, h * 0.49f), Size(w * 0.14f, h * 0.08f), CornerRadius(w * 0.02f))
        }

        CollectableShape.GT3_RACE_CAR -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.64f)
                lineTo(w * 0.14f, h * 0.46f)
                lineTo(w * 0.32f, h * 0.35f)
                lineTo(w * 0.58f, h * 0.35f)
                lineTo(w * 0.76f, h * 0.47f)
                lineTo(w * 0.90f, h * 0.53f)
                lineTo(w * 0.90f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.50f)
            drawCabinGlassWithInterior(w, h, 0.34f, 0.56f, 0.71f, 0.38f, 0.46f, line)
            drawLine(spec.secondary, Offset(w * 0.17f, h * 0.46f), Offset(w * 0.14f, h * 0.32f), w * 0.028f)
            drawLine(spec.secondary, Offset(w * 0.08f, h * 0.32f), Offset(w * 0.25f, h * 0.33f), w * 0.04f, StrokeCap.Round)
            drawLine(spec.accent, Offset(w * 0.80f, h * 0.63f), Offset(w * 0.93f, h * 0.63f), w * 0.028f, StrokeCap.Round)
            // White race number door roundel
            drawCircle(Color.White, w * 0.055f, Offset(w * 0.50f, h * 0.54f))
            drawCircle(CAR_INK, w * 0.055f, Offset(w * 0.50f, h * 0.54f), style = Stroke(line * 0.7f))
        }

        CollectableShape.RALLY_CAR -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.62f)
                lineTo(w * 0.16f, h * 0.42f)
                lineTo(w * 0.29f, h * 0.31f)
                lineTo(w * 0.58f, h * 0.31f)
                lineTo(w * 0.74f, h * 0.45f)
                lineTo(w * 0.87f, h * 0.49f)
                lineTo(w * 0.87f, h * 0.63f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.49f)
            drawCabinGlassWithInterior(w, h, 0.28f, 0.56f, 0.70f, 0.34f, 0.44f, line)
            drawLine(spec.secondary, Offset(w * 0.10f, h * 0.31f), Offset(w * 0.26f, h * 0.31f), w * 0.04f, StrokeCap.Round)
            drawCircle(Color(0xFFFFF59D), w * 0.032f, Offset(w * 0.84f, h * 0.46f))
        }

        CollectableShape.LE_MANS_PROTOTYPE -> {
            val body = Path().apply {
                moveTo(w * 0.08f, h * 0.64f)
                lineTo(w * 0.12f, h * 0.48f)
                lineTo(w * 0.32f, h * 0.44f)
                lineTo(w * 0.44f, h * 0.34f)
                lineTo(w * 0.60f, h * 0.36f)
                lineTo(w * 0.76f, h * 0.47f)
                lineTo(w * 0.91f, h * 0.54f)
                lineTo(w * 0.91f, h * 0.64f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.16f, beltEnd = 0.84f, beltY = 0.51f)
            drawLine(spec.secondary, Offset(w * 0.20f, h * 0.34f), Offset(w * 0.46f, h * 0.36f), w * 0.034f, StrokeCap.Round)
            drawLine(spec.accent, Offset(w * 0.07f, h * 0.33f), Offset(w * 0.22f, h * 0.33f), w * 0.036f, StrokeCap.Round)
            drawOval(Color(0xFF1A222D), Offset(w * 0.45f, h * 0.37f), Size(w * 0.17f, h * 0.09f))
        }

        CollectableShape.FORMULA_OPEN_WHEEL -> {
            val chassis = Path().apply {
                moveTo(w * 0.16f, h * 0.62f)
                lineTo(w * 0.26f, h * 0.42f)
                lineTo(w * 0.54f, h * 0.44f)
                lineTo(w * 0.89f, h * 0.56f)
                lineTo(w * 0.89f, h * 0.64f)
                close()
            }
            drawPath(chassis, paintBrush)
            drawPath(chassis, CAR_INK, style = Stroke(line, join = StrokeJoin.Round))
            // Halo & helmet
            drawCircle(spec.accent, w * 0.042f, Offset(w * 0.55f, h * 0.44f))
            drawArc(Color(0xFF212121), 180f, 180f, false, Offset(w * 0.46f, h * 0.37f), Size(w * 0.18f, h * 0.12f), style = Stroke(w * 0.028f))
            // Rear & Front wings
            drawRect(spec.secondary, Offset(w * 0.10f, h * 0.33f), Size(w * 0.11f, h * 0.14f))
            drawRect(CAR_INK, Offset(w * 0.10f, h * 0.33f), Size(w * 0.11f, h * 0.14f), style = Stroke(line))
            drawRoundRect(spec.secondary, Offset(w * 0.80f, h * 0.58f), Size(w * 0.14f, h * 0.06f), CornerRadius(w * 0.02f))
            drawLine(spec.accent, Offset(w * 0.30f, h * 0.52f), Offset(w * 0.64f, h * 0.52f), w * 0.032f, StrokeCap.Round)
        }

        CollectableShape.VINTAGE_RACER -> {
            val body = Path().apply {
                moveTo(w * 0.10f, h * 0.64f)
                cubicTo(w * 0.12f, h * 0.46f, w * 0.34f, h * 0.35f, w * 0.52f, h * 0.36f)
                cubicTo(w * 0.70f, h * 0.37f, w * 0.88f, h * 0.48f, w * 0.89f, h * 0.63f)
                close()
            }
            drawBodyAndHighlight(body, paintBrush, w, h, line, beltStart = 0.18f, beltEnd = 0.80f, beltY = 0.50f)
            drawCabinGlassWithInterior(w, h, 0.36f, 0.56f, 0.66f, 0.38f, 0.46f, line)
            drawCircle(Color.White, w * 0.072f, Offset(w * 0.50f, h * 0.54f))
            drawCircle(CAR_INK, w * 0.072f, Offset(w * 0.50f, h * 0.54f), style = Stroke(line * 0.8f))
        }

        else -> Unit
    }

    // Subtle authentic livery detail on closed-body cars
    if (spec.shape != CollectableShape.FORMULA_OPEN_WHEEL) {
        when (spec.pattern) {
            ArtPattern.RACING_STRIPE -> {
                drawLine(
                    color = spec.secondary.copy(alpha = 0.9f),
                    start = Offset(w * 0.22f, h * 0.54f),
                    end = Offset(w * 0.84f, h * 0.54f),
                    strokeWidth = w * 0.022f,
                    cap = StrokeCap.Round,
                )
            }
            ArtPattern.TWO_TONE -> {
                drawLine(
                    color = spec.secondary.copy(alpha = 0.85f),
                    start = Offset(w * 0.14f, h * 0.58f),
                    end = Offset(w * 0.86f, h * 0.58f),
                    strokeWidth = w * 0.036f,
                    cap = StrokeCap.Round,
                )
            }
            ArtPattern.CARBON_AERO -> {
                drawLine(
                    color = Color(0xFF171A1F),
                    start = Offset(w * 0.12f, h * 0.625f),
                    end = Offset(w * 0.90f, h * 0.625f),
                    strokeWidth = w * 0.026f,
                    cap = StrokeCap.Round,
                )
            }
            ArtPattern.SIDE_ACCENT -> {
                drawLine(
                    color = spec.accent.copy(alpha = 0.85f),
                    start = Offset(w * 0.34f, h * 0.55f),
                    end = Offset(w * 0.64f, h * 0.55f),
                    strokeWidth = w * 0.020f,
                    cap = StrokeCap.Round,
                )
            }
            else -> Unit
        }
    }

    // Headlight lens & taillight bezel
    if (spec.shape != CollectableShape.FORMULA_OPEN_WHEEL) {
        drawOval(Color(0xFFFFF8C4), Offset(w * 0.83f, h * 0.50f), Size(w * 0.055f, h * 0.042f))
        drawOval(CAR_INK, Offset(w * 0.83f, h * 0.50f), Size(w * 0.055f, h * 0.042f), style = Stroke(line * 0.75f))
    }
    drawOval(Color(0xFFFF3D3D), Offset(w * 0.12f, h * 0.49f), Size(w * 0.04f, h * 0.036f))

    if (spec.shape == CollectableShape.FORMULA_OPEN_WHEEL) {
        // Carbon suspension wishbones linking chassis to open wheels
        drawLine(Color(0xFF23272E), Offset(w * 0.34f, h * 0.58f), Offset(rearX, wheelY), w * 0.022f, StrokeCap.Round)
        drawLine(Color(0xFF23272E), Offset(w * 0.67f, h * 0.58f), Offset(frontX, wheelY), w * 0.022f, StrokeCap.Round)
    } else {
        // Dark recessed wheel well arches behind wheels
        drawCircle(Color(0xFF14161A), wheelR * 1.16f, Offset(rearX, wheelY))
        drawCircle(Color(0xFF14161A), wheelR * 1.16f, Offset(frontX, wheelY))
    }

    // 5-spoke alloys, brake rotors & colored calipers
    drawDetailedCarWheel(rearX, wheelY, wheelR, spec.secondary, spec.accent, line)
    drawDetailedCarWheel(frontX, wheelY, wheelR, spec.secondary, spec.accent, line)
}

private fun DrawScope.drawBodyAndHighlight(
    body: Path,
    paintBrush: Brush,
    w: Float,
    h: Float,
    line: Float,
    beltStart: Float,
    beltEnd: Float,
    beltY: Float,
) {
    drawPath(body, paintBrush)
    drawLine(
        color = Color.White.copy(alpha = 0.36f),
        start = Offset(w * beltStart, h * beltY),
        end = Offset(w * beltEnd, h * beltY),
        strokeWidth = w * 0.016f,
        cap = StrokeCap.Round,
    )
    drawPath(body, CAR_INK, style = Stroke(line, join = StrokeJoin.Round))
}

private fun DrawScope.drawCabinGlassWithInterior(
    w: Float,
    h: Float,
    leftX: Float,
    midX: Float,
    rightX: Float,
    topY: Float,
    bottomY: Float,
    line: Float,
) {
    val glass = Path().apply {
        moveTo(w * leftX, h * bottomY)
        lineTo(w * (leftX + 0.06f), h * topY)
        lineTo(w * midX, h * topY)
        lineTo(w * rightX, h * bottomY)
        close()
    }
    drawPath(glass, Brush.verticalGradient(listOf(Color(0xFF425568), Color(0xFF19232E))))
    // Interior headrest silhouette
    drawOval(Color(0xFF12161B), Offset(w * ((leftX + midX) * 0.5f), h * (bottomY - 0.06f)), Size(w * 0.055f, h * 0.055f))
    // Diagonal sky reflection
    drawLine(
        color = Color.White.copy(alpha = 0.34f),
        start = Offset(w * (leftX + 0.09f), h * (topY + 0.018f)),
        end = Offset(w * (midX - 0.03f), h * (topY + 0.018f)),
        strokeWidth = w * 0.016f,
        cap = StrokeCap.Round,
    )
    drawPath(glass, CAR_INK, style = Stroke(line * 0.85f, join = StrokeJoin.Round))
}

private fun DrawScope.drawDetailedCarWheel(
    cx: Float,
    cy: Float,
    r: Float,
    rimAccent: Color,
    caliperColor: Color,
    line: Float,
) {
    drawCircle(Color(0xFF171A1F), r, Offset(cx, cy))
    drawCircle(CAR_INK, r, Offset(cx, cy), style = Stroke(line))
    // Slotted silver rotor & brake caliper
    drawCircle(Color(0xFF9EA7B3), r * 0.62f, Offset(cx, cy))
    drawCircle(caliperColor, r * 0.24f, Offset(cx - r * 0.24f, cy))
    // 5 alloy spokes
    for (i in 0 until 5) {
        val rad = Math.toRadians((i * 72 - 18).toDouble())
        drawLine(
            color = Color(0xFFE0E5EC),
            start = Offset(cx, cy),
            end = Offset(cx + (r * 0.68f * cos(rad)).toFloat(), cy + (r * 0.68f * sin(rad)).toFloat()),
            strokeWidth = r * 0.14f,
            cap = StrokeCap.Round,
        )
    }
    drawCircle(Color(0xFFCFD8DC), r * 0.72f, Offset(cx, cy), style = Stroke(r * 0.12f))
    drawCircle(rimAccent, r * 0.17f, Offset(cx, cy))
}
