package br.uepa.escutauepa.model

// Agrupa as informações textuais que serão utilizadas por qualquer manifestação

/* Tipo de classe que contém dados,
possuindo métodos gerados pelo compilador
para tratamento facilitado */
data class ConteudoManifestacao(
    val titulo: String,
    val descricao: String
)
