package kz.arctan.educheck.question_answer.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.arctan.educheck.question_answer.data.QuestionAnswerApi
import javax.inject.Inject

@HiltViewModel
class QuestionAnswerViewModel @Inject constructor(
    private val questionAnswerApi: QuestionAnswerApi,
) : ViewModel() {
    private val _state = MutableStateFlow(QuestionAnswerState())
    val state = _state.asStateFlow()

    fun reduce(intent: QuestionAnswerIntent) {
        when (intent) {
            is QuestionAnswerIntent.AgeChangeIntent -> _state.update { it.copy(age = intent.age) }

            is QuestionAnswerIntent.QuestionChangeIntent -> _state.update { it.copy(question = intent.question) }

            QuestionAnswerIntent.SendQuestionIntent -> viewModelScope.launch {
                try {
                    _state.update { it.copy(isLoading = true) }
                    Log.d("QUESTION_ANSWER_VIEWMODEL", "request was send")
                    val answer =
                        questionAnswerApi.askQuestion(
                            _state.value.question,
                            _state.value.chosenSubject
                        )
                    _state.update { it.copy(explanation = answer.body() ?: "") }
                } catch (e: Exception) {
                    Log.e("QUESTION_ANSWER_VIEWMODEL+", e.localizedMessage)
                } finally {
                    _state.update { it.copy(isLoading = false) }
                }
            }

            is QuestionAnswerIntent.SubjectClickIntent -> _state.update {
                it.copy(
                    chosenSubject = intent.subject,
                    isDropDownMenuShown = false
                )
            }

            is QuestionAnswerIntent.ToggleDropDownMenuIntent -> _state.update {
                it.copy(isDropDownMenuShown = intent.onToggle)
            }
        }
    }
}