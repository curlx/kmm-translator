package com.ccb.kmmtranslator.di

import com.ccb.kmmtranslator.database.TranslateDatabase
import com.ccb.kmmtranslator.translate.data.history.SqlDelightHistoryDataSource
import com.ccb.kmmtranslator.translate.data.local.DatabaseDriverFactory
import com.ccb.kmmtranslator.translate.data.remote.HttpClientFactory
import com.ccb.kmmtranslator.translate.data.translate.KtorTranslateClient
import com.ccb.kmmtranslator.translate.domain.history.HistoryDataSource
import com.ccb.kmmtranslator.translate.domain.translate.Translate
import com.ccb.kmmtranslator.translate.domain.translate.TranslateClient

class AppModule {

    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    val translateUseCase: Translate by lazy {
        Translate(translateClient, historyDataSource)
    }
}