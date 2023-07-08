package kz.arctan.educheck.question_answer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kz.arctan.educheck.question_answer.data.QuestionAnswerApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object QuestionAnswerModule {
    @Provides
    @ViewModelScoped
    fun provideQuestionAnswerApi(): QuestionAnswerApi {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
        return Retrofit.Builder()
            .baseUrl("http://192.168.248.19:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }
}