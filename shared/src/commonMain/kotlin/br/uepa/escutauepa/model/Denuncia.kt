package br.uepa.escutauepa.model

// Subclasse de Manifestacao, que configura uma manifestação da natureza denúncia
class Denuncia(
    id: String,
    autorId: String,
    conteudo: ConteudoManifestacao,
    destinatario: Destinatario,
    dataCriacao: String
) : Manifestacao(
    id, autorId, conteudo, destinatario, dataCriacao
) {
    override fun obterNatureza(): String {
        return "Denúncia"
    }

    override fun validar(): Boolean {
        return super.validar() &&
                conteudo.descricao.length >= 20
    }
}