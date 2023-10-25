package com.ccb.kmmtranslator.translate.domain.translate

import com.ccb.kmmtranslator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): String
}