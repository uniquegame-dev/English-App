package com.example.englishapp.data

import android.content.Context
import android.content.SharedPreferences

enum class LanguageMode(val displayName: String, val shortName: String) {
    ENGLISH("English", "EN"),
    HINGLISH("Hinglish", "हिं")
}

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("english_learning_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "user_name"
        private const val KEY_LANGUAGE = "language_mode"
        private const val KEY_SENTENCE_COMPLETED = "sentence_completed"
        private const val KEY_SENTENCE_SCORE = "sentence_score"
        private const val KEY_SENTENCE_TOTAL = "sentence_total"
    }

    fun getUsername(): String {
        return prefs.getString(KEY_USERNAME, "") ?: ""
    }

    fun saveUsername(name: String) {
        prefs.edit().putString(KEY_USERNAME, name.trim()).apply()
    }

    fun getLanguageMode(): LanguageMode {
        val raw = prefs.getString(KEY_LANGUAGE, LanguageMode.ENGLISH.name)
        return try {
            LanguageMode.valueOf(raw ?: LanguageMode.ENGLISH.name)
        } catch (_: Exception) {
            LanguageMode.ENGLISH
        }
    }

    fun saveLanguageMode(mode: LanguageMode) {
        prefs.edit().putString(KEY_LANGUAGE, mode.name).apply()
    }

    fun getSentenceProgress(): SentenceProgress {
        val completed = prefs.getBoolean(KEY_SENTENCE_COMPLETED, false)
        val score = prefs.getInt(KEY_SENTENCE_SCORE, 0)
        val total = prefs.getInt(KEY_SENTENCE_TOTAL, 0)
        return SentenceProgress(completed = completed, score = score, total = total)
    }

    fun saveSentenceProgress(score: Int, total: Int) {
        prefs.edit()
            .putBoolean(KEY_SENTENCE_COMPLETED, true)
            .putInt(KEY_SENTENCE_SCORE, score)
            .putInt(KEY_SENTENCE_TOTAL, total)
            .apply()
    }

    fun clearAll() {
        prefs.edit().clear().apply()
    }
}

data class SentenceProgress(
    val completed: Boolean = false,
    val score: Int = 0,
    val total: Int = 0
) {
    val percentage: Int
        get() = if (total > 0) ((score.toFloat() / total) * 100).toInt() else 0
}
