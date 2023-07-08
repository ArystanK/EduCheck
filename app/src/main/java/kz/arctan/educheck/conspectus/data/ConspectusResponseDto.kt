package kz.arctan.educheck.conspectus.data

data class ConspectusResponseDto(
    val keywords: List<String>,
    val summary: String,
) {
    override fun toString(): String {
        val result = StringBuilder("Ключевые слова:\n")
        keywords.forEach { result.append("* $it\n") }
        result.append("\n")
        result.append(summary)
        return result.toString()
    }
}