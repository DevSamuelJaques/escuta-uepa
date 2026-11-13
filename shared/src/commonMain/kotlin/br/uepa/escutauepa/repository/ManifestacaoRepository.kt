package br.uepa.escutauepa.repository

import br.uepa.escutauepa.model.Manifestacao

// Interface que conterá os métodos para operações relacionadas a manifestação do aplicativo
interface ManifestacaoRepository {

    fun salvar(manifestacao: Manifestacao) // Salva a manifestação em memória

    fun buscarPorId(id: String): Manifestacao? // Busca a manifestação por id ou retorna null

    fun listarPorAutor(autorId: String): List<Manifestacao> // Lista as manifestações por usuário
}