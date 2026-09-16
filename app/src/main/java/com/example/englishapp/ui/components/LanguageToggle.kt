package com.example.englishapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.data.LanguageMode
import com.example.englishapp.ui.theme.PrimaryBlueLight
import com.example.englishapp.ui.theme.TextMuted
import com.example.englishapp.ui.theme.TextPrimary

@Composable
fun LanguageToggle(
    currentMode: LanguageMode,
    onModeSelected: (LanguageMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag("language_toggle")
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(3.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LanguagePill(
                title = "English",
                isSelected = currentMode == LanguageMode.ENGLISH,
                onClick = { onModeSelected(LanguageMode.ENGLISH) },
                testTag = "lang_english_btn"
            )
            LanguagePill(
                title = "Hinglish",
                isSelected = currentMode == LanguageMode.HINGLISH,
                onClick = { onModeSelected(LanguageMode.HINGLISH) },
                testTag = "lang_hinglish_btn"
            )
        }
    }
}

@Composable
private fun LanguagePill(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryBlueLight else Color.Transparent,
        animationSpec = tween(durationMillis = 200),
        label = "pill_bg"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else TextSecondaryColor(),
        animationSpec = tween(durationMillis = 200),
        label = "pill_text"
    )

    Box(
        modifier = Modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Composable
private fun TextSecondaryColor(): Color = TextMuted
