package kz.arctan.educheck.essay.presentation

sealed interface EssayCheckIntent {
    @JvmInline
    value class EssayChangeIntent(val essay: String) : EssayCheckIntent
    object TakePictureIntent : EssayCheckIntent
    object CheckIntent : EssayCheckIntent
}