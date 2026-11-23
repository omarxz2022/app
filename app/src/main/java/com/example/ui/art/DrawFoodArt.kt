package com.example.ui.art

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

internal fun DrawScope.drawFoodShape(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height
    val cx = w * 0.5f
    val cy = h * 0.54f

    // Subtle soft plate shadow
    drawOval(
        color = Color.Black.copy(alpha = 0.28f),
        topLeft = Offset(w * 0.14f, h * 0.76f),
        size = Size(w * 0.72f, h * 0.14f),
    )

    when (spec.shape) {
        CollectableShape.ESPRESSO_CUP -> {
            // Saucer
            drawOval(spec.accent, Offset(w * 0.18f, h * 0.64f), Size(w * 0.64f, h * 0.18f))
            drawOval(Color.Black.copy(alpha = 0.14f), Offset(w * 0.24f, h * 0.67f), Size(w * 0.52f, h * 0.11f))
            // Cup handle
            drawCircle(
                color = spec.accent,
                radius = w * 0.09f,
                center = Offset(w * 0.69f, h * 0.50f),
                style = Stroke(width = w * 0.045f),
            )
            // Cup body
            val cup = Path().apply {
                moveTo(w * 0.28f, h * 0.36f)
                lineTo(w * 0.68f, h * 0.36f)
                cubicTo(w * 0.66f, h * 0.66f, w * 0.58f, h * 0.71f, w * 0.48f, h * 0.71f)
                cubicTo(w * 0.38f, h * 0.71f, w * 0.30f, h * 0.66f, w * 0.28f, h * 0.36f)
                close()
            }
            drawPath(cup, spec.accent)
            drawPath(cup, spec.primary.copy(alpha = 0.22f))
            // Crema top
            drawOval(spec.primary, Offset(w * 0.30f, h * 0.32f), Size(w * 0.36f, h * 0.09f))
            drawOval(spec.secondary, Offset(w * 0.34f, h * 0.335f), Size(w * 0.28f, h * 0.06f))
        }

        CollectableShape.CROISSANT -> {
            drawPlate(w, h, Color(0xFFFAF6EE))
            val body = Path().apply {
                moveTo(w * 0.16f, h * 0.64f)
                cubicTo(w * 0.22f, h * 0.32f, w * 0.78f, h * 0.32f, w * 0.84f, h * 0.64f)
                cubicTo(w * 0.72f, h * 0.58f, w * 0.64f, h * 0.58f, cx, h * 0.60f)
                cubicTo(w * 0.36f, h * 0.58f, w * 0.28f, h * 0.58f, w * 0.16f, h * 0.64f)
                close()
            }
            drawPath(body, spec.primary)
            // Crescent segments
            listOf(0.32f, 0.44f, 0.56f, 0.68f).forEach { xFrac ->
                drawLine(
                    color = spec.secondary,
                    start = Offset(w * xFrac, h * 0.40f),
                    end = Offset(w * xFrac, h * 0.59f),
                    strokeWidth = w * 0.025f,
                    cap = StrokeCap.Round,
                )
            }
            drawOval(spec.accent.copy(alpha = 0.55f), Offset(w * 0.40f, h * 0.41f), Size(w * 0.20f, h * 0.06f))
        }

        CollectableShape.PIZZA_SLICE -> {
            drawPlate(w, h, Color(0xFFF3ECE1))
            val slice = Path().apply {
                moveTo(w * 0.24f, h * 0.24f)
                lineTo(w * 0.80f, h * 0.44f)
                lineTo(w * 0.34f, h * 0.78f)
                close()
            }
            drawPath(slice, spec.secondary)
            val sauce = Path().apply {
                moveTo(w * 0.28f, h * 0.29f)
                lineTo(w * 0.72f, h * 0.45f)
                lineTo(w * 0.36f, h * 0.72f)
                close()
            }
            drawPath(sauce, spec.primary)
            // Melted cheese & basil
            drawCircle(spec.secondary, w * 0.07f, Offset(w * 0.42f, h * 0.46f))
            drawCircle(spec.secondary, w * 0.055f, Offset(w * 0.56f, h * 0.48f))
            drawCircle(spec.accent, w * 0.042f, Offset(w * 0.44f, h * 0.56f))
            drawCircle(Color.White.copy(alpha = 0.85f), w * 0.038f, Offset(w * 0.38f, h * 0.40f))
            // Crust rim
            drawLine(
                color = Color(0xFFC97B35),
                start = Offset(w * 0.23f, h * 0.24f),
                end = Offset(w * 0.81f, h * 0.44f),
                strokeWidth = w * 0.075f,
                cap = StrokeCap.Round,
            )
        }

        CollectableShape.PRETZEL -> {
            drawPlate(w, h, Color(0xFFF4EFE6))
            val loop = Path().apply {
                moveTo(cx, h * 0.66f)
                cubicTo(w * 0.14f, h * 0.64f, w * 0.18f, h * 0.26f, cx, h * 0.36f)
                cubicTo(w * 0.82f, h * 0.26f, w * 0.86f, h * 0.64f, cx, h * 0.66f)
                close()
            }
            drawPath(loop, spec.primary, style = Stroke(width = w * 0.095f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            drawLine(spec.secondary, Offset(w * 0.36f, h * 0.36f), Offset(w * 0.64f, h * 0.60f), w * 0.055f, StrokeCap.Round)
            drawLine(spec.primary, Offset(w * 0.64f, h * 0.36f), Offset(w * 0.36f, h * 0.60f), w * 0.065f, StrokeCap.Round)
            // Salt crystals
            listOf(Offset(w * 0.30f, h * 0.42f), Offset(w * 0.70f, h * 0.44f), Offset(cx, h * 0.58f), Offset(w * 0.56f, h * 0.35f)).forEach {
                drawCircle(spec.accent, w * 0.018f, it)
            }
        }

        CollectableShape.RAMEN_BOWL -> {
            // Bowl
            val bowl = Path().apply {
                moveTo(w * 0.16f, h * 0.44f)
                lineTo(w * 0.84f, h * 0.44f)
                cubicTo(w * 0.82f, h * 0.76f, w * 0.68f, h * 0.78f, cx, h * 0.78f)
                cubicTo(w * 0.32f, h * 0.78f, w * 0.18f, h * 0.76f, w * 0.16f, h * 0.44f)
                close()
            }
            drawPath(bowl, spec.primary)
            // Broth
            drawOval(spec.secondary, Offset(w * 0.18f, h * 0.37f), Size(w * 0.64f, h * 0.16f))
            // Egg & chashu toppings
            drawOval(Color.White, Offset(w * 0.54f, h * 0.39f), Size(w * 0.14f, h * 0.10f))
            drawCircle(Color(0xFFF5A623), w * 0.032f, Offset(w * 0.61f, h * 0.44f))
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.35f, h * 0.44f))
            // Chopsticks
            drawLine(Color(0xFFE0B475), Offset(w * 0.24f, h * 0.22f), Offset(w * 0.78f, h * 0.35f), w * 0.024f, StrokeCap.Round)
            drawLine(Color(0xFFC99653), Offset(w * 0.28f, h * 0.18f), Offset(w * 0.82f, h * 0.31f), w * 0.024f, StrokeCap.Round)
        }

        CollectableShape.BURGER -> {
            // Bottom bun
            drawRoundRect(spec.primary, Offset(w * 0.20f, h * 0.64f), Size(w * 0.60f, h * 0.11f), CornerRadius(w * 0.06f))
            // Patty
            drawRoundRect(spec.secondary, Offset(w * 0.17f, h * 0.52f), Size(w * 0.66f, h * 0.12f), CornerRadius(w * 0.06f))
            // Melted cheese triangle
            val cheese = Path().apply {
                moveTo(w * 0.20f, h * 0.52f)
                lineTo(w * 0.80f, h * 0.52f)
                lineTo(w * 0.58f, h * 0.62f)
                lineTo(w * 0.36f, h * 0.56f)
                close()
            }
            drawPath(cheese, spec.accent)
            // Lettuce wave
            drawRoundRect(Color(0xFF4CAF50), Offset(w * 0.18f, h * 0.46f), Size(w * 0.64f, h * 0.06f), CornerRadius(w * 0.04f))
            // Top dome bun
            drawArc(spec.primary, 180f, 180f, true, Offset(w * 0.20f, h * 0.24f), Size(w * 0.60f, h * 0.44f))
            listOf(Offset(w * 0.38f, h * 0.32f), Offset(w * 0.50f, h * 0.29f), Offset(w * 0.62f, h * 0.34f)).forEach {
                drawCircle(Color(0xFFFFF5D6), w * 0.015f, it)
            }
        }

        CollectableShape.TACO -> {
            drawPlate(w, h, Color(0xFFF5EFE6))
            // Taco shell back & filling
            drawArc(spec.secondary, 180f, 180f, true, Offset(w * 0.20f, h * 0.30f), Size(w * 0.60f, h * 0.50f))
            drawCircle(spec.accent, w * 0.08f, Offset(w * 0.38f, h * 0.46f))
            drawCircle(spec.accent, w * 0.08f, Offset(w * 0.60f, h * 0.46f))
            drawArc(spec.primary, 180f, 180f, true, Offset(w * 0.18f, h * 0.38f), Size(w * 0.64f, h * 0.44f))
            // Lime wedge
            drawArc(Color(0xFF7CB342), 0f, 180f, true, Offset(w * 0.62f, h * 0.58f), Size(w * 0.20f, h * 0.18f))
        }

        CollectableShape.WRAP_PITA -> {
            drawPlate(w, h, Color(0xFFF4EFE4))
            val wrap = Path().apply {
                moveTo(w * 0.22f, h * 0.72f)
                lineTo(w * 0.56f, h * 0.24f)
                lineTo(w * 0.80f, h * 0.42f)
                lineTo(w * 0.42f, h * 0.78f)
                close()
            }
            drawPath(wrap, spec.primary)
            drawCircle(spec.secondary, w * 0.09f, Offset(w * 0.64f, h * 0.34f))
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.71f, h * 0.40f))
            drawLine(spec.secondary.copy(alpha = 0.45f), Offset(w * 0.34f, h * 0.60f), Offset(w * 0.58f, h * 0.42f), w * 0.03f, StrokeCap.Round)
        }

        CollectableShape.GELATO_CONE -> {
            // Waffle cone
            val cone = Path().apply {
                moveTo(w * 0.34f, h * 0.46f)
                lineTo(w * 0.66f, h * 0.46f)
                lineTo(cx, h * 0.84f)
                close()
            }
            drawPath(cone, spec.secondary)
            drawLine(Color(0xFF8C5A22), Offset(w * 0.40f, h * 0.54f), Offset(w * 0.58f, h * 0.54f), w * 0.02f)
            drawLine(Color(0xFF8C5A22), Offset(w * 0.44f, h * 0.64f), Offset(w * 0.55f, h * 0.64f), w * 0.02f)
            // Double scoop
            drawCircle(spec.primary, w * 0.16f, Offset(cx, h * 0.40f))
            drawCircle(spec.accent, w * 0.14f, Offset(w * 0.46f, h * 0.28f))
            // Wafer stick
            drawLine(Color(0xFFC68A3F), Offset(w * 0.58f, h * 0.26f), Offset(w * 0.70f, h * 0.14f), w * 0.04f, StrokeCap.Round)
        }

        CollectableShape.EGGS_BENEDICT -> {
            drawPlate(w, h, Color(0xFFFDFBF6))
            // Muffin & salmon base
            drawOval(Color(0xFFC48B45), Offset(w * 0.20f, h * 0.50f), Size(w * 0.28f, h * 0.20f))
            drawOval(Color(0xFFC48B45), Offset(w * 0.52f, h * 0.50f), Size(w * 0.28f, h * 0.20f))
            drawOval(spec.secondary, Offset(w * 0.22f, h * 0.46f), Size(w * 0.25f, h * 0.16f))
            drawOval(spec.secondary, Offset(w * 0.53f, h * 0.46f), Size(w * 0.25f, h * 0.16f))
            // Poached eggs + hollandaise
            drawOval(spec.accent, Offset(w * 0.23f, h * 0.38f), Size(w * 0.23f, h * 0.17f))
            drawOval(spec.accent, Offset(w * 0.54f, h * 0.38f), Size(w * 0.23f, h * 0.17f))
            drawOval(spec.primary, Offset(w * 0.25f, h * 0.38f), Size(w * 0.19f, h * 0.12f))
            drawOval(spec.primary, Offset(w * 0.56f, h * 0.38f), Size(w * 0.19f, h * 0.12f))
        }

        CollectableShape.CURRY_RICE -> {
            drawPlate(w, h, Color(0xFFF7F4EC))
            // Curry half
            drawArc(spec.primary, -90f, 180f, true, Offset(w * 0.22f, h * 0.28f), Size(w * 0.56f, h * 0.48f))
            // Rice half
            drawArc(spec.accent, 90f, 180f, true, Offset(w * 0.22f, h * 0.28f), Size(w * 0.56f, h * 0.48f))
            // Cutlet / naan strips
            drawRoundRect(spec.secondary, Offset(w * 0.36f, h * 0.40f), Size(w * 0.28f, h * 0.10f), CornerRadius(w * 0.04f))
            drawRoundRect(spec.secondary, Offset(w * 0.38f, h * 0.53f), Size(w * 0.24f, h * 0.09f), CornerRadius(w * 0.04f))
        }

        CollectableShape.FISH_CHIPS -> {
            drawPlate(w, h, Color(0xFFECEFF1))
            // Crispy fish fillet
            val fish = Path().apply {
                moveTo(w * 0.18f, h * 0.52f)
                cubicTo(w * 0.34f, h * 0.30f, w * 0.66f, h * 0.32f, w * 0.80f, h * 0.48f)
                cubicTo(w * 0.62f, h * 0.62f, w * 0.34f, h * 0.64f, w * 0.18f, h * 0.52f)
                close()
            }
            drawPath(fish, spec.primary)
            // Golden chips
            listOf(0.30f, 0.42f, 0.54f, 0.66f).forEach { xf ->
                drawRoundRect(spec.secondary, Offset(w * xf, h * 0.58f), Size(w * 0.07f, h * 0.16f), CornerRadius(w * 0.02f))
            }
            drawCircle(spec.accent, w * 0.07f, Offset(w * 0.72f, h * 0.62f))
        }

        CollectableShape.SANDWICH_SUB -> {
            drawPlate(w, h, Color(0xFFF6F2EA))
            // Diagonal sandwich layers
            drawRoundRect(spec.primary, Offset(w * 0.18f, h * 0.54f), Size(w * 0.64f, h * 0.14f), CornerRadius(w * 0.06f))
            drawRoundRect(spec.secondary, Offset(w * 0.20f, h * 0.46f), Size(w * 0.60f, h * 0.09f), CornerRadius(w * 0.04f))
            drawRoundRect(spec.accent, Offset(w * 0.22f, h * 0.41f), Size(w * 0.56f, h * 0.06f), CornerRadius(w * 0.03f))
            drawRoundRect(spec.primary, Offset(w * 0.18f, h * 0.30f), Size(w * 0.64f, h * 0.12f), CornerRadius(w * 0.06f))
        }

        CollectableShape.POKE_BOWL -> {
            drawOval(spec.primary, Offset(w * 0.16f, h * 0.26f), Size(w * 0.68f, h * 0.54f))
            drawOval(Color(0xFFF7F5EF), Offset(w * 0.22f, h * 0.31f), Size(w * 0.56f, h * 0.44f))
            // Sections: fish cubes, avocado, egg/rice
            drawRoundRect(spec.secondary, Offset(w * 0.28f, h * 0.38f), Size(w * 0.18f, h * 0.14f), CornerRadius(w * 0.04f))
            drawRoundRect(spec.accent, Offset(w * 0.52f, h * 0.38f), Size(w * 0.18f, h * 0.14f), CornerRadius(w * 0.04f))
            drawCircle(Color(0xFFF5A623), w * 0.07f, Offset(cx, h * 0.60f))
        }

        CollectableShape.PASTA_PLATE -> {
            drawPlate(w, h, Color(0xFFFAF8F4))
            // Swirled pasta mound
            drawOval(spec.primary, Offset(w * 0.24f, h * 0.34f), Size(w * 0.52f, h * 0.36f))
            drawArc(spec.secondary, 10f, 240f, false, Offset(w * 0.29f, h * 0.38f), Size(w * 0.42f, h * 0.26f), style = Stroke(w * 0.03f, cap = StrokeCap.Round))
            drawArc(spec.accent, 130f, 220f, false, Offset(w * 0.34f, h * 0.42f), Size(w * 0.32f, h * 0.19f), style = Stroke(w * 0.025f, cap = StrokeCap.Round))
            drawCircle(spec.accent, w * 0.045f, Offset(cx, h * 0.48f))
        }

        CollectableShape.DUMPLING_BASKET -> {
            // Bamboo steamer basket
            drawOval(Color(0xFFB58242), Offset(w * 0.16f, h * 0.28f), Size(w * 0.68f, h * 0.52f))
            drawOval(Color(0xFF3E2B19), Offset(w * 0.22f, h * 0.33f), Size(w * 0.56f, h * 0.42f))
            // Three pleated dumplings
            listOf(Offset(w * 0.38f, h * 0.46f), Offset(w * 0.62f, h * 0.46f), Offset(cx, h * 0.60f)).forEach { pt ->
                drawOval(spec.primary, Offset(pt.x - w * 0.11f, pt.y - h * 0.08f), Size(w * 0.22f, h * 0.16f))
                drawCircle(spec.accent, w * 0.025f, pt)
            }
        }

        CollectableShape.FRIED_CHICKEN -> {
            drawPlate(w, h, Color(0xFFF6F2EB))
            // Drumsticks / golden cutlets
            drawOval(spec.primary, Offset(w * 0.22f, h * 0.32f), Size(w * 0.36f, h * 0.24f))
            drawOval(spec.secondary, Offset(w * 0.42f, h * 0.44f), Size(w * 0.36f, h * 0.24f))
            drawLine(Color(0xFFF5EFE6), Offset(w * 0.22f, h * 0.48f), Offset(w * 0.15f, h * 0.58f), w * 0.05f, StrokeCap.Round)
            drawCircle(spec.accent, w * 0.05f, Offset(w * 0.68f, h * 0.36f))
        }

        CollectableShape.PAELLA_PAN -> {
            // Pan handles
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.13f, cy), style = Stroke(w * 0.03f))
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.87f, cy), style = Stroke(w * 0.03f))
            // Pan rim & saffron base
            drawCircle(Color(0xFF22252A), w * 0.34f, Offset(cx, cy))
            drawCircle(spec.primary, w * 0.29f, Offset(cx, cy))
            // Seafood pieces & mussels
            listOf(Offset(w * 0.38f, h * 0.46f), Offset(w * 0.62f, h * 0.48f), Offset(cx, h * 0.62f)).forEach {
                drawCircle(spec.secondary, w * 0.055f, it)
            }
            drawCircle(spec.accent, w * 0.04f, Offset(w * 0.46f, h * 0.56f))
        }

        CollectableShape.STEAK_CUT -> {
            drawPlate(w, h, Color(0xFFFAF8F5))
            val steak = Path().apply {
                moveTo(w * 0.22f, h * 0.44f)
                cubicTo(w * 0.26f, h * 0.28f, w * 0.72f, h * 0.28f, w * 0.78f, h * 0.46f)
                cubicTo(w * 0.80f, h * 0.64f, w * 0.58f, h * 0.72f, w * 0.36f, h * 0.68f)
                cubicTo(w * 0.20f, h * 0.66f, w * 0.18f, h * 0.54f, w * 0.22f, h * 0.44f)
                close()
            }
            drawPath(steak, spec.primary)
            // Grill sear lines / marbling
            drawLine(spec.secondary, Offset(w * 0.34f, h * 0.40f), Offset(w * 0.46f, h * 0.58f), w * 0.028f, StrokeCap.Round)
            drawLine(spec.secondary, Offset(w * 0.48f, h * 0.38f), Offset(w * 0.60f, h * 0.56f), w * 0.028f, StrokeCap.Round)
            drawCircle(spec.accent, w * 0.055f, Offset(w * 0.66f, h * 0.58f))
        }

        CollectableShape.LOBSTER_CRAB -> {
            drawPlate(w, h, Color(0xFFF3F6FA))
            // Body & claws
            drawOval(spec.primary, Offset(w * 0.34f, h * 0.36f), Size(w * 0.32f, h * 0.40f))
            drawCircle(spec.primary, w * 0.09f, Offset(w * 0.25f, h * 0.34f))
            drawCircle(spec.primary, w * 0.09f, Offset(w * 0.75f, h * 0.34f))
            drawLine(spec.primary, Offset(w * 0.36f, h * 0.44f), Offset(w * 0.26f, h * 0.36f), w * 0.04f)
            drawLine(spec.primary, Offset(w * 0.64f, h * 0.44f), Offset(w * 0.74f, h * 0.36f), w * 0.04f)
            // Drawn butter bowl
            drawCircle(spec.secondary, w * 0.08f, Offset(w * 0.72f, h * 0.64f))
        }

        CollectableShape.SUSHI_NIGIRI -> {
            // Wooden geta board
            drawRoundRect(Color(0xFF7D5333), Offset(w * 0.14f, h * 0.32f), Size(w * 0.72f, h * 0.42f), CornerRadius(w * 0.04f))
            // Two nigiri pieces
            listOf(0.22f, 0.52f).forEach { xf ->
                drawRoundRect(spec.secondary, Offset(w * xf, h * 0.44f), Size(w * 0.24f, h * 0.16f), CornerRadius(w * 0.05f))
                drawRoundRect(spec.primary, Offset(w * (xf - 0.01f), h * 0.38f), Size(w * 0.26f, h * 0.10f), CornerRadius(w * 0.05f))
                drawRect(Color(0xFF1F2B21), Offset(w * (xf + 0.09f), h * 0.38f), Size(w * 0.06f, h * 0.22f))
            }
            drawCircle(spec.accent, w * 0.05f, Offset(w * 0.78f, h * 0.62f))
        }

        CollectableShape.OYSTER_SHELL -> {
            val shell = Path().apply {
                moveTo(w * 0.24f, h * 0.64f)
                cubicTo(w * 0.18f, h * 0.32f, w * 0.52f, h * 0.22f, w * 0.78f, h * 0.38f)
                cubicTo(w * 0.84f, h * 0.60f, w * 0.52f, h * 0.78f, w * 0.24f, h * 0.64f)
                close()
            }
            drawPath(shell, spec.primary)
            drawOval(spec.secondary, Offset(w * 0.32f, h * 0.36f), Size(w * 0.36f, h * 0.26f))
            drawCircle(spec.accent, w * 0.065f, Offset(cx, h * 0.48f))
        }

        CollectableShape.DUCK_ROAST -> {
            drawPlate(w, h, Color(0xFFF6F2EC))
            drawOval(spec.primary, Offset(w * 0.20f, h * 0.34f), Size(w * 0.58f, h * 0.38f))
            drawOval(spec.secondary, Offset(w * 0.28f, h * 0.39f), Size(w * 0.40f, h * 0.22f))
            drawCircle(spec.accent, w * 0.06f, Offset(w * 0.70f, h * 0.58f))
        }

        CollectableShape.CASSEROLE_TAGINE -> {
            // Dish base
            drawOval(spec.primary, Offset(w * 0.18f, h * 0.52f), Size(w * 0.64f, h * 0.26f))
            // Conical dome / baked top
            val cone = Path().apply {
                moveTo(w * 0.22f, h * 0.58f)
                lineTo(cx, h * 0.22f)
                lineTo(w * 0.78f, h * 0.58f)
                close()
            }
            drawPath(cone, spec.secondary)
            drawCircle(spec.accent, w * 0.055f, Offset(cx, h * 0.22f))
        }

        CollectableShape.TIRAMISU_CAKE -> {
            drawPlate(w, h, Color(0xFFF6F2EB))
            // Layered slice
            drawRoundRect(spec.secondary, Offset(w * 0.24f, h * 0.38f), Size(w * 0.52f, h * 0.30f), CornerRadius(w * 0.03f))
            drawRect(spec.primary, Offset(w * 0.24f, h * 0.46f), Size(w * 0.52f, h * 0.06f))
            drawRect(spec.primary, Offset(w * 0.24f, h * 0.58f), Size(w * 0.52f, h * 0.06f))
            drawRoundRect(spec.accent, Offset(w * 0.24f, h * 0.35f), Size(w * 0.52f, h * 0.06f), CornerRadius(w * 0.03f))
        }

        CollectableShape.CAVIAR_TIN -> {
            // Luxury tin & pearls
            drawCircle(spec.secondary, w * 0.33f, Offset(cx, cy))
            drawCircle(spec.primary, w * 0.28f, Offset(cx, cy))
            drawCircle(spec.accent, w * 0.22f, Offset(cx, cy))
            listOf(
                Offset(cx - w * 0.08f, cy - h * 0.05f),
                Offset(cx + w * 0.07f, cy - h * 0.06f),
                Offset(cx, cy + h * 0.06f),
                Offset(cx - w * 0.05f, cy + h * 0.05f),
            ).forEach { drawCircle(Color(0xFF1A1C20), w * 0.032f, it) }
        }

        CollectableShape.CHEESE_TRUFFLE -> {
            drawPlate(w, h, Color(0xFFF5EFE6))
            val wedge = Path().apply {
                moveTo(w * 0.20f, h * 0.62f)
                lineTo(w * 0.78f, h * 0.56f)
                lineTo(w * 0.62f, h * 0.30f)
                close()
            }
            drawPath(wedge, spec.primary)
            drawCircle(spec.secondary, w * 0.11f, Offset(w * 0.34f, h * 0.56f))
            drawCircle(spec.accent, w * 0.04f, Offset(w * 0.56f, h * 0.48f))
        }

        CollectableShape.TASTING_PLATE -> {
            drawPlate(w, h, spec.primary)
            drawCircle(spec.secondary, w * 0.31f, Offset(cx, cy), style = Stroke(w * 0.025f))
            // Cloche dome or Michelin plating
            drawArc(spec.secondary, 180f, 180f, true, Offset(w * 0.28f, h * 0.30f), Size(w * 0.44f, h * 0.40f))
            drawCircle(spec.accent, w * 0.045f, Offset(cx, h * 0.28f))
        }

        else -> Unit
    }
}

private fun DrawScope.drawPlate(w: Float, h: Float, plateColor: Color) {
    drawCircle(plateColor, w * 0.36f, Offset(w * 0.5f, h * 0.54f))
    drawCircle(Color.Black.copy(alpha = 0.09f), w * 0.28f, Offset(w * 0.5f, h * 0.54f), style = Stroke(w * 0.015f))
}
