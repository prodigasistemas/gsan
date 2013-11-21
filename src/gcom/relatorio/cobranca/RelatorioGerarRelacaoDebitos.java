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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.bean.GerarRelacaoDebitosHelper;
import gcom.cadastro.imovel.bean.GerarRelacaoDebitosImovelHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * [UC0227] - Gerar Relação de Débitos
 * 
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioGerarRelacaoDebitos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GERAR_RELACAO_DEBITOS);
	}

	@Deprecated
	public RelatorioGerarRelacaoDebitos() {
		super(null, "");
	}

	/**
	 * 
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Rafael Santos
	 * @date 26/05/2006
	 * 
	 * @return
	 * @throws TarefaException
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// id da genrencia regional
		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicao = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");

		// Aba de Débito
		String[] tipoDebito = (String[]) getParametro("tipoDebito");
		String valorDebitoInicial = (String) getParametro("valorDebitoInicial");
		String valorDebitoFinal = (String) getParametro("valorDebitoFinal");
		String qtdContasInicial = (String) getParametro("qtdContasInicial");
		String qtdContasFinal = (String) getParametro("qtdContasFinal");
		String referenciaFaturaInicial = (String) getParametro("referenciaFaturaInicial");
		String referenciaFaturaFinal = (String) getParametro("referenciaFaturaFinal");
		String vencimentoInicial = (String) getParametro("vencimentoInicial");
		String vencimentoFinal = (String) getParametro("vencimentoFinal");
		String qtdImoveis = (String) getParametro("qtdImoveis");
		String qtdMaiores = (String) getParametro("qtdMaiores");
		String indicadorCodigoBarra = (String) getParametro("indicadorCodigoBarra");

		// Ordenação
		String ordenacao = (String) getParametro("ordenacao");
		
		String indicadorCpfCnpj = (String) getParametro("indicadorCpfCnpj");		
		String cpfCnpj = (String) getParametro("cpfCnpj");
		
		// Tipo de Débito
		/*
		 * Collection<Integer> colecaoTipoDebito = new ArrayList(); if
		 * (tipoDebito != null && tipoDebito.length > 0) { String[] tiposDebito =
		 * tipoDebito; for (int i = 0; i < tiposDebito.length; i++) { if (new
		 * Integer(tiposDebito[i]).intValue() !=
		 * ConstantesSistema.NUMERO_NAO_INFORMADO) { colecaoTipoDebito.add(new
		 * Integer(tiposDebito[i])); } } }
		 */

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDadosRelatorio = fachada.gerarRelacaoDebitos(
				imovelCondominioID, imovelPrincipalID, nomeContaID,
				situacaoAgua, consumoMinimoInicial, consumoMinimoFinal,
				situacaoLigacaoEsgoto, consumoMinimoFixadoEsgotoInicial,
				consumoMinimoFixadoEsgotoFinal,
				intervaloPercentualEsgotoInicial,
				intervaloPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImoveFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, perfilImovelID,
				pocoTipoID, tipoSituacaoFaturamentoID, situacaoCobrancaID,
				tipoSituacaoEspecialCobrancaID, anormalidadeElo,
				areaConstruidaInicial, areaConstruidaFinal, ocorrenciaCadastro,
				tarifaConsumo, gerenciaRegional, localidadeOrigem,
				localidadeDestino, setorComercialOrigemCD,
				setorComercialDestinoCD, qudraOrigem, quadraDestino,
				loteOrigem, loteDestino, cep, logradouroID, bairroID,
				municipioID, tipoMedicaoID, indicadorMedicao, subCategoriaID,
				categoriaImovelID, quantidadeEconomiasInicial,
				quantidadeEconomiasFinal, diaVencimentoAlternativo, clienteID,
				clienteTipoID, clienteRelacaoTipoID, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, areaConstruidaFaixa, tipoDebito,
				valorDebitoInicial, valorDebitoFinal, qtdContasInicial,
				qtdContasFinal, referenciaFaturaInicial, referenciaFaturaFinal,
				vencimentoInicial, vencimentoFinal, qtdImoveis, qtdMaiores,
				indicadorCodigoBarra, ordenacao, indicadorCpfCnpj, cpfCnpj);

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		byte[] retorno = null;

		// bean do relatorio
		RelatorioGerarRelacaoDebitosBean relatorioGerarRelacaoDebitosBean = null;

		// dados para o relatorio
		if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {

			Iterator iteratorColecaoDadosRelatorio = colecaoDadosRelatorio
					.iterator();

			Integer idLocalidade = null;
			Integer idGerencia = null;
			int countContasLocalidade = 0;
			int countImovelLocalidade = 0;
			int countDebitosLocalidade = 0;
			int countContasGerencia = 0;
			int countImovelGerencia = 0;
			int countDebitosGerencia = 0;
			int countContasTotal = 0;
			int countImovelTotal = 0;
			int countDebitosTotal = 0;
			BigDecimal totalDebitosLocalidade = new BigDecimal("0.00");
			BigDecimal totalContasLocalidade = new BigDecimal("0.00");
			BigDecimal totalGuiasPagamentoLocalidade = new BigDecimal("0.00");
			BigDecimal totalDebitoACobrarLocalidade = new BigDecimal("0.00");
			BigDecimal totalAcrescimosLocalidade = new BigDecimal("0.00");
			BigDecimal totalDebitosGerencia = new BigDecimal("0.00");
			BigDecimal totalContasGerencia = new BigDecimal("0.00");
			BigDecimal totalGuiasPagamentoGerencia = new BigDecimal("0.00");
			BigDecimal totalDebitoACobrarGerencia = new BigDecimal("0.00");
			BigDecimal totalAcrescimosGerencia = new BigDecimal("0.00");
			BigDecimal totalDebitosTotal = new BigDecimal("0.00");
			BigDecimal totalContasTotal = new BigDecimal("0.00");
			BigDecimal totalGuiasPagamentoTotal = new BigDecimal("0.00");
			BigDecimal totalDebitoACobrarTotal = new BigDecimal("0.00");
			BigDecimal totalAcrescimosTotal = new BigDecimal("0.00");

			while (iteratorColecaoDadosRelatorio.hasNext()) {
				GerarRelacaoDebitosHelper gerarRelacaoDebitosHelper = (GerarRelacaoDebitosHelper) iteratorColecaoDadosRelatorio
						.next();

				GerarRelacaoDebitosImovelHelper gerarRelacaoDebitosImovelHelper = gerarRelacaoDebitosHelper
						.getGerarRelacaoDebitosImovelHelper();

				if (idLocalidade == null) {
					idLocalidade = new Integer(gerarRelacaoDebitosImovelHelper
							.getIdLocalidade());
				} else {
					Integer idLocalAtual = new Integer(
							gerarRelacaoDebitosImovelHelper.getIdLocalidade());
					if (!idLocalidade.equals(idLocalAtual)) {
						countContasLocalidade = 0;
						countImovelLocalidade = 0;
						countDebitosLocalidade = 0;
						totalDebitosLocalidade = new BigDecimal("0.00");
						totalContasLocalidade = new BigDecimal("0.00");
						totalGuiasPagamentoLocalidade = new BigDecimal("0.00");
						totalDebitoACobrarLocalidade = new BigDecimal("0.00");
						totalAcrescimosLocalidade = new BigDecimal("0.00");
						idLocalidade = idLocalAtual;
					}
				}

				if (idGerencia == null) {
					idGerencia = new Integer(gerarRelacaoDebitosImovelHelper
							.getIdGerenciaRegional());
				} else {
					Integer idGerenciaAtual = new Integer(
							gerarRelacaoDebitosImovelHelper
									.getIdGerenciaRegional());
					if (!idGerencia.equals(idGerenciaAtual)) {
						countContasGerencia = 0;
						countImovelGerencia = 0;
						countDebitosGerencia = 0;
						totalDebitosGerencia = new BigDecimal("0.00");
						totalContasGerencia = new BigDecimal("0.00");
						totalGuiasPagamentoGerencia = new BigDecimal("0.00");
						totalDebitoACobrarGerencia = new BigDecimal("0.00");
						totalAcrescimosGerencia = new BigDecimal("0.00");
						idGerencia = idGerenciaAtual;

					}
				}

				countImovelLocalidade++;
				countImovelGerencia++;
				countImovelTotal++;

				if (gerarRelacaoDebitosHelper.getTotalGeralAtualizado() != null) {

					totalDebitosLocalidade = totalDebitosLocalidade
							.add(gerarRelacaoDebitosHelper
									.getTotalGeralAtualizado());
					totalDebitosGerencia = totalDebitosGerencia
							.add(gerarRelacaoDebitosHelper
									.getTotalGeralAtualizado());
					totalDebitosTotal = totalDebitosTotal
							.add(gerarRelacaoDebitosHelper
									.getTotalGeralAtualizado());
				}

				Collection colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean = null;

				colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean = new ArrayList();

				// carregar os dados das guias de pagamento
				if (gerarRelacaoDebitosHelper.getColecaoGuiasPagamento() != null
						&& !gerarRelacaoDebitosHelper
								.getColecaoGuiasPagamento().isEmpty()) {

					Iterator iteratorColecaoGuiasPagamento = gerarRelacaoDebitosHelper
							.getColecaoGuiasPagamento().iterator();

					RelatorioGerarRelacaoDebitosGuiasPagamentoBean relatorioGerarRelacaoDebitosGuiasPagamentoBean = null;
					RelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean relatorioGerarRelacaoDebitosGuiasPagamentoTotalBean = null;

					Collection colecaoRelatorioGerarRelacaoDebitosGuiaPagamentoTotal = null;

					while (iteratorColecaoGuiasPagamento.hasNext()) {

						GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) iteratorColecaoGuiasPagamento
								.next();

						colecaoRelatorioGerarRelacaoDebitosGuiaPagamentoTotal = new ArrayList();
						relatorioGerarRelacaoDebitosGuiasPagamentoTotalBean = new RelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean();
						// total da guia de pagamento
						relatorioGerarRelacaoDebitosGuiasPagamentoTotalBean
								.setGuiaValorTotal(Util
										.formatarMoedaReal(gerarRelacaoDebitosHelper
												.getTotalGuiasPagamento()));
						colecaoRelatorioGerarRelacaoDebitosGuiaPagamentoTotal
								.add(relatorioGerarRelacaoDebitosGuiasPagamentoTotalBean);

						// dados da guia de pagamento
						relatorioGerarRelacaoDebitosGuiasPagamentoBean = new RelatorioGerarRelacaoDebitosGuiasPagamentoBean(
								Util
										.formatarData(guiaPagamentoValoresHelper
												.getGuiaPagamento()
												.getDataVencimento()),
								Util
										.formatarMoedaReal(guiaPagamentoValoresHelper
												.getGuiaPagamento()
												.getValorDebito()),
								colecaoRelatorioGerarRelacaoDebitosGuiaPagamentoTotal);

						colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean
								.add(relatorioGerarRelacaoDebitosGuiasPagamentoBean);
					}// fim do while de guias
				}// fim da condição das guias

				totalContasLocalidade = totalContasLocalidade
						.add(gerarRelacaoDebitosHelper.getTotalContas());
				totalGuiasPagamentoLocalidade = totalGuiasPagamentoLocalidade
						.add(gerarRelacaoDebitosHelper.getTotalGuiasPagamento());
				totalDebitoACobrarLocalidade = totalDebitoACobrarLocalidade
						.add(gerarRelacaoDebitosHelper.getTotalDebitosACobrar());
				totalAcrescimosLocalidade = totalAcrescimosLocalidade.add(
						gerarRelacaoDebitosHelper.getTotalMulta()).add(
						gerarRelacaoDebitosHelper.getTotalJuros()).add(
						gerarRelacaoDebitosHelper
								.getTotalAtualizacaoMonetaria());

				totalContasGerencia = totalContasGerencia
						.add(gerarRelacaoDebitosHelper.getTotalContas());
				totalGuiasPagamentoGerencia = totalGuiasPagamentoGerencia
						.add(gerarRelacaoDebitosHelper.getTotalGuiasPagamento());
				totalDebitoACobrarGerencia = totalDebitoACobrarGerencia
						.add(gerarRelacaoDebitosHelper.getTotalDebitosACobrar());
				totalAcrescimosGerencia = totalAcrescimosGerencia.add(
						gerarRelacaoDebitosHelper.getTotalMulta()).add(
						gerarRelacaoDebitosHelper.getTotalJuros()).add(
						gerarRelacaoDebitosHelper
								.getTotalAtualizacaoMonetaria());

				totalContasTotal = totalContasTotal
						.add(gerarRelacaoDebitosHelper.getTotalContas());
				totalGuiasPagamentoTotal = totalGuiasPagamentoTotal
						.add(gerarRelacaoDebitosHelper.getTotalGuiasPagamento());
				totalDebitoACobrarTotal = totalDebitoACobrarTotal
						.add(gerarRelacaoDebitosHelper.getTotalDebitosACobrar());
				totalAcrescimosTotal = totalAcrescimosTotal.add(
						gerarRelacaoDebitosHelper.getTotalMulta()).add(
						gerarRelacaoDebitosHelper.getTotalJuros()).add(
						gerarRelacaoDebitosHelper
								.getTotalAtualizacaoMonetaria());

				BigDecimal contaDacGuia = gerarRelacaoDebitosHelper
						.getTotalContas()
						.add(
								gerarRelacaoDebitosHelper
										.getTotalDebitosACobrar()
										.add(
												gerarRelacaoDebitosHelper
														.getTotalGuiasPagamento()));
				BigDecimal acrescimo = gerarRelacaoDebitosHelper
						.getTotalMulta()
						.add(
								gerarRelacaoDebitosHelper
										.getTotalJuros()
										.add(
												gerarRelacaoDebitosHelper
														.getTotalAtualizacaoMonetaria()));

				// carrega os dados dos valores totais do imovel
				RelatorioGerarRelacaoDebitosTotaisImovelBean relatorioGerarRelacaoDebitosTotaisImovelBean = new RelatorioGerarRelacaoDebitosTotaisImovelBean(
						Util.formatarMoedaReal(gerarRelacaoDebitosHelper
								.getTotalContas()), Util
								.formatarMoedaReal(gerarRelacaoDebitosHelper
										.getTotalDebitosACobrar()), Util
								.formatarMoedaReal(contaDacGuia),// Util.formatarMoedaReal(gerarRelacaoDebitosHelper.getTotalContas()),
						Util.formatarMoedaReal(gerarRelacaoDebitosHelper
								.getTotalGuiasPagamento()), Util
								.formatarMoedaReal(acrescimo),// Util.formatarMoedaReal(gerarRelacaoDebitosHelper.getTotalMulta()),
						Util.formatarMoedaReal(gerarRelacaoDebitosHelper
								.getTotalJuros()), Util
								.formatarMoedaReal(gerarRelacaoDebitosHelper
										.getTotalAtualizacaoMonetaria()), Util
								.formatarMoedaReal(gerarRelacaoDebitosHelper
										.getTotalGeralAtualizado()),
						colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean);

				// adiciona os dados dos valores totais do imovel
				Collection colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean = new ArrayList();
				colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean
						.add(relatorioGerarRelacaoDebitosTotaisImovelBean);

				// colecao dos creditos a realizar
				Collection colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = null;

				colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new ArrayList();

				// carrega os dados de debito a cobrar e credito a realizar
				if (gerarRelacaoDebitosHelper
						.getColecaoDebitosACobrarCreditoARealizar() != null
						&& !gerarRelacaoDebitosHelper
								.getColecaoDebitosACobrarCreditoARealizar()
								.isEmpty()) {

					RelatorioGerarRelacaoDebitosTipoDebitoCreditoBean relatorioGerarRelacaoDebitosTipoDebitoCreditoBean = null;

					Iterator iteratorColecaoDebitosACobrarCreditoARealizar = gerarRelacaoDebitosHelper
							.getColecaoDebitosACobrarCreditoARealizar()
							.iterator();
					while (iteratorColecaoDebitosACobrarCreditoARealizar
							.hasNext()) {

						countDebitosLocalidade++;
						countDebitosGerencia++;
						countDebitosTotal++;

						relatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new RelatorioGerarRelacaoDebitosTipoDebitoCreditoBean();

						Object object = iteratorColecaoDebitosACobrarCreditoARealizar
								.next();
						if (object instanceof DebitoACobrar) {
							DebitoACobrar debitoACobrar = (DebitoACobrar) object;

							relatorioGerarRelacaoDebitosTipoDebitoCreditoBean
									.setDebitoTipoDebito(debitoACobrar
											.getDebitoTipo().getDescricao());
							relatorioGerarRelacaoDebitosTipoDebitoCreditoBean
									.setDebitoValor(Util
											.formatarMoedaReal(debitoACobrar
													.getValorTotalComBonus()));

						} else if (object instanceof CreditoARealizar) {
							CreditoARealizar creditoARealizar = (CreditoARealizar) object;

							relatorioGerarRelacaoDebitosTipoDebitoCreditoBean
									.setDebitoTipoDebito(creditoARealizar
											.getCreditoTipo().getDescricao());
							relatorioGerarRelacaoDebitosTipoDebitoCreditoBean
									.setDebitoValor(Util
											.formatarMoedaReal(creditoARealizar
													.getValorTotalComBonus()));

						}

						colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
								.add(relatorioGerarRelacaoDebitosTipoDebitoCreditoBean);
					}// fim do whilede creditos
				}// fim if decreditos

				// colecao de contas
				Collection colecaoRelatorioGerarRelacaoDebitosContasBean = null;

				colecaoRelatorioGerarRelacaoDebitosContasBean = new ArrayList();

				// carregar a colecao de contas
				if (gerarRelacaoDebitosHelper.getColecaoContas() != null
						&& !gerarRelacaoDebitosHelper.getColecaoContas()
								.isEmpty()) {

					RelatorioGerarRelacaoDebitosContasBean relatorioGerarRelacaoDebitosContasBean = null;

					Iterator iteratorColecaoContas = gerarRelacaoDebitosHelper
							.getColecaoContas().iterator();

					RelatorioGerarRelacaoDebitosContasTotalBean relatorioGerarRelacaoDebitosContasTotalBean = null;
					Collection colecaoRelatorioGerarRelacaoDebitosContasTotalBean = null;

					while (iteratorColecaoContas.hasNext()) {
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iteratorColecaoContas
								.next();

						countContasLocalidade++;
						countContasGerencia++;
						countContasTotal++;

						String revisao = "";
						if (contaValoresHelper.getConta()
								.getContaMotivoRevisao() != null
								&& contaValoresHelper.getConta()
										.getContaMotivoRevisao().getId() != null) {
							revisao = new String("R");
						}

						String codigoBarras = "";

						String anoMesFormatado = "";
						String anoMesRecebido = ""
								+ contaValoresHelper.getConta().getReferencia();
						if (anoMesRecebido.length() < 6) {
							anoMesFormatado = anoMesRecebido;
						} else {
							String mes = anoMesRecebido.substring(4, 6);
							String ano = anoMesRecebido.substring(0, 4);
							anoMesFormatado = mes + "" + ano;
						}
						String representacaoNumericaCodBarraFormatada = "";

						if (indicadorCodigoBarra.equals("1")) {
							codigoBarras = fachada
									.obterRepresentacaoNumericaCodigoBarra(
											new Integer(3),
											new BigDecimal(contaValoresHelper
													.getConta()
													.getValorTotalConta()),
											new Integer(
													gerarRelacaoDebitosHelper
															.getGerarRelacaoDebitosImovelHelper()
															.getIdLocalidade()),
											new Integer(
													gerarRelacaoDebitosHelper
															.getGerarRelacaoDebitosImovelHelper()
															.getIdImovel()),
											anoMesFormatado,
											new Integer(
													new Short(
															contaValoresHelper
																	.getConta()
																	.getDigitoVerificadorConta())
															.toString()), null,
											null, null, null, null, null,null);

							// Formata a representação númerica do código de
							// barras
							representacaoNumericaCodBarraFormatada = codigoBarras
									.substring(0, 11)
									+ "-"
									+ codigoBarras.substring(11, 12)
									+ " "
									+ codigoBarras.substring(12, 23)
									+ "-"
									+ codigoBarras.substring(23, 24)
									+ " "
									+ codigoBarras.substring(24, 35)
									+ "-"
									+ codigoBarras.substring(35, 36)
									+ " "
									+ codigoBarras.substring(36, 47)
									+ "-" + codigoBarras.substring(47, 48);
						}
						colecaoRelatorioGerarRelacaoDebitosContasTotalBean = new ArrayList();

						// dados do total da conta
						relatorioGerarRelacaoDebitosContasTotalBean = new RelatorioGerarRelacaoDebitosContasTotalBean(
								Util
										.formatarMoedaReal(gerarRelacaoDebitosHelper
												.getTotalContas()),
								Util
										.formatarMoedaReal(gerarRelacaoDebitosHelper
												.getTotalContaAtualizado()));

						colecaoRelatorioGerarRelacaoDebitosContasTotalBean
								.add(relatorioGerarRelacaoDebitosContasTotalBean);

						relatorioGerarRelacaoDebitosContasBean = new RelatorioGerarRelacaoDebitosContasBean(
								revisao,
								Util
										.formatarAnoMesParaMesAno(contaValoresHelper
												.getConta().getReferencia())
										+ "-"
										+ contaValoresHelper.getConta()
												.getDigitoVerificadorConta(),
								Util.formatarData(contaValoresHelper.getConta()
										.getDataVencimentoConta()),
								Util.formatarMoedaReal(new BigDecimal(
										contaValoresHelper.getConta()
												.getValorTotalConta())),
								representacaoNumericaCodBarraFormatada,
								colecaoRelatorioGerarRelacaoDebitosContasTotalBean);

						colecaoRelatorioGerarRelacaoDebitosContasBean
								.add(relatorioGerarRelacaoDebitosContasBean);

					}// fim while de contas

				}// fim if de contas

				String percentualEsgoto = "";
				if (gerarRelacaoDebitosImovelHelper.getPercentualEsgoto() != null) {
					percentualEsgoto = percentualEsgoto
							+ gerarRelacaoDebitosImovelHelper
									.getPercentualEsgoto() + " %";

				}
				relatorioGerarRelacaoDebitosBean = new RelatorioGerarRelacaoDebitosBean(
						gerarRelacaoDebitosImovelHelper.getIdImovel(),
						gerarRelacaoDebitosImovelHelper.getInscricaoImovel(),
						gerarRelacaoDebitosImovelHelper.getNomeClienteUsuario(),
						gerarRelacaoDebitosImovelHelper
								.getNomeClienteResponsavel(),
						gerarRelacaoDebitosImovelHelper.getEndereco(),
						gerarRelacaoDebitosImovelHelper
								.getQuantidadeEconomias(),
						gerarRelacaoDebitosImovelHelper.getCategoriaPrincipal(),
						gerarRelacaoDebitosImovelHelper
								.getSubcategoriaPrincipal(),
						gerarRelacaoDebitosImovelHelper.getSituacaoAgua(),
						gerarRelacaoDebitosImovelHelper.getSituacaoEsgoto(),
						percentualEsgoto,
						gerarRelacaoDebitosImovelHelper.getDataCorte(),
						gerarRelacaoDebitosImovelHelper.getConsumoMediaImovel(),
						gerarRelacaoDebitosImovelHelper
								.getNomeGerenciaRegional()
								+ " - "
								+ gerarRelacaoDebitosImovelHelper
										.getGerenciaRegional(),
						gerarRelacaoDebitosImovelHelper.getIdLocalidade()
								+ " - "
								+ gerarRelacaoDebitosImovelHelper
										.getDescricaoLocalidade(),
						gerarRelacaoDebitosImovelHelper.getRota(),
						gerarRelacaoDebitosImovelHelper.getSequencialRota(),
						"" + countContasLocalidade,
						"" + countImovelLocalidade,
						"" + countDebitosLocalidade,
						"" + countContasGerencia,
						"" + countImovelGerencia,
						"" + countDebitosGerencia,
						"" + countContasTotal,
						"" + countImovelTotal,
						"" + countDebitosTotal,
						Util.formatarMoedaReal(totalDebitosLocalidade),
						Util.formatarMoedaReal(totalDebitosGerencia),
						Util.formatarMoedaReal(totalDebitosTotal),
						colecaoRelatorioGerarRelacaoDebitosContasBean,
						colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean,
						colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean,
						colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoBean,
						totalContasLocalidade, totalGuiasPagamentoLocalidade,
						totalDebitoACobrarLocalidade,
						totalAcrescimosLocalidade, totalContasGerencia,
						totalGuiasPagamentoGerencia,
						totalDebitoACobrarGerencia, totalAcrescimosGerencia,
						totalContasTotal, totalGuiasPagamentoTotal,
						totalDebitoACobrarTotal, totalAcrescimosTotal);

				// add item da colecao
				relatorioBeans.add(relatorioGerarRelacaoDebitosBean);
			}
		}else{
			RelatorioGerarRelacaoDebitosBean beanNaoExisteDados
				= new RelatorioGerarRelacaoDebitosBean();
			
			beanNaoExisteDados.setMensagemNaoExisteDados("A pesquisa não retornou nenhum resultado.");
			beanNaoExisteDados.setMatriculaImovel("0");
			
			relatorioBeans.add(beanNaoExisteDados);
		}

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAnoArrecadacao", Util
				.formatarAnoMesParaMesAno(sistemaParametro
						.getAnoMesArrecadacao().intValue()));

		parametros.put("tipoFormatoRelatorio", "R0610");

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		/*
		 * parametros.put("gerenciaRegional",
		 * gerenciaRegional.getNomeAbreviado());
		 * parametros.put("idLocalidadeOrigem", imovelParametrosInicial
		 * .getLocalidade().getId() == null ? "" : "" +
		 * imovelParametrosInicial.getLocalidade().getId());
		 * parametros.put("idLocalidadeDestino", imovelParametrosFinal
		 * .getLocalidade().getId() == null ? "" : "" +
		 * imovelParametrosFinal.getLocalidade().getId());
		 * parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
		 * .getLocalidade().getDescricao());
		 * parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
		 * .getLocalidade().getDescricao());
		 * parametros.put("idSetorComercialOrigem", imovelParametrosInicial
		 * .getSetorComercial().getId() == null ? "" : "" +
		 * imovelParametrosInicial.getSetorComercial().getCodigo());
		 * parametros.put("idSetorComercialDestino", imovelParametrosFinal
		 * .getSetorComercial().getId() == null ? "" : "" +
		 * imovelParametrosFinal.getSetorComercial().getCodigo());
		 * parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
		 * .getSetorComercial().getDescricao());
		 * parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
		 * .getSetorComercial().getDescricao());
		 * parametros.put("numeroQuadraOrigem", imovelParametrosInicial
		 * .getQuadra().getNumeroQuadra() == 0 ? "" : "" +
		 * imovelParametrosInicial.getQuadra().getNumeroQuadra());
		 * parametros.put("numeroQuadraDestino",
		 * imovelParametrosFinal.getQuadra() .getNumeroQuadra() == 0 ? "" : "" +
		 * imovelParametrosFinal.getQuadra().getNumeroQuadra());
		 * parametros.put("loteOrigem", imovelParametrosInicial.getLote() == 0 ? "" : "" +
		 * imovelParametrosInicial.getLote()); parametros.put("loteDestino",
		 * imovelParametrosFinal.getLote() == 0 ? "" : "" +
		 * imovelParametrosFinal.getLote()); parametros.put("idMunicipio",
		 * municipio.getId() == null ? "" : "" + municipio.getId());
		 * parametros.put("nomeMunicipio", municipio.getNome());
		 * parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : "" +
		 * bairro.getCodigo()); parametros.put("nomeBairro", bairro.getNome());
		 * parametros.put("cep",
		 * imovelParametrosInicial.getLogradouroCep().getCep().getCodigo() ==
		 * null ? "" : "" +
		 * imovelParametrosInicial.getLogradouroCep().getCep().getCodigo());
		 * parametros.put("idLogradouro",
		 * imovelParametrosInicial.getLogradouroCep().getLogradouro() .getId() ==
		 * null ? "" : "" +
		 * imovelParametrosInicial.getLogradouroCep().getLogradouro().getId());
		 * parametros.put("nomeLogradouro", imovelParametrosInicial
		 * .getLogradouroCep().getLogradouro().getNome());
		 * parametros.put("idCliente", clienteImovelParametros.getCliente()
		 * .getId() == null ? "" : "" +
		 * clienteImovelParametros.getCliente().getId());
		 * parametros.put("nomeCliente", clienteImovelParametros.getCliente()
		 * .getNome()); parametros.put("tipoRelacao", clienteImovelParametros
		 * .getClienteRelacaoTipo().getDescricao());
		 * parametros.put("tipoCliente", clienteImovelParametros.getCliente()
		 * .getClienteTipo().getDescricao()); parametros.put("imovelCondominio",
		 * imovelParametrosInicial .getImovelCondominio().getId() == null ? "" : "" +
		 * imovelParametrosInicial.getImovelCondominio().getId());
		 * parametros.put("imovelPrincipal", imovelParametrosInicial
		 * .getImovelPrincipal().getId() == null ? "" : "" +
		 * imovelParametrosInicial.getImovelPrincipal().getId());
		 * parametros.put("nomeConta", imovelParametrosInicial.getNomeConta()
		 * .getNomeConta()); parametros.put("situacaoLigacaoAgua",
		 * imovelParametrosInicial .getLigacaoAguaSituacao().getDescricao());
		 * parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
		 * .getLigacaoEsgotoSituacao().getDescricao());
		 * parametros.put("consumoMinimoFixadoAguaInicial",
		 * imovelParametrosInicial.getLigacaoAgua()
		 * .getNumeroConsumoMinimoAgua() == null ? null : "" +
		 * imovelParametrosInicial.getLigacaoAgua()
		 * .getNumeroConsumoMinimoAgua());
		 * parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
		 * .getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? null : "" +
		 * imovelParametrosFinal.getLigacaoAgua()
		 * .getNumeroConsumoMinimoAgua());
		 * parametros.put("percentualEsgotoInicial", imovelParametrosInicial
		 * .getLigacaoEsgoto().getPercentual() == null ? null :
		 * imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
		 * .toString()); parametros.put("percentualEsgotoFinal",
		 * imovelParametrosFinal .getLigacaoEsgoto().getPercentual() == null ?
		 * null : imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
		 * .toString()); parametros .put("consumoMinimoFixadoEsgotoInicial",
		 * imovelParametrosInicial.getLigacaoEsgoto() .getConsumoMinimo() ==
		 * null ? null : "" + imovelParametrosInicial.getLigacaoEsgoto()
		 * .getConsumoMinimo());
		 * parametros.put("consumoMinimoFixadoEsgotoFinal",
		 * imovelParametrosFinal .getLigacaoEsgoto().getConsumoMinimo() == null ?
		 * null : "" +
		 * imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		 * parametros.put("indicadorMedicao", indicadorMedicao == null ? "" :
		 * indicadorMedicao.equals(new Short("0")) ? "SEM MEDIÇÃO" : "COM
		 * MEDIÇÃO"); parametros.put("tipoMedicao",
		 * medicaoHistoricoParametrosInicial .getMedicaoTipo().getDescricao());
		 * parametros .put( "mediaMinimaImovelInicial",
		 * consumoHistoricoParametrosInicial.getConsumoMedio() == null ? null : "" +
		 * consumoHistoricoParametrosInicial .getConsumoMedio()); parametros
		 * .put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
		 * .getConsumoMedio() == null ? null : "" +
		 * consumoHistoricoParametrosFinal.getConsumoMedio()); parametros
		 * .put("mediaMinimaHidrometroInicial",
		 * medicaoHistoricoParametrosInicial .getConsumoMedioHidrometro() ==
		 * null ? null : "" + medicaoHistoricoParametrosInicial
		 * .getConsumoMedioHidrometro()); parametros
		 * .put("mediaMinimaHidrometroFinal", medicaoHistoricoParametrosFinal
		 * .getConsumoMedioHidrometro() == null ? null : "" +
		 * medicaoHistoricoParametrosFinal .getConsumoMedioHidrometro());
		 * parametros.put("perfilImovel", imovelParametrosInicial
		 * .getImovelPerfil().getDescricao()); parametros.put("categoria",
		 * categoria.getDescricao()); parametros.put("subCategoria",
		 * subcategoria.getDescricao()); parametros.put("qtdeEconomiaInicial",
		 * imovelParametrosInicial .getQuantidadeEconomias() == null ? null : "" +
		 * imovelParametrosInicial.getQuantidadeEconomias());
		 * parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
		 * .getQuantidadeEconomias() == null ? null : "" +
		 * imovelParametrosFinal.getQuantidadeEconomias());
		 * parametros.put("numeroPontosInicial", imovelParametrosInicial
		 * .getNumeroPontosUtilizacao() == 0 ? null : "" +
		 * imovelParametrosInicial.getNumeroPontosUtilizacao());
		 * parametros.put("numeroPontosFinal", imovelParametrosFinal
		 * .getNumeroPontosUtilizacao() == 0 ? null : "" +
		 * imovelParametrosFinal.getNumeroPontosUtilizacao());
		 * parametros.put("numeroMoradoresInicial", imovelParametrosInicial
		 * .getNumeroMorador() == 0 ? null : "" +
		 * imovelParametrosInicial.getNumeroMorador());
		 * parametros.put("numeroMoradoresFinal", imovelParametrosFinal
		 * .getNumeroMorador() == 0 ? null : "" +
		 * imovelParametrosFinal.getNumeroMorador());
		 * parametros.put("areaConstruidaInicial", imovelParametrosInicial
		 * .getAreaConstruida().equals(new Short("0")) ? null : "" +
		 * imovelParametrosInicial.getAreaConstruida());
		 * parametros.put("areaConstruidaFinal", imovelParametrosFinal
		 * .getAreaConstruida().equals(new Short("0")) ? null : "" +
		 * imovelParametrosFinal.getAreaConstruida());
		 * parametros.put("tipoPoco", imovelParametrosInicial.getPocoTipo()
		 * .getDescricao()); parametros.put("tipoSituacaoEspecialFaturamento",
		 * imovelParametrosInicial.getFaturamentoSituacaoTipo()
		 * .getDescricao()); parametros.put("tipoSituacaoEspecialCobranca",
		 * imovelParametrosInicial .getCobrancaSituacaoTipo().getDescricao());
		 * parametros.put("situacaoCobranca", cobrancaSituacao == null ? "" :
		 * cobrancaSituacao.getDescricao());
		 * parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
		 * .getDiaVencimento() == null ? "" : "" +
		 * imovelParametrosInicial.getDiaVencimento());
		 * parametros.put("anormalidadeElo", imovelParametrosInicial
		 * .getEloAnormalidade() == null ? "" : imovelParametrosInicial
		 * .getEloAnormalidade().getDescricao());
		 * parametros.put("ocorrenciaCadastro", imovelParametrosInicial
		 * .getCadastroOcorrencia().getDescricao());
		 * parametros.put("tarifaConsumo", imovelParametrosInicial
		 * .getConsumoTarifa().getDescricao());
		 */
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GERAR_RELACAO_DEBITOS,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.GERAR_RELACAO_DEBITOS, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado*/
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return -1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarRelacaoDebitosResumido",
				this);
	}
}
