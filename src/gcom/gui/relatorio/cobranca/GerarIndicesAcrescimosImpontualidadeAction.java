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

import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarIndicesAcrescimosImpontualidade;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarIndicesAcrescimosImpontualidadeAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarDadosParaLeitura");

		IndiceAcrescimosImpontualidadeRelatorioActionForm indiceAcrescimosImpontualidadeRelatorioActionForm = (IndiceAcrescimosImpontualidadeRelatorioActionForm) actionForm;

		Collection colecaoIndicesAcrescimosImpontualidade = this
				.gerarColecaoDadosParaLeituraHelper(indiceAcrescimosImpontualidadeRelatorioActionForm);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioGerarIndicesAcrescimosImpontualidade relatorio = new RelatorioGerarIndicesAcrescimosImpontualidade(
				this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("todosAcrescimos",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getTodosAcrecimos());

		relatorio.addParametro("mesAnoReferenciaInicial",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getMesAnoReferenciaInicial());

		relatorio.addParametro("mesAnoReferenciaFinal",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getMesAnoReferenciaFinal());

		relatorio.addParametro("colecaoIndicesAcrescimosImpontualidade",
				colecaoIndicesAcrescimosImpontualidade);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		return retorno;
	}

	private Collection gerarColecaoDadosParaLeituraHelper(
			IndiceAcrescimosImpontualidadeRelatorioActionForm indiceAcrescimosImpontualidadeRelatorioActionForm) {

		Collection retorno = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
		filtroIndicesAcrescimosImpontualidade
				.setCampoOrderBy(FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA);

		if (indiceAcrescimosImpontualidadeRelatorioActionForm
				.getTodosAcrecimos() != null
				&& indiceAcrescimosImpontualidadeRelatorioActionForm
						.getTodosAcrecimos().equals("2")) {
			Integer anoMesReferenciaInicial = Util
					.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeRelatorioActionForm
							.getMesAnoReferenciaInicial());
			Integer anoMesReferenciaFinal = Util
					.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeRelatorioActionForm
							.getMesAnoReferenciaFinal());

			filtroIndicesAcrescimosImpontualidade
					.adicionarParametro(new MaiorQue(
							FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
							anoMesReferenciaInicial));
			filtroIndicesAcrescimosImpontualidade
					.adicionarParametro(new MenorQue(
							FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
							anoMesReferenciaFinal));

		}

		retorno = fachada.pesquisar(filtroIndicesAcrescimosImpontualidade,
				IndicesAcrescimosImpontualidade.class.getName());
		if (Util.isVazioOrNulo(retorno)) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Índice Acréscimos Impontualidade");
		}

		return retorno;
	}
}