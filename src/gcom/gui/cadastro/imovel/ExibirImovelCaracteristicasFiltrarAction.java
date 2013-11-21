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

import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirImovelCaracteristicasFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarCaracteristicasImovel");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		 Fachada fachada = Fachada.getInstancia();
		 
		 Collection<ImovelPerfil> collectionImovelPerfil = null;
		 Collection<Categoria> collectionImovelCategoria= null;
		 Collection<Subcategoria> collectionImovelSubcategoria = null;
		 Collection<AreaConstruidaFaixa> collectionAreaConstuidaFaixa = null;
		 Collection<PocoTipo> collectionTipoPoco = null;
		 
		 String parametroGerarRelatorio = (String)sessao.getAttribute("parametroGerarRelatorio");
		 
		 if(imovelOutrosCriteriosActionForm.getCategoriaImovel() != null &&
				 !imovelOutrosCriteriosActionForm.getCategoriaImovel().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				 && !imovelOutrosCriteriosActionForm.getCategoriaImovel().trim().equals("")){
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, imovelOutrosCriteriosActionForm.getCategoriaImovel()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);	 
		 } else if(imovelOutrosCriteriosActionForm.getSubcategoria() != null
				 && !imovelOutrosCriteriosActionForm.getSubcategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				 && !imovelOutrosCriteriosActionForm.getSubcategoria().trim().equalsIgnoreCase("")){
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovelOutrosCriteriosActionForm.getSubcategoria()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 Subcategoria subcategoria = collectionImovelSubcategoria.iterator().next();
			 FiltroCategoria filtroCategoria = new FiltroCategoria();
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 Categoria.RESIDENCIAL));
			 }
			 filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			 
			 if(subcategoria.getCategoria() != null){
			 	 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, subcategoria.getCategoria().getId()));
			 	 imovelOutrosCriteriosActionForm.setCategoriaImovel("" + subcategoria.getCategoria().getId());
			 }
			 collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			 
			 filtroSubcategoria.limparListaParametros();
			 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, imovelOutrosCriteriosActionForm.getCategoriaImovel()));
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );
			 
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			 
			 sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			 
		 }else{
			 FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			 filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ImovelPerfil.TARIFA_SOCIAL));
			 }
			 
			 collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
			 if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Perfil do Imóvel");			
			 }				 
			 
			 
			 FiltroCategoria filtroCategoria = new FiltroCategoria();
			 filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 Categoria.RESIDENCIAL));
			 }
			 collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			 if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Categoria");			
			 }				 
			 
			 FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			 filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			 
			 if(parametroGerarRelatorio.trim().equalsIgnoreCase("RelatorioTarifaSocial")){
				 filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
						 Categoria.RESIDENCIAL));
			 }
			 collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );		 
			 if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Subcategoria");			
			 }				 
			 
			 FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
			 filtroAreaConstruidaFaixa.setCampoOrderBy(FiltroAreaConstruidaFaixa.CODIGO);
			 collectionAreaConstuidaFaixa = fachada.pesquisar(filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class.getName() );
			 if(collectionAreaConstuidaFaixa == null || collectionAreaConstuidaFaixa.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Intervalo de Área Construída");			
			 }			 
			 
			 FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
			 collectionTipoPoco = fachada.pesquisar(filtroPocoTipo, PocoTipo.class.getName() );
			 if(collectionTipoPoco == null || collectionTipoPoco.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Poço");			
			 }				 
			 
			 sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
			 sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			 sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			 sessao.setAttribute("collectionAreaConstuidaFaixa", collectionAreaConstuidaFaixa);
			 sessao.setAttribute("collectionTipoPoco", collectionTipoPoco);
		 }
		 		 
		return retorno;
	}

}
