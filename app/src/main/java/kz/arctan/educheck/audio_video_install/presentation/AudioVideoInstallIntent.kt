package kz.arctan.educheck.audio_video_install.presentation

sealed interface AudioVideoInstallIntent {
    object UploadFileIntent : AudioVideoInstallIntent
    object StartTestIntent : AudioVideoInstallIntent
    @JvmInline
    value class LinkChangeIntent(val link: String) : AudioVideoInstallIntent
}