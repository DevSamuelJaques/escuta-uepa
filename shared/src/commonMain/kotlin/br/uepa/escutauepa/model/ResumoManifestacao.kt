package br.uepa.escutauepa.model

// Classe para organização das informações a serem exibidos na consulta de histórico
data class ResumoManifestacao(
    val id: String,
    val titulo: String,
    val natureza: String,
    val destinatario: Destinatario,
    val dataCriacao: String,
    val status: StatusManifestacao
)