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

private val INK = Color(0xFF1F2329)

internal fun DrawScope.drawFoodShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height
    val cx = w * 0.5f
    val cy = h * 0.54f
    val line = (w * 0.014f).coerceAtLeast(1.1f)

    // Realistic studio contact shadow
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0x45000000), Color(0x18000000), Color.Transparent),
            center = Offset(cx, h * 0.78f),
            radius = w * 0.44f,
        ),
        topLeft = Offset(w * 0.08f, h * 0.67f),
        size = Size(w * 0.84f, h * 0.22f),
    )

    when (spec.shape) {
        CollectableShape.ESPRESSO_CUP -> {
            // Porcelain saucer
            drawOval(
                brush = Brush.verticalGradient(listOf(Color(0xFFFAF7F0), Color(0xFFDCD5C7))),
                topLeft = Offset(w * 0.16f, h * 0.62f),
                size = Size(w * 0.68f, h * 0.20f),
            )
            drawOval(INK, Offset(w * 0.16f, h * 0.62f), Size(w * 0.68f, h * 0.20f), style = Stroke(line))
            drawOval(Color(0x22000000), Offset(w * 0.27f, h * 0.66f), Size(w * 0.46f, h * 0.11f))

            // Porcelain cup handle
            drawCircle(spec.accent, w * 0.09f, Offset(w * 0.69f, h * 0.50f), style = Stroke(w * 0.048f))
            drawCircle(INK, w * 0.112f, Offset(w * 0.69f, h * 0.50f), style = Stroke(line))

            // Cup body with 3D porcelain shading
            val cup = Path().apply {
                moveTo(w * 0.27f, h * 0.36f)
                lineTo(w * 0.69f, h * 0.36f)
                cubicTo(w * 0.67f, h * 0.66f, w * 0.59f, h * 0.72f, w * 0.48f, h * 0.72f)
                cubicTo(w * 0.37f, h * 0.72f, w * 0.29f, h * 0.66f, w * 0.27f, h * 0.36f)
                close()
            }
            drawPath(cup, Brush.horizontalGradient(listOf(spec.accent.lighten(0.15f), spec.accent, spec.accent.darken(0.18f))))
            drawPath(cup, INK, style = Stroke(line, join = StrokeJoin.Round))

            // Espresso crema & latte swirl
            drawOval(spec.accent, Offset(w * 0.27f, h * 0.31f), Size(w * 0.42f, h * 0.11f))
            drawOval(INK, Offset(w * 0.27f, h * 0.31f), Size(w * 0.42f, h * 0.11f), style = Stroke(line))
            drawOval(
                brush = Brush.radialGradient(listOf(spec.secondary, spec.primary), Offset(w * 0.46f, h * 0.36f), w * 0.18f),
                topLeft = Offset(w * 0.30f, h * 0.325f),
                size = Size(w * 0.36f, h * 0.08f),
            )
            // Specular highlight & steam
            drawSteamWisps(w, h)
        }

        CollectableShape.CROISSANT -> {
            drawStudioPlate(w, h, Color(0xFFFAF6EE), line)
            val body = Path().apply {
                moveTo(w * 0.16f, h * 0.62f)
                cubicTo(w * 0.20f, h * 0.31f, w * 0.80f, h * 0.31f, w * 0.84f, h * 0.62f)
                cubicTo(w * 0.74f, h * 0.68f, w * 0.66f, h * 0.59f, cx, h * 0.60f)
                cubicTo(w * 0.34f, h * 0.59f, w * 0.26f, h * 0.68f, w * 0.16f, h * 0.62f)
                close()
            }
            drawPath(
                body,
                Brush.verticalGradient(listOf(spec.primary.lighten(0.24f), spec.primary, spec.secondary)),
            )
            drawPath(body, INK, style = Stroke(line, join = StrokeJoin.Round))
            // Laminated flaky pastry creases
            listOf(0.32f, 0.42f, 0.58f, 0.68f).forEach { xf ->
                val ridge = Path().apply {
                    moveTo(w * xf, h * 0.37f)
                    cubicTo(w * (xf + 0.03f), h * 0.46f, w * (xf - 0.01f), h * 0.54f, w * xf, h * 0.60f)
                }
                drawPath(ridge, spec.secondary, style = Stroke(line * 1.4f, cap = StrokeCap.Round))
            }
            drawOval(Color.White.copy(alpha = 0.28f), Offset(w * 0.38f, h * 0.39f), Size(w * 0.22f, h * 0.06f))
        }

        CollectableShape.PIZZA_SLICE -> {
            drawStudioPlate(w, h, Color(0xFFF6F1E6), line)
            val crust = Path().apply {
                moveTo(w * 0.20f, h * 0.28f)
                cubicTo(w * 0.44f, h * 0.22f, w * 0.68f, h * 0.29f, w * 0.82f, h * 0.43f)
                lineTo(w * 0.36f, h * 0.79f)
                close()
            }
            drawPath(crust, Brush.linearGradient(listOf(Color(0xFFD99A4E), Color(0xFFB36922))))
            drawPath(crust, INK, style = Stroke(line, join = StrokeJoin.Round))
            val sauce = Path().apply {
                moveTo(w * 0.28f, h * 0.34f)
                lineTo(w * 0.74f, h * 0.45f)
                lineTo(w * 0.37f, h * 0.74f)
                close()
            }
            drawPath(sauce, spec.primary)
            // Melted mozzarella puddles with specular gloss
            listOf(
                Offset(w * 0.42f, h * 0.45f) to w * 0.07f,
                Offset(w * 0.57f, h * 0.48f) to w * 0.06f,
                Offset(w * 0.43f, h * 0.59f) to w * 0.055f,
            ).forEach { (pt, r) ->
                drawCircle(spec.secondary, r, pt)
                drawCircle(Color.White.copy(alpha = 0.45f), r * 0.35f, Offset(pt.x - r * 0.25f, pt.y - r * 0.25f))
            }
            // Basil leaf & crust blister char
            drawOval(spec.accent, Offset(w * 0.47f, h * 0.49f), Size(w * 0.09f, h * 0.055f))
            drawCircle(Color(0xFF3E2212), w * 0.022f, Offset(w * 0.38f, h * 0.27f))
            drawCircle(Color(0xFF3E2212), w * 0.018f, Offset(w * 0.64f, h * 0.34f))
        }

        CollectableShape.PRETZEL -> {
            drawStudioPlate(w, h, Color(0xFFF6F1E6), line)
            val loop = Path().apply {
                moveTo(cx, h * 0.68f)
                cubicTo(w * 0.14f, h * 0.66f, w * 0.17f, h * 0.25f, cx, h * 0.36f)
                cubicTo(w * 0.83f, h * 0.25f, w * 0.86f, h * 0.66f, cx, h * 0.68f)
                close()
            }
            drawPath(loop, INK, style = Stroke(w * 0.12f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            drawPath(
                loop,
                Brush.verticalGradient(listOf(spec.primary.lighten(0.18f), spec.primary, spec.secondary)),
                style = Stroke(w * 0.092f, cap = StrokeCap.Round, join = StrokeJoin.Round),
            )
            drawLine(spec.primary.darken(0.15f), Offset(w * 0.37f, h * 0.36f), Offset(w * 0.63f, h * 0.61f), w * 0.07f, StrokeCap.Round)
            drawLine(spec.primary.lighten(0.12f), Offset(w * 0.63f, h * 0.36f), Offset(w * 0.37f, h * 0.61f), w * 0.07f, StrokeCap.Round)
            // Coarse pretzel salt crystals
            listOf(
                Offset(w * 0.28f, h * 0.44f),
                Offset(w * 0.72f, h * 0.45f),
                Offset(cx, h * 0.58f),
                Offset(w * 0.58f, h * 0.36f),
                Offset(w * 0.40f, h * 0.63f),
            ).forEach {
                drawRect(Color.White, Offset(it.x - w * 0.014f, it.y - w * 0.014f), Size(w * 0.028f, w * 0.028f))
            }
        }

        CollectableShape.RAMEN_BOWL -> {
            // Ceramic bowl silhouette
            val bowl = Path().apply {
                moveTo(w * 0.14f, h * 0.44f)
                lineTo(w * 0.86f, h * 0.44f)
                cubicTo(w * 0.84f, h * 0.76f, w * 0.69f, h * 0.80f, cx, h * 0.80f)
                cubicTo(w * 0.31f, h * 0.80f, w * 0.16f, h * 0.76f, w * 0.14f, h * 0.44f)
                close()
            }
            drawPath(bowl, Brush.horizontalGradient(listOf(spec.primary.lighten(0.16f), spec.primary, spec.primary.darken(0.28f))))
            drawPath(bowl, INK, style = Stroke(line, join = StrokeJoin.Round))

            // Glossy broth rim & broth radial
            drawOval(spec.primary.darken(0.18f), Offset(w * 0.14f, h * 0.34f), Size(w * 0.72f, h * 0.22f))
            drawOval(INK, Offset(w * 0.14f, h * 0.34f), Size(w * 0.72f, h * 0.22f), style = Stroke(line))
            drawOval(
                brush = Brush.radialGradient(listOf(spec.secondary.lighten(0.18f), spec.secondary), Offset(cx, h * 0.44f), w * 0.32f),
                topLeft = Offset(w * 0.17f, h * 0.36f),
                size = Size(w * 0.66f, h * 0.18f),
            )

            // Wavy noodle strands
            listOf(0.42f, 0.45f, 0.48f).forEach { yf ->
                drawArc(
                    color = Color(0xFFF6D988),
                    startAngle = 15f,
                    sweepAngle = 150f,
                    useCenter = false,
                    topLeft = Offset(w * 0.24f, h * yf),
                    size = Size(w * 0.50f, h * 0.06f),
                    style = Stroke(w * 0.018f, cap = StrokeCap.Round),
                )
            }
            // Nori seaweed sheets
            drawRect(Color(0xFF1F2B1E), Offset(w * 0.63f, h * 0.24f), Size(w * 0.14f, h * 0.14f))
            drawRect(INK, Offset(w * 0.63f, h * 0.24f), Size(w * 0.14f, h * 0.14f), style = Stroke(line * 0.8f))
            // Soft-boiled egg with amber yolk
            drawOval(Color(0xFFFDFBF5), Offset(w * 0.24f, h * 0.40f), Size(w * 0.16f, h * 0.10f))
            drawOval(INK, Offset(w * 0.24f, h * 0.40f), Size(w * 0.16f, h * 0.10f), style = Stroke(line * 0.75f))
            drawCircle(Color(0xFFF5821F), w * 0.036f, Offset(w * 0.32f, h * 0.45f))
            // Chashu meat slice + scallions
            drawOval(spec.accent, Offset(w * 0.48f, h * 0.42f), Size(w * 0.22f, h * 0.11f))
            drawOval(INK, Offset(w * 0.48f, h * 0.42f), Size(w * 0.22f, h * 0.11f), style = Stroke(line * 0.75f))
            drawCircle(Color(0xFF5CA646), w * 0.020f, Offset(w * 0.44f, h * 0.40f))
            drawCircle(Color(0xFF70BA57), w * 0.018f, Offset(w * 0.48f, h * 0.39f))
            drawSteamWisps(w, h)
        }

        CollectableShape.BURGER -> {
            // Bottom bun
            drawRoundRect(
                brush = Brush.verticalGradient(listOf(spec.primary, spec.primary.darken(0.22f))),
                topLeft = Offset(w * 0.19f, h * 0.63f),
                size = Size(w * 0.62f, h * 0.12f),
                cornerRadius = CornerRadius(w * 0.06f),
            )
            drawRoundRect(INK, Offset(w * 0.19f, h * 0.63f), Size(w * 0.62f, h * 0.12f), CornerRadius(w * 0.06f), style = Stroke(line))
            // Seared patty
            drawRoundRect(
                brush = Brush.verticalGradient(listOf(spec.secondary.lighten(0.10f), spec.secondary.darken(0.25f))),
                topLeft = Offset(w * 0.16f, h * 0.51f),
                size = Size(w * 0.68f, h * 0.13f),
                cornerRadius = CornerRadius(w * 0.06f),
            )
            drawRoundRect(INK, Offset(w * 0.16f, h * 0.51f), Size(w * 0.68f, h * 0.13f), CornerRadius(w * 0.06f), style = Stroke(line))
            // Draped glossy cheddar slice
            val cheese = Path().apply {
                moveTo(w * 0.19f, h * 0.51f)
                lineTo(w * 0.81f, h * 0.51f)
                lineTo(w * 0.60f, h * 0.63f)
                lineTo(w * 0.42f, h * 0.55f)
                lineTo(w * 0.28f, h * 0.59f)
                close()
            }
            drawPath(cheese, spec.accent)
            drawPath(cheese, INK, style = Stroke(line * 0.85f, join = StrokeJoin.Round))
            // Tomato + ruffled lettuce
            drawRoundRect(Color(0xFFD93829), Offset(w * 0.21f, h * 0.46f), Size(w * 0.58f, h * 0.055f), CornerRadius(w * 0.03f))
            drawRoundRect(Color(0xFF4CA64C), Offset(w * 0.17f, h * 0.43f), Size(w * 0.66f, h * 0.055f), CornerRadius(w * 0.04f))
            // Brioche dome top bun
            drawArc(
                brush = Brush.verticalGradient(listOf(spec.primary.lighten(0.28f), spec.primary, spec.primary.darken(0.15f))),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.19f, h * 0.23f),
                size = Size(w * 0.62f, h * 0.42f),
            )
            drawArc(INK, 180f, 180f, true, Offset(w * 0.19f, h * 0.23f), Size(w * 0.62f, h * 0.42f), style = Stroke(line))
            listOf(Offset(w * 0.36f, h * 0.31f), Offset(w * 0.50f, h * 0.28f), Offset(w * 0.63f, h * 0.33f), Offset(w * 0.44f, h * 0.35f)).forEach {
                drawOval(Color(0xFFFFF6DC), Offset(it.x, it.y), Size(w * 0.026f, w * 0.016f))
            }
        }

        CollectableShape.TACO -> {
            drawStudioPlate(w, h, Color(0xFFF6F1E6), line)
            drawArc(spec.secondary, 180f, 180f, true, Offset(w * 0.18f, h * 0.30f), Size(w * 0.64f, h * 0.48f))
            drawCircle(spec.accent, w * 0.075f, Offset(w * 0.38f, h * 0.44f))
            drawCircle(spec.accent, w * 0.075f, Offset(w * 0.58f, h * 0.44f))
            drawCircle(Color(0xFFD93829), w * 0.04f, Offset(cx, h * 0.42f))
            drawArc(
                brush = Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary, spec.primary.darken(0.18f))),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.16f, h * 0.39f),
                size = Size(w * 0.68f, h * 0.42f),
            )
            drawArc(INK, 180f, 180f, true, Offset(w * 0.16f, h * 0.39f), Size(w * 0.68f, h * 0.42f), style = Stroke(line))
            // Fresh lime wedge
            drawArc(Color(0xFF7CB342), 0f, 180f, true, Offset(w * 0.63f, h * 0.58f), Size(w * 0.20f, h * 0.18f))
            drawArc(INK, 0f, 180f, true, Offset(w * 0.63f, h * 0.58f), Size(w * 0.20f, h * 0.18f), style = Stroke(line * 0.8f))
        }

        CollectableShape.WRAP_PITA -> {
            drawStudioPlate(w, h, Color(0xFFF5EFE4), line)
            val wrap = Path().apply {
                moveTo(w * 0.20f, h * 0.70f)
                lineTo(w * 0.54f, h * 0.24f)
                lineTo(w * 0.80f, h * 0.42f)
                lineTo(w * 0.42f, h * 0.78f)
                close()
            }
            drawPath(wrap, Brush.linearGradient(listOf(spec.primary.lighten(0.16f), spec.primary, spec.primary.darken(0.2f))))
            drawPath(wrap, INK, style = Stroke(line, join = StrokeJoin.Round))
            drawCircle(spec.secondary, w * 0.085f, Offset(w * 0.64f, h * 0.33f))
            drawCircle(INK, w * 0.085f, Offset(w * 0.64f, h * 0.33f), style = Stroke(line * 0.8f))
            drawCircle(spec.accent, w * 0.055f, Offset(w * 0.72f, h * 0.40f))
            drawLine(spec.primary.darken(0.32f), Offset(w * 0.36f, h * 0.60f), Offset(w * 0.56f, h * 0.44f), w * 0.025f, StrokeCap.Round)
        }

        CollectableShape.GELATO_CONE -> {
            val cone = Path().apply {
                moveTo(w * 0.34f, h * 0.46f)
                lineTo(w * 0.66f, h * 0.46f)
                lineTo(cx, h * 0.85f)
                close()
            }
            drawPath(cone, Brush.horizontalGradient(listOf(spec.secondary.lighten(0.16f), spec.secondary, spec.secondary.darken(0.22f))))
            drawPath(cone, INK, style = Stroke(line, join = StrokeJoin.Round))
            drawLine(spec.secondary.darken(0.35f), Offset(w * 0.39f, h * 0.54f), Offset(w * 0.59f, h * 0.54f), line)
            drawLine(spec.secondary.darken(0.35f), Offset(w * 0.43f, h * 0.64f), Offset(w * 0.56f, h * 0.64f), line)
            // Scoops with pistachio/gelato gloss
            drawCircle(Brush.radialGradient(listOf(spec.primary.lighten(0.22f), spec.primary), Offset(w * 0.46f, h * 0.36f), w * 0.16f), w * 0.16f, Offset(cx, h * 0.40f))
            drawCircle(INK, w * 0.16f, Offset(cx, h * 0.40f), style = Stroke(line))
            drawCircle(Brush.radialGradient(listOf(spec.accent.lighten(0.2f), spec.accent), Offset(w * 0.44f, h * 0.25f), w * 0.14f), w * 0.14f, Offset(w * 0.47f, h * 0.28f))
            drawCircle(INK, w * 0.14f, Offset(w * 0.47f, h * 0.28f), style = Stroke(line))
        }

        CollectableShape.EGGS_BENEDICT -> {
            drawStudioPlate(w, h, Color(0xFFFAF8F3), line)
            listOf(0.20f, 0.52f).forEach { xf ->
                drawOval(Color(0xFFC48B45), Offset(w * xf, h * 0.50f), Size(w * 0.28f, h * 0.20f))
                drawOval(INK, Offset(w * xf, h * 0.50f), Size(w * 0.28f, h * 0.20f), style = Stroke(line * 0.8f))
                drawOval(spec.secondary, Offset(w * (xf + 0.02f), h * 0.46f), Size(w * 0.24f, h * 0.15f))
                drawOval(spec.accent, Offset(w * (xf + 0.03f), h * 0.38f), Size(w * 0.22f, h * 0.16f))
                drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.18f), spec.primary)), Offset(w * (xf + 0.04f), h * 0.38f), Size(w * 0.20f, h * 0.12f))
                drawOval(INK, Offset(w * (xf + 0.04f), h * 0.38f), Size(w * 0.20f, h * 0.12f), style = Stroke(line * 0.75f))
            }
        }

        CollectableShape.CURRY_RICE -> {
            drawStudioPlate(w, h, Color(0xFFF7F4EC), line)
            drawArc(Brush.radialGradient(listOf(spec.primary.lighten(0.15f), spec.primary), Offset(w * 0.62f, h * 0.50f), w * 0.30f), -90f, 180f, true, Offset(w * 0.21f, h * 0.28f), Size(w * 0.58f, h * 0.48f))
            drawArc(spec.accent, 90f, 180f, true, Offset(w * 0.21f, h * 0.28f), Size(w * 0.58f, h * 0.48f))
            drawOval(INK, Offset(w * 0.21f, h * 0.28f), Size(w * 0.58f, h * 0.48f), style = Stroke(line * 0.85f))
            drawRoundRect(spec.secondary, Offset(w * 0.36f, h * 0.39f), Size(w * 0.28f, h * 0.10f), CornerRadius(w * 0.04f))
            drawRoundRect(INK, Offset(w * 0.36f, h * 0.39f), Size(w * 0.28f, h * 0.10f), CornerRadius(w * 0.04f), style = Stroke(line * 0.8f))
            drawRoundRect(spec.secondary, Offset(w * 0.38f, h * 0.53f), Size(w * 0.24f, h * 0.09f), CornerRadius(w * 0.04f))
        }

        CollectableShape.FISH_CHIPS -> {
            drawStudioPlate(w, h, Color(0xFFF3F6F8), line)
            val fish = Path().apply {
                moveTo(w * 0.17f, h * 0.52f)
                cubicTo(w * 0.32f, h * 0.29f, w * 0.66f, h * 0.31f, w * 0.81f, h * 0.48f)
                cubicTo(w * 0.64f, h * 0.63f, w * 0.32f, h * 0.64f, w * 0.17f, h * 0.52f)
                close()
            }
            drawPath(fish, Brush.verticalGradient(listOf(spec.primary.lighten(0.22f), spec.primary, spec.primary.darken(0.2f))))
            drawPath(fish, INK, style = Stroke(line, join = StrokeJoin.Round))
            listOf(0.29f, 0.41f, 0.53f, 0.65f).forEach { xf ->
                drawRoundRect(spec.secondary, Offset(w * xf, h * 0.58f), Size(w * 0.07f, h * 0.15f), CornerRadius(w * 0.02f))
                drawRoundRect(INK, Offset(w * xf, h * 0.58f), Size(w * 0.07f, h * 0.15f), CornerRadius(w * 0.02f), style = Stroke(line * 0.7f))
            }
            drawCircle(spec.accent, w * 0.068f, Offset(w * 0.72f, h * 0.62f))
        }

        CollectableShape.SANDWICH_SUB -> {
            drawStudioPlate(w, h, Color(0xFFF6F2EA), line)
            drawRoundRect(spec.primary, Offset(w * 0.18f, h * 0.54f), Size(w * 0.64f, h * 0.14f), CornerRadius(w * 0.06f))
            drawRoundRect(INK, Offset(w * 0.18f, h * 0.54f), Size(w * 0.64f, h * 0.14f), CornerRadius(w * 0.06f), style = Stroke(line))
            drawRoundRect(spec.secondary, Offset(w * 0.20f, h * 0.46f), Size(w * 0.60f, h * 0.09f), CornerRadius(w * 0.04f))
            drawRoundRect(spec.accent, Offset(w * 0.22f, h * 0.41f), Size(w * 0.56f, h * 0.06f), CornerRadius(w * 0.03f))
            drawRoundRect(Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary)), Offset(w * 0.18f, h * 0.30f), Size(w * 0.64f, h * 0.12f), CornerRadius(w * 0.06f))
            drawRoundRect(INK, Offset(w * 0.18f, h * 0.30f), Size(w * 0.64f, h * 0.12f), CornerRadius(w * 0.06f), style = Stroke(line))
        }

        CollectableShape.POKE_BOWL -> {
            drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.18f), spec.primary)), Offset(w * 0.15f, h * 0.26f), Size(w * 0.70f, h * 0.54f))
            drawOval(INK, Offset(w * 0.15f, h * 0.26f), Size(w * 0.70f, h * 0.54f), style = Stroke(line))
            drawOval(Color(0xFFF8F6F0), Offset(w * 0.22f, h * 0.31f), Size(w * 0.56f, h * 0.44f))
            drawRoundRect(spec.secondary, Offset(w * 0.28f, h * 0.38f), Size(w * 0.18f, h * 0.14f), CornerRadius(w * 0.04f))
            drawRoundRect(spec.accent, Offset(w * 0.52f, h * 0.38f), Size(w * 0.18f, h * 0.14f), CornerRadius(w * 0.04f))
            drawCircle(Color(0xFFF5A623), w * 0.068f, Offset(cx, h * 0.60f))
            drawCircle(INK, w * 0.068f, Offset(cx, h * 0.60f), style = Stroke(line * 0.7f))
        }

        CollectableShape.PASTA_PLATE -> {
            drawStudioPlate(w, h, Color(0xFFFAF8F4), line)
            drawOval(Brush.radialGradient(listOf(spec.primary.lighten(0.18f), spec.primary), Offset(cx, cy), w * 0.28f), Offset(w * 0.23f, h * 0.34f), Size(w * 0.54f, h * 0.36f))
            drawOval(INK, Offset(w * 0.23f, h * 0.34f), Size(w * 0.54f, h * 0.36f), style = Stroke(line * 0.85f))
            drawArc(spec.secondary, 10f, 240f, false, Offset(w * 0.28f, h * 0.38f), Size(w * 0.44f, h * 0.26f), style = Stroke(w * 0.028f, cap = StrokeCap.Round))
            drawArc(spec.accent, 130f, 220f, false, Offset(w * 0.34f, h * 0.42f), Size(w * 0.32f, h * 0.18f), style = Stroke(w * 0.022f, cap = StrokeCap.Round))
            drawCircle(Color(0xFF4F8E47), w * 0.042f, Offset(cx, h * 0.48f))
        }

        CollectableShape.DUMPLING_BASKET -> {
            drawOval(Brush.verticalGradient(listOf(Color(0xFFC89552), Color(0xFF8C5E26))), Offset(w * 0.15f, h * 0.28f), Size(w * 0.70f, h * 0.52f))
            drawOval(INK, Offset(w * 0.15f, h * 0.28f), Size(w * 0.70f, h * 0.52f), style = Stroke(line))
            drawOval(Color(0xFF3A2817), Offset(w * 0.22f, h * 0.33f), Size(w * 0.56f, h * 0.42f))
            listOf(Offset(w * 0.38f, h * 0.46f), Offset(w * 0.62f, h * 0.46f), Offset(cx, h * 0.60f)).forEach { pt ->
                drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.18f), spec.primary)), Offset(pt.x - w * 0.11f, pt.y - h * 0.08f), Size(w * 0.22f, h * 0.16f))
                drawOval(INK, Offset(pt.x - w * 0.11f, pt.y - h * 0.08f), Size(w * 0.22f, h * 0.16f), style = Stroke(line * 0.8f))
                drawCircle(spec.accent, w * 0.025f, pt)
            }
            drawSteamWisps(w, h)
        }

        CollectableShape.FRIED_CHICKEN -> {
            drawStudioPlate(w, h, Color(0xFFF6F2EB), line)
            drawLine(Color(0xFFF5EFE6), Offset(w * 0.24f, h * 0.46f), Offset(w * 0.14f, h * 0.58f), w * 0.055f, StrokeCap.Round)
            drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary)), Offset(w * 0.22f, h * 0.32f), Size(w * 0.36f, h * 0.24f))
            drawOval(INK, Offset(w * 0.22f, h * 0.32f), Size(w * 0.36f, h * 0.24f), style = Stroke(line))
            drawOval(Brush.verticalGradient(listOf(spec.secondary.lighten(0.2f), spec.secondary)), Offset(w * 0.42f, h * 0.44f), Size(w * 0.36f, h * 0.24f))
            drawOval(INK, Offset(w * 0.42f, h * 0.44f), Size(w * 0.36f, h * 0.24f), style = Stroke(line))
            drawCircle(spec.accent, w * 0.048f, Offset(w * 0.68f, h * 0.36f))
        }

        CollectableShape.PAELLA_PAN -> {
            drawCircle(Color(0xFF2B2E34), w * 0.06f, Offset(w * 0.13f, cy), style = Stroke(w * 0.032f))
            drawCircle(Color(0xFF2B2E34), w * 0.06f, Offset(w * 0.87f, cy), style = Stroke(w * 0.032f))
            drawCircle(Color(0xFF202328), w * 0.34f, Offset(cx, cy))
            drawCircle(INK, w * 0.34f, Offset(cx, cy), style = Stroke(line))
            drawCircle(Brush.radialGradient(listOf(spec.primary.lighten(0.18f), spec.primary), Offset(cx, cy), w * 0.29f), w * 0.29f, Offset(cx, cy))
            listOf(Offset(w * 0.38f, h * 0.46f), Offset(w * 0.62f, h * 0.48f), Offset(cx, h * 0.62f)).forEach {
                drawCircle(spec.secondary, w * 0.055f, it)
                drawCircle(INK, w * 0.055f, it, style = Stroke(line * 0.7f))
            }
            drawCircle(spec.accent, w * 0.04f, Offset(w * 0.46f, h * 0.56f))
        }

        CollectableShape.STEAK_CUT -> {
            drawStudioPlate(w, h, Color(0xFFFAF8F5), line)
            val steak = Path().apply {
                moveTo(w * 0.22f, h * 0.44f)
                cubicTo(w * 0.26f, h * 0.28f, w * 0.72f, h * 0.28f, w * 0.78f, h * 0.46f)
                cubicTo(w * 0.80f, h * 0.64f, w * 0.58f, h * 0.72f, w * 0.36f, h * 0.68f)
                cubicTo(w * 0.20f, h * 0.66f, w * 0.18f, h * 0.54f, w * 0.22f, h * 0.44f)
                close()
            }
            drawPath(steak, Brush.verticalGradient(listOf(spec.primary.lighten(0.16f), spec.primary, spec.primary.darken(0.28f))))
            drawPath(steak, INK, style = Stroke(line, join = StrokeJoin.Round))
            // Intramuscular Wagyu marbling & grill marks
            drawLine(spec.secondary.copy(alpha = 0.85f), Offset(w * 0.33f, h * 0.40f), Offset(w * 0.45f, h * 0.58f), w * 0.022f, StrokeCap.Round)
            drawLine(spec.secondary.copy(alpha = 0.85f), Offset(w * 0.47f, h * 0.38f), Offset(w * 0.59f, h * 0.56f), w * 0.022f, StrokeCap.Round)
            drawLine(INK.copy(alpha = 0.45f), Offset(w * 0.36f, h * 0.56f), Offset(w * 0.60f, h * 0.42f), line)
            // Compound butter pat & herb
            drawCircle(Color(0xFFF6D45C), w * 0.055f, Offset(w * 0.56f, h * 0.50f))
            drawCircle(spec.accent, w * 0.042f, Offset(w * 0.68f, h * 0.58f))
        }

        CollectableShape.LOBSTER_CRAB -> {
            drawStudioPlate(w, h, Color(0xFFF3F6FA), line)
            drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.22f), spec.primary)), Offset(w * 0.34f, h * 0.36f), Size(w * 0.32f, h * 0.40f))
            drawOval(INK, Offset(w * 0.34f, h * 0.36f), Size(w * 0.32f, h * 0.40f), style = Stroke(line))
            listOf(w * 0.25f, w * 0.75f).forEach { xPos ->
                drawCircle(spec.primary, w * 0.088f, Offset(xPos, h * 0.34f))
                drawCircle(INK, w * 0.088f, Offset(xPos, h * 0.34f), style = Stroke(line))
            }
            drawLine(spec.primary, Offset(w * 0.36f, h * 0.44f), Offset(w * 0.26f, h * 0.36f), w * 0.038f)
            drawLine(spec.primary, Offset(w * 0.64f, h * 0.44f), Offset(w * 0.74f, h * 0.36f), w * 0.038f)
            drawCircle(spec.secondary, w * 0.08f, Offset(w * 0.72f, h * 0.64f))
            drawCircle(INK, w * 0.08f, Offset(w * 0.72f, h * 0.64f), style = Stroke(line * 0.8f))
        }

        CollectableShape.SUSHI_NIGIRI -> {
            drawRoundRect(Brush.verticalGradient(listOf(Color(0xFF9C6940), Color(0xFF6B4423))), Offset(w * 0.13f, h * 0.32f), Size(w * 0.74f, h * 0.42f), CornerRadius(w * 0.04f))
            drawRoundRect(INK, Offset(w * 0.13f, h * 0.32f), Size(w * 0.74f, h * 0.42f), CornerRadius(w * 0.04f), style = Stroke(line))
            listOf(0.21f, 0.51f).forEach { xf ->
                drawRoundRect(spec.secondary, Offset(w * xf, h * 0.44f), Size(w * 0.24f, h * 0.16f), CornerRadius(w * 0.05f))
                drawRoundRect(INK, Offset(w * xf, h * 0.44f), Size(w * 0.24f, h * 0.16f), CornerRadius(w * 0.05f), style = Stroke(line * 0.8f))
                drawRoundRect(Brush.verticalGradient(listOf(spec.primary.lighten(0.22f), spec.primary)), Offset(w * (xf - 0.01f), h * 0.38f), Size(w * 0.26f, h * 0.11f), CornerRadius(w * 0.05f))
                drawRoundRect(INK, Offset(w * (xf - 0.01f), h * 0.38f), Size(w * 0.26f, h * 0.11f), CornerRadius(w * 0.05f), style = Stroke(line * 0.8f))
                drawRect(Color(0xFF1E2920), Offset(w * (xf + 0.09f), h * 0.38f), Size(w * 0.06f, h * 0.22f))
            }
            drawCircle(Color(0xFF7CB342), w * 0.045f, Offset(w * 0.79f, h * 0.62f))
        }

        CollectableShape.OYSTER_SHELL -> {
            val shell = Path().apply {
                moveTo(w * 0.24f, h * 0.64f)
                cubicTo(w * 0.18f, h * 0.32f, w * 0.52f, h * 0.22f, w * 0.78f, h * 0.38f)
                cubicTo(w * 0.84f, h * 0.60f, w * 0.52f, h * 0.78f, w * 0.24f, h * 0.64f)
                close()
            }
            drawPath(shell, Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary, spec.primary.darken(0.22f))))
            drawPath(shell, INK, style = Stroke(line, join = StrokeJoin.Round))
            drawOval(spec.secondary, Offset(w * 0.32f, h * 0.36f), Size(w * 0.36f, h * 0.26f))
            drawCircle(spec.accent, w * 0.065f, Offset(cx, h * 0.48f))
            drawCircle(Color.White.copy(alpha = 0.55f), w * 0.02f, Offset(cx - w * 0.02f, h * 0.46f))
        }

        CollectableShape.DUCK_ROAST -> {
            drawStudioPlate(w, h, Color(0xFFF6F2EC), line)
            drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary)), Offset(w * 0.20f, h * 0.34f), Size(w * 0.58f, h * 0.38f))
            drawOval(INK, Offset(w * 0.20f, h * 0.34f), Size(w * 0.58f, h * 0.38f), style = Stroke(line))
            drawOval(spec.secondary, Offset(w * 0.28f, h * 0.39f), Size(w * 0.40f, h * 0.22f))
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.70f, h * 0.58f))
        }

        CollectableShape.CASSEROLE_TAGINE -> {
            drawOval(Brush.verticalGradient(listOf(spec.primary.lighten(0.18f), spec.primary)), Offset(w * 0.17f, h * 0.52f), Size(w * 0.66f, h * 0.26f))
            drawOval(INK, Offset(w * 0.17f, h * 0.52f), Size(w * 0.66f, h * 0.26f), style = Stroke(line))
            val cone = Path().apply {
                moveTo(w * 0.22f, h * 0.58f)
                lineTo(cx, h * 0.22f)
                lineTo(w * 0.78f, h * 0.58f)
                close()
            }
            drawPath(cone, Brush.horizontalGradient(listOf(spec.secondary.lighten(0.18f), spec.secondary, spec.secondary.darken(0.24f))))
            drawPath(cone, INK, style = Stroke(line, join = StrokeJoin.Round))
            drawCircle(spec.accent, w * 0.055f, Offset(cx, h * 0.22f))
        }

        CollectableShape.TIRAMISU_CAKE -> {
            drawStudioPlate(w, h, Color(0xFFF6F2EB), line)
            drawRoundRect(spec.secondary, Offset(w * 0.24f, h * 0.38f), Size(w * 0.52f, h * 0.30f), CornerRadius(w * 0.03f))
            drawRect(spec.primary, Offset(w * 0.24f, h * 0.46f), Size(w * 0.52f, h * 0.06f))
            drawRect(spec.primary, Offset(w * 0.24f, h * 0.58f), Size(w * 0.52f, h * 0.06f))
            drawRoundRect(spec.accent, Offset(w * 0.24f, h * 0.35f), Size(w * 0.52f, h * 0.06f), CornerRadius(w * 0.03f))
            drawRoundRect(INK, Offset(w * 0.24f, h * 0.35f), Size(w * 0.52f, h * 0.33f), CornerRadius(w * 0.03f), style = Stroke(line))
        }

        CollectableShape.CAVIAR_TIN -> {
            drawCircle(Brush.radialGradient(listOf(spec.secondary.lighten(0.25f), spec.secondary), Offset(cx, cy), w * 0.34f), w * 0.33f, Offset(cx, cy))
            drawCircle(INK, w * 0.33f, Offset(cx, cy), style = Stroke(line))
            drawCircle(spec.primary, w * 0.27f, Offset(cx, cy))
            drawCircle(spec.accent, w * 0.22f, Offset(cx, cy))
            listOf(
                Offset(cx - w * 0.08f, cy - h * 0.05f),
                Offset(cx + w * 0.07f, cy - h * 0.06f),
                Offset(cx, cy + h * 0.06f),
                Offset(cx - w * 0.05f, cy + h * 0.05f),
                Offset(cx + w * 0.06f, cy + h * 0.04f),
            ).forEach {
                drawCircle(Color(0xFF15181C), w * 0.036f, it)
                drawCircle(Color.White.copy(alpha = 0.7f), w * 0.011f, Offset(it.x - w * 0.012f, it.y - w * 0.012f))
            }
        }

        CollectableShape.CHEESE_TRUFFLE -> {
            drawStudioPlate(w, h, Color(0xFFF5EFE6), line)
            val wedge = Path().apply {
                moveTo(w * 0.20f, h * 0.62f)
                lineTo(w * 0.78f, h * 0.56f)
                lineTo(w * 0.62f, h * 0.30f)
                close()
            }
            drawPath(wedge, Brush.verticalGradient(listOf(spec.primary.lighten(0.2f), spec.primary)))
            drawPath(wedge, INK, style = Stroke(line, join = StrokeJoin.Round))
            drawCircle(spec.secondary, w * 0.11f, Offset(w * 0.34f, h * 0.56f))
            drawCircle(INK, w * 0.11f, Offset(w * 0.34f, h * 0.56f), style = Stroke(line * 0.8f))
            drawCircle(spec.accent, w * 0.038f, Offset(w * 0.56f, h * 0.48f))
        }

        CollectableShape.TASTING_PLATE -> {
            drawStudioPlate(w, h, spec.primary, line)
            drawCircle(spec.secondary, w * 0.31f, Offset(cx, cy), style = Stroke(w * 0.022f))
            drawArc(
                brush = Brush.horizontalGradient(listOf(Color(0xFFF5F7FA), Color(0xFFC2C9D2), Color(0xFF8C96A4))),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.27f, h * 0.30f),
                size = Size(w * 0.46f, h * 0.40f),
            )
            drawArc(INK, 180f, 180f, true, Offset(w * 0.27f, h * 0.30f), Size(w * 0.46f, h * 0.40f), style = Stroke(line))
            drawCircle(spec.accent, w * 0.045f, Offset(cx, h * 0.28f))
        }

        else -> Unit
    }
}

private fun DrawScope.drawStudioPlate(w: Float, h: Float, plateColor: Color, line: Float) {
    val cx = w * 0.5f
    val cy = h * 0.54f
    drawCircle(
        brush = Brush.radialGradient(listOf(plateColor, plateColor.darken(0.12f)), Offset(cx, cy), w * 0.38f),
        radius = w * 0.37f,
        center = Offset(cx, cy),
    )
    drawCircle(INK, w * 0.37f, Offset(cx, cy), style = Stroke(line))
    drawCircle(Color.Black.copy(alpha = 0.08f), w * 0.29f, Offset(cx, cy), style = Stroke(line * 0.8f))
}

private fun DrawScope.drawSteamWisps(w: Float, h: Float) {
    listOf(0.42f, 0.54f).forEach { xf ->
        val wisp = Path().apply {
            moveTo(w * xf, h * 0.28f)
            cubicTo(w * (xf - 0.04f), h * 0.22f, w * (xf + 0.05f), h * 0.17f, w * xf, h * 0.11f)
        }
        drawPath(wisp, Color(0x558B949E), style = Stroke(w * 0.022f, cap = StrokeCap.Round))
    }
}
