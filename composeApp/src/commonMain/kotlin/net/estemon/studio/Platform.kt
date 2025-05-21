package net.estemon.studio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform