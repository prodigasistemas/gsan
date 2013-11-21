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
package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarAnormalidadeLeituraAction extends GcomAction {
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
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarAnormalidadeLeituraActionForm filtrarAnormalidadeLeituraActionForm = (FiltrarAnormalidadeLeituraActionForm) actionForm;
		//	Código para checar ou não o ATUALIZAR
		
		/*if (httpServletRequest.getParameter("menu") != null) {
			filtrarAnormalidadeLeituraActionForm.setDescricao("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorRelativoHidrometro("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorImovelSemHidrometro("");
			filtrarAnormalidadeLeituraActionForm.setUsoRestritoSistema("");
			filtrarAnormalidadeLeituraActionForm.setPerdaTarifaSocial("");
			filtrarAnormalidadeLeituraActionForm.setOsAutomatico("");
			filtrarAnormalidadeLeituraActionForm.setTipoServico("");
			filtrarAnormalidadeLeituraActionForm
					.setConsumoLeituraNaoInformado("");
			filtrarAnormalidadeLeituraActionForm.setConsumoLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm.setLeituraLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm
					.setLeituraLeituraNaoturaInformado("");

		}
*/
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		if (filtrarAnormalidadeLeituraActionForm.getTipoServico() == null
				|| filtrarAnormalidadeLeituraActionForm.getTipoServico().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico.setCampoOrderBy(FiltroTipoServico.DESCRICAO);

			filtroTipoServico.adicionarParametro(new ParametroSimples(
					FiltroTipoServico.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna banco
			colecaoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tipo de Servico");
			} else {
				sessao.setAttribute("colecaoTipoServico", colecaoPesquisa);
			}

		} 

		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);

		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo",
				colecaoLeituraAnormalidadeConsumo);
		
		//coleção anormalidade leitura

		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura
				.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);

		Collection colecaoLeituraAnormalidadeLeitura = fachada.pesquisar(
				filtroLeituraAnormalidadeLeitura,
				LeituraAnormalidadeLeitura.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeLeitura",
				colecaoLeituraAnormalidadeLeitura);

		//	Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarAnormalidadeLeitura");

		return retorno;

	}
}
