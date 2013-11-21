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
package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ExibirFiltrarExcecoesLeiturasConsumosAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarExcecoesLeiturasConsumosAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("colecaoIdsImovelTotal");
		sessao.removeAttribute("totalRegistros");
		sessao.removeAttribute("numeroPaginasPesquisa");
		sessao.removeAttribute("filtroMedicaoHistoricoSql");
		sessao.removeAttribute("index");
		sessao.removeAttribute("indiceImovel");
		sessao.removeAttribute("imovelMicromedicaoDadosResumo");
		sessao.removeAttribute("imovelMicromedicaoCarregaMedicaoResumo");
		sessao.removeAttribute("medicaoHistoricoAnoMesAtual");
		sessao.removeAttribute("imovelMicromedicaoConsumo");
		sessao.removeAttribute("medicoesHistoricos");
		sessao.removeAttribute("imoveisMicromedicao");
		
		/*
		 * Colocado por Raphael Rossiter em 04/12/2007 - Analista: Claudio Lira
		 * OBJ: Não perder os registros selecionados na paginação
		 */
		sessao.removeAttribute("idsImoveisJaSelecionados");
		
		//joga caminho do foward na sessao(vem do menu) para saber pra q caso d euso retornar
		if(httpServletRequest.getParameter("nomeCaminhoMapping") != null 
				&& !httpServletRequest.getParameter("nomeCaminhoMapping").trim().equalsIgnoreCase("")){
			sessao.setAttribute("nomeCaminhoMapping", httpServletRequest.getParameter("nomeCaminhoMapping"));
		}
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"filtrarExcecoesLeiturasConsumosWizardAction",
				"filtrarExcecoesLeiturasConsumosAction",
				"cancelarFiltrarExcecoesLeiturasConsumos","",
				"exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim&objetoConsulta=1");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "LocalizacaoA.gif", "LocalizacaoD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosLocalidade",
						"validarExcecoesLeiturasConsumosLocalizacao"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "CaracteristicaA.gif", "CaracteristicaD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosCaracteristicas",
						"validarExcecoesLeiturasConsumosCaracteristica"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "LigacoesConsumosA.gif", "LigacoesConsumosD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosAnormalidade",
						"validarExcecoesLeiturasConsumosAnormalidade"));
		
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
