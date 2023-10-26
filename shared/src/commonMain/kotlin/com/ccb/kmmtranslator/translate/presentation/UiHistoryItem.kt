package com.ccb.kmmtranslator.translate.presentation

import com.ccb.kmmtranslator.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage,
)
