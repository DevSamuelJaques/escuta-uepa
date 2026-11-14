package br.uepa.escutauepa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.uepa.escutauepa.model.Manifestacao
import br.uepa.escutauepa.model.ResumoManifestacao
import br.uepa.escutauepa.service.ManifestacaoService

class HistoricoViewModel(
    private val service: ManifestacaoService
) {
    var manifestacoes by mutableStateOf(
        emptyList<ResumoManifestacao>()
    )
        private set

    var selecionada by mutableStateOf<Manifestacao?>(null)
        private set

    var mensagemErro by mutableStateOf<String?>(null)
        private set

    fun carregarHistorico() {
        mensagemErro = null

        try {
            manifestacoes = service.listarResumos()
        } catch (erro: IllegalStateException) {
            manifestacoes = emptyList()
            mensagemErro = erro.message
                ?: "Não foi possível carregar o histórico."
        }
    }

    fun abrirDetalhes(id: String) {
        mensagemErro = null

        try {
            selecionada = service.buscarPorId(id)

            if (selecionada == null) {
                mensagemErro = "Manifestação não encontrada."
            }
        } catch (erro: IllegalStateException) {
            selecionada = null
            mensagemErro = erro.message
        }
    }

    fun fecharDetalhes() {
        selecionada = null
    }

    fun limpar() {
        manifestacoes = emptyList()
        selecionada = null
        mensagemErro = null
    }
}