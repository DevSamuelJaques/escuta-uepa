package br.uepa.escutauepa

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform