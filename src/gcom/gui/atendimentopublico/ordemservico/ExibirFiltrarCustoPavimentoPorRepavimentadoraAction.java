/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 21/12/2010
 */
public class ExibirFiltrarCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarCustoPavimentoPorRepavimentadora");

		FiltrarCustoPavimentoPorRepavimentadoraActionForm form = (FiltrarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.reset();
			form.setAtualizar("1");
			
			if(sessao.getAttribute("colecaoUnidadeRepavimentadora") == null){
				this.pesquisarUnidadeRepavimentadora(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoRua") == null){
				this.pesquisarTipoPavimentoRua(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoCalcada") == null){
				this.pesquisarTipoPavimentoCalcada(sessao);
			}
		}

		return retorno;
	}

	/**
	 * Pesquisar Unidade Repavimentadora 
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarUnidadeRepavimentadora(HttpSession sessao) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R"));
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
			
			sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Repavimentadora");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Rua  
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarTipoPavimentoRua(HttpSession sessao) {

		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(
				FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoRua.setConsultaSemLimites(true);
		
		filtroPavimentoRua.setCampoOrderBy(FiltroPavimentoRua.DESCRICAO);
		
		Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
				filtroPavimentoRua, PavimentoRua.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoRua)){
			
			sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Pavimento Rua");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Calçada
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarTipoPavimentoCalcada(HttpSession sessao) {

		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
				FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoCalcada.setConsultaSemLimites(true);
		
		filtroPavimentoCalcada.setCampoOrderBy(FiltroPavimentoCalcada.DESCRICAO);
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
				filtroPavimentoCalcada, PavimentoCalcada.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoCalcada)){
			
			sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		}
	}
	
}