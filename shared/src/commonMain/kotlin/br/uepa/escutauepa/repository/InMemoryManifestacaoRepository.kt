package br.uepa.escutauepa.repository

import br.uepa.escutauepa.model.Manifestacao

// Classe que implementa a interface ManifestacaoRepository e estabelece o armazenamento em tempo de execução
class InMemoryManifestacaoRepository : ManifestacaoRepository {

    private val manifestacoes =
        mutableListOf<Manifestacao>()

    override fun salvar(manifestacao: Manifestacao) {

        require(
            manifestacoes.none { it.id == manifestacao.id }
        ) {
            "Já existe uma manifestação com esse ID."
        }

        manifestacoes.add(manifestacao)
    }

    override fun buscarPorId(id: String): Manifestacao? {
        return manifestacoes.find { it.id == id }
    }

    override fun listarPorAutor(
        autorId: String
    ): List<Manifestacao> {
        return manifestacoes.filter {
            it.autorId == autorId
        }
    }
}