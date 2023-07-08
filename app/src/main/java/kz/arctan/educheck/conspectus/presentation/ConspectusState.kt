package kz.arctan.educheck.conspectus.presentation

import kz.arctan.educheck.conspectus.domain.Questions

data class ConspectusState(
    val articleLink: String = "",
    val conspectus: String = "",
    val generativeQuestions: Questions = Questions(emptyList(), emptyList()),
    val extractiveQuestions: Questions = Questions(emptyList(), emptyList()),
    val userExtractiveAnswers: List<String> = emptyList(),
    val userGenerativeAnswers: List<String> = emptyList(),
    val extractiveTestResults: List<String> = emptyList(),
    val generativeTestResults: List<String> = emptyList(),
)
