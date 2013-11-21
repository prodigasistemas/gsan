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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.ContaRevisaoFaixaValor;
import gcom.faturamento.bean.ContasEmRevisaoRelatorioHelper;
import gcom.faturamento.conta.FiltroContaRevisaoFaixaValor;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
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
 * classe responsável por criar o relatório de volumes faturados
 * 
 * @author Rafael Corrêa
 * @created 12/09/2007
 */
public class RelatorioContasEmRevisao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioContasEmRevisao(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO);
	}

	@Deprecated
	public RelatorioContasEmRevisao() {
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

		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
		Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
		Collection<Integer> colecaoIdsMotivoRevisao = (Collection<Integer>) getParametro("colecaoIdsMotivoRevisao");
		Integer idImovelPerfil = (Integer) getParametro("idImovelPerfil");
		Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
		Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
		Integer idCategoria = (Integer) getParametro("idCategoria");
		Integer idEsferaPoder = (Integer) getParametro("idEsferaPoder");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContasEmRevisaoBean relatorioBean = null;
		
		
		/** Comentado por Arthur Carvalho
		 * Solicitado por Rodrigo
		 * Motivo: sobrecarregando a base da compesa
		 */
//		Integer contasRevisaoCount = fachada.pesquisarDadosRelatorioContasRevisaoCount(idGerenciaRegional, idUnidadeNegocio, 
//				idLocalidadeInicial, idLocalidadeFinal, codigoSetorComercialInicial, codigoSetorComercialFinal,
//				colecaoIdsMotivoRevisao, idImovelPerfil, referenciaInicial, referenciaFinal, idCategoria, 
//				idEsferaPoder);
//		
//		if ( contasRevisaoCount == null || contasRevisaoCount == 0 ) {
//			//Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
//			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
//		}


		Collection colecaoContasEmRevisaoRelatorioHelper = fachada
				.pesquisarDadosRelatorioContasRevisao(idGerenciaRegional, idUnidadeNegocio,
						idLocalidadeInicial, idLocalidadeFinal, 
						codigoSetorComercialInicial, codigoSetorComercialFinal,
						colecaoIdsMotivoRevisao, idImovelPerfil, referenciaInicial,
						referenciaFinal, idCategoria, idEsferaPoder);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoContasEmRevisaoRelatorioHelper != null
				&& !colecaoContasEmRevisaoRelatorioHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoContasEmRevisaoRelatorioHelperIterator = colecaoContasEmRevisaoRelatorioHelper
					.iterator();
			
			// Cria as variáveis para verificar se os totalizadores de gerência
			// e elo serão mostrados no relatório
			String imprimeLocalidade = null;
			String imprimeElo = null;
			String imprimeUnidadeNegocio = null;
			String imprimeGerenciaRegional = null;
			
			if (idGerenciaRegional != null) {
				imprimeGerenciaRegional = "SIM";
				imprimeUnidadeNegocio = "SIM";
				imprimeLocalidade = "SIM";
			} else if (idUnidadeNegocio != null) {
				imprimeUnidadeNegocio = "SIM";
				imprimeLocalidade = "SIM";
			} else if (idLocalidadeInicial != null) {
				imprimeLocalidade = "SIM";
			}
			
			FiltroContaRevisaoFaixaValor filtroContaRevisaoFaixaValor = new FiltroContaRevisaoFaixaValor(
					FiltroContaRevisaoFaixaValor.VALOR_INICIO_FAIXA);
			
			Collection colecaoContaRevisaoFaixaValor = fachada.pesquisar(
					filtroContaRevisaoFaixaValor, ContaRevisaoFaixaValor.class
							.getName());

			// Cria as variáveis de totalização
			Integer qtdeContasTotalSetorComercial = new Integer("0");
			BigDecimal valorContasTotalSetorComercial = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1SetorComercial = new Integer("0");
			BigDecimal valorContasTotalFaixa1SetorComercial = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2SetorComercial = new Integer("0");
			BigDecimal valorContasTotalFaixa2SetorComercial = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3SetorComercial = new Integer("0");
			BigDecimal valorContasTotalFaixa3SetorComercial = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4SetorComercial = new Integer("0");
			BigDecimal valorContasTotalFaixa4SetorComercial = new BigDecimal("0.00");
			
			Integer qtdeContasTotalLocalidade = new Integer("0");
			BigDecimal valorContasTotalLocalidade = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1Localidade = new Integer("0");
			BigDecimal valorContasTotalFaixa1Localidade = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2Localidade = new Integer("0");
			BigDecimal valorContasTotalFaixa2Localidade = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3Localidade = new Integer("0");
			BigDecimal valorContasTotalFaixa3Localidade = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4Localidade = new Integer("0");
			BigDecimal valorContasTotalFaixa4Localidade = new BigDecimal("0.00");
			
			Integer qtdeContasTotalUnidadeNegocio = new Integer("0");
			BigDecimal valorContasTotalUnidadeNegocio = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1UnidadeNegocio = new Integer("0");
			BigDecimal valorContasTotalFaixa1UnidadeNegocio = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2UnidadeNegocio = new Integer("0");
			BigDecimal valorContasTotalFaixa2UnidadeNegocio = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3UnidadeNegocio = new Integer("0");
			BigDecimal valorContasTotalFaixa3UnidadeNegocio = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4UnidadeNegocio = new Integer("0");
			BigDecimal valorContasTotalFaixa4UnidadeNegocio = new BigDecimal("0.00");
			
			Integer qtdeContasTotalElo = new Integer("0");
			BigDecimal valorContasTotalElo = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1Elo = new Integer("0");
			BigDecimal valorContasTotalFaixa1Elo = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2Elo = new Integer("0");
			BigDecimal valorContasTotalFaixa2Elo = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3Elo = new Integer("0");
			BigDecimal valorContasTotalFaixa3Elo = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4Elo = new Integer("0");
			BigDecimal valorContasTotalFaixa4Elo = new BigDecimal("0.00");
			
			Integer qtdeContasTotalGerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalGerenciaRegional = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1GerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalFaixa1GerenciaRegional = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2GerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalFaixa2GerenciaRegional = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3GerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalFaixa3GerenciaRegional = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4GerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalFaixa4GerenciaRegional = new BigDecimal("0.00");
			
			Integer qtdeContasTotalGeral = new Integer("0");
			BigDecimal valorContasTotalGeral = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa1Geral = new Integer("0");
			BigDecimal valorContasTotalFaixa1Geral = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa2Geral = new Integer("0");
			BigDecimal valorContasTotalFaixa2Geral = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa3Geral = new Integer("0");
			BigDecimal valorContasTotalFaixa3Geral = new BigDecimal("0.00");
			Integer qtdeContasTotalFaixa4Geral = new Integer("0");
			BigDecimal valorContasTotalFaixa4Geral = new BigDecimal("0.00");

			Integer idSetorComercialAnterior = null;
			Integer idLocalidadeAnterior = null;
			Integer idEloAnterior = null;
			Integer idUnidadeNegocioAnterior = null;
			Integer idGerenciaRegionalAnterior = null;
			
			boolean zerarSetorComercial = false;
			boolean zerarLocalidade = false;
			boolean zerarElo = false;
			boolean zerarUnidadeNegocio = false;
			boolean zerarGerenciaRegional = false;

			// laço para criar a coleção de parâmetros da analise
			while (colecaoContasEmRevisaoRelatorioHelperIterator.hasNext()) {

				ContasEmRevisaoRelatorioHelper contasEmRevisaoRelatorioHelper = (ContasEmRevisaoRelatorioHelper) colecaoContasEmRevisaoRelatorioHelperIterator
						.next();
				
				// Seta os valores das variáveis de controle de totalização para
				// verificar quando deve ser zerado os totalizadores
				if (idSetorComercialAnterior == null) {
					idSetorComercialAnterior = contasEmRevisaoRelatorioHelper.getCodigoSetorComercial();
				}
				
				if (idLocalidadeAnterior == null) {
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper.getIdLocalidade();
				}

				if (idEloAnterior == null) {
					idEloAnterior = contasEmRevisaoRelatorioHelper.getIdElo();
				}
				
				if (idUnidadeNegocioAnterior == null) {
					idUnidadeNegocioAnterior = contasEmRevisaoRelatorioHelper.getIdUnidadeNegocio();
				}

				if (idGerenciaRegionalAnterior == null) {
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper.getIdGerenciaRegional();
				}

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Gerência Regional
				String gerenciaRegional = "";

				if (contasEmRevisaoRelatorioHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeGerenciaRegional();

					// Caso tenha mudado a gerência regional do imóvel seta a
					// variável para true, para posteriormente zerar todas as
					// variáveis de totalização da gerência regional
					if (!idGerenciaRegionalAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdGerenciaRegional())) {
						zerarGerenciaRegional = true;
					}
				}
				
				// Unidade Negocio
				String unidadeNegocio = "";

				if (contasEmRevisaoRelatorioHelper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = contasEmRevisaoRelatorioHelper
							.getIdUnidadeNegocio()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeUnidadeNegocio();

					// Caso tenha mudado a gerência regional do imóvel seta a
					// variável para true, para posteriormente zerar todas as
					// variáveis de totalização da gerência regional
					if (!idUnidadeNegocioAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdUnidadeNegocio())) {
						zerarUnidadeNegocio = true;
					}
				}
				

				// Elo
				String elo = "";

				if (contasEmRevisaoRelatorioHelper.getIdElo() != null) {
					elo = contasEmRevisaoRelatorioHelper.getIdElo() + " - "
							+ contasEmRevisaoRelatorioHelper.getNomeElo();

					// Caso tenha mudado o elo do imóvel seta a variável para
					// true, para posteriormente zerar todas as variáveis de
					// totalização do elo
					if (!idEloAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdElo())) {
						zerarElo = true;
					}
				}

				// Localidade
				String localidade = "";

				if (contasEmRevisaoRelatorioHelper.getIdLocalidade() != null) {
					localidade = contasEmRevisaoRelatorioHelper
							.getIdLocalidade()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeLocalidade();

					// Caso tenha mudado a localidade do imóvel seta a variável
					// para true, para posteriormente zerar todas as variáveis
					// de totalização da localidade
					if (!idLocalidadeAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdLocalidade())) {
						zerarLocalidade = true;
					}
				}
				
				// SetorComercial
				String setorComercial = "";

				if (contasEmRevisaoRelatorioHelper.getCodigoSetorComercial() != null) {
					setorComercial = contasEmRevisaoRelatorioHelper.getCodigoSetorComercial() + " - "
							+ contasEmRevisaoRelatorioHelper.getNomeSetorComercial();

					// Caso tenha mudado o setorComercial do imóvel seta a variável para
					// true, para posteriormente zerar todas as variáveis de
					// totalização do setorComercial
					if (!idSetorComercialAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getCodigoSetorComercial())) {
						zerarSetorComercial = true;
					}
				}
				
				// Zera os totalizadores da setor Comercial
				if (zerarSetorComercial) {
					qtdeContasTotalSetorComercial = new Integer("0");
					valorContasTotalSetorComercial = new BigDecimal("0.00");
					qtdeContasTotalFaixa1SetorComercial = new Integer("0");
					valorContasTotalFaixa1SetorComercial = new BigDecimal("0.00");
					qtdeContasTotalFaixa2SetorComercial = new Integer("0");
					valorContasTotalFaixa2SetorComercial = new BigDecimal("0.00");
					qtdeContasTotalFaixa3SetorComercial = new Integer("0");
					valorContasTotalFaixa3SetorComercial = new BigDecimal("0.00");
					qtdeContasTotalFaixa4SetorComercial = new Integer("0");
					valorContasTotalFaixa4SetorComercial = new BigDecimal("0.00");

					zerarSetorComercial = false;
					idSetorComercialAnterior = contasEmRevisaoRelatorioHelper
							.getCodigoSetorComercial();
				}

				// Zera os totalizadores da localidade
				if (zerarLocalidade) {
					qtdeContasTotalLocalidade = new Integer("0");
					valorContasTotalLocalidade = new BigDecimal("0.00");
					qtdeContasTotalFaixa1Localidade = new Integer("0");
					valorContasTotalFaixa1Localidade = new BigDecimal("0.00");
					qtdeContasTotalFaixa2Localidade = new Integer("0");
					valorContasTotalFaixa2Localidade = new BigDecimal("0.00");
					qtdeContasTotalFaixa3Localidade = new Integer("0");
					valorContasTotalFaixa3Localidade = new BigDecimal("0.00");
					qtdeContasTotalFaixa4Localidade = new Integer("0");
					valorContasTotalFaixa4Localidade = new BigDecimal("0.00");

					zerarLocalidade = false;
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper
							.getIdLocalidade();
				}

				// Zera os totalizadores do elo
				if (zerarElo) {
					qtdeContasTotalElo = new Integer("0");
					valorContasTotalElo = new BigDecimal("0.00");
					qtdeContasTotalFaixa1Elo = new Integer("0");
					valorContasTotalFaixa1Elo = new BigDecimal("0.00");
					qtdeContasTotalFaixa2Elo = new Integer("0");
					valorContasTotalFaixa2Elo = new BigDecimal("0.00");
					qtdeContasTotalFaixa3Elo = new Integer("0");
					valorContasTotalFaixa3Elo = new BigDecimal("0.00");
					qtdeContasTotalFaixa4Elo = new Integer("0");
					valorContasTotalFaixa4Elo = new BigDecimal("0.00");

					zerarElo = false;
					idEloAnterior = contasEmRevisaoRelatorioHelper
							.getIdElo();
				}
				
				// Zera os totalizadores do unidadeNegocio
				if (zerarUnidadeNegocio) {
					qtdeContasTotalUnidadeNegocio = new Integer("0");
					valorContasTotalUnidadeNegocio = new BigDecimal("0.00");
					qtdeContasTotalFaixa1UnidadeNegocio = new Integer("0");
					valorContasTotalFaixa1UnidadeNegocio = new BigDecimal("0.00");
					qtdeContasTotalFaixa2UnidadeNegocio = new Integer("0");
					valorContasTotalFaixa2UnidadeNegocio = new BigDecimal("0.00");
					qtdeContasTotalFaixa3UnidadeNegocio = new Integer("0");
					valorContasTotalFaixa3UnidadeNegocio = new BigDecimal("0.00");
					qtdeContasTotalFaixa4UnidadeNegocio = new Integer("0");
					valorContasTotalFaixa4UnidadeNegocio = new BigDecimal("0.00");

					zerarUnidadeNegocio = false;
					idUnidadeNegocioAnterior = contasEmRevisaoRelatorioHelper
							.getIdUnidadeNegocio();
				}

				// Zera os totalizadores da gerência regional
				if (zerarGerenciaRegional) {
					qtdeContasTotalGerenciaRegional = new Integer("0");
					valorContasTotalGerenciaRegional = new BigDecimal("0.00");
					qtdeContasTotalFaixa1GerenciaRegional = new Integer("0");
					valorContasTotalFaixa1GerenciaRegional = new BigDecimal("0.00");
					qtdeContasTotalFaixa2GerenciaRegional = new Integer("0");
					valorContasTotalFaixa2GerenciaRegional = new BigDecimal("0.00");
					qtdeContasTotalFaixa3GerenciaRegional = new Integer("0");
					valorContasTotalFaixa3GerenciaRegional = new BigDecimal("0.00");
					qtdeContasTotalFaixa4GerenciaRegional = new Integer("0");
					valorContasTotalFaixa4GerenciaRegional = new BigDecimal("0.00");

					zerarGerenciaRegional = false;
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional();
				}

				// Inscrição
				String inscricao = contasEmRevisaoRelatorioHelper.getInscricaoFormatada();
				
				// Imóvel
				String idImovel = "";

				if (contasEmRevisaoRelatorioHelper.getIdImovel() != null) {
					idImovel = contasEmRevisaoRelatorioHelper.getIdImovel()
							.toString();
				}
				
				// Nome do Usuário
				String nomeUsuario = "";

				if (contasEmRevisaoRelatorioHelper.getNomeUsuario() != null) {
					nomeUsuario = contasEmRevisaoRelatorioHelper
							.getNomeUsuario();
				}
				
				// Telefone
				String telefone = "";
				
				if (contasEmRevisaoRelatorioHelper.getTelefone() != null
						&& !contasEmRevisaoRelatorioHelper.getTelefone().trim()
								.equals("")) {
					telefone = contasEmRevisaoRelatorioHelper.getDddTelefone();
				}
				
				// Mês/Ano da Fatura
				String mesAnoFatura = "";
				
				if (contasEmRevisaoRelatorioHelper.getAnoMesReferenciaConta() != null) {
					mesAnoFatura = Util
							.formatarMesAnoReferencia(contasEmRevisaoRelatorioHelper
									.getAnoMesReferenciaConta());
				}
				
				// Data da Reclamação
				String dataReclamacao = "";
				
				if (contasEmRevisaoRelatorioHelper.getDataRevisao() != null) {
					dataReclamacao = Util
							.formatarData(contasEmRevisaoRelatorioHelper
									.getDataRevisao());
				}

				// Valor da Conta
				String valorConta = "";

				if (contasEmRevisaoRelatorioHelper.getValorConta() != null) {
					valorConta = Util.formatarMoedaReal(contasEmRevisaoRelatorioHelper.getValorConta());

					// Soma os valores aos totalizadores de cada grupo
					qtdeContasTotalSetorComercial = qtdeContasTotalSetorComercial + 1;
					valorContasTotalSetorComercial = valorContasTotalSetorComercial.add(contasEmRevisaoRelatorioHelper.getValorConta());
					
					qtdeContasTotalLocalidade = qtdeContasTotalLocalidade + 1;
					valorContasTotalLocalidade = valorContasTotalLocalidade.add(contasEmRevisaoRelatorioHelper.getValorConta());
					
					qtdeContasTotalElo = qtdeContasTotalElo + 1;
					valorContasTotalElo = valorContasTotalElo.add(contasEmRevisaoRelatorioHelper.getValorConta());
					
					qtdeContasTotalUnidadeNegocio = qtdeContasTotalUnidadeNegocio + 1;
					valorContasTotalUnidadeNegocio = valorContasTotalUnidadeNegocio.add(contasEmRevisaoRelatorioHelper.getValorConta());
					
					qtdeContasTotalGerenciaRegional = qtdeContasTotalGerenciaRegional + 1;
					valorContasTotalGerenciaRegional = valorContasTotalGerenciaRegional.add(contasEmRevisaoRelatorioHelper.getValorConta());
					
					qtdeContasTotalGeral = qtdeContasTotalGeral + 1;
					valorContasTotalGeral = valorContasTotalGeral.add(contasEmRevisaoRelatorioHelper.getValorConta());
				}
				
				// Motivo da Reclamação
				String motivoReclamacao = "";

				if (contasEmRevisaoRelatorioHelper.getIdMotivoRevisao() != null) {
					motivoReclamacao = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getDescricaoMotivoRevisao();
				}
				
				// Total Faixas
				String totalFaixasSetorComercial = "";
				String totalFaixasLocalidade = "";
				String totalFaixasElo = "";
				String totalFaixasUnidadeNegocio = "";
				String totalFaixasGerenciaRegional = "";
				String totalFaixasGeral = "";

				if (colecaoContaRevisaoFaixaValor != null
						&& !colecaoContaRevisaoFaixaValor.isEmpty()) {
					Iterator colecaoContaRevisaoFaixaValorIterator = colecaoContaRevisaoFaixaValor
							.iterator();
					int i = 1;

					while (colecaoContaRevisaoFaixaValorIterator.hasNext()) {

						ContaRevisaoFaixaValor contaRevisaoFaixaValor = (ContaRevisaoFaixaValor) colecaoContaRevisaoFaixaValorIterator
								.next();

						// Se for a primeira faixa
						if (i == 1) {

							// Verifica se o valor da conta está no intervalo da
							// faixa
							if (contasEmRevisaoRelatorioHelper.getValorConta() != null
									&& contaRevisaoFaixaValor
											.getValorFaixaInicio().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) <= 0
									&& contaRevisaoFaixaValor
											.getValorFaixaFim().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) >= 0) {
								
								qtdeContasTotalFaixa1SetorComercial = qtdeContasTotalFaixa1SetorComercial + 1;
								valorContasTotalFaixa1SetorComercial = valorContasTotalFaixa1SetorComercial
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa1Localidade = qtdeContasTotalFaixa1Localidade + 1;
								valorContasTotalFaixa1Localidade = valorContasTotalFaixa1Localidade
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa1Elo = qtdeContasTotalFaixa1Elo + 1;
								valorContasTotalFaixa1Elo = valorContasTotalFaixa1Elo
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());
								
								qtdeContasTotalFaixa1UnidadeNegocio = qtdeContasTotalFaixa1UnidadeNegocio + 1;
								valorContasTotalFaixa1UnidadeNegocio = valorContasTotalFaixa1UnidadeNegocio
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa1GerenciaRegional = qtdeContasTotalFaixa1GerenciaRegional + 1;
								valorContasTotalFaixa1GerenciaRegional = valorContasTotalFaixa1GerenciaRegional
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa1Geral = qtdeContasTotalFaixa1Geral + 1;
								valorContasTotalFaixa1Geral = valorContasTotalFaixa1Geral
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

							}
							// SetorComercial
							if (qtdeContasTotalFaixa1SetorComercial != null) {
								totalFaixasSetorComercial = totalFaixasSetorComercial
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1SetorComercial
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1SetorComercial);
							}
							// Localidade
							if (qtdeContasTotalFaixa1Localidade != null) {
								totalFaixasLocalidade = totalFaixasLocalidade
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1Localidade
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1Localidade);
							}
							// Elo
							if (qtdeContasTotalFaixa1Elo != null) {
								totalFaixasElo = totalFaixasElo
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1Elo.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1Elo);
							}
							// Unidade Negocio
							if (qtdeContasTotalFaixa1UnidadeNegocio != null) {
								totalFaixasUnidadeNegocio = totalFaixasUnidadeNegocio
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1UnidadeNegocio
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1UnidadeNegocio);
							}
							// Gerência Regional
							if (qtdeContasTotalFaixa1GerenciaRegional != null) {
								totalFaixasGerenciaRegional = totalFaixasGerenciaRegional
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1GerenciaRegional
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1GerenciaRegional);
							}
							// Geral
							if (qtdeContasTotalFaixa1Geral != null) {
								totalFaixasGeral = totalFaixasGeral
										+ "  "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa1Geral.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa1Geral);
							}
						}
						// Se for a segunda faixa
						else if (i == 2) {
							 
							// Verifica se o valor da conta está no intervalo da faixa
							if (contasEmRevisaoRelatorioHelper.getValorConta() != null
									&& contaRevisaoFaixaValor
											.getValorFaixaInicio().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) <= 0
									&& contaRevisaoFaixaValor
											.getValorFaixaFim().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) >= 0) {
								
								qtdeContasTotalFaixa2SetorComercial = qtdeContasTotalFaixa2SetorComercial + 1;
								valorContasTotalFaixa2SetorComercial = valorContasTotalFaixa2SetorComercial
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa2Localidade = qtdeContasTotalFaixa2Localidade + 1;
								valorContasTotalFaixa2Localidade = valorContasTotalFaixa2Localidade
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa2Elo = qtdeContasTotalFaixa2Elo + 1;
								valorContasTotalFaixa2Elo = valorContasTotalFaixa2Elo
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());
								
								qtdeContasTotalFaixa2UnidadeNegocio = qtdeContasTotalFaixa2UnidadeNegocio + 1;
								valorContasTotalFaixa2UnidadeNegocio = valorContasTotalFaixa2UnidadeNegocio
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa2GerenciaRegional = qtdeContasTotalFaixa2GerenciaRegional + 1;
								valorContasTotalFaixa2GerenciaRegional = valorContasTotalFaixa2GerenciaRegional
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa2Geral = qtdeContasTotalFaixa2Geral + 1;
								valorContasTotalFaixa2Geral = valorContasTotalFaixa2Geral
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

							}
							
							// SetorComercial
							if (qtdeContasTotalFaixa2SetorComercial != null) {
								totalFaixasSetorComercial = totalFaixasSetorComercial
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2SetorComercial
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2SetorComercial);
							}
							
							// Localidade
							if (qtdeContasTotalFaixa2Localidade != null) {
								totalFaixasLocalidade = totalFaixasLocalidade
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2Localidade
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2Localidade);
							}
							// Elo
							if (qtdeContasTotalFaixa2Elo != null) {
								totalFaixasElo = totalFaixasElo
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2Elo.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2Elo);
							}
							// Unidade Negocio
							if (qtdeContasTotalFaixa2UnidadeNegocio != null) {
								totalFaixasUnidadeNegocio = totalFaixasUnidadeNegocio
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2UnidadeNegocio
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2UnidadeNegocio);
							}
							// Gerência Regional
							if (qtdeContasTotalFaixa2GerenciaRegional != null) {
								totalFaixasGerenciaRegional = totalFaixasGerenciaRegional
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2GerenciaRegional
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2GerenciaRegional);
							}
							// Geral
							if (qtdeContasTotalFaixa2Geral != null) {
								totalFaixasGeral = totalFaixasGeral
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa2Geral.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa2Geral);
							}
						}
						// Se for a terceira faixa
						else if (i == 3) {
							
							// Verifica se o valor da conta está no intervalo da faixa
							if (contasEmRevisaoRelatorioHelper.getValorConta() != null
									&& contaRevisaoFaixaValor
											.getValorFaixaInicio().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) <= 0
									&& contaRevisaoFaixaValor
											.getValorFaixaFim().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) >= 0) {
								
								qtdeContasTotalFaixa3SetorComercial = qtdeContasTotalFaixa3SetorComercial + 1;
								valorContasTotalFaixa3SetorComercial = valorContasTotalFaixa3SetorComercial
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa3Localidade = qtdeContasTotalFaixa3Localidade + 1;
								valorContasTotalFaixa3Localidade = valorContasTotalFaixa3Localidade
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa3Elo = qtdeContasTotalFaixa3Elo + 1;
								valorContasTotalFaixa3Elo = valorContasTotalFaixa3Elo
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());
								
								qtdeContasTotalFaixa3UnidadeNegocio = qtdeContasTotalFaixa3UnidadeNegocio + 1;
								valorContasTotalFaixa3UnidadeNegocio = valorContasTotalFaixa3UnidadeNegocio
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa3GerenciaRegional = qtdeContasTotalFaixa3GerenciaRegional + 1;
								valorContasTotalFaixa3GerenciaRegional = valorContasTotalFaixa3GerenciaRegional
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa3Geral = qtdeContasTotalFaixa3Geral + 1;
								valorContasTotalFaixa3Geral = valorContasTotalFaixa3Geral
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

							}
							
							// SetorComercial
							if (qtdeContasTotalFaixa3SetorComercial != null) {
								totalFaixasSetorComercial = totalFaixasSetorComercial
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3SetorComercial
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3SetorComercial);
							}
							// Localidade
							if (qtdeContasTotalFaixa3Localidade != null) {
								totalFaixasLocalidade = totalFaixasLocalidade
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3Localidade
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3Localidade);
							}
							// Elo
							if (qtdeContasTotalFaixa3Elo != null) {
								totalFaixasElo = totalFaixasElo
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3Elo.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3Elo);
							}
							// Unidade Negocio
							if (qtdeContasTotalFaixa3UnidadeNegocio != null) {
								totalFaixasUnidadeNegocio = totalFaixasUnidadeNegocio
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3UnidadeNegocio
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3UnidadeNegocio);
							}
							// Gerência Regional
							if (qtdeContasTotalFaixa3GerenciaRegional != null) {
								totalFaixasGerenciaRegional = totalFaixasGerenciaRegional
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3GerenciaRegional
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3GerenciaRegional);
							}
							// Geral
							if (qtdeContasTotalFaixa3Geral != null) {
								totalFaixasGeral = totalFaixasGeral
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A       "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa3Geral.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa3Geral);
							}
						} 						
						// Se for a quarta faixa
						else if (i == 4) {
							
							// Verifica se o valor da conta está no intervalo da faixa
							if (contasEmRevisaoRelatorioHelper.getValorConta() != null
									&& contaRevisaoFaixaValor
											.getValorFaixaInicio().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) <= 0
									&& contaRevisaoFaixaValor
											.getValorFaixaFim().compareTo(
													contasEmRevisaoRelatorioHelper
															.getValorConta()) >= 0) {
								
								qtdeContasTotalFaixa4SetorComercial = qtdeContasTotalFaixa4SetorComercial + 1;
								valorContasTotalFaixa4SetorComercial = valorContasTotalFaixa4SetorComercial
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa4Localidade = qtdeContasTotalFaixa4Localidade + 1;
								valorContasTotalFaixa4Localidade = valorContasTotalFaixa4Localidade
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa4Elo = qtdeContasTotalFaixa4Elo + 1;
								valorContasTotalFaixa4Elo = valorContasTotalFaixa4Elo
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());
								
								qtdeContasTotalFaixa4UnidadeNegocio = qtdeContasTotalFaixa4UnidadeNegocio + 1;
								valorContasTotalFaixa4UnidadeNegocio = valorContasTotalFaixa4UnidadeNegocio
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa4GerenciaRegional = qtdeContasTotalFaixa4GerenciaRegional + 1;
								valorContasTotalFaixa4GerenciaRegional = valorContasTotalFaixa4GerenciaRegional
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

								qtdeContasTotalFaixa4Geral = qtdeContasTotalFaixa4Geral + 1;
								valorContasTotalFaixa4Geral = valorContasTotalFaixa4Geral
										.add(contasEmRevisaoRelatorioHelper
												.getValorConta());

							}
							// SetorComercial
							if (qtdeContasTotalFaixa4SetorComercial != null) {
								totalFaixasSetorComercial = totalFaixasSetorComercial
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4SetorComercial
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4SetorComercial);
							}
							// Localidade
							if (qtdeContasTotalFaixa4Localidade != null) {
								totalFaixasLocalidade = totalFaixasLocalidade
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4Localidade
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4Localidade);
							}
							// Elo
							if (qtdeContasTotalFaixa4Elo != null) {
								totalFaixasElo = totalFaixasElo
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4Elo.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4Elo);
							}
							// UnidadeNegocio
							if (qtdeContasTotalFaixa4UnidadeNegocio != null) {
								totalFaixasUnidadeNegocio = totalFaixasUnidadeNegocio
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4UnidadeNegocio
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4UnidadeNegocio);
							}
							// Gerência Regional
							if (qtdeContasTotalFaixa4GerenciaRegional != null) {
								totalFaixasGerenciaRegional = totalFaixasGerenciaRegional
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4GerenciaRegional
												.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4GerenciaRegional);
							}
							// Geral
							if (qtdeContasTotalFaixa4Geral != null) {
								totalFaixasGeral = totalFaixasGeral
										+ " \n"
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaInicio())
										+ " A "
										+ Util
												.formatarMoedaReal(contaRevisaoFaixaValor
														.getValorFaixaFim())
										+ "    CONTAS "
										+ qtdeContasTotalFaixa4Geral.toString()
										+ "    VALOR  "
										+ Util
												.formatarMoedaReal(valorContasTotalFaixa4Geral);
							}
						} else {
							break;
						}

						i++;

					}
				}

				relatorioBean = new RelatorioContasEmRevisaoBean();
				relatorioBean.setGerenciaRegional(gerenciaRegional);
				relatorioBean.setUnidadeNegocio(unidadeNegocio);
				relatorioBean.setElo(elo);
				relatorioBean.setLocalidade(localidade);
				relatorioBean.setSetorComercial(setorComercial);
				
				relatorioBean.setInscricao(inscricao);
				relatorioBean.setImovel(idImovel);
				relatorioBean.setUsuario(nomeUsuario);
				relatorioBean.setTelefone(telefone);
				relatorioBean.setMesAnoFatura(mesAnoFatura);
				relatorioBean.setDataReclamacao(dataReclamacao);
				relatorioBean.setValorConta(valorConta);
				relatorioBean.setMotivoReclamacao(motivoReclamacao);
				
				relatorioBean.setQtdeTotalContasEmRevisaoSetorComercial(qtdeContasTotalSetorComercial.toString());
				relatorioBean.setValorTotalContasEmRevisaoSetorComercial(Util.formatarMoedaReal(valorContasTotalSetorComercial));
				relatorioBean.setTotaisPorFaixaSetorComercial(totalFaixasSetorComercial);
				
				relatorioBean.setQtdeTotalContasEmRevisaoLocalidade(qtdeContasTotalLocalidade.toString());
				relatorioBean.setValorTotalContasEmRevisaoLocalidade(Util.formatarMoedaReal(valorContasTotalLocalidade));
				relatorioBean.setTotaisPorFaixaLocalidade(totalFaixasLocalidade);
				
				relatorioBean.setQtdeTotalContasEmRevisaoElo(qtdeContasTotalElo.toString());
				relatorioBean.setValorTotalContasEmRevisaoElo(Util.formatarMoedaReal(valorContasTotalElo));
				relatorioBean.setTotaisPorFaixaElo(totalFaixasElo);
				
				relatorioBean.setQtdeTotalContasEmRevisaoUnidadeNegocio(qtdeContasTotalUnidadeNegocio.toString());
				relatorioBean.setValorTotalContasEmRevisaoUnidadeNegocio(Util.formatarMoedaReal(valorContasTotalUnidadeNegocio));
				relatorioBean.setTotaisPorFaixaUnidadeNegocio(totalFaixasUnidadeNegocio);
				
				relatorioBean.setQtdeTotalContasEmRevisaoGerenciaRegional(qtdeContasTotalGerenciaRegional.toString());
				relatorioBean.setValorTotalContasEmRevisaoGerenciaRegional(Util.formatarMoedaReal(valorContasTotalGerenciaRegional));
				relatorioBean.setTotaisPorFaixaGerenciaRegional(totalFaixasGerenciaRegional);
				
				relatorioBean.setQtdeTotalContasEmRevisaoGeral(qtdeContasTotalGeral.toString());
				relatorioBean.setValorTotalContasEmRevisaoGeral(Util.formatarMoedaReal(valorContasTotalGeral));
				relatorioBean.setTotaisPorFaixaGeral(totalFaixasGeral);
				
				relatorioBean.setImprimeLocalidade(imprimeLocalidade);
				relatorioBean.setImprimeElo(imprimeElo);
				relatorioBean.setImprimeGerenciaRegional(imprimeUnidadeNegocio);
				relatorioBean.setImprimeGerenciaRegional(imprimeGerenciaRegional);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		Integer anoMes = sistemaParametro.getAnoMesFaturamento();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
		
		parametros.put("tipoFormatoRelatorio", "R0636");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.CONTAS_EM_REVISAO,
					idFuncionalidadeIniciada);
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasEmRevisao",
				this);
	}
}