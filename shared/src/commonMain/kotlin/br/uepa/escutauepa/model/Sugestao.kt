package br.uepa.escutauepa.model

// Subclasse de Manifestacao, que configura uma manifestação da natureza sugestão
class Sugestao(
    id: String,
    autorId: String,
    conteudo: ConteudoManifestacao,
    destinatario: Destinatario,
    dataCriacao: String
) : Manifestacao(
    id, autorId, conteudo, destinatario, dataCriacao
) {
    override fun obterNatureza(): String {
        return "Sugestão"
    }
}