package kz.arctan.educheck.question_answer.presentation

sealed interface QuestionAnswerIntent {
    @JvmInline
    value class ToggleDropDownMenuIntent(val onToggle: Boolean) : QuestionAnswerIntent

    @JvmInline
    value class SubjectClickIntent(val subject: String) : QuestionAnswerIntent

    @JvmInline
    value class AgeChangeIntent(val age: String) : QuestionAnswerIntent

    @JvmInline
    value class QuestionChangeIntent(val question: String) : QuestionAnswerIntent
    object SendQuestionIntent : QuestionAnswerIntent
}