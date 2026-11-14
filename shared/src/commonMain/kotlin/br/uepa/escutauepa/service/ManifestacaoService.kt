package br.uepa.escutauepa.service

import br.uepa.escutauepa.model.Manifestacao
import br.uepa.escutauepa.model.ResumoManifestacao
import br.uepa.escutauepa.model.Usuario
import br.uepa.escutauepa.repository.ManifestacaoRepository

/* Classe que implementa o serviço de cadastro de manifestações,
fornecendo validações necessárias e comunicando com a interface
ManifestacaoRepository, sem precisar saber como as manifestações
são armazenadas de fato, focando apenas em ser a "ponte" para
o funcionamento correto da funcionalidade */
class ManifestacaoService(
    private val repository: ManifestacaoRepository,
    private val autenticacaoService: AutenticacaoService
) {
    fun gerarProximoId(): String {
        obterUsuarioAtual()
        return repository.gerarProximoId()
    }

    private fun obterUsuarioAtual(): Usuario {
        return autenticacaoService.usuarioAtual
            ?: throw IllegalStateException(
                "Usuário não autenticado."
            )
    }

    // Verifica se a manifestação é do usuário, valida a manifestação e realiza seu armazenamento
    fun cadastrar(manifestacao: Manifestacao) {
        val usuario = obterUsuarioAtual()

        require(manifestacao.autorId == usuario.id) {
            "A manifestação deve pertencer ao usuário autenticado."
        }

        require(manifestacao.validar()) {
            "Os dados da manifestação são inválidos."
        }

        repository.salvar(manifestacao)
    }

    fun listarPorUsuario(): List<Manifestacao> {
        val usuario = obterUsuarioAtual()

        return repository.listarPorAutor(usuario.id)
    }

    fun buscarPorId(id: String): Manifestacao? {
        val usuario = obterUsuarioAtual()

        val manifestacao = repository.buscarPorId(id)
            ?: return null

        return manifestacao.takeIf {
            it.autorId == usuario.id
        }
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
