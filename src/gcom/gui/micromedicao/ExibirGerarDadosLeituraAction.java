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
package gcom.gui.micromedicao;

import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
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
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 28/06/2008
 */
public class ExibirGerarDadosLeituraAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarDadosLeitura");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarDadosLeituraActionForm form = (GerarDadosLeituraActionForm) actionForm;
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		
		if (form.getIdRota() != null && !form.getIdRota().trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getIdRota()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			
			Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRota != null && !colecaoRota.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
				form.setCodigoRota(rota.getCodigo().toString());
				
				form.setIdGrupoFaturamento(rota.getFaturamentoGrupo().getId() + "");
				
				sessao.setAttribute("idFaturamentoGrupo", rota.getFaturamentoGrupo().getId());
			}
			
		}else{
			sessao.removeAttribute("idFaturamentoGrupo");
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeInicial()));
			
			if (localidade != null) {
				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			} else {
				form.setIdLocalidadeInicial("");
				form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInicialInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeInicial("");
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeFinal()));
			
			if (localidade != null) {
				form.setIdLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());
			} else {
				form.setIdLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeFinal("");
		}
		
		return retorno;

	}

}
