package kz.arctan.educheck.conspectus.data

import kz.arctan.educheck.conspectus.domain.Questions
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ConspectusApi {
    @POST("/article/parse")
    suspend fun createConspectus(@Query("url") url: String): Response<ConspectusResponseDto>

    @POST("/qna/generate/extractive")
    suspend fun generateExtractiveQuestions(
        @Query("text") text: String,
        @Query("lang") lang: String = "eng",
    ): Response<Questions>

    @POST("/qna/generate/generative")
    suspend fun generateGenerativeQuestions(
        @Query("text") text: String,
        @Query("lang") lang: String = "eng",
    ): Response<Questions>

    @POST("sentence/compare")
    suspend fun compareAnswers(
        @Query("correct") correct: String,
        @Query("suggested") suggested: String,
    ): Response<CheckAnswersDto>
}