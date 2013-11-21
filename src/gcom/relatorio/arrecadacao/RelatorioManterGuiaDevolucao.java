/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioManterGuiaDevolucao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioManterGuiaDevolucao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterGuiaDevolucao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroGuiaDevolucao filtroGuiaDevolucao = (FiltroGuiaDevolucao) getParametro("filtroGuiaDevolucao");
		GuiaDevolucao guiaDevolucaoParametrosInicial = (GuiaDevolucao) getParametro("guiaDevolucaoParametrosInicial");
		GuiaDevolucao guiaDevolucaoParametrosFinal = (GuiaDevolucao) getParametro("guiaDevolucaoParametrosFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento.debitoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

		filtroGuiaDevolucao.setConsultaSemLimites(true);

		Collection colecaoGuiasDevolucoes = fachada
				.pesquisarGuiaDevolucaoRelatorio(filtroGuiaDevolucao);

		RelatorioManterGuiaDevolucaoBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoGuiasDevolucoes != null && !colecaoGuiasDevolucoes.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoGuiasDevolucoesIterator = colecaoGuiasDevolucoes
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoGuiasDevolucoesIterator.hasNext()) {

				GuiaDevolucao guiaDevolucao = (GuiaDevolucao) colecaoGuiasDevolucoesIterator
						.next();

				String tipoDebito = "";

				if (guiaDevolucao.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
					tipoDebito = guiaDevolucao.getGuiaPagamento().getDebitoTipo().getDescricao();
				} else if (guiaDevolucao.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
					tipoDebito = 
						guiaDevolucao.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getDescricao();
				} else if (guiaDevolucao.getDocumentoTipo().getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
					tipoDebito = guiaDevolucao.getDebitoTipo().getDescricao();
				}

				// Cria o objeto que será impresso no relatório setando os
				// campos que serão mostrados e fazendo as verificações para
				// evitar NullPointerException
				relatorioBean = new RelatorioManterGuiaDevolucaoBean(

						// Gerência Regional
						guiaDevolucao.getLocalidade().getGerenciaRegional() == null ? ""
								: guiaDevolucao.getLocalidade()
										.getGerenciaRegional().getId()
										+ " - "
										+ guiaDevolucao.getLocalidade()
												.getGerenciaRegional()
												.getNome(),

						// Localidade
						guiaDevolucao.getLocalidade().getId().toString()
								+ " - "
								+ guiaDevolucao.getLocalidade().getDescricao(),

						// Matrícula do Imóvel ou Código do Cliente
						guiaDevolucao.getImovel() == null ? guiaDevolucao
								.getCliente().getId().toString()
								: guiaDevolucao.getImovel().getId().toString(),

						// Inscrição do Imóvel ou Nome do Cliente
						guiaDevolucao.getImovel() == null ? guiaDevolucao
								.getCliente().getNome() : guiaDevolucao
								.getImovel().getInscricaoFormatada(),

						// Tipo Documento
						guiaDevolucao.getDocumentoTipo() == null ? ""
								: guiaDevolucao.getDocumentoTipo()
										.getDescricaoDocumentoTipo(),

						// Data Emissão
						guiaDevolucao.getDataEmissao() == null ? "" : Util
								.formatarData(guiaDevolucao.getDataEmissao()),

						// Referência da Conta
						guiaDevolucao.getConta() == null ? "" : Util
								.formatarAnoMesParaMesAno(guiaDevolucao
										.getConta()
										.getFormatarAnoMesParaMesAno()),

						// Tipo de Débito
						tipoDebito,

						// Tipo de Crédito
						guiaDevolucao.getCreditoTipo() == null ? ""
								: guiaDevolucao.getCreditoTipo().getDescricao(),

						// Valor da Guia de Devolução
						guiaDevolucao.getValorDevolucao() == null ? "" : Util
								.formatarMoedaReal(guiaDevolucao
										.getValorDevolucao()));

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Seta os parâmetros de acordo com o que o usuário digitou ou de acordo
		// com a qtde de devoluções de cada tipo e seus respectivos valores
		if (guiaDevolucaoParametrosInicial.getAnoMesReferenciaContabil() != null) {
			parametros
					.put(
							"periodoArrecadacao",
							Util
									.formatarAnoMesParaMesAno(guiaDevolucaoParametrosInicial
											.getAnoMesReferenciaContabil())
									+ " a "
									+ Util
											.formatarAnoMesParaMesAno(guiaDevolucaoParametrosFinal
													.getAnoMesReferenciaContabil()));

		} else {
			parametros.put("periodoArrecadacao", "");
		}

		if (guiaDevolucaoParametrosInicial.getAnoMesReferenciaGuiaDevolucao() != null) {
			parametros
					.put(
							"periodoGuia",
							Util
									.formatarAnoMesParaMesAno(guiaDevolucaoParametrosInicial
											.getAnoMesReferenciaGuiaDevolucao())
									+ " a "
									+ Util
											.formatarAnoMesParaMesAno(guiaDevolucaoParametrosFinal
													.getAnoMesReferenciaGuiaDevolucao()));

		} else {
			parametros.put("periodoGuia", "");
		}

		if (guiaDevolucaoParametrosInicial.getDataEmissao() != null) {
			parametros.put("periodoEmissao", Util
					.formatarData(guiaDevolucaoParametrosInicial
							.getDataEmissao())
					+ " a "
					+ Util.formatarData(guiaDevolucaoParametrosFinal
							.getDataEmissao()));
		} else {
			parametros.put("periodoEmissao", "");
		}

		if (guiaDevolucaoParametrosInicial.getDataEmissao() != null) {
			parametros.put("periodoValidade", Util
					.formatarData(guiaDevolucaoParametrosInicial
							.getDataValidade())
					+ " a "
					+ Util.formatarData(guiaDevolucaoParametrosFinal
							.getDataValidade()));
		} else {
			parametros.put("periodoValidade", "");
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_GUIA_DEVOLUCAO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroGuiaDevolucao) getParametro("filtroGuiaDevolucao"),
				GuiaDevolucao.class.getName());

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterGuiaDevolucao", this);
	}

}
