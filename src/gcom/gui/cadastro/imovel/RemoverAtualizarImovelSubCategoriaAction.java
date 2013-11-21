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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de sub categoria em Manter Imovel
 * 
 * @author Rafael Santos
 * @created 11/02/2006
 * 
 */
public class RemoverAtualizarImovelSubCategoriaAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("inserirImovelSubCategoria");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		// Cria variaveis
		Collection colecaoImovelSubCategorias = (Collection) sessao
				.getAttribute("colecaoImovelSubCategorias");

		Collection colecaoImovelSubcategoriasRemovidas = (Collection) sessao.getAttribute(
				"colecaoImovelSubcategoriasRemoviadas");
		if (colecaoImovelSubcategoriasRemovidas == null){
			colecaoImovelSubcategoriasRemovidas = new ArrayList();
		}

		ImovelSubcategoria imovelSubcategoria = null;
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
		
		// Obtém os ids de remoção
		String[] removerImovelSubCategoria = httpServletRequest
				.getParameterValues("removerImovelSubCategoria");

		if (colecaoImovelSubCategorias != null && !colecaoImovelSubCategorias.equals("")) {
			
			Usuario usuario = this.getUsuarioLogado(httpServletRequest);
			
			boolean temPermissao = 
				this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.REMOVER_CATEGORIA_NAO_RESIDENCIAL_IMOVEL,
					usuario);
			
			Iterator imovelSubcategoriaIterator = colecaoImovelSubCategorias.iterator();

			while (imovelSubcategoriaIterator.hasNext()) {
				
				imovelSubcategoria = (ImovelSubcategoria) imovelSubcategoriaIterator.next();
				
				for (int i = 0; i < removerImovelSubCategoria.length; i++) {
					
					if (ExibirAtualizarImovelSubCategoriaAction.obterTimestampIdImovelSubcategoria(imovelSubcategoria) == 
						new Long(removerImovelSubCategoria[i]).longValue()) {
						
						Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
						boolean podeRemover = true;
						
						FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
						
						filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.IMOVEL_ID, imovel.getId()));
						
						filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.SUBCATEGORIA_ID, 
						imovelSubcategoria.getComp_id().getSubcategoria().getId()));
						
						Collection colecaoImovelSubcategoria = fachada.pesquisar(filtroImovelSubCategoria,
						FiltroImovelSubCategoria.class.getName());
						
						if(!temPermissao && categoria.getId().intValue() != Categoria.RESIDENCIAL_INT &&
						(colecaoImovelSubcategoria != null && !colecaoImovelSubcategoria.isEmpty())){
			                throw new ActionServletException("atencao.permissao_remover_categoria_imovel");        		
						}
						
						//adicionado por Vivianne 27/07/2009 - analista: Ana Breda 
						if (imovel != null && imovel.getImovelPerfil() != null
			                    && imovel.getImovelPerfil().getId() != null 
			                    && imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
							  throw new ActionServletException( "atencao.subcategoria_na_tarifa_social_remover");
						}
			                      
						
						if(podeRemover && !(colecaoImovelSubcategoriasRemovidas.contains(imovelSubcategoria))){
							if(fachada.pesquisarExistenciaImovelEconomia(imovel.getId(),imovelSubcategoria.getComp_id().getSubcategoria().getId())){
				                throw new ActionServletException("atencao.existencia_imovel_economia"); 
							}
							colecaoImovelSubcategoriasRemovidas.add(imovelSubcategoria);
							imovelSubcategoriaIterator.remove();
						}
					}
				}
			}
			sessao.setAttribute(
					"colecaoImovelSubcategoriasRemoviadas",
					colecaoImovelSubcategoriasRemovidas);

		}

		return retorno;
	}
}
