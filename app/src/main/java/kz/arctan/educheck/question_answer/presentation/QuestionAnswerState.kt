package kz.arctan.educheck.question_answer.presentation

data class QuestionAnswerState(
    val isDropDownMenuShown: Boolean = false,
    val subjects: List<String> = listOf(
        "biology",
        "chemistry",
        "physics",
        "history",
        "math",
        "geography"
    ),
    val chosenSubject: String = "",
    val age: String = "",
    val question: String = "",
    val explanation: String = "",
    val isLoading: Boolean = false,
)
