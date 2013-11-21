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
package gcom.gui.faturamento.conta;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarMensagemContaAction");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		
		sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		FiltrarMensagemContaActionForm filtrarMensagemContaActionForm = (FiltrarMensagemContaActionForm) actionForm;
		
		String idLocalidade = filtrarMensagemContaActionForm.getLocalidade();
		String idGerenciaRegional = filtrarMensagemContaActionForm.getGerenciaRegional();
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			if (idGerenciaRegional != null && !"".equalsIgnoreCase(idGerenciaRegional)){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, idGerenciaRegional));
				filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			}
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				filtrarMensagemContaActionForm.setLocalidadeDescricao(localidade.getDescricao());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				filtrarMensagemContaActionForm.setLocalidade("");
				filtrarMensagemContaActionForm.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		String idSetorComercial = filtrarMensagemContaActionForm.getSetorComercial();
		
		if (idSetorComercial != null && !idSetorComercial.trim().equals("")){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
			
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetor != null && !colecaoSetor.isEmpty()) {
				SetorComercial setorComercial = (SetorComercial) colecaoSetor.iterator().next();
				filtrarMensagemContaActionForm.setSetorComercialDescricao(setorComercial.getDescricao());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				filtrarMensagemContaActionForm.setSetorComercialDescricao("");
				filtrarMensagemContaActionForm.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialInexistente", true);
			}
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		
		String idQuadra = filtrarMensagemContaActionForm.getQuadra();
		
		if (idQuadra != null && !idQuadra.trim().equals("")){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidade));
			filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, idSetorComercial));
			filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, idQuadra));
			
			
			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
				Quadra quadra = (Quadra) colecaoQuadra.iterator().next();
				filtrarMensagemContaActionForm.setQuadra(quadra.getDescricao());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				filtrarMensagemContaActionForm.setQuadra("");
				httpServletRequest.setAttribute("quadraInexistente", true);
			}
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		httpServletRequest.removeAttribute("i");
		
		String atualizar = httpServletRequest.getParameter("atualizar");
		String menu = httpServletRequest.getParameter("menu");
		
		if (atualizar == null && menu == null){
			boolean i = true;
			httpServletRequest.setAttribute("i",i);
		}
		
		return retorno;

	}

}
