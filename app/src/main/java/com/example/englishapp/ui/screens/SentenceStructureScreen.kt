package com.example.englishapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.data.LanguageMode
import com.example.englishapp.data.QuizQuestion
import com.example.englishapp.data.SentenceExample
import com.example.englishapp.data.SentenceRepository
import com.example.englishapp.data.SentenceType
import com.example.englishapp.data.SentenceTypeDetail
import com.example.englishapp.ui.QuizState
import com.example.englishapp.ui.SentenceTab
import com.example.englishapp.ui.components.LanguageToggle
import com.example.englishapp.ui.theme.AccentAmber
import com.example.englishapp.ui.theme.AccentAmberContainer
import com.example.englishapp.ui.theme.DeclarativeBg
import com.example.englishapp.ui.theme.DeclarativeColor
import com.example.englishapp.ui.theme.ErrorContainer
import com.example.englishapp.ui.theme.ErrorRed
import com.example.englishapp.ui.theme.ExclamatoryBg
import com.example.englishapp.ui.theme.ExclamatoryColor
import com.example.englishapp.ui.theme.ImperativeBg
import com.example.englishapp.ui.theme.ImperativeColor
import com.example.englishapp.ui.theme.InterrogativeBg
import com.example.englishapp.ui.theme.InterrogativeColor
import com.example.englishapp.ui.theme.OnAccentAmberContainer
import com.example.englishapp.ui.theme.OnErrorContainer
import com.example.englishapp.ui.theme.OnPrimaryContainer
import com.example.englishapp.ui.theme.OnSuccessContainer
import com.example.englishapp.ui.theme.PrimaryBlue
import com.example.englishapp.ui.theme.PrimaryBlueLight
import com.example.englishapp.ui.theme.PrimaryContainer
import com.example.englishapp.ui.theme.SuccessContainer
import com.example.englishapp.ui.theme.SuccessGreen
import com.example.englishapp.ui.theme.TextMuted
import com.example.englishapp.ui.theme.TextPrimary
import com.example.englishapp.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentenceStructureScreen(
    currentLanguageMode: LanguageMode,
    currentTab: SentenceTab,
    quizState: QuizState,
    onLanguageToggle: (LanguageMode) -> Unit,
    onTabSelected: (SentenceTab) -> Unit,
    onBack: () -> Unit,
    onOptionSelected: (SentenceType) -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isHinglish = currentLanguageMode == LanguageMode.HINGLISH

    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top App Bar
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Sentence Structure",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = if (isHinglish) "Part A: Seekhein • Part B: Practice" else "Part A: Learn • Part B: Practice",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back",
                            tint = TextPrimary
                        )
                    }
                },
                actions = {
                    LanguageToggle(
                        currentMode = currentLanguageMode,
                        onModeSelected = onLanguageToggle,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            // Two Part Tabs
            PrimaryTabRow(
                selectedTabIndex = if (currentTab == SentenceTab.LEARN) 0 else 1,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = PrimaryBlueLight,
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(if (currentTab == SentenceTab.LEARN) 0 else 1),
                        color = PrimaryBlueLight
                    )
                }
            ) {
                Tab(
                    selected = currentTab == SentenceTab.LEARN,
                    onClick = { onTabSelected(SentenceTab.LEARN) },
                    text = {
                        Text(
                            text = if (isHinglish) "Part A: Sentence & Types" else "Part A: Sentence & Types",
                            fontWeight = if (currentTab == SentenceTab.LEARN) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_learn")
                )
                Tab(
                    selected = currentTab == SentenceTab.PRACTICE,
                    onClick = { onTabSelected(SentenceTab.PRACTICE) },
                    text = {
                        Text(
                            text = if (isHinglish) "Part B: Practice Quiz" else "Part B: Practice Quiz",
                            fontWeight = if (currentTab == SentenceTab.PRACTICE) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_practice")
                )
            }

            // Content Body
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (currentTab == SentenceTab.LEARN) {
                    LearnSectionContent(
                        isHinglish = isHinglish,
                        onGoToPractice = { onTabSelected(SentenceTab.PRACTICE) }
                    )
                } else {
                    PracticeSectionContent(
                        quizState = quizState,
                        isHinglish = isHinglish,
                        onOptionSelected = onOptionSelected,
                        onNextQuestion = onNextQuestion,
                        onRestartQuiz = onRestartQuiz,
                        onReviewLesson = { onTabSelected(SentenceTab.LEARN) },
                        onBackToHome = onBack
                    )
                }
            }
        }
    }
}

// ==========================================
// PART A: LEARN SECTION
// ==========================================
@Composable
private fun LearnSectionContent(
    isHinglish: Boolean,
    onGoToPractice: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section Header Card: Definition of Sentence
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .testTag("sentence_definition_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(PrimaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Definition",
                            tint = PrimaryBlueLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isHinglish) "Sentence Kya Hota Hai?" else "What is a Sentence?",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = if (isHinglish) "सरल परिभाषा (Simple Definition)" else "Core Foundation",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isHinglish) SentenceRepository.sentenceDefinitionHinglish else SentenceRepository.sentenceDefinitionEn,
                    fontSize = 14.sp,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Key Rules Bullet points
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val points = if (isHinglish) SentenceRepository.sentenceRuleKeyPointsHinglish else SentenceRepository.sentenceRuleKeyPointsEn
                    points.forEach { point ->
                        Row(verticalAlignment = Alignment.Top) {
                            Text(
                                text = "✓",
                                color = PrimaryBlueLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = point,
                                fontSize = 12.sp,
                                color = TextPrimary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sentence Types Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isHinglish) "Sentence Ke 4 Types" else "4 Types of Sentences",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4 Sentence Types Cards
        SentenceRepository.sentenceTypes.forEachIndexed { index, typeDetail ->
            SentenceTypeCard(
                index = index + 1,
                detail = typeDetail,
                isHinglish = isHinglish
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bottom CTA to take practice quiz
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isHinglish) "Kya aapne 4 types samajh liye?" else "Ready to test what you learned?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (isHinglish) {
                        "Part B me aaiye aur 6 mazedaar sawalon ka jawab dekar apna score check karein!"
                    } else {
                        "Jump to Part B to identify sentence types with immediate feedback and scoring."
                    },
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onGoToPractice,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("start_practice_cta_btn"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlueLight
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isHinglish) "Part B: Practice Quiz Shuru Karein" else "Go to Part B: Practice Quiz",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Go to Practice",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SentenceTypeCard(
    index: Int,
    detail: SentenceTypeDetail,
    isHinglish: Boolean
) {
    val (primaryColor, bgColor) = when (detail.type) {
        SentenceType.DECLARATIVE -> Pair(DeclarativeColor, DeclarativeBg)
        SentenceType.INTERROGATIVE -> Pair(InterrogativeColor, InterrogativeBg)
        SentenceType.IMPERATIVE -> Pair(ImperativeColor, ImperativeBg)
        SentenceType.EXCLAMATORY -> Pair(ExclamatoryColor, ExclamatoryBg)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 600.dp)
            .testTag("sentence_type_card_${detail.type.name}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header row with Badge and Punctuation rule
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(primaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$index",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = detail.type.title,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = detail.type.hindiTitle,
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }

                // Punctuation chip
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(bgColor)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Ends with '${detail.type.punctuation}'",
                        color = primaryColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Explanation
            Text(
                text = if (isHinglish) detail.descriptionHinglish else detail.descriptionEn,
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Rule Pill
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rule: ",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
                Text(
                    text = if (isHinglish) detail.ruleHinglish else detail.ruleEn,
                    fontSize = 11.sp,
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Examples header
            Text(
                text = if (isHinglish) "Udaharan (Examples):" else "Examples:",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2 to 3 Examples
            detail.examples.forEachIndexed { exIndex, example ->
                ExampleItemRow(
                    index = exIndex + 1,
                    example = example,
                    accentColor = primaryColor
                )
                if (exIndex < detail.examples.size - 1) {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
private fun ExampleItemRow(
    index: Int,
    example: SentenceExample,
    accentColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$index. \"${example.english}\"",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "→ ${example.translation}",
                fontSize = 12.sp,
                color = TextMuted,
                fontStyle = FontStyle.Italic
            )
            if (example.note.isNotBlank()) {
                Text(
                    text = example.note,
                    fontSize = 10.sp,
                    color = accentColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// ==========================================
// PART B: PRACTICE SECTION
// ==========================================
@Composable
private fun PracticeSectionContent(
    quizState: QuizState,
    isHinglish: Boolean,
    onOptionSelected: (SentenceType) -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit,
    onReviewLesson: () -> Unit,
    onBackToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (quizState.isFinished) {
            // Quiz Complete Screen
            QuizCompletionView(
                quizState = quizState,
                isHinglish = isHinglish,
                onRestartQuiz = onRestartQuiz,
                onReviewLesson = onReviewLesson,
                onBackToHome = onBackToHome
            )
        } else {
            val question = quizState.currentQuestion
            if (question != null) {
                QuizQuestionView(
                    question = question,
                    quizState = quizState,
                    isHinglish = isHinglish,
                    onOptionSelected = onOptionSelected,
                    onNextQuestion = onNextQuestion
                )
            }
        }
    }
}

@Composable
private fun QuizQuestionView(
    question: QuizQuestion,
    quizState: QuizState,
    isHinglish: Boolean,
    onOptionSelected: (SentenceType) -> Unit,
    onNextQuestion: () -> Unit
) {
    val currentNum = quizState.currentIndex + 1
    val total = quizState.totalQuestions
    val progress = currentNum.toFloat() / total

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 540.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Question tracker & score
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isHinglish) "Sawal $currentNum of $total" else "Question $currentNum of $total",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMuted
            )
            Text(
                text = "Score: ${quizState.currentScore}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlueLight
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Progress line
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = PrimaryBlueLight,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Prompt Question Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("practice_question_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "What type of sentence is this?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )

                if (isHinglish) {
                    Text(
                        text = "(Ye kis type ka sentence hai?)",
                        fontSize = 12.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // The Sentence Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(PrimaryContainer.copy(alpha = 0.6f))
                        .border(
                            width = 1.dp,
                            color = PrimaryBlueLight.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(18.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "\"${question.sentence}\"",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryBlue,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Four Options
        SentenceType.entries.forEach { option ->
            QuizOptionButton(
                option = option,
                question = question,
                quizState = quizState,
                onSelect = { onOptionSelected(option) }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Immediate Feedback Explanation Card
        AnimatedVisibility(
            visible = quizState.hasAnsweredCurrent,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("feedback_explanation_card"),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (quizState.isCorrect) SuccessContainer else ErrorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = if (quizState.isCorrect) Icons.Default.CheckCircle else Icons.Default.Info,
                            contentDescription = "Result Feedback",
                            tint = if (quizState.isCorrect) SuccessGreen else ErrorRed,
                            modifier = Modifier
                                .size(22.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (quizState.isCorrect) {
                                    if (isHinglish) "Sahi Jawab! (Correct)" else "Correct Answer!"
                                } else {
                                    if (isHinglish) "Galat Jawab! (Correct: ${question.correctType.title})" else "Incorrect! (Correct: ${question.correctType.title})"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = if (quizState.isCorrect) OnSuccessContainer else OnErrorContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isHinglish) question.explanationHinglish else question.explanationEn,
                                fontSize = 13.sp,
                                color = if (quizState.isCorrect) OnSuccessContainer else OnErrorContainer,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 'Next Question' Button
                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("next_question_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlueLight
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (currentNum == total) {
                                if (isHinglish) "Result Dekhein" else "Finish & View Score"
                            } else {
                                "Next question"
                            },
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizOptionButton(
    option: SentenceType,
    question: QuizQuestion,
    quizState: QuizState,
    onSelect: () -> Unit
) {
    val isAnswered = quizState.hasAnsweredCurrent
    val isSelected = quizState.selectedOption == option
    val isThisOptionCorrect = option == question.correctType

    val containerColor by animateColorAsState(
        targetValue = when {
            !isAnswered -> MaterialTheme.colorScheme.surface
            isSelected && isThisOptionCorrect -> SuccessContainer
            isSelected && !isThisOptionCorrect -> ErrorContainer
            !isSelected && isThisOptionCorrect -> SuccessContainer.copy(alpha = 0.5f)
            else -> MaterialTheme.colorScheme.surface
        },
        label = "option_bg"
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            !isAnswered -> MaterialTheme.colorScheme.outline
            isSelected && isThisOptionCorrect -> SuccessGreen
            isSelected && !isThisOptionCorrect -> ErrorRed
            !isSelected && isThisOptionCorrect -> SuccessGreen
            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        },
        label = "option_border"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.5.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .clickable(enabled = !isAnswered, onClick = onSelect)
            .testTag("option_${option.name.lowercase()}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = option.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = option.hindiTitle,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }

            // Indicator Icon
            if (isAnswered) {
                if (isThisOptionCorrect) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Correct",
                        tint = SuccessGreen,
                        modifier = Modifier.size(20.dp)
                    )
                } else if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Incorrect",
                        tint = ErrorRed,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// ==========================================
// QUIZ COMPLETION SUMMARY
// ==========================================
@Composable
private fun QuizCompletionView(
    quizState: QuizState,
    isHinglish: Boolean,
    onRestartQuiz: () -> Unit,
    onReviewLesson: () -> Unit,
    onBackToHome: () -> Unit
) {
    val total = quizState.totalQuestions
    val score = quizState.currentScore
    val percentage = if (total > 0) ((score.toFloat() / total) * 100).toInt() else 0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 500.dp)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("quiz_result_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Trophy badge
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(AccentAmberContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = AccentAmber,
                        modifier = Modifier.size(42.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (percentage >= 80) {
                        if (isHinglish) "Shabash! Bohot Badhiya!" else "Outstanding Work!"
                    } else if (percentage >= 50) {
                        if (isHinglish) "Achha Prayas! (Good Effort)" else "Good Effort!"
                    } else {
                        if (isHinglish) "Koi Baat Nahi, Dubara Seekhein!" else "Keep Practicing!"
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isHinglish) {
                        "Aapne Sentence Structure practice poori kar li hai aur aapka score update ho gaya hai."
                    } else {
                        "You have completed the Sentence Structure practice. Your progress has been saved!"
                    },
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Score Display
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(PrimaryContainer)
                        .padding(horizontal = 24.dp, vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$score / $total",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue
                        )
                        Text(
                            text = "$percentage% Accuracy",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlueLight
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Actions: Retake Practice
                Button(
                    onClick = onRestartQuiz,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("retake_practice_btn"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlueLight
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Retake",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isHinglish) "Dubara Practice Karein" else "Practice Again",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Review Lesson (Part A)
                OutlinedButton(
                    onClick = onReviewLesson,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("review_lesson_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isHinglish) "Part A: Lesson Review Karein" else "Review Part A Lesson",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryBlueLight
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Back to Home
                OutlinedButton(
                    onClick = onBackToHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("back_to_home_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isHinglish) "Home Par Jayein" else "Back to Home",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}
