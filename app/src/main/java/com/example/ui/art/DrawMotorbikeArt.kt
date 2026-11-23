package com.example.ui.art

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

internal fun DrawScope.drawMotorbikeShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height

    // Ground shadow
    drawOval(
        color = Color.Black.copy(alpha = 0.35f),
        topLeft = Offset(w * 0.12f, h * 0.72f),
        size = Size(w * 0.76f, h * 0.12f),
    )

    val wheelR = when (spec.shape) {
        CollectableShape.MINI_MOTO -> w * 0.115f
        CollectableShape.SCOOTER -> w * 0.105f
        else -> w * 0.135f
    }
    val rearX = w * 0.26f
    val frontX = w * 0.74f
    val wheelY = h * 0.65f

    // Exhaust pipe
    drawLine(
        color = Color(0xFFB0BEC5),
        start = Offset(w * 0.44f, h * 0.64f),
        end = Offset(w * 0.16f, h * 0.60f),
        strokeWidth = w * 0.042f,
        cap = StrokeCap.Round,
    )

    // Wheels
    drawBikeWheel(rearX, wheelY, wheelR, spec.secondary)
    drawBikeWheel(frontX, wheelY, wheelR, spec.secondary)

    // Engine block
    drawRoundRect(
        color = Color(0xFF37474F),
        topLeft = Offset(w * 0.38f, h * 0.50f),
        size = Size(w * 0.22f, h * 0.16f),
        cornerRadius = CornerRadius(w * 0.03f),
    )

    when (spec.shape) {
        CollectableShape.MINI_MOTO -> {
            drawLine(spec.secondary, Offset(w * 0.60f, h * 0.36f), Offset(frontX, wheelY), w * 0.04f, StrokeCap.Round)
            drawRoundRect(spec.primary, Offset(w * 0.28f, h * 0.44f), Size(w * 0.36f, h * 0.12f), CornerRadius(w * 0.05f))
            drawCircle(Color(0xFFFFF59D), w * 0.035f, Offset(w * 0.66f, h * 0.42f))
        }

        CollectableShape.SCOOTER -> {
            // Step-through apron & seat
            val apron = Path().apply {
                moveTo(w * 0.22f, h * 0.46f)
                lineTo(w * 0.48f, h * 0.48f)
                lineTo(w * 0.60f, h * 0.64f)
                lineTo(w * 0.72f, h * 0.62f)
                lineTo(w * 0.66f, h * 0.34f)
                lineTo(w * 0.56f, h * 0.34f)
                lineTo(w * 0.54f, h * 0.56f)
                lineTo(w * 0.40f, h * 0.54f)
                lineTo(w * 0.26f, h * 0.42f)
                close()
            }
            drawPath(apron, spec.primary)
            drawRoundRect(spec.secondary, Offset(w * 0.24f, h * 0.41f), Size(w * 0.24f, h * 0.06f), CornerRadius(w * 0.03f))
            drawCircle(Color(0xFFFFF9C4), w * 0.032f, Offset(w * 0.68f, h * 0.38f))
        }

        CollectableShape.RETRO_ROADSTER -> {
            drawLine(Color(0xFFB0BEC5), Offset(w * 0.60f, h * 0.35f), Offset(frontX, wheelY), w * 0.038f, StrokeCap.Round)
            // Classic round tank + flat bench seat
            drawOval(spec.primary, Offset(w * 0.42f, h * 0.36f), Size(w * 0.22f, h * 0.13f))
            drawRoundRect(Color(0xFF5D4037), Offset(w * 0.22f, h * 0.43f), Size(w * 0.24f, h * 0.06f), CornerRadius(w * 0.03f))
            drawCircle(Color(0xFFFFF59D), w * 0.04f, Offset(w * 0.66f, h * 0.40f))
        }

        CollectableShape.CRUISER_CHOPPER -> {
            // Raked front fork
            drawLine(Color(0xFFCFD8DC), Offset(w * 0.56f, h * 0.32f), Offset(frontX, wheelY), w * 0.04f, StrokeCap.Round)
            // Low teardrop tank & swept seat
            drawOval(spec.primary, Offset(w * 0.38f, h * 0.38f), Size(w * 0.22f, h * 0.12f))
            drawRoundRect(Color(0xFF212121), Offset(w * 0.22f, h * 0.46f), Size(w * 0.20f, h * 0.07f), CornerRadius(w * 0.03f))
            drawCircle(Color(0xFFFFF59D), w * 0.042f, Offset(w * 0.62f, h * 0.38f))
        }

        CollectableShape.BAGGER_TOURER -> {
            // Rear hard saddlebag
            drawRoundRect(spec.primary, Offset(w * 0.16f, h * 0.46f), Size(w * 0.22f, h * 0.16f), CornerRadius(w * 0.04f))
            // Batwing fairing & tall windshield
            val fairing = Path().apply {
                moveTo(w * 0.50f, h * 0.36f)
                lineTo(w * 0.72f, h * 0.40f)
                lineTo(w * 0.66f, h * 0.58f)
                lineTo(w * 0.42f, h * 0.54f)
                close()
            }
            drawPath(fairing, spec.primary)
            drawLine(Color(0xFF90CAF9).copy(alpha = 0.65f), Offset(w * 0.56f, h * 0.36f), Offset(w * 0.64f, h * 0.24f), w * 0.035f, StrokeCap.Round)
        }

        CollectableShape.ADVENTURE_ENDURO -> {
            drawLine(spec.secondary, Offset(w * 0.60f, h * 0.30f), Offset(frontX, wheelY), w * 0.038f, StrokeCap.Round)
            // High rally beak + tall tank
            val body = Path().apply {
                moveTo(w * 0.20f, h * 0.44f)
                lineTo(w * 0.48f, h * 0.34f)
                lineTo(w * 0.74f, h * 0.42f)
                lineTo(w * 0.56f, h * 0.50f)
                lineTo(w * 0.24f, h * 0.50f)
                close()
            }
            drawPath(body, spec.primary)
            drawLine(Color(0xFF81D4FA).copy(alpha = 0.65f), Offset(w * 0.56f, h * 0.36f), Offset(w * 0.64f, h * 0.24f), w * 0.03f, StrokeCap.Round)
        }

        CollectableShape.NAKED_STREETFIGHTER -> {
            drawLine(Color(0xFFE6B64B), Offset(w * 0.58f, h * 0.36f), Offset(frontX, wheelY), w * 0.04f, StrokeCap.Round)
            // Exposed trellis frame accent + aggressive tank
            drawLine(spec.secondary, Offset(w * 0.30f, h * 0.46f), Offset(w * 0.56f, h * 0.42f), w * 0.035f, StrokeCap.Round)
            val tank = Path().apply {
                moveTo(w * 0.20f, h * 0.40f)
                lineTo(w * 0.42f, h * 0.44f)
                lineTo(w * 0.58f, h * 0.35f)
                lineTo(w * 0.66f, h * 0.45f)
                lineTo(w * 0.34f, h * 0.50f)
                close()
            }
            drawPath(tank, spec.primary)
        }

        CollectableShape.SUPERSPORT_FAIRING,
        CollectableShape.MOTOGP_PROTOTYPE,
        CollectableShape.CUSTOM_TURBINE -> {
            drawLine(Color(0xFFD4AF37), Offset(w * 0.60f, h * 0.36f), Offset(frontX, wheelY), w * 0.038f, StrokeCap.Round)
            val fairing = Path().apply {
                moveTo(w * 0.16f, h * 0.34f)
                lineTo(w * 0.38f, h * 0.44f)
                lineTo(w * 0.58f, h * 0.35f)
                lineTo(w * 0.78f, h * 0.46f)
                lineTo(w * 0.62f, h * 0.60f)
                lineTo(w * 0.34f, h * 0.58f)
                close()
            }
            drawPath(fairing, spec.primary)
            // Aero accent fairing stripe & windscreen
            drawLine(spec.secondary, Offset(w * 0.42f, h * 0.48f), Offset(w * 0.70f, h * 0.48f), w * 0.038f, StrokeCap.Round)
            drawLine(Color(0xFF90CAF9).copy(alpha = 0.7f), Offset(w * 0.58f, h * 0.36f), Offset(w * 0.68f, h * 0.31f), w * 0.032f, StrokeCap.Round)
            if (spec.shape == CollectableShape.MOTOGP_PROTOTYPE) {
                // MotoGP front downforce winglet
                drawLine(spec.accent, Offset(w * 0.69f, h * 0.43f), Offset(w * 0.79f, h * 0.40f), w * 0.032f, StrokeCap.Round)
            }
            if (spec.shape == CollectableShape.CUSTOM_TURBINE) {
                drawCircle(spec.accent, w * 0.05f, Offset(w * 0.50f, h * 0.52f))
            }
        }

        else -> Unit
    }
}

private fun DrawScope.drawBikeWheel(cx: Float, cy: Float, r: Float, rimAccent: Color) {
    drawCircle(Color(0xFF16181C), r, Offset(cx, cy))
    drawCircle(rimAccent, r * 0.68f, Offset(cx, cy), style = Stroke(r * 0.14f))
    drawCircle(Color(0xFFCFD8DC), r * 0.40f, Offset(cx, cy), style = Stroke(r * 0.08f))
    drawCircle(Color(0xFFCFD8DC), r * 0.14f, Offset(cx, cy))
}
