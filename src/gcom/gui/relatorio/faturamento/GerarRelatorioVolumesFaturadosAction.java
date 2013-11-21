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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.GerarRelatorioVolumesFaturadosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioVolumesFaturados;
import gcom.relatorio.faturamento.RelatorioVolumesFaturadosResumido;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class GerarRelatorioVolumesFaturadosAction extends
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

		GerarRelatorioVolumesFaturadosActionForm gerarRelatorioVolumesFaturadosActionForm = (GerarRelatorioVolumesFaturadosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		Localidade localidade = null;

		String idLocalidade = gerarRelatorioVolumesFaturadosActionForm
				.getIdLocalidade();

		if (idLocalidade != null && !idLocalidade.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		}

		Integer anoMes = null;
		String mesAnoDigitado = gerarRelatorioVolumesFaturadosActionForm
				.getMesAno();

		if (mesAnoDigitado != null && !mesAnoDigitado.equals("")) {
			anoMes = Util.formatarMesAnoComBarraParaAnoMes(mesAnoDigitado);
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		String opcaoRelatorio = gerarRelatorioVolumesFaturadosActionForm
				.getOpcaoRelatorio();

		if (opcaoRelatorio != null && opcaoRelatorio.equals("resumido")) {

			RelatorioVolumesFaturadosResumido relatorioVolumesFaturadosResumido = new RelatorioVolumesFaturadosResumido(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
			relatorioVolumesFaturadosResumido.addParametro("idLocalidade",
					localidade.getId());
			relatorioVolumesFaturadosResumido.addParametro("anoMes", anoMes);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioVolumesFaturadosResumido.addParametro(
					"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

			retorno = processarExibicaoRelatorio(
					relatorioVolumesFaturadosResumido, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} else {

			RelatorioVolumesFaturados relatorioVolumesFaturados = new RelatorioVolumesFaturados(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
			relatorioVolumesFaturados.addParametro("idLocalidade", localidade
					.getId());
			relatorioVolumesFaturados.addParametro("anoMes", anoMes);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioVolumesFaturados.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));

			retorno = processarExibicaoRelatorio(relatorioVolumesFaturados,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
