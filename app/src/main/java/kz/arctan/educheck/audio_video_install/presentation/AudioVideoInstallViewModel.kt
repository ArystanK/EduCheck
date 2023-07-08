package kz.arctan.educheck.audio_video_install.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AudioVideoInstallViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(AudioVideoInstallState())
    val state = _state.asStateFlow()

    fun reduce(intent: AudioVideoInstallIntent) {

    }
}