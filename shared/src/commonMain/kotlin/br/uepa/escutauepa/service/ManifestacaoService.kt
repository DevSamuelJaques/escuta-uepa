package br.uepa.escutauepa.service

import br.uepa.escutauepa.model.Manifestacao
import br.uepa.escutauepa.model.ResumoManifestacao
import br.uepa.escutauepa.repository.ManifestacaoRepository

/* Classe que implementa o serviço de cadastro de manifestações,
fornecendo validações necessárias e comunicando com a interface
ManifestacaoRepository, sem precisar saber como as manifestações
são armazenadas de fato, focando apenas em ser a "ponte" para
o funcionamento correto da funcionalidade */
class ManifestacaoService(
    private val repository: ManifestacaoRepository
) {
    // Valida a manifestação e solicita seu armazenamento
    fun cadastrar(manifestacao: Manifestacao) {

        require(manifestacao.validar()) {
            "Os dados da manifestação são inválidos."
        }

        repository.salvar(manifestacao)
    }

    fun buscarPorId(id: String): Manifestacao? {
        return repository.buscarPorId(id)
    }

    fun listarPorUsuario(
        autorId: String
    ): List<Manifestacao> {
        return repository.listarPorAutor(autorId)
    }

    fun listarResumos(): List<ResumoManifestacao> {
        return listarPorUsuario().map { manifestacao ->
            ResumoManifestacao(
                id = manifestacao.id,
                titulo = manifestacao.conteudo.titulo,
                natureza = manifestacao.obterNatureza(),
                destinatario = manifestacao.destinatario,
                dataCriacao = manifestacao.dataCriacao,
                status = manifestacao.controleStatus.status
            )
        }
    }

}