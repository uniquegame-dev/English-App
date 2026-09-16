package com.example.englishapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.data.LanguageMode
import com.example.englishapp.data.SentenceProgress
import com.example.englishapp.ui.components.LanguageToggle
import com.example.englishapp.ui.theme.AccentAmber
import com.example.englishapp.ui.theme.AccentAmberContainer
import com.example.englishapp.ui.theme.OnAccentAmberContainer
import com.example.englishapp.ui.theme.PrimaryBlue
import com.example.englishapp.ui.theme.PrimaryBlueLight
import com.example.englishapp.ui.theme.PrimaryContainer
import com.example.englishapp.ui.theme.SuccessContainer
import com.example.englishapp.ui.theme.SuccessGreen
import com.example.englishapp.ui.theme.TextMuted
import com.example.englishapp.ui.theme.TextPrimary
import com.example.englishapp.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    username: String,
    currentLanguageMode: LanguageMode,
    sentenceProgress: SentenceProgress,
    onLanguageToggle: (LanguageMode) -> Unit,
    onOpenSentenceStructure: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isHinglish = currentLanguageMode == LanguageMode.HINGLISH

    val animatedProgress by animateFloatAsState(
        targetValue = if (sentenceProgress.completed) {
            (sentenceProgress.score.toFloat() / sentenceProgress.total.coerceAtLeast(1)).coerceIn(0f, 1f)
        } else {
            0f
        },
        animationSpec = tween(durationMillis = 600),
        label = "progress_anim"
    )

    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top App Bar / Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Greeting text
                Column {
                    Text(
                        text = if (isHinglish) "Namaste," else "Welcome,",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted
                    )
                    Text(
                        text = if (username.isNotBlank()) "$username! 👋" else "Learner! 👋",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                // Language toggle switch on the top right
                LanguageToggle(
                    currentMode = currentLanguageMode,
                    onModeSelected = onLanguageToggle
                )
            }

            // Simple Progress Indicator / Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 500.dp)
                    .testTag("progress_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(if (sentenceProgress.completed) SuccessContainer else PrimaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (sentenceProgress.completed) Icons.Default.CheckCircle else Icons.Default.Stars,
                                    contentDescription = "Progress Status",
                                    tint = if (sentenceProgress.completed) SuccessGreen else PrimaryBlueLight,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (isHinglish) "Aapki Seekh Ki Progress" else "Your Learning Progress",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        }

                        Text(
                            text = if (sentenceProgress.completed) {
                                "${sentenceProgress.score} / ${sentenceProgress.total}"
                            } else {
                                "0 / 1"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (sentenceProgress.completed) SuccessGreen else PrimaryBlueLight
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Progress bar
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .testTag("overall_progress_bar"),
                        color = if (sentenceProgress.completed) SuccessGreen else PrimaryBlueLight,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (sentenceProgress.completed) {
                            if (isHinglish) {
                                "Badhai ho! Sentence Structure pura hua. Score: ${sentenceProgress.score}/${sentenceProgress.total} (${sentenceProgress.percentage}%)"
                            } else {
                                "Sentence Structure completed! Score: ${sentenceProgress.score}/${sentenceProgress.total} (${sentenceProgress.percentage}%)"
                            }
                        } else {
                            if (isHinglish) {
                                "Aaj ka target: 'Sentence Structure' padhein aur practice quiz solve karein."
                            } else {
                                "Start your first topic 'Sentence Structure' to build foundational English."
                            }
                        },
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Grammar Section Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 500.dp)
                    .padding(horizontal = 4.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isHinglish) "Grammar (व्याकरण)" else "Grammar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Only ONE card for now: 'Sentence Structure'
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 500.dp)
                    .testTag("sentence_structure_card")
                    .clickable(onClick = onOpenSentenceStructure),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    // Badge & status row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryContainer)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Topic 1 • Beginner",
                                color = PrimaryBlue,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (sentenceProgress.completed) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SuccessContainer)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Completed (${sentenceProgress.score}/${sentenceProgress.total})",
                                    color = SuccessGreen,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(AccentAmberContainer)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (isHinglish) "Start karein" else "Ready to start",
                                    color = OnAccentAmberContainer,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(PrimaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = "Sentence Structure Icon",
                                tint = PrimaryBlueLight,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Sentence Structure",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = if (isHinglish) "वाक्य और उसके 4 प्रकार" else "Sentences & 4 Core Types",
                                fontSize = 13.sp,
                                color = TextMuted
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isHinglish) {
                            "Janein sentence kya hota hai aur Declarative, Interrogative, Imperative, aur Exclamatory sentences ko simple examples ke saath seekhein."
                        } else {
                            "Understand what a sentence is, master the 4 core types with everyday examples, and test your skills with a quick quiz."
                        },
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Pills for Part A & Part B
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SubTopicPill(label = if (isHinglish) "Part A: Samjhein (Learn)" else "Part A: Learn")
                        SubTopicPill(label = if (isHinglish) "Part B: Practice Quiz" else "Part B: Practice")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action prompt
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (sentenceProgress.completed) {
                                if (isHinglish) "Dubara padhein ya quiz dein" else "Review & Practice again"
                            } else {
                                if (isHinglish) "Padhna shuru karein" else "Start learning"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlueLight
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Open lesson",
                            tint = PrimaryBlueLight,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun SubTopicPill(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextSecondary,
            fontWeight = FontWeight.Medium
        )
    }
}
