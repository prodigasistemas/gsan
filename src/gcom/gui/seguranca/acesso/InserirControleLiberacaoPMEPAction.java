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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Daniel Alves
 * @date 23/07/2010
 */
public class InserirControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma novo Controle Liberação Permissão Especial
	 * 
	 * [UC0280] Inserir Controle Liberação Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ExibirInserirControleLiberacaoPMEPActionForm inserirControleLiberacaoPMEPActionForm = (ExibirInserirControleLiberacaoPMEPActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtendo dados do form e setando no Objeto ControleLiberacaoPermissaoEspecial
		
		ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial = new ControleLiberacaoPermissaoEspecial();


		if (inserirControleLiberacaoPMEPActionForm.getIdFuncionalidade() != null
				&& !inserirControleLiberacaoPMEPActionForm.getIdFuncionalidade().trim()
						.equalsIgnoreCase("") ) {

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidade.ID,
							inserirControleLiberacaoPMEPActionForm
									.getIdFuncionalidade()));

			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade,
					Funcionalidade.class.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				Funcionalidade funcionalidade = (Funcionalidade) Util
						.retonarObjetoDeColecao(colecaoFuncionalidade);
				
				controleLiberacaoPermissaoEspecial.setFuncionalidade(funcionalidade);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Funcionalidade");
			}

		}
		
		if (inserirControleLiberacaoPMEPActionForm.getIdPermissaoEspecial() != null
				&& !inserirControleLiberacaoPMEPActionForm.getIdPermissaoEspecial()
						.trim().equalsIgnoreCase("") ) {

			FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
			filtroPermissaoEspecial
					.adicionarParametro(new ParametroSimples(
							FiltroPermissaoEspecial.ID,
							inserirControleLiberacaoPMEPActionForm
									.getIdPermissaoEspecial()));

			Collection colecaoPermissaoEspecial = fachada.pesquisar(
					filtroPermissaoEspecial,
					PermissaoEspecial.class.getName());

			if (colecaoPermissaoEspecial != null
					&& !colecaoPermissaoEspecial.isEmpty()) {
				PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util
						.retonarObjetoDeColecao(colecaoPermissaoEspecial);
				
				controleLiberacaoPermissaoEspecial.setPermissaoEspecial(permissaoEspecial);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Permissão Especial");
			}

		}
		
		controleLiberacaoPermissaoEspecial.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		controleLiberacaoPermissaoEspecial.setUltimaAlteracao(new Date());		
		
		Usuario usuarioLogado =(Usuario) (httpServletRequest.getSession()).getAttribute("usuarioLogado");
		
		Integer idControleLiberacaoPermissaoEspecial = (Integer) fachada.inserirControleLiberacaoPermissaoEspecial(
				controleLiberacaoPermissaoEspecial, usuarioLogado);


		sessao.removeAttribute("colecaoFuncionalidadeTela");

		montarPaginaSucesso(httpServletRequest, "Novo Controle de Liberação de Permissão " +
				" Especial inserido com sucesso.",
				"Inserir outro Controle de Liberação de Permissão Especial",
				"exibirInserirControleLiberacaoPMEPAction.do?menu=sim",
				"exibirManterControleLiberacaoPMEPAction.do?idContLibPMEP="
						+ idControleLiberacaoPermissaoEspecial, "Atualizar Controle de Liberação de Permissão Especial inserido");

		return retorno;
	}
}