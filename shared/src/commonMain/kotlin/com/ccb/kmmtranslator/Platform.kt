package com.ccb.kmmtranslator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform