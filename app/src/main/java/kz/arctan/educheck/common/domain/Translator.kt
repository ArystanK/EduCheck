package kz.arctan.educheck.common.domain

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class Translator {
    fun translateOnEnglish(
        textOnRussian: String,
    ): Result<String> {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.RUSSIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val translator = Translation.getClient(options)
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)

//                translator.translate(textOnRussian)
//                    .addOnSuccessListener {
//                        return
//                    }
//                    .onSuccessTask { translatedText ->
//                        Result.success(translatedText)
//                    }
//            }
//            .addOnFailureListener { exception ->
//                return Result.failure(exception)
            }
        TODO()
    }

    fun translateOnRussian(textOnEnglish: String): String {
        TODO()
    }
}