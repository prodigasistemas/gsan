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

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
import org.apache.struts.validator.DynaValidatorActionForm;

public class ExibirFiltrarImovelLocalizacaoAction extends GcomAction {
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
				.findForward("filtrarClienteOutrosCriterios");

		DynaValidatorActionForm pesquisarActionForm = (DynaValidatorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Parte que pega as coleções da sessão

		// Gerência Regional
		if(sessao.getAttribute("gerenciasRegionais") == null){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection gerenciasRegionais = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());
			
			if (gerenciasRegionais == null || gerenciasRegionais.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Gerência Regional");
			} else {
				sessao.setAttribute("gerenciasRegionais", gerenciasRegionais);
			}
		}

		// Unidade Negocio
		if(sessao.getAttribute("colecaoUnidadeNegocio") == null){
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoUnidadeNegocio());
		}

		
		// -------Parte que trata do código quando o usuário tecla enter

		// Código do Município
        String codigoDigitadoMunicipioEnter = (String) pesquisarActionForm
        .get("idMunicipio");

		// Verifica se o código foi digitado
		if (codigoDigitadoMunicipioEnter != null
				&& !codigoDigitadoMunicipioEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
                pesquisarActionForm.set("idMunicipio", ""
                        + ((Municipio) ((List) municipioEncontrado).get(0))
                                .getId());
                
                pesquisarActionForm.set("descricaoMunicipioCliente",
                        ((Municipio) ((List) municipioEncontrado).get(0))
                                .getNome());
				//httpServletRequest.setAttribute("municipioNaoEncontrado",
					//	"true");

			} else {
                pesquisarActionForm.set("idMunicipioCliente", "");
                httpServletRequest.setAttribute("municipioNaoEncontrado",
                        "exception");
                pesquisarActionForm.set("descricaoMunicipioCliente",
                        "Código inexistente");
			}
		}

		// Código do Bairro
        String codigoDigitadoBairroEnter = (String) pesquisarActionForm
        .get("codigoBairro");
		// Verifica se o código foi digitado
		if (codigoDigitadoBairroEnter != null
				&& !codigoDigitadoBairroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {
			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoBairroEnter));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// Adiciona a busca por município se ele foi digitado na página
			if (codigoDigitadoMunicipioEnter != null
					&& !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O bairro foi encontrado
                pesquisarActionForm.set("codigoBairro", ""
                        + ((Bairro) ((List) bairroEncontrado).get(0))
                                .getId());
                pesquisarActionForm.set("descricaoMunicipioCliente",
                        ((Bairro) ((List) bairroEncontrado).get(0))
                                .getNome());
				//httpServletRequest.setAttribute("bairroNaoEncontrado",
					//	"true");

			} else {
                pesquisarActionForm.set("codigoBairro", "");
                httpServletRequest.setAttribute("bairroNaoEncontrado",
                        "exception");
                pesquisarActionForm.set("descricaoBairroCliente",
                        "Código inexistente");
			}
		}

		// Código do Logradouro
        String codigoDigitadoLogradouroEnter = (String) pesquisarActionForm
        .get("idLogradouro");

		// Verifica se o código foi digitado
		if (codigoDigitadoLogradouroEnter != null
				&& !codigoDigitadoLogradouroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoLogradouroEnter) > 0) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoLogradouroEnter));
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// Adiciona a busca por município se ele foi digitado na página
			// if (codigoDigitadoMunicipioEnter != null
			// && !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
			// filtroLogradouro.adicionarParametro(new
			// ParametroSimples(FiltroLogradouro.ID_MUNICIPIO,
			// codigoDigitadoMunicipioEnter));
			// }

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O logradouro foi encontrado
                pesquisarActionForm.set("idLogradouro", ""
                        + ((Logradouro) ((List) logradouroEncontrado).get(0))
                                .getId());
                pesquisarActionForm.set("descricaoMunicipioCliente",
                        ((Logradouro) ((List) logradouroEncontrado).get(0))
                                .getNome());
				//httpServletRequest.setAttribute("bairroNaoEncontrado",
					//	"true");

			} else {
                pesquisarActionForm.set("idLogradouro", "");
                httpServletRequest.setAttribute("logradouroNaoEncontrado",
                        "exception");
                pesquisarActionForm.set("descricaoLogradouroCliente",
                        "Código inexistente");
			}
		}

		return retorno;
	}

}
