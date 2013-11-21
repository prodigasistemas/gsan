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
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Fernando Fontelles
 * 
 * @created 25/08/2009
 */
public class PesquisarCategoriaSuperiorAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaCategoriaSuperiorResultado");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		

		PesquisarCategoriaSuperiorActionForm pesquisarCategoriaSuperiorActionForm 
			= (PesquisarCategoriaSuperiorActionForm) actionForm;

		// Recupera os parâmetros do form
		
		String descricao = pesquisarCategoriaSuperiorActionForm.getDescricao();
		String tipoPesquisa = pesquisarCategoriaSuperiorActionForm.getTipoPesquisa();
		String modulo = pesquisarCategoriaSuperiorActionForm.getIdModulo();
		String indicadorUso = pesquisarCategoriaSuperiorActionForm.getIndicadorUso();
		
		// filtro para a pesquisa da unidade organizacional
		
		FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroFuncionalidadeCategoria.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroFuncionalidadeCategoria.DESCRICAO_MENU_ITEM, descricao));
			} else {
				filtroFuncionalidadeCategoria.adicionarParametro(
					new ComparacaoTexto(FiltroFuncionalidadeCategoria.DESCRICAO_MENU_ITEM, descricao));
			}
		}	

		if (modulo != null && !modulo.trim().equals(
				"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroFuncionalidadeCategoria.adicionarParametro(
					new ParametroSimples(FiltroFuncionalidadeCategoria.MODULO_ID, modulo));
		}
		
		if (indicadorUso !=null && !indicadorUso.equalsIgnoreCase( "" )){
			filtroFuncionalidadeCategoria.adicionarParametro( new ParametroSimples
					(FiltroFuncionalidadeCategoria.INDICADOR_USO, indicadorUso ) );
        	peloMenosUmParametroInformado = true;
        }
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// adiciona as dependências para serem mostradas na página
		
		filtroFuncionalidadeCategoria.adicionarCaminhoParaCarregamentoEntidade("modulo");
		
		//Faz a busca das Categorias das Funcionalidades
        Collection colecaoCategoriaSuperior = Fachada.getInstancia()
        	.pesquisar(filtroFuncionalidadeCategoria, FuncionalidadeCategoria.class.getName());
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroFuncionalidadeCategoria, FuncionalidadeCategoria.class.getName());
		
		colecaoCategoriaSuperior = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		// Coloca a coleção na sessão			
		sessao.setAttribute("colecaoCategoriaSuperior",colecaoCategoriaSuperior);
		
		if(resultado.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "categoria superior");
		}
		
		return retorno;
	}

}
