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
package gcom.gui.relatorio.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioManterCronogramaFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

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

public class GerarRelatorioCronogramaFaturamentoManterAction extends
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

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

		// Inicio da parte que vai mandar os parametros para o relatório

		FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensalParametros = new FaturamentoGrupoCronogramaMensal();

		String idGrupoFaturamento = (String) pesquisarActionForm
				.get("idGrupoFaturamento");

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		if (idGrupoFaturamento != null
				&& !idGrupoFaturamento.equals("")
				&& !idGrupoFaturamento.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idGrupoFaturamento));

			Collection colecaoGruposFaturamentos = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			if (colecaoGruposFaturamentos != null
					&& !colecaoGruposFaturamentos.isEmpty()) {

				filtroFaturamentoAtividadeCronograma
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
								idGrupoFaturamento));

				Iterator colecaoGruposFaturamentosIterator = colecaoGruposFaturamentos
						.iterator();

				faturamentoGrupo = (FaturamentoGrupo) colecaoGruposFaturamentosIterator
						.next();

			}
		}

		Integer anoMes = null;

		String mesAnoPesquisar = (String) pesquisarActionForm.get("mesAno");

		if (mesAnoPesquisar != null && !mesAnoPesquisar.equals("")) {

			anoMes = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(mesAnoPesquisar));

			filtroFaturamentoAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
							anoMes));
		}

		// seta os parametros que serão mostrados no relatório

		if (faturamentoGrupo != null) {
			faturamentoGrupoCronogramaMensalParametros
				.setFaturamentoGrupo(faturamentoGrupo);
		}
		
		if (anoMes != null) {
			faturamentoGrupoCronogramaMensalParametros.setAnoMesReferencia(anoMes);
		}

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterCronogramaFaturamento relatorioManterCronogramaFaturamento = new RelatorioManterCronogramaFaturamento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCronogramaFaturamento.addParametro(
				"filtroFaturamentoAtividadeCronograma",
				filtroFaturamentoAtividadeCronograma);
		relatorioManterCronogramaFaturamento.addParametro(
				"faturamentoGrupoCronogramaMensalParametros",
				faturamentoGrupoCronogramaMensalParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCronogramaFaturamento.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterCronogramaFaturamento, tipoRelatorio,
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