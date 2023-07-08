package kz.arctan.educheck.essay.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

data class EssayCheckDto(val result: String)

interface EssayCheckApi {
    @POST("essay/check")
    suspend fun checkEssay(@Query("essay") essay: String): Response<EssayCheckDto>
}