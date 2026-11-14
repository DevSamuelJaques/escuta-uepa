package br.uepa.escutauepa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.uepa.escutauepa.model.*
import br.uepa.escutauepa.service.ManifestacaoService

enum class NaturezaManifestacao {
    SUGESTAO,
    RECLAMACAO,
    DENUNCIA
}

class ManifestacaoViewModel(
    private val service: ManifestacaoService
) {
    var natureza by mutableStateOf(
        NaturezaManifestacao.SUGESTAO
    )
        private set

    var destinatario by mutableStateOf(
        Destinatario.CAMPUS
    )
        private set

    var titulo by mutableStateOf("")
        private set

    var descricao by mutableStateOf("")
        private set

    var mensagemErro by mutableStateOf<String?>(null)
        private set

    var cadastroRealizado by mutableStateOf(false)
        private set

    private var proximoId = 1L

    fun selecionarNatureza(valor: NaturezaManifestacao) {
        natureza = valor
        mensagemErro = null
    }

    fun selecionarDestinatario(valor: Destinatario) {
        destinatario = valor
    }

    fun atualizarTitulo(valor: String) {
        titulo = valor
        mensagemErro = null
    }

    fun atualizarDescricao(valor: String) {
        descricao = valor
        mensagemErro = null
    }

    fun cadastrar(autorId: String) {
        mensagemErro = null
        cadastroRealizado = false

        val conteudo = ConteudoManifestacao(
            titulo = titulo.trim(),
            descricao = descricao.trim()
        )

        val id = "manifestacao-${proximoId}"

        // Data provisória até implementarmos
        // a geração automática de datas.
        val data = "Data não informada"

        val manifestacao: Manifestacao = when (natureza) {
            NaturezaManifestacao.SUGESTAO -> Sugestao(
                id, autorId, conteudo, destinatario, data
            )

            NaturezaManifestacao.RECLAMACAO -> Reclamacao(
                id, autorId, conteudo, destinatario, data
            )

            NaturezaManifestacao.DENUNCIA -> Denuncia(
                id, autorId, conteudo, destinatario, data
            )
        }

        try {
            service.cadastrar(manifestacao)
            proximoId++
            cadastroRealizado = true
        } catch (erro: IllegalArgumentException) {
            mensagemErro = erro.message
                ?: "Dados inválidos."
        } catch (erro: IllegalStateException) {
            mensagemErro = erro.message
                ?: "Não foi possível cadastrar."
        }
    }

    fun limparFormulario() {
        natureza = NaturezaManifestacao.SUGESTAO
        destinatario = Destinatario.CAMPUS
        titulo = ""
        descricao = ""
        mensagemErro = null
        cadastroRealizado = false
    }
}