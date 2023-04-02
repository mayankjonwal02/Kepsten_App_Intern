package com.example.kepstenapp1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform