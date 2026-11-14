package br.uepa.escutauepa.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.uepa.escutauepa.model.*
import br.uepa.escutauepa.viewmodel.HistoricoViewModel

private val VerdeUEPA = Color(0xFF007F77)

@Composable
fun HistoricoScreen(
    viewModel: HistoricoViewModel,
    onVoltar: () -> Unit
) {
    LaunchedEffect(viewModel) {
        viewModel.carregarHistorico()
    }

    viewModel.selecionada?.let { manifestacao ->
        DetalhesManifestacaoDialog(
            manifestacao = manifestacao,
            onFechar = viewModel::fecharDetalhes
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextButton(onClick = onVoltar) {
            Text("← Voltar")
        }

        Text(
            text = "Meu histórico",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Acompanhe suas manifestações.",
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        viewModel.mensagemErro?.let { erro ->
            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(Modifier.height(16.dp))
        }

        if (viewModel.manifestacoes.isEmpty()) {
            Text(
                text = "Nenhuma manifestação encontrada.",
                color = Color.Gray
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = viewModel.manifestacoes,
                    key = { it.id }
                ) { resumo ->
                    ItemHistorico(
                        resumo = resumo,
                        onClick = {
                            viewModel.abrirDetalhes(resumo.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemHistorico(
    resumo: ResumoManifestacao,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = resumo.titulo,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${resumo.natureza} • " +
                        resumo.destinatario.name,
                color = VerdeUEPA
            )

            Text(
                text = resumo.dataCriacao,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = when (resumo.status) {
                    StatusManifestacao.PENDENTE ->
                        "Pendente"
                    StatusManifestacao.EM_ANALISE ->
                        "Em análise"
                    StatusManifestacao.CONCLUIDA ->
                        "Concluída"
                },
                color = VerdeUEPA,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DetalhesManifestacaoDialog(
    manifestacao: Manifestacao,
    onFechar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onFechar,
        title = {
            Text(manifestacao.conteudo.titulo)
        },
        text = {
            Column(
                modifier = Modifier
                    .heightIn(max = 420.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Natureza: " +
                            manifestacao.obterNatureza()
                )

                Text(
                    "Destinatário: " +
                            manifestacao.destinatario.name
                )

                Text(
                    "Data: " +
                            manifestacao.dataCriacao
                )

                Text(
                    "Status: " +
                            manifestacao.controleStatus.status.name
                )

                HorizontalDivider()

                Text("Descrição")

                Text(manifestacao.conteudo.descricao)
            }
        },
        confirmButton = {
            TextButton(onClick = onFechar) {
                Text("Fechar")
            }
        }
    )
}