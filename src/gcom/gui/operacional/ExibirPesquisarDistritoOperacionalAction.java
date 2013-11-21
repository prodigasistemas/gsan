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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroZonaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.ZonaAbastecimento;
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
 * [UC0780] Pesquisar Distrito Operacional
 * 
 * @date 05/05/2008
 * @author Arthur Carvalho
 */
public class ExibirPesquisarDistritoOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarDistritoOperacional");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarDistritoOperacionalActionForm pesquisarDistritoOperacionalActionForm = (PesquisarDistritoOperacionalActionForm) actionForm;
		
		if (httpServletRequest.getParameter("limparForm") != null && !httpServletRequest.getParameter("limparForm").equals("")) {
			pesquisarDistritoOperacionalActionForm.setNomeDistritoOperacional("");
			pesquisarDistritoOperacionalActionForm.setSetorAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarDistritoOperacionalActionForm.setZonaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarDistritoOperacionalActionForm.setIndicadorUso("");
		}
		
		//[FS0001] Verificar Existencia de dados - Pesquisa de Sistema Abastecimento
		FiltroSetorAbastecimento filtroSetorAbastecimento = new FiltroSetorAbastecimento();
		
		filtroSetorAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorAbastecimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoSetorAbastecimento = (Collection)
			fachada.pesquisar(filtroSetorAbastecimento, SetorAbastecimento.class.getName());
		
		if (colecaoSetorAbastecimento == null || colecaoSetorAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Setor Abastecimento");
		}
		
		//[FS0001] Verificar Existencia de dados - Pesquisa de Zona Abastecimento
		FiltroZonaAbastecimento filtroZonaAbastecimento = new FiltroZonaAbastecimento();
		
		filtroZonaAbastecimento.adicionarParametro(new ParametroSimples(FiltroZonaAbastecimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoZonaAbastecimento = (Collection)
			fachada.pesquisar(filtroZonaAbastecimento, ZonaAbastecimento.class.getName());
		
		if (colecaoZonaAbastecimento == null || colecaoZonaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Zona Abastecimento");
		}
		
		httpServletRequest.setAttribute("colecaoSetorAbastecimento", colecaoSetorAbastecimento);
		httpServletRequest.setAttribute("colecaoZonaAbastecimento", colecaoZonaAbastecimento);
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaQuadraFace") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaQuadraFace",
			httpServletRequest.getParameter("caminhoRetornoTelaPesquisaQuadraFace"));

		}

		return retorno;

	}

}
