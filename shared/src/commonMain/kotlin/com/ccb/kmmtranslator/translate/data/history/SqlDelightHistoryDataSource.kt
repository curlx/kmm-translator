package com.ccb.kmmtranslator.translate.data.history

import com.ccb.kmmtranslator.core.domain.util.CommonFlow
import com.ccb.kmmtranslator.core.domain.util.toCommonFlow
import com.ccb.kmmtranslator.database.TranslateDatabase
import com.ccb.kmmtranslator.translate.domain.history.HistoryDataSource
import com.ccb.kmmtranslator.translate.domain.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase,
): HistoryDataSource {

    private val queries by lazy { db.translateQueries }

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { history ->
                history.map { it.toHistoryItem() }
            }.toCommonFlow()
    }

    override suspend fun insertHistory(item: HistoryItem) {
        queries.insertHistory(
            item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds(),
        )
    }
}