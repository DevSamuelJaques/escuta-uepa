package br.uepa.escutauepa.model

// Representa o usuário do aplicativo

/* Tipo de classe que contém dados,
possuindo métodos gerados pelo compilador
para tratamento facilitado */
data class Usuario(
    val id: String,
    val nome: String,
    val email: String
)
