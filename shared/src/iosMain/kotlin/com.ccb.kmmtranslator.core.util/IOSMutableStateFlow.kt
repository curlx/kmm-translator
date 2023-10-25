package com.ccb.kmmtranslator.core.util

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(
    initialValue: T
 ): CommonMutableStateFlow<T>(MutableStateFlow(initialValue))