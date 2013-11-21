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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioManterAvisoBancario extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterAvisoBancario(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AVISO_BANCARIO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterAvisoBancario() {
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

		AvisoBancarioHelper avisoBancarioHelper = (AvisoBancarioHelper) getParametro("avisoBancarioHelper");
		AvisoBancarioHelper avisoBancarioHelperParametros = (AvisoBancarioHelper) getParametro("avisoBancarioHelperParametros");
		Arrecadador arrecadadorParametros = (Arrecadador) getParametro("arrecadadorParametros");
		ContaBancaria contaBancariaParametros = (ContaBancaria) getParametro("contaBancariaParametros");
		ArrecadadorMovimento arrecadadorMovimentoParametros = (ArrecadadorMovimento) getParametro("arrecadadorMovimentoParametros");
		String avisosAbertosFechadosParametros = (String) getParametro("avisosAbertosFechadosParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioAvisosBancariosBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterAvisoBancarioBean relatorioAvisoBancarioBean = null;

		Collection colecaoAvisosBancarios = fachada
				.pesquisarAvisoBancarioRelatorio(avisoBancarioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAvisosBancarios != null && !colecaoAvisosBancarios.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoAvisosBancariosIterator = colecaoAvisosBancarios
					.iterator();

//			BigDecimal totalDeducoes = new BigDecimal("0.00");

			// laço para criar a coleção de parâmetros da analise
			while (colecaoAvisosBancariosIterator.hasNext()) {

				AvisoBancarioRelatorioHelper avisoBancarioRelatorioHelper = (AvisoBancarioRelatorioHelper) colecaoAvisosBancariosIterator
						.next();
				
				BigDecimal totalDeducoes = new BigDecimal("0.00");

				Collection colecaoDeducoes = fachada
						.pesquisarAvisoDeducoesAvisoBancarioRelatorio(avisoBancarioRelatorioHelper
								.getIdAvisoBancario());

				Collection colecaoRelatorioAvisoBancarioDeducoesBeans = new ArrayList();

				if (colecaoDeducoes != null && !colecaoDeducoes.isEmpty()) {
					Iterator colecaoDeducoesIterator = colecaoDeducoes
							.iterator();

					RelatorioManterAvisoBancarioDeducoesBean relatorioDeducoesBean = null;

					while (colecaoDeducoesIterator.hasNext()) {
						DeducoesRelatorioHelper deducoesRelatorioHelper = (DeducoesRelatorioHelper) colecaoDeducoesIterator
								.next();
						totalDeducoes = totalDeducoes
								.add(deducoesRelatorioHelper.getValorDeducao());

						relatorioDeducoesBean = new RelatorioManterAvisoBancarioDeducoesBean(

								// Tipo da Dedução
								deducoesRelatorioHelper.getTipo(),

								// Valor da Dedução
								Util.formatarMoedaReal(deducoesRelatorioHelper
										.getValorDeducao()));

						// adiciona o bean a coleção
						colecaoRelatorioAvisoBancarioDeducoesBeans
								.add(relatorioDeducoesBean);

					}
					
				}

				Collection colecaoAcertos = fachada
						.pesquisarAvisoAcertosAvisoBancarioRelatorio(avisoBancarioRelatorioHelper
								.getIdAvisoBancario());

				Collection colecaoRelatorioAvisoBancarioAcertosBeans = new ArrayList();

				if (colecaoAcertos != null && !colecaoAcertos.isEmpty()) {
					Iterator colecaoAcertosIterator = colecaoAcertos.iterator();

					RelatorioManterAvisoBancarioAcertosBean relatorioAcetosBean = null;

					while (colecaoAcertosIterator.hasNext()) {
						AcertosRelatorioHelper acertosRelatorioHelper = (AcertosRelatorioHelper) colecaoAcertosIterator
								.next();

						relatorioAcetosBean = new RelatorioManterAvisoBancarioAcertosBean(

								// Número Documento
								"",

								// Banco
								acertosRelatorioHelper.getBanco(),

								// Agencia
								acertosRelatorioHelper.getAgencia().toString(),

								// Número Conta
								acertosRelatorioHelper.getNumeroConta(),

								// Data
								Util.formatarData(acertosRelatorioHelper
										.getDataAcerto()),

								// Valor
								Util.formatarMoedaReal(acertosRelatorioHelper
										.getValorAcerto()),

								// Tipo
								acertosRelatorioHelper.getTipo().shortValue() == AvisoBancario.INDICADOR_CREDITO
										.shortValue() ? "CRÉDITO" : "DÉBITO"

						);

						// adiciona o bean a coleção
						colecaoRelatorioAvisoBancarioAcertosBeans
								.add(relatorioAcetosBean);

					}
				}

				relatorioAvisoBancarioBean = new RelatorioManterAvisoBancarioBean(

						// Id Aviso Bancário
						avisoBancarioRelatorioHelper.getIdAvisoBancario()
								.toString(),

						// Arrecadador
						avisoBancarioRelatorioHelper.getNomeArrecadador(),

						// Data Lançamento
						Util.formatarData(avisoBancarioRelatorioHelper
								.getDataLancamento()),

						// Sequencial
						avisoBancarioRelatorioHelper.getSequencial() == null ? ""
								: avisoBancarioRelatorioHelper.getSequencial()
										.toString(),

						// Tipo
						avisoBancarioRelatorioHelper.getTipo().shortValue() == AvisoBancario.INDICADOR_CREDITO
								.shortValue() ? "CRÉDITO" : "DÉBITO",

						// Número Documento
						avisoBancarioRelatorioHelper.getNumeroDocumento()
								.toString(),

						// Banco
						avisoBancarioRelatorioHelper.getBanco(),

						// Agência
						avisoBancarioRelatorioHelper.getAgencia().toString(),

						// Número Conta
						avisoBancarioRelatorioHelper.getNumeroConta(),

						// Data Realização
						Util.formatarData(avisoBancarioRelatorioHelper
								.getDataRealizacao()),

						// Total Arrecadação
						avisoBancarioRelatorioHelper
								.getTotalArrecadacao(),

						// Total Deduções
						totalDeducoes,

						// Total Devolução
						avisoBancarioRelatorioHelper
								.getTotalDevolucao(),

						// Valor Aviso
						avisoBancarioRelatorioHelper
								.getValorAviso(),

						// Coleção de Deduções
						colecaoRelatorioAvisoBancarioDeducoesBeans,

						// Coleção de Acertos
						colecaoRelatorioAvisoBancarioAcertosBeans);

				// adiciona o bean a coleção
				relatorioAvisosBancariosBeans.add(relatorioAvisoBancarioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Id Arrecadador

		if (arrecadadorParametros != null) {

			parametros.put("idArrecadador", arrecadadorParametros.getCodigoAgente()
					.toString());

		} else {
			parametros.put("idArrecadador", "");
		}

		// Nome Arrecadador
		if (arrecadadorParametros != null) {
			parametros.put("nomeArrecadador", arrecadadorParametros
					.getCliente().getNome());
		} else {
			parametros.put("nomeArrecadador", "");
		}

		// Período Lançamento

		if (avisoBancarioHelperParametros.getDataLancamentoInicial() != null) {

			parametros.put("periodoLancamentoInicial", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataLancamentoInicial()));

		} else {
			parametros.put("periodoLancamentoInicial", null);
		}

		if (avisoBancarioHelperParametros.getDataLancamentoFinal() != null) {

			parametros.put("periodoLancamentoFinal", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataLancamentoFinal()));

		} else {
			parametros.put("periodoLancamentoFinal", null);
		}

		// Tipo Aviso

		String tipoAviso = "";

		if (avisoBancarioHelperParametros.getTipoAviso() != null
				&& !avisoBancarioHelperParametros.getTipoAviso().equals("")) {
			if (avisoBancarioHelperParametros.getTipoAviso().equals(
					new Short("1"))) {
				tipoAviso = "Crédito";
			} else if (avisoBancarioHelperParametros.getTipoAviso().equals(
					new Short("2"))) {
				tipoAviso = "Débito";
			}
		}

		parametros.put("tipoAviso", tipoAviso);

		// Conta Bancária

		if (contaBancariaParametros != null) {

			parametros.put("contaBancaria", contaBancariaParametros
					.getAgencia().getBanco().getId().toString()
					+ contaBancariaParametros.getAgencia().getCodigoAgencia()
							.toString()
					+ contaBancariaParametros.getNumeroConta());

		} else {
			parametros.put("contaBancaria", "");
		}

		// Movimento

		if (arrecadadorMovimentoParametros != null) {

			parametros.put("movimento", arrecadadorMovimentoParametros
					.getCodigoBanco().toString()
					+ arrecadadorMovimentoParametros.getCodigoRemessa()
							.toString()
					+ arrecadadorMovimentoParametros
							.getDescricaoIdentificacaoServico()
					+ arrecadadorMovimentoParametros
							.getNumeroSequencialArquivo().toString());

		} else {
			parametros.put("movimento", "");
		}

		// Período Arrecadação

		if (avisoBancarioHelperParametros
				.getAnoMesReferenciaArrecadacaoInicial() != 0) {

			parametros.put("periodoArrecadacaoInicial", Util
					.formatarAnoMesParaMesAno(avisoBancarioHelperParametros
							.getAnoMesReferenciaArrecadacaoInicial()));

		} else {
			parametros.put("periodoArrecadacaoInicial", null);
		}

		if (avisoBancarioHelperParametros.getAnoMesReferenciaArrecadacaoFinal() != 0) {

			parametros.put("periodoArrecadacaoFinal", Util
					.formatarAnoMesParaMesAno(avisoBancarioHelperParametros
							.getAnoMesReferenciaArrecadacaoFinal()));

		} else {
			parametros.put("periodoArrecadacaoFinal", null);
		}

		// Período Previsão Crédito/Débito

		if (avisoBancarioHelperParametros.getDataPrevistaInicial() != null) {

			parametros.put("periodoPrevisaoCreditoDebitoInicial", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataPrevistaInicial()));

		} else {
			parametros.put("periodoPrevisaoCreditoDebitoInicial", null);
		}

		if (avisoBancarioHelperParametros.getDataPrevistaFinal() != null) {

			parametros.put("periodoPrevisaoCreditoDebitoFinal", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataPrevistaFinal()));

		} else {
			parametros.put("periodoPrevisaoCreditoDebitoFinal", null);
		}

		// Período Realização Crédito/Débito

		if (avisoBancarioHelperParametros.getDataRealizadaInicial() != null) {

			parametros.put("periodoRealizacaoCreditoDebitoInicial", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataRealizadaInicial()));

		} else {
			parametros.put("periodoRealizacaoCreditoDebitoInicial", null);
		}

		if (avisoBancarioHelperParametros.getDataRealizadaFinal() != null) {

			parametros.put("periodoRealizacaoCreditoDebitoFinal", Util
					.formatarData(avisoBancarioHelperParametros
							.getDataRealizadaFinal()));

		} else {
			parametros.put("periodoRealizacaoCreditoDebitoFinal", null);
		}

		// Intervalo Valor Realizado

		if (avisoBancarioHelperParametros.getValorRealizadoInicial() != null) {

			parametros.put("intervaloValorRealizadoInicial", Util
					.formatarMoedaReal(avisoBancarioHelperParametros
							.getValorRealizadoInicial()));

		} else {
			parametros.put("intervaloValorRealizadoInicial", null);
		}

		if (avisoBancarioHelperParametros.getValorRealizadoFinal() != null) {

			parametros.put("intervaloValorRealizadoFinal", Util
					.formatarMoedaReal(avisoBancarioHelperParametros
							.getValorRealizadoFinal()));

		} else {
			parametros.put("intervaloValorRealizadoInicial", null);
		}

		// Avisos Abertos/Fechados

		String avisosAbertosFechados = "";

		if (avisosAbertosFechadosParametros != null
				&& !avisosAbertosFechadosParametros.equals("")
				&& !avisosAbertosFechadosParametros.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			if (avisosAbertosFechadosParametros.equals(""
					+ ConstantesSistema.MOVIMENTO_ABERTO)) {
				avisosAbertosFechados = "Aberto";
			} else if (avisosAbertosFechadosParametros.equals(""
					+ ConstantesSistema.MOVIMENTO_FECHADO)) {
				avisosAbertosFechados = "Fechado";
			}
		}

		parametros.put("avisosAbertosFechados", avisosAbertosFechados);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(
				relatorioAvisosBancariosBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_AVISO_BANCARIO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_AVISO_BANCARIO, idFuncionalidadeIniciada);
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
		Fachada fachada = Fachada.getInstancia();

		AvisoBancarioHelper avisoBancarioHelper = (AvisoBancarioHelper) getParametro("avisoBancarioHelper");
		Collection colecaoAvisosBancario = fachada.filtrarAvisoBancarioAbertoFechado(avisoBancarioHelper);

		AvisoBancarioHelper objetoAvisoBancario = null;
		AvisoBancarioHelper avisoBancarioHelperNovo = null;
		Iterator iterator = colecaoAvisosBancario.iterator();
	
		while (iterator.hasNext()) {
			objetoAvisoBancario = (AvisoBancarioHelper) iterator.next();
			avisoBancarioHelperNovo = new AvisoBancarioHelper();
	
			avisoBancarioHelperNovo
					.setAvisoBancario(objetoAvisoBancario.getAvisoBancario());
			avisoBancarioHelperNovo
				.setTipoAviso(avisoBancarioHelper.getTipoAviso());
		}
		
		retorno = Fachada.getInstancia()
				.filtrarAvisoBancarioAbertoFechadoCount(avisoBancarioHelper,avisoBancarioHelperNovo);

		return retorno;
	}


	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterAvisoBancario", this);
	}
}