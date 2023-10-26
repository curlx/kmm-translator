package com.ccb.kmmtranslator.translate.domain.translate

import com.ccb.kmmtranslator.core.domain.language.Language
import com.ccb.kmmtranslator.core.domain.util.Resource
import com.ccb.kmmtranslator.translate.domain.history.HistoryDataSource
import com.ccb.kmmtranslator.translate.domain.history.HistoryItem

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource,
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): Resource<String> {
        return try {
            val translatedText = client.translate(fromLanguage, fromText, toLanguage)

            historyDataSource.insertHistory(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText,
                )
            )

            Resource.Success(translatedText)
        } catch(e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}