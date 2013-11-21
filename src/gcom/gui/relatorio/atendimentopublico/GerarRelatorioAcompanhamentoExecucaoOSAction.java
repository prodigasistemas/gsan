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
package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoExecucaoOSActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoExecucaoOS;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAcompanhamentoExecucaoOSAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioAcompanhamentoExecucaoOSActionForm form = (GerarRelatorioAcompanhamentoExecucaoOSActionForm) actionForm;

		// Recupera os valores do form para serem passados como parâmetros para
		// o RelatorioAcompanhamentoExecucaoOS e nele executar a pesquisa
		// solicitada pelo usuário
		String origemServico = form.getOrigemServico();
		String situacaoOS = form.getSituacaoOrdemServico();
		String[] idsServicosTipos = form.getTipoServicoSelecionados();
		String idUnidadeAtendimento = form.getIdUnidadeAtendimento();
		String idUnidadeAtual = form.getIdUnidadeAtual();
		String idUnidadeEncerramento = form.getIdUnidadeEncerramento();
		String periodoAtendimentoInicial = form.getPeriodoAtendimentoInicial();
		String periodoAtendimentoFinal = form.getPeriodoAtendimentoFinal();
		String periodoEncerramentoInicial = form.getPeriodoEncerramentoInicial();
		String periodoEncerramentoFinal = form.getPeriodoEncerramentoFinal();
		String idEquipeProgramacao = form.getIdEquipeProgramacao();
		String idEquipeExecucao = form.getIdEquipeExecucao();
		String tipoOrdenacao = form.getTipoOrdenacao();
		
		if(periodoAtendimentoInicial != null && !periodoAtendimentoInicial.equals("")){
			if(periodoAtendimentoFinal==null || periodoAtendimentoFinal.equals("")){
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"atendimento");
			}/*else{
				Date ini = Util.converteStringParaDate(periodoAtendimentoInicial);
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(ini);
				Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(periodoAtendimentoFinal),numeroDias); 
				if(dataLimite.after(ini)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
				}
			}*/
		}
		
		if(periodoEncerramentoInicial != null && !periodoEncerramentoInicial.equals("")){
			if(periodoEncerramentoFinal==null || periodoEncerramentoFinal.equals("")){
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"encerramento");
			}else{
				Date ini = Util.converteStringParaDate(periodoEncerramentoInicial);
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(ini);
				Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(periodoEncerramentoFinal),numeroDias); 
				if(dataLimite.after(ini)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
				}
			}
		}

		// Formata as datas para Date
		Date periodoAtendimentoInicialFormatado = null;

		if (periodoAtendimentoInicial != null
				&& !periodoAtendimentoInicial.trim().equals("")) {
			periodoAtendimentoInicialFormatado = Util
					.converteStringParaDate(periodoAtendimentoInicial);
		}

		Date periodoAtendimentoFinalFormatado = null;

		if (periodoAtendimentoFinal != null
				&& !periodoAtendimentoFinal.trim().equals("")) {
			periodoAtendimentoFinalFormatado = Util
					.converteStringParaDate(periodoAtendimentoFinal);
		}

		Date periodoEncerramentoInicialFormatado = null;

		if (periodoEncerramentoInicial != null
				&& !periodoEncerramentoInicial.trim().equals("")) {
			periodoEncerramentoInicialFormatado = Util
					.converteStringParaDate(periodoEncerramentoInicial);
		}

		Date periodoEncerramentoFinalFormatado = null;

		if (periodoEncerramentoFinal != null
				&& !periodoEncerramentoFinal.trim().equals("")) {
			periodoEncerramentoFinalFormatado = Util
					.converteStringParaDate(periodoEncerramentoFinal);
		}

		validarGeracaoRelatorio(origemServico, situacaoOS, idsServicosTipos,
				idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento,
				periodoAtendimentoInicialFormatado,
				periodoAtendimentoFinalFormatado,
				periodoEncerramentoInicialFormatado,
				periodoEncerramentoFinalFormatado, idEquipeProgramacao,
				idEquipeExecucao);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioAcompanhamentoExecucaoOS relatorioAcompanhamentoExecucaoOS = new RelatorioAcompanhamentoExecucaoOS(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioAcompanhamentoExecucaoOS.addParametro("origemServico",
				origemServico);
		relatorioAcompanhamentoExecucaoOS
				.addParametro("situacaoOS", situacaoOS);
		relatorioAcompanhamentoExecucaoOS.addParametro("idsServicosTipos",
				idsServicosTipos);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeAtendimento",
				idUnidadeAtendimento);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeAtual",
				idUnidadeAtual);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeEncerramento",
				idUnidadeEncerramento);
		relatorioAcompanhamentoExecucaoOS
				.addParametro("periodoAtendimentoInicial",
						periodoAtendimentoInicialFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoAtendimentoFinal", periodoAtendimentoFinalFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoEncerramentoInicial",
				periodoEncerramentoInicialFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoEncerramentoFinal", periodoEncerramentoFinalFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro("idEquipeProgramacao",
				idEquipeProgramacao);
		relatorioAcompanhamentoExecucaoOS.addParametro("idEquipeExecucao",
				idEquipeExecucao);
		relatorioAcompanhamentoExecucaoOS.addParametro("tipoOrdenacao",
				tipoOrdenacao);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAcompanhamentoExecucaoOS.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioAcompanhamentoExecucaoOS,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;

	}

	private void validarGeracaoRelatorio(String origemServico,
			String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = null;
		Collection colecaoUnidade = null;
		
		//[FS0006]
//		if((idUnidadeAtendimento == null || idUnidadeAtendimento.equals(""))
//				&& (idUnidadeAtual == null || idUnidadeAtual.equals(""))){
//			throw new ActionServletException(
//					"atencao.sem.unidade.atendimento.nem.atual", null);
//		}

		// Verifica se a unidade de atendimento existe
		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.equals("")) {

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							idUnidadeAtendimento));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Atendimento");
			}

		}

		// Verifica se a unidade atual existe
		if (idUnidadeAtual != null && !idUnidadeAtual.equals("")) {

			colecaoUnidade = null;

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidadeAtual));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Atual");
			}

		}

		// Verifica se a unidade de encerramento existe
		if (idUnidadeEncerramento != null && !idUnidadeEncerramento.equals("")) {

			colecaoUnidade = null;

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							idUnidadeEncerramento));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Encerramento");
			}

		}

		FiltroEquipe filtroEquipe = null;
		Collection colecaoEquipe = null;

		// Verifica se a equipe de programação existe
		if (idEquipeProgramacao != null && !idEquipeProgramacao.equals("")) {

			filtroEquipe = new FiltroEquipe();
			filtroEquipe.adicionarParametro(new ParametroSimples(
					FiltroEquipe.ID, idEquipeProgramacao));

			colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class
					.getName());

			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Equipe de Programação");
			}

		}

		// Verifica se a equipe de execução existe
		if (idEquipeExecucao != null && !idEquipeExecucao.equals("")) {

			colecaoEquipe = null;

			filtroEquipe = new FiltroEquipe();
			filtroEquipe.adicionarParametro(new ParametroSimples(
					FiltroEquipe.ID, idEquipeExecucao));

			colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class
					.getName());

			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Equipe de Execução");
			}

		}

		// Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada
				.pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
						origemServico, situacaoOS, idsServicosTipos,
						idUnidadeAtendimento, idUnidadeAtual,
						idUnidadeEncerramento, periodoAtendimentoInicial,
						periodoAtendimentoFinal, periodoEncerramentoInicial,
						periodoEncerramentoFinal, idEquipeProgramacao,
						idEquipeExecucao);
		
		if (qtdeResultados == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

	}
}
