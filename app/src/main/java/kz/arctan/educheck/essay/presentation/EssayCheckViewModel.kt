package kz.arctan.educheck.essay.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.arctan.educheck.essay.data.EssayCheckApi
import javax.inject.Inject

@HiltViewModel
class EssayCheckViewModel @Inject constructor(
    private val essayCheckApi: EssayCheckApi,
) : ViewModel() {
    private val _state = MutableStateFlow(EssayCheckState())
    val state = _state.asStateFlow()

    fun reduce(intent: EssayCheckIntent) {
        when (intent) {
            EssayCheckIntent.CheckIntent -> viewModelScope.launch {
                try {
                    _state.update { it.copy(isLoading = true) }
                    val result =
                        essayCheckApi.checkEssay(_state.value.essay).body() ?: return@launch
                    _state.update { it.copy(feedback = result.result) }
                } catch (e: Exception) {
                    Log.e("ESSAY_CHECK_VIEWMODEL", e.localizedMessage ?: "Some error")
                } finally {
                    _state.update { it.copy(isLoading = false) }
                }
            }

            is EssayCheckIntent.EssayChangeIntent ->
                _state.update { it.copy(essay = intent.essay) }

            EssayCheckIntent.TakePictureIntent -> TODO()
        }
    }
}