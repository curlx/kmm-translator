package com.ccb.kmmtranslator.translate.domain.history

import com.ccb.kmmtranslator.core.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistory(item: HistoryItem)
}