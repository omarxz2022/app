package com.example.ui.art

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

internal fun DrawScope.drawCarShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height

    // Floor reflection shadow
    drawOval(
        color = Color.Black.copy(alpha = 0.36f),
        topLeft = Offset(w * 0.08f, h * 0.69f),
        size = Size(w * 0.84f, h * 0.12f),
    )

    val rearX = w * 0.26f
    val frontX = w * 0.74f
    val wheelY = h * 0.65f
    val wheelR = if (spec.shape == CollectableShape.SUV_OFFROADER || spec.shape == CollectableShape.PICKUP_TRUCK) {
        w * 0.115f
    } else {
        w * 0.098f
    }

    when (spec.shape) {
        CollectableShape.CITY_HATCH -> {
            val body = Path().apply {
                moveTo(w * 0.14f, h * 0.62f)
                lineTo(w * 0.17f, h * 0.42f)
                lineTo(w * 0.28f, h * 0.30f)
                lineTo(w * 0.58f, h * 0.30f)
                lineTo(w * 0.74f, h * 0.45f)
                lineTo(w * 0.86f, h * 0.49f)
                lineTo(w * 0.86f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.27f, 0.56f, 0.70f, 0.34f, 0.45f)
        }

        CollectableShape.SEDAN_SALOON -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.63f)
                lineTo(w * 0.14f, h * 0.45f)
                lineTo(w * 0.28f, h * 0.42f)
                lineTo(w * 0.38f, h * 0.31f)
                lineTo(w * 0.62f, h * 0.31f)
                lineTo(w * 0.75f, h * 0.44f)
                lineTo(w * 0.88f, h * 0.48f)
                lineTo(w * 0.88f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.34f, 0.60f, 0.72f, 0.34f, 0.44f)
        }

        CollectableShape.WAGON_ESTATE -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.63f)
                lineTo(w * 0.15f, h * 0.34f)
                lineTo(w * 0.62f, h * 0.32f)
                lineTo(w * 0.76f, h * 0.45f)
                lineTo(w * 0.88f, h * 0.49f)
                lineTo(w * 0.88f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.20f, 0.59f, 0.72f, 0.35f, 0.45f)
        }

        CollectableShape.SUV_OFFROADER -> {
            val body = Path().apply {
                moveTo(w * 0.13f, h * 0.60f)
                lineTo(w * 0.15f, h * 0.29f)
                lineTo(w * 0.66f, h * 0.29f)
                lineTo(w * 0.76f, h * 0.42f)
                lineTo(w * 0.87f, h * 0.45f)
                lineTo(w * 0.87f, h * 0.61f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.20f, 0.62f, 0.73f, 0.33f, 0.43f)
            // Spare tire / roof rack accent
            drawLine(spec.secondary, Offset(w * 0.20f, h * 0.27f), Offset(w * 0.60f, h * 0.27f), w * 0.025f, StrokeCap.Round)
        }

        CollectableShape.PICKUP_TRUCK -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.60f)
                lineTo(w * 0.12f, h * 0.44f)
                lineTo(w * 0.44f, h * 0.44f)
                lineTo(w * 0.46f, h * 0.30f)
                lineTo(w * 0.68f, h * 0.30f)
                lineTo(w * 0.76f, h * 0.43f)
                lineTo(w * 0.88f, h * 0.45f)
                lineTo(w * 0.88f, h * 0.61f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.48f, 0.66f, 0.73f, 0.34f, 0.43f)
        }

        CollectableShape.ROADSTER_CONVERTIBLE -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.64f)
                lineTo(w * 0.15f, h * 0.47f)
                lineTo(w * 0.42f, h * 0.47f)
                lineTo(w * 0.68f, h * 0.45f)
                lineTo(w * 0.88f, h * 0.50f)
                lineTo(w * 0.88f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            // Windshield & roll hoops
            drawLine(Color(0xFF90CAF9), Offset(w * 0.56f, h * 0.46f), Offset(w * 0.49f, h * 0.35f), w * 0.032f, StrokeCap.Round)
            drawCircle(spec.secondary, w * 0.042f, Offset(w * 0.36f, h * 0.44f))
        }

        CollectableShape.MUSCLE_COUPE -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.64f)
                lineTo(w * 0.14f, h * 0.46f)
                lineTo(w * 0.27f, h * 0.44f)
                lineTo(w * 0.39f, h * 0.34f)
                lineTo(w * 0.58f, h * 0.34f)
                lineTo(w * 0.74f, h * 0.45f)
                lineTo(w * 0.89f, h * 0.50f)
                lineTo(w * 0.89f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.35f, 0.56f, 0.70f, 0.37f, 0.45f)
        }

        CollectableShape.MID_ENGINE_SUPERCAR -> {
            val body = Path().apply {
                moveTo(w * 0.10f, h * 0.64f)
                lineTo(w * 0.13f, h * 0.47f)
                lineTo(w * 0.34f, h * 0.36f)
                lineTo(w * 0.56f, h * 0.36f)
                lineTo(w * 0.76f, h * 0.47f)
                lineTo(w * 0.90f, h * 0.53f)
                lineTo(w * 0.90f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.36f, 0.55f, 0.72f, 0.39f, 0.46f)
            // Side intake scoop
            drawRoundRect(spec.secondary, Offset(w * 0.41f, h * 0.49f), Size(w * 0.14f, h * 0.08f), CornerRadius(w * 0.02f))
        }

        CollectableShape.GT3_RACE_CAR -> {
            val body = Path().apply {
                moveTo(w * 0.12f, h * 0.64f)
                lineTo(w * 0.14f, h * 0.46f)
                lineTo(w * 0.32f, h * 0.35f)
                lineTo(w * 0.58f, h * 0.35f)
                lineTo(w * 0.76f, h * 0.47f)
                lineTo(w * 0.90f, h * 0.53f)
                lineTo(w * 0.90f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.34f, 0.56f, 0.71f, 0.38f, 0.46f)
            // Swan-neck GT rear wing & front splitter
            drawLine(spec.secondary, Offset(w * 0.17f, h * 0.46f), Offset(w * 0.14f, h * 0.32f), w * 0.028f)
            drawLine(spec.secondary, Offset(w * 0.08f, h * 0.32f), Offset(w * 0.25f, h * 0.33f), w * 0.038f, StrokeCap.Round)
            drawLine(spec.accent, Offset(w * 0.80f, h * 0.63f), Offset(w * 0.93f, h * 0.63f), w * 0.028f, StrokeCap.Round)
        }

        CollectableShape.RALLY_CAR -> {
            val body = Path().apply {
                moveTo(w * 0.13f, h * 0.62f)
                lineTo(w * 0.16f, h * 0.42f)
                lineTo(w * 0.29f, h * 0.31f)
                lineTo(w * 0.58f, h * 0.31f)
                lineTo(w * 0.74f, h * 0.45f)
                lineTo(w * 0.87f, h * 0.49f)
                lineTo(w * 0.87f, h * 0.63f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.28f, 0.56f, 0.70f, 0.34f, 0.44f)
            // Bi-plane rally wing & hood light pod
            drawLine(spec.secondary, Offset(w * 0.10f, h * 0.31f), Offset(w * 0.26f, h * 0.31f), w * 0.04f, StrokeCap.Round)
            drawCircle(Color(0xFFFFF59D), w * 0.032f, Offset(w * 0.84f, h * 0.46f))
        }

        CollectableShape.LE_MANS_PROTOTYPE -> {
            val body = Path().apply {
                moveTo(w * 0.09f, h * 0.64f)
                lineTo(w * 0.12f, h * 0.48f)
                lineTo(w * 0.32f, h * 0.44f)
                lineTo(w * 0.44f, h * 0.34f)
                lineTo(w * 0.60f, h * 0.36f)
                lineTo(w * 0.76f, h * 0.47f)
                lineTo(w * 0.91f, h * 0.54f)
                lineTo(w * 0.91f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            // Dorsal fin & rear LMP wing
            drawLine(spec.secondary, Offset(w * 0.20f, h * 0.34f), Offset(w * 0.46f, h * 0.36f), w * 0.035f, StrokeCap.Round)
            drawLine(spec.accent, Offset(w * 0.07f, h * 0.33f), Offset(w * 0.22f, h * 0.33f), w * 0.035f, StrokeCap.Round)
            drawOval(Color(0xFF1A222D), Offset(w * 0.45f, h * 0.37f), Size(w * 0.17f, h * 0.09f))
        }

        CollectableShape.FORMULA_OPEN_WHEEL -> {
            // Sidepod & nose cone
            val chassis = Path().apply {
                moveTo(w * 0.16f, h * 0.62f)
                lineTo(w * 0.26f, h * 0.42f)
                lineTo(w * 0.54f, h * 0.44f)
                lineTo(w * 0.89f, h * 0.56f)
                lineTo(w * 0.89f, h * 0.64f)
                close()
            }
            drawPath(chassis, spec.primary)
            // Airbox & Halo
            drawArc(Color(0xFF212121), 180f, 180f, false, Offset(w * 0.46f, h * 0.37f), Size(w * 0.18f, h * 0.12f), style = Stroke(w * 0.025f))
            // Tall rear wing & low front wing
            drawRect(spec.secondary, Offset(w * 0.10f, h * 0.33f), Size(w * 0.11f, h * 0.14f))
            drawRoundRect(spec.secondary, Offset(w * 0.80f, h * 0.58f), Size(w * 0.14f, h * 0.06f), CornerRadius(w * 0.02f))
            drawLine(spec.accent, Offset(w * 0.30f, h * 0.52f), Offset(w * 0.64f, h * 0.52f), w * 0.032f, StrokeCap.Round)
        }

        CollectableShape.VINTAGE_RACER -> {
            val body = Path().apply {
                moveTo(w * 0.11f, h * 0.64f)
                cubicTo(w * 0.12f, h * 0.46f, w * 0.34f, h * 0.35f, w * 0.52f, h * 0.36f)
                cubicTo(w * 0.70f, h * 0.37f, w * 0.88f, h * 0.48f, w * 0.89f, h * 0.63f)
                close()
            }
            drawPath(body, spec.primary)
            drawCabinGlass(w, h, 0.36f, 0.56f, 0.66f, 0.38f, 0.46f)
            // Vintage number roundel
            drawCircle(Color.White, w * 0.075f, Offset(w * 0.50f, h * 0.54f))
        }

        else -> Unit
    }

    // Headlight & taillight accents
    drawCircle(Color(0xFFFFF59D), w * 0.026f, Offset(w * 0.86f, h * 0.53f))
    drawCircle(Color(0xFFFF5252), w * 0.022f, Offset(w * 0.14f, h * 0.52f))

    // Wheels
    drawCarWheel(rearX, wheelY, wheelR, spec.secondary, spec.accent)
    drawCarWheel(frontX, wheelY, wheelR, spec.secondary, spec.accent)
}

private fun DrawScope.drawCabinGlass(
    w: Float,
    h: Float,
    leftX: Float,
    midX: Float,
    rightX: Float,
    topY: Float,
    bottomY: Float,
) {
    val glass = Path().apply {
        moveTo(w * leftX, h * bottomY)
        lineTo(w * (leftX + 0.06f), h * topY)
        lineTo(w * midX, h * topY)
        lineTo(w * rightX, h * bottomY)
        close()
    }
    drawPath(glass, Color(0xFF1D2B3A))
    drawLine(
        color = Color(0xFF90CAF9).copy(alpha = 0.45f),
        start = Offset(w * (leftX + 0.08f), h * (topY + 0.02f)),
        end = Offset(w * (midX - 0.02f), h * (topY + 0.02f)),
        strokeWidth = w * 0.018f,
        cap = StrokeCap.Round,
    )
}

private fun DrawScope.drawCarWheel(
    cx: Float,
    cy: Float,
    r: Float,
    rimAccent: Color,
    caliperColor: Color,
) {
    drawCircle(Color(0xFF141619), r, Offset(cx, cy))
    drawCircle(Color(0xFFB0BEC5), r * 0.62f, Offset(cx, cy), style = Stroke(r * 0.14f))
    drawCircle(caliperColor, r * 0.42f, Offset(cx - r * 0.15f, cy))
    drawCircle(rimAccent, r * 0.18f, Offset(cx, cy))
}
