package kz.arctan.educheck.question_answer.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface QuestionAnswerApi {
    @POST("/qna/ask")
    suspend fun askQuestion(
        @Query("question") question: String,
        @Query("subject") subject: String,
    ): Response<String>
}