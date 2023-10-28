package com.ccb.kmmtranslator.android.di

import android.app.Application
import com.ccb.kmmtranslator.database.TranslateDatabase
import com.ccb.kmmtranslator.translate.data.history.SqlDelightHistoryDataSource
import com.ccb.kmmtranslator.translate.data.local.DatabaseDriverFactory
import com.ccb.kmmtranslator.translate.data.remote.HttpClientFactory
import com.ccb.kmmtranslator.translate.data.translate.KtorTranslateClient
import com.ccb.kmmtranslator.translate.domain.history.HistoryDataSource
import com.ccb.kmmtranslator.translate.domain.translate.Translate
import com.ccb.kmmtranslator.translate.domain.translate.TranslateClient
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriverFactory(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource,
    ): Translate {
        return Translate(client, dataSource)
    }
}
