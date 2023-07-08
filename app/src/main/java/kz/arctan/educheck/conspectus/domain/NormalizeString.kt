package kz.arctan.educheck.conspectus.domain

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase

fun String.normalize(): String {
    return trim().toLowerCase(Locale.current)
}