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
package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Arthur Carvalho, Mariana Victor
 * @created 14 de agosto de 2009
 * @date 14/08/2009, 18/11/2010
 */
public class ExibirInserirCobrancaGrupoAction extends GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirCobrancaGrupo");

		InserirCobrancaGrupoActionForm form = (InserirCobrancaGrupoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		List colecaoHelper = null;
		
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			form.setIndicadorUso("2");
		} else if (httpServletRequest.getParameter("empresa") != null && !httpServletRequest.getParameter("empresa").equals("-1")){
			String idEmpresa = httpServletRequest.getParameter("empresa");

			if(sessao.getAttribute("collectionContrato") == null){
				
				colecaoHelper = new ArrayList();
				colecaoHelper = fachada.obterDadosItensContratoServico(new Integer(idEmpresa));

				if(colecaoHelper != null && colecaoHelper.size() > 0){
					sessao.setAttribute("collectionContrato", colecaoHelper);
				}
			}else{
				colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			}

		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			form.setDescricao("");

			if (form.getDescricao() == null
					|| form.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.ID);

				colecaoPesquisa = fachada.pesquisar(filtroCobrancaGrupo,
						CobrancaGrupo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Grupo De Cobrança");
				} else {
					sessao.setAttribute("colecaoCobrancaGrupo", colecaoPesquisa);
				}

				// Coleção Grupo de Cobrança
				filtroCobrancaGrupo = new FiltroCobrancaGrupo();
				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.ID);

				Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo,
						CobrancaGrupo.class.getName());
				sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);

			}

		}
		
		Collection<Empresa> collectionEmpresa = fachada.pesquisarEmpresasContratoServico();
		httpServletRequest.setAttribute("collectionEmpresa", collectionEmpresa);
		
		return retorno;
	}
}
