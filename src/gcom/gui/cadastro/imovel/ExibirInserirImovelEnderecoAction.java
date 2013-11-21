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

import java.util.Collection;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelEnderecoAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("inserirImovelEndereco");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		String removeEndereco = httpServletRequest
				.getParameter("removeEndereco");

		if (removeEndereco != null && !removeEndereco.equals("")) {
			sessao.removeAttribute("colecaoEnderecos");
		}else {
			//**********************************************************************
			// Autor: Ivan Sergio
			// Data: 23/07/2009
			// CRC2103
			// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
			// cliente é invocado pelo Inserir/Manter Imovel como PopUp
			//**********************************************************************
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
				Object obj = (Object) colecaoEndereco.iterator().next();
				if (!(obj instanceof Imovel)) {
					sessao.removeAttribute("colecaoEnderecos");
				}
				if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
				}
			}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
				sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
			}
			//**********************************************************************
		}

		if (httpServletRequest.getAttribute("confirmou") != null) {
			sessao.setAttribute("confirmou", "sim");
		}
		
		sessao.removeAttribute("gis");

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) sessao
				.getAttribute("InserirImovelActionForm");

		String codigoSetorComercial = (String) inserirImovelLocalidadeActionForm
				.get("idSetorComercial");
		String idLocalidade = (String) inserirImovelLocalidadeActionForm
				.get("idLocalidade");

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);

		// coloca parametro no filtro
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidade)));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));

		// Obtém a instância da Fachada
		// Fachada fachada = Fachada.getInstancia();

		// pesquisa
//		Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
//				SetorComercial.class.getName());
//		Collection colecaoEndereco = (Collection) sessao
//		.getAttribute("colecaoEnderecos");
//
//		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
//
//			Imovel imovel = (Imovel) colecaoEndereco.iterator().next();
//
//			System.out
//					.println("imovel.getLogradouroBairro().getLogradouro().getMunicipio().getId().toString()="
//							+ imovel.getLogradouroBairro().getLogradouro()
//									.getMunicipio().getId().intValue());
//			System.out
//					.println("( ((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getId()="
//							+ (((SetorComercial) ((List) setorComerciais)
//									.get(0)).getMunicipio().getId().intValue()));
//			System.out
//					.println("boolean="
//							+ (!(imovel.getLogradouroBairro().getLogradouro()
//									.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//									.get(0)).getMunicipio().getId().intValue()))));
//
//			if (imovel.getLogradouroBairro() != null
//					&& imovel.getLogradouroBairro().getLogradouro() != null
//					&& imovel.getLogradouroBairro().getLogradouro()
//							.getMunicipio() != null
//					&& (!(imovel.getLogradouroBairro().getLogradouro()
//							.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//							.get(0)).getMunicipio().getId().intValue())))) {
//
//				Usuario usuario = (Usuario) sessao
//						.getAttribute("usuarioLogado");
//
//				if (!fachada
//						.verificarPermissaoInserirImovelMunicipioLogradouroDiferenteSetor(usuario)) {
//
//					throw new ActionServletException(
//							"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
//
//				} else {
//					if (sessao.getAttribute("confirmou") == null) {
//						httpServletRequest.setAttribute("destino",
//								actionMapping
//										.findForward("inserirImovelEndereco"));
//
//						// httpServletRequest.setAttribute("confirmou", "sim");
//
//						// retorno = actionMapping
//						// .findForward("gerenciado");
//						String destino ="inserirImovelEndereco" ;
//						actionMapping.setParameter(destino);
//
//						retorno = montarPaginaConfirmacaoWizard(
//								"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
//								httpServletRequest, actionMapping, "");
//					}
//				}

				// httpServletRequest.setAttribute("atencao","O município do
				// logradouro não é o mesmo do setor comercial");

				// URL da próxima ABA
				// httpServletRequest
				// .setAttribute(
				// "proximaAba",
				// "/gsan/inserirImovelWizardAction.do?destino=3&action=inserirImovelEnderecoAction");
				//
				// // URL da ABA anterior
				// httpServletRequest
				// .setAttribute(
				// "voltarAba",
				// "/gsan/inserirImovelWizardAction.do?removeEndereco=true&destino=2&action=inserirImovelLocalidadeAction");
				//
				// retorno = actionMapping.findForward("telaOpcaoConsultar");

				// sessao.removeAttribute("colecaoEnderecos");
				// throw new
				// ActionServletException("atencao.municipio.diferente.setor_comercial.logradouro");

			
		
		return retorno;
	}

}
