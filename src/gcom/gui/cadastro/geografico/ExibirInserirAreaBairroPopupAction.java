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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.BairroArea;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição do popup de inserir area de bairro
 * 
 * @author Vivianne Sousa
 * @created 19/12/2006
 */
public class ExibirInserirAreaBairroPopupAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirAreaBairroPopup");

		AdicionarAreaBairroActionForm adicionarAreaBairroActionForm = (AdicionarAreaBairroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String municipioId = "";
		String municipioDescricao = "";
		String bairroCodigo = "";
		String bairroDescricao = "";
		
	
		 FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		 filtroDistritoOperacional.setCampoOrderBy(FiltroDistritoOperacional.DESCRICAO);
         Collection collectionDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());
		
		sessao.setAttribute("collectionDistritoOperacional",collectionDistritoOperacional);
         
		if(sessao.getAttribute("BairroActionForm") != null){
			
			BairroActionForm bairroActionForm = (BairroActionForm) sessao.getAttribute("BairroActionForm");
			
			if (bairroActionForm.getIdMunicipio() != null){
				municipioId = bairroActionForm.getIdMunicipio();
			}
			
			if (bairroActionForm.getNomeMunicipio() != null){
				municipioDescricao = bairroActionForm.getNomeMunicipio();
			}
			
			if (bairroActionForm.getCodigoBairro() != null){
				bairroCodigo = bairroActionForm.getCodigoBairro();
			}
			
			if (bairroActionForm.getNomeBairro() != null){
				bairroDescricao = bairroActionForm.getNomeBairro();
			}
			
			adicionarAreaBairroActionForm.setMunicipioId(municipioId);
			adicionarAreaBairroActionForm.setMunicipioDescricao(municipioDescricao);
			adicionarAreaBairroActionForm.setBairroCodigo(bairroCodigo);
			adicionarAreaBairroActionForm.setBairroDescricao(bairroDescricao);
			
		}
		
		if (httpServletRequest.getParameter("desfazer")!= null){		
			adicionarAreaBairroActionForm.setAreaBairroNome("");
			adicionarAreaBairroActionForm.setDistritoOperacionalID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
//			adicionarAreaBairroActionForm.setDistritoOperacionalDescricao("");
		}
		
		// -------Parte que trata do código quando o usuário tecla enter
//		String distritoOperacionalID = adicionarAreaBairroActionForm.getDistritoOperacionalID();
//
//		if(distritoOperacionalID != null &&
//				!distritoOperacionalID.equals("")){
//			
//			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
//			
//			filtroDistritoOperacional
//			        .adicionarParametro(new ParametroSimples(
//			                FiltroDistritoOperacional.ID,
//			                distritoOperacionalID));
//			
//			filtroDistritoOperacional
//			        .adicionarParametro(new ParametroSimples(
//			                FiltroDistritoOperacional.INDICADORUSO,
//			                ConstantesSistema.INDICADOR_USO_ATIVO));
//			
//			//Retorna Distrito Operacional
//			Collection colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
//			        DistritoOperacional.class.getName());
//			
//			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
//			    //Distrito Operacional nao encontrado
//			    //Limpa o campo distritoOperacionalID do formulário
//				adicionarAreaBairroActionForm.setDistritoOperacionalID("");
//				adicionarAreaBairroActionForm
//			            .setDistritoOperacionalDescricao("Distrito operacional inexistente.");
//			    httpServletRequest.setAttribute("corDistritoOperacional",
//			            "exception");
//			    
//			    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
//			} else {
//			    DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
//			            .retonarObjetoDeColecao(colecaoPesquisa);
//			    adicionarAreaBairroActionForm.setDistritoOperacionalID(String
//			            .valueOf(objetoDistritoOperacional.getId()));
//			    adicionarAreaBairroActionForm
//			            .setDistritoOperacionalDescricao(objetoDistritoOperacional
//			                    .getDescricao());
//			    httpServletRequest.setAttribute("corDistritoOperacional",
//			            "valor");
//			    
//			    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
//			}
//
//		}
		//-------Fim da Parte que trata do código quando o usuário tecla enter
		
		
		Collection colecaoBairroArea = (Collection)sessao.getAttribute("colecaoBairroArea");
		
		if (httpServletRequest.getParameter("ultimaAlteracao")!= null
				&& !httpServletRequest.getParameter("ultimaAlteracao").equals("")){
			
			String ultimaAlteracao = ((String)httpServletRequest.getParameter("ultimaAlteracao"));
			
			Iterator iter = colecaoBairroArea.iterator();
			
			while (iter.hasNext()) {
				BairroArea bairroArea = (BairroArea) iter.next();
				
				if (bairroArea.getUltimaAlteracao().getTime() == Long.parseLong(ultimaAlteracao)) {
					adicionarAreaBairroActionForm.setAreaBairroNome(bairroArea.getNome());
					if(bairroArea.getDistritoOperacional() != null){
					adicionarAreaBairroActionForm.setDistritoOperacionalID("" + bairroArea.getDistritoOperacional().getId());
					}
//					adicionarAreaBairroActionForm.setDistritoOperacionalDescricao(bairroArea.getDistritoOperacional().getDescricao());
				}
							
			}
			
			sessao.setAttribute("atualizar",ultimaAlteracao);	
		}else if (httpServletRequest.getParameter("reloadPopup") == null){
			sessao.removeAttribute("atualizar");	
		}
		
		return retorno;
	}
}
