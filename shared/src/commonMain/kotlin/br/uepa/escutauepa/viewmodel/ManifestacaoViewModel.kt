package br.uepa.escutauepa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.uepa.escutauepa.model.*
import br.uepa.escutauepa.service.ManifestacaoService
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.todayIn
import kotlin.time.Clock

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

        val id = service.gerarProximoId()

        // Sistema para gerar a data automaticamente ao cadastrar uma manifestação
        val dataHoje: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

        val formatoBrasileiro = LocalDate.Format {
            this@Format.day(padding = Padding.ZERO)
            // Garante 2 dígitos (ex: 05, 14)
            char('/')
            monthNumber() // Garante 2 dígitos (ex: 01, 11)
            char('/')
            year() // Ano com 4 dígitos (ex: 2026)
        }

        val data = dataHoje.format(formatoBrasileiro)

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