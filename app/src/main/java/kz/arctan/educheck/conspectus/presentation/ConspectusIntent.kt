package kz.arctan.educheck.conspectus.presentation

sealed interface ConspectusIntent {
    @JvmInline
    value class ArticleLinkChangeIntent(val articleLink: String) : ConspectusIntent
    object GenerateConspectusIntent : ConspectusIntent
    object StartTestIntent : ConspectusIntent
    object CheckAnswersIntent : ConspectusIntent

    data class UserGenerativeAnswerChange(val id: Int, val answer: String): ConspectusIntent
    data class UserExtractiveAnswerChange(val id: Int, val answer: String): ConspectusIntent
}