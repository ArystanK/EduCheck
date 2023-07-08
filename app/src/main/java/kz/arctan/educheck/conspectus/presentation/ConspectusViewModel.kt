package kz.arctan.educheck.conspectus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.arctan.educheck.conspectus.data.ConspectusApi
import kz.arctan.educheck.conspectus.domain.normalize
import kz.arctan.educheck.conspectus.domain.toQuestions
import javax.inject.Inject

@HiltViewModel
class ConspectusViewModel @Inject constructor(
    private val conspectusApi: ConspectusApi,
) : ViewModel() {
    private val _state = MutableStateFlow(ConspectusState())
    val state = _state.asStateFlow()

    fun reduce(intent: ConspectusIntent) {
        when (intent) {
            is ConspectusIntent.ArticleLinkChangeIntent -> _state.update { it.copy(articleLink = intent.articleLink) }
            ConspectusIntent.GenerateConspectusIntent -> viewModelScope.launch {
                val conspectus =
                    conspectusApi.createConspectus(_state.value.articleLink).body() ?: return@launch
                _state.update { it.copy(conspectus = conspectus.toString()) }
            }

            ConspectusIntent.StartTestIntent -> viewModelScope.launch {
                launch inner@{
                    val generativeQuestion = conspectusApi
                        .generateGenerativeQuestions(_state.value.conspectus).body()
                        ?: return@inner
                    println("generative come ${generativeQuestion.size}")
                    _state.update {
                        it.copy(
                            generativeQuestions = generativeQuestion,
                            userGenerativeAnswers = List(generativeQuestion.size) { "" }
                        )
                    }
                }
                launch inner@{
                    val extractive =
                        conspectusApi.generateExtractiveQuestions(_state.value.conspectus).body()
                            ?.join()?.let { if (it.size > 5) it.take(5) else it }?.toQuestions()
                            ?: return@inner
                    println("extractive come ${extractive.size}")
                    _state.update {
                        it.copy(
                            extractiveQuestions = extractive,
                            userExtractiveAnswers = List(extractive.size) { "" })
                    }
                }
            }

            ConspectusIntent.CheckAnswersIntent -> viewModelScope.launch {
                launch {
                    val results =
                        _state.value.userExtractiveAnswers.zip(_state.value.extractiveQuestions.answers)
                            .map { if (it.first.normalize() == it.second.normalize()) "100" else "0" }
                    println(results.size)
                    _state.update { it.copy(extractiveTestResults = results) }
                }
                launch {
                    val results =
                        _state.value.userGenerativeAnswers.zip(_state.value.generativeQuestions.answers)
                            .asFlow()
                            .buffer()
                            .map {
                                val result =
                                    conspectusApi.compareAnswers(it.second, it.first).body()?.detail
                                        ?: ""
                                if ("completely incorrect" in result) "0"
                                else if ("somewhat correct" in result) "50"
                                else if ("mostly correct" in result) "80"
                                else "100"
                            }.toList()
                    println(results.size)
                    _state.update { it.copy(generativeTestResults = results) }
                }
            }

            is ConspectusIntent.UserGenerativeAnswerChange -> _state.update {
                it.copy(userGenerativeAnswers = it.userGenerativeAnswers
                    .mapIndexed { id, answer -> if (id == intent.id) intent.answer else answer })
            }

            is ConspectusIntent.UserExtractiveAnswerChange -> _state.update {
                it.copy(userExtractiveAnswers = it.userExtractiveAnswers
                    .mapIndexed { index, answer -> if (index == intent.id) intent.answer else answer })
            }
        }
    }
}