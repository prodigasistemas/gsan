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
package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.cobranca.CriterioCobrancaFiltrarActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterCriterioCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioCriterioCobrancaManterAction extends
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

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroCobrancaCriterio filtroCobrancaCriterio = (FiltroCobrancaCriterio) sessao
				.getAttribute("filtroCobrancaCriterio");

		// Inicio da parte que vai mandar os parametros para o relatório

		CobrancaCriterio cobrancaCriterioParametros = new CobrancaCriterio();

		// Descrição

		String descricao = "";

		String descricaoPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getDescricaoCriterio();

		if (descricaoPesquisar != null && !descricaoPesquisar.equals("")) {

			descricao = descricaoPesquisar;

		}

		// Data de Início da Vigência

		Date dataInicio = null;

		String dataInicioPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getDataInicioVigencia();

		if (dataInicioPesquisar != null && !dataInicioPesquisar.equals("")) {

			dataInicio = Util.converteStringParaDate(dataInicioPesquisar);

		}

		// Número de Anos para Determinar Conta Antiga

		Short numeroAnos = null;

		String numeroAnosPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getNumeroAnoContaAntiga();

		if (numeroAnosPesquisar != null && !numeroAnosPesquisar.equals("")) {

			numeroAnos = new Short(numeroAnosPesquisar);

		}

		// Imóvel com Situação Especial de Cobrança

		Short situacaoEspecialCobranca = null;

		String situacaoEspecialCobrancaPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSitEspecial();

		if (situacaoEspecialCobrancaPesquisar != null
				&& !situacaoEspecialCobrancaPesquisar.equals("")
				&& !situacaoEspecialCobrancaPesquisar.equals("3")) {

			situacaoEspecialCobranca = new Short(
					situacaoEspecialCobrancaPesquisar);

		}

		// Imóvel com Situação de Cobrança

		Short situacaoCobranca = null;

		String situacaoCobrancaPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSit();

		if (situacaoCobrancaPesquisar != null
				&& !situacaoCobrancaPesquisar.equals("")
				&& !situacaoCobrancaPesquisar.equals("3")) {

			situacaoCobranca = new Short(situacaoCobrancaPesquisar);

		}

		// Contas em Revisão

		Short contasRevisao = null;

		String contasRevisaoPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoContasRevisao();

		if (contasRevisaoPesquisar != null
				&& !contasRevisaoPesquisar.equals("")
				&& !contasRevisaoPesquisar.equals("3")) {

			contasRevisao = new Short(contasRevisaoPesquisar);

		}

		// Imóvel com Débito só da Conta do Mês

		Short imovelDebitoContaMes = null;

		String imovelDebitoContaMesPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoMesConta();

		if (imovelDebitoContaMesPesquisar != null
				&& !imovelDebitoContaMesPesquisar.equals("")
				&& !imovelDebitoContaMesPesquisar.equals("3")) {

			imovelDebitoContaMes = new Short(imovelDebitoContaMesPesquisar);

		}

		// Inquilino com Débito só da Conta do Mês Independente do Valor da Conta

		Short inquilinoDebitoContaMes = null;

		String inquilinoDebitoContaMesPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoInquilinoDebitoMesConta();

		if (inquilinoDebitoContaMesPesquisar != null
				&& !inquilinoDebitoContaMesPesquisar.equals("")
				&& !inquilinoDebitoContaMesPesquisar.equals("3")) {

			inquilinoDebitoContaMes = new Short(inquilinoDebitoContaMesPesquisar);

		}
		
		// Imóvel com Débito só de Contas Antigas

		Short imovelDebitoContasAntigas = null;

		String imovelDebitoContasAntigasPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoContasAntigas();

		if (imovelDebitoContasAntigasPesquisar != null
				&& !imovelDebitoContasAntigasPesquisar.equals("")
				&& !imovelDebitoContasAntigasPesquisar.equals("3")) {

			imovelDebitoContasAntigas = new Short(imovelDebitoContasAntigasPesquisar);

		}
		
		// Indicador de Uso

		Short indicadorUso = null;

		String indicadorUsoPesquisar = (String) criterioCobrancaFiltrarActionForm
				.getIndicadorUso();

		if (indicadorUsoPesquisar != null
				&& !indicadorUsoPesquisar.equals("")
				&& !indicadorUsoPesquisar.equals("3")) {

			indicadorUso = new Short(indicadorUsoPesquisar);

		}

		// seta os parametros que serão mostrados no relatório
		cobrancaCriterioParametros.setDescricaoCobrancaCriterio(descricao);
		cobrancaCriterioParametros.setDataInicioVigencia(dataInicio);
		cobrancaCriterioParametros.setNumeroContaAntiga(numeroAnos);
		cobrancaCriterioParametros.setIndicadorEmissaoImovelParalisacao(situacaoEspecialCobranca);
		cobrancaCriterioParametros.setIndicadorEmissaoImovelSituacaoCobranca(situacaoCobranca);
		cobrancaCriterioParametros.setIndicadorEmissaoContaRevisao(contasRevisao);
		cobrancaCriterioParametros.setIndicadorEmissaoDebitoContaMes(imovelDebitoContaMes);
		cobrancaCriterioParametros.setIndicadorEmissaoInquilinoDebitoContaMes(inquilinoDebitoContaMes);
		cobrancaCriterioParametros.setIndicadorEmissaoDebitoContaAntiga(imovelDebitoContasAntigas);
		cobrancaCriterioParametros.setIndicadorUso(indicadorUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterCriterioCobranca relatorioManterCriterioCobranca = new RelatorioManterCriterioCobranca(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCriterioCobranca.addParametro("filtroCobrancaCriterio",
				filtroCobrancaCriterio);
		relatorioManterCriterioCobranca.addParametro(
				"cobrancaCriterioParametros", cobrancaCriterioParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCriterioCobranca.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterCriterioCobranca, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
}