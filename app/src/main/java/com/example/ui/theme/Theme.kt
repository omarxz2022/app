package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class SemanticColors(
    val correct: Color,
    val onCorrect: Color,
    val correctContainer: Color,
    val onCorrectContainer: Color,
    val wrong: Color,
    val onWrong: Color,
    val wrongContainer: Color,
    val onWrongContainer: Color,
    val brass: Color,
    val brassContainer: Color,
    val onBrassContainer: Color,
)

private val LightSemanticColors = SemanticColors(
    correct = EmeraldCorrect,
    onCorrect = Color.White,
    correctContainer = EmeraldContainerLight,
    onCorrectContainer = Color(0xFF0A3D24),
    wrong = CrimsonWrong,
    onWrong = Color.White,
    wrongContainer = CrimsonContainerLight,
    onWrongContainer = Color(0xFF60120E),
    brass = BrassGold,
    brassContainer = BrassGoldContainer,
    onBrassContainer = Color(0xFF4D2B00),
)

private val DarkSemanticColors = SemanticColors(
    correct = MintCorrect,
    onCorrect = Color(0xFF052E18),
    correctContainer = EmeraldContainerDark,
    onCorrectContainer = Color(0xFFB4F1CE),
    wrong = CoralWrong,
    onWrong = Color(0xFF56100D),
    wrongContainer = CrimsonContainerDark,
    onWrongContainer = Color(0xFFFCDAD7),
    brass = LuminousBrass,
    brassContainer = LuminousBrassContainer,
    onBrassContainer = Color(0xFFFBE0AD),
)

val LocalSemanticColors = staticCompositionLocalOf { LightSemanticColors }

private val LightColorScheme = lightColorScheme(
    primary = OxfordNavy,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCE6F4),
    onPrimaryContainer = OxfordNavy,
    secondary = OxfordNavyLight,
    onSecondary = Color.White,
    secondaryContainer = ParchmentVariant,
    onSecondaryContainer = DeepInk,
    tertiary = BrassGold,
    onTertiary = Color.White,
    tertiaryContainer = BrassGoldContainer,
    onTertiaryContainer = Color(0xFF4D2B00),
    background = ParchmentBg,
    onBackground = DeepInk,
    surface = ParchmentSurface,
    onSurface = DeepInk,
    surfaceVariant = ParchmentVariant,
    onSurfaceVariant = SlateInk,
    outline = SubtleOutline,
    outlineVariant = Color(0xFFE9E2D4),
    error = CrimsonWrong,
    onError = Color.White,
    errorContainer = CrimsonContainerLight,
    onErrorContainer = Color(0xFF60120E),
)

private val DarkColorScheme = darkColorScheme(
    primary = NavyAccentDark,
    onPrimary = Color(0xFF0A1B30),
    primaryContainer = NavyContainerDark,
    onPrimaryContainer = Color(0xFFD8E7FC),
    secondary = Color(0xFFB6C4D8),
    onSecondary = Color(0xFF192434),
    secondaryContainer = MidnightSurfaceHigh,
    onSecondaryContainer = IvoryText,
    tertiary = LuminousBrass,
    onTertiary = Color(0xFF332000),
    tertiaryContainer = LuminousBrassContainer,
    onTertiaryContainer = Color(0xFFFBE0AD),
    background = MidnightBg,
    onBackground = IvoryText,
    surface = MidnightSurface,
    onSurface = IvoryText,
    surfaceVariant = MidnightSurfaceHigh,
    onSurfaceVariant = MutedIvory,
    outline = DarkOutline,
    outlineVariant = Color(0xFF253142),
    error = CoralWrong,
    onError = Color(0xFF56100D),
    errorContainer = CrimsonContainerDark,
    onErrorContainer = Color(0xFFFCDAD7),
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Composable
fun VerbCraftTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val semanticColors = if (darkTheme) DarkSemanticColors else LightSemanticColors

    CompositionLocalProvider(LocalSemanticColors provides semanticColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}
