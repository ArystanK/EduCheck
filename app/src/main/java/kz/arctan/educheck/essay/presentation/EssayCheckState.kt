package kz.arctan.educheck.essay.presentation

data class EssayCheckState(
    val essay: String = "",
    val result: String = "",
    val feedback: String = "",
    val isLoading: Boolean = false,
)
