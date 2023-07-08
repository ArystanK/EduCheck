package kz.arctan.educheck.conspectus.domain

data class Questions(
    val questions: List<String>,
    val answers: List<String>,
) : Iterable<Pair<String, String>> {
    val size = questions.size
    fun join(): List<Pair<String, String>> = questions.zip(answers)

    operator fun plus(questions: Questions): Questions =
        Questions(this.questions + questions.questions, answers + questions.answers)

    override fun iterator(): Iterator<Pair<String, String>> = questions.zip(answers).iterator()
}

fun List<Pair<String, String>>.toQuestions(): Questions =
    Questions(map { it.first }, map { it.second })