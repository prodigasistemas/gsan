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

import java.util.Collection;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import gcom.fachada.*;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 30 de Junho de 2004
 */
public class FiltrarBairroAction extends GcomAction {
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

		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String codigoBairro = (String) pesquisarActionForm.get("codigoBairro");
		String nome = (String) pesquisarActionForm.get("nomeBairro");
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipio");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		// String atualizar = (String) pesquisarActionForm.get("atualizar");
		String atualizar = httpServletRequest.getParameter("atualizar");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

		Fachada fachada = Fachada.getInstancia();

		FiltroBairro filtroBairro = new FiltroBairro(FiltroBairro.NOME);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, new Integer(codigoBairro)));
		}
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroBairro.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroBairro.NOME, nome));
			} else {
				filtroBairro.adicionarParametro(new ComparacaoTexto(
						FiltroBairro.NOME, nome));
			}
		}
		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, new Integer(idMunicipio)));
		}

		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoBairro = (Collection) fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
			if (atualizar != null && colecaoBairro.size() == 1) {
				Bairro bairro = (Bairro) colecaoBairro.iterator().next();
				httpServletRequest.setAttribute("idRegistroAtualizacao", bairro
						.getId());

				retorno = actionMapping.findForward("exibirAtualizarBairro");

			} else {
				retorno = actionMapping.findForward("retornarFiltroBairro");
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// Manda o filtro pelo request para o ExibirManterClienteAction
		httpServletRequest.setAttribute("filtroBairro", filtroBairro);
		sessao.setAttribute("filtroBairro", filtroBairro);

		return retorno;

	}
}
