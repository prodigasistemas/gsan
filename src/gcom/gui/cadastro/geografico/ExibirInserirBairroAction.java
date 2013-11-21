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

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirInserirBairroAction extends GcomAction {
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
		ActionForward retorno = null;

		BairroActionForm bairroActionForm = (BairroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String redirecionarPagina = httpServletRequest.getParameter("redirecionarPagina");
		
		if(redirecionarPagina != null){
			retorno = actionMapping.findForward(redirecionarPagina);
		}else{
		    retorno = actionMapping.findForward("inserirBairro");
		}

		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) bairroActionForm
				.getIdMunicipio();

		// Verifica se o código foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				bairroActionForm
						.setIdMunicipio(((Municipio) ((List) municipioEncontrado)
								.get(0)).getId().toString());
				bairroActionForm
						.setNomeMunicipio(((Municipio) ((List) municipioEncontrado)
								.get(0)).getNome());
				
				// Retorna o maior código existente para o munícipio pesquisado
				int codigo = fachada.pesquisarMaximoCodigoBairro(new Integer(idDigitadoEnterMunicipio));
				
				// Acrescenta 1 no valor do código para setar o primeiro código vazio para o usuário
				codigo = codigo + 1;
				
				bairroActionForm.setCodigoBairro("" + codigo);
				
				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");

			} else {
				bairroActionForm.setIdMunicipio("");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"true");
				bairroActionForm.setNomeMunicipio("Município inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

			}
		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter

		
		
		if (httpServletRequest.getParameter("limparForm") != null){
			bairroActionForm.setIdMunicipio("");
			bairroActionForm.setNomeMunicipio("");
			bairroActionForm.setCodigoBairro("");
			bairroActionForm.setNomeBairro("");
			bairroActionForm.setCodigoBairroPrefeitura("");
			sessao.removeAttribute("colecaoBairroArea");
			
		}
		
		
		sessao.setAttribute("reloadPage","INSERIRBAIRRO");
		
		return retorno;
	}
}
