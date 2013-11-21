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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action responsável pela pre-exibição da pagina de filtrar logradouro
 * 
 * @author Sávio Luiz
 * @date   28/06/2006 
 */
public class ExibirFiltrarLogradouroAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("filtrarLogradouro");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("consultaLogradouro") != null) {
			sessao.removeAttribute("consultaLogradouro");
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");
		
		String idDigitadoEnterBairro = (String) pesquisarActionForm.get("idBairro");
		
		String codigoDigitadoEnterCep = (String) pesquisarActionForm.get("cep");
		
		if (sessao.getAttribute("atualizar") != null){
			sessao.removeAttribute("atualizar");
		}
		if (sessao.getAttribute("manter") != null){
			sessao.removeAttribute("manter");
		}
		
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
				pesquisarActionForm.set("idMunicipioFiltro",
						((Municipio) ((List) municipioEncontrado).get(0))
								.getId().toString());
				pesquisarActionForm.set("nomeMunicipio",
						((Municipio) ((List) municipioEncontrado).get(0))
								.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idBairro");

			} else {
				pesquisarActionForm.set("idMunicipioFiltro", "");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"true");
				pesquisarActionForm.set("nomeMunicipio",
						"Município inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipioFiltro");

			}
		}
		
		if (idDigitadoEnterBairro != null
				&& !idDigitadoEnterBairro.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterBairro) > 0) {
			
			if (idDigitadoEnterMunicipio == null || idDigitadoEnterMunicipio.trim().equalsIgnoreCase("")) {
	            //Limpa os campos bairroCD e bairroID do formulario
	            pesquisarActionForm.set("idBairro", "");
	            pesquisarActionForm.set("nomeBairro", "Informe Município");
	            httpServletRequest.setAttribute("corBairro", "exception");
	            
	            httpServletRequest.setAttribute("nomeCampo", "idMunicipioFiltro");
	        } else {
	            //Recebe o valor do campo bairroCD do formulário.
	            String idBairro = (String) pesquisarActionForm.get("idBairro");

	            FiltroBairro filtroBairro = new FiltroBairro();

	            filtroBairro.adicionarParametro(new ParametroSimples(
	                    FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));

	            filtroBairro.adicionarParametro(new ParametroSimples(
	                    FiltroBairro.CODIGO, idBairro));

	            filtroBairro.adicionarParametro(new ParametroSimples(
	                    FiltroBairro.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));

	            //Retorna bairro
	           Collection<Bairro> colecaoPesquisa = fachada.pesquisar(filtroBairro, Bairro.class
	                    .getName());

	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                //bairro nao encontrado
	                //Limpa os campos bairroCD, bairroID e bairroNome
	                pesquisarActionForm.set("idBairro", "");
	                pesquisarActionForm.set("nomeBairro","Bairro inexistente.");
	                httpServletRequest.setAttribute("corBairro", "exception");
	                
	                httpServletRequest.setAttribute("nomeCampo", "idLogradouro");
	            } else {
	                Bairro objetoBairro = (Bairro) Util
	                        .retonarObjetoDeColecao(colecaoPesquisa);
	                pesquisarActionForm.set("idBairro", String.valueOf(objetoBairro
	                        .getCodigo()));
	                pesquisarActionForm.set("nomeBairro", objetoBairro.getNome());
	                httpServletRequest.setAttribute("corBairro", "valor");
	                
	                httpServletRequest.setAttribute("nomeCampo", "idBairro");
	            }

	        }
		}
		
		
		//Verifica se o código do CEP foi digitado
		if ((codigoDigitadoEnterCep != null
			&& !codigoDigitadoEnterCep.trim().equals("")
			&& Integer.parseInt(codigoDigitadoEnterCep) > 0)) {
			
			FiltroCep filtroCep = new FiltroCep();

			filtroCep.adicionarParametro(new ParametroSimples(
			FiltroCep.CODIGO, codigoDigitadoEnterCep));
			
			filtroCep.adicionarParametro(new ParametroSimples(
			FiltroCep.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection cepEncontrado = fachada.pesquisar(filtroCep,
			Cep.class.getName());

			if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
				// O CEP foi encontrado
				pesquisarActionForm.set("cep",
						((Cep) ((List) cepEncontrado).get(0))
								.getCodigo().toString());
				
				pesquisarActionForm.set("retornoCep",
						((Cep) ((List) cepEncontrado).get(0))
								.getDescricaoLogradouroFormatada());
				
				httpServletRequest.setAttribute("nomeCampo", "Button");

			} else {
				pesquisarActionForm.set("cep", "");
				httpServletRequest.setAttribute("cepNaoEncontrado",
						"true");
				pesquisarActionForm.set("retornoCep",
						"CEP INVÁLIDO");

				httpServletRequest.setAttribute("nomeCampo", "cep");

			}
		}

		// verifica se não foi a pesquisa do enter
		// se não for então é a primeira vez que entra no action
		// prepara as coleções de logradouro tipo e logradouro titulo para
		// exibir
		// na página
		if ((idDigitadoEnterMunicipio == null
			|| idDigitadoEnterMunicipio.trim().equals("")) &&
			(codigoDigitadoEnterCep == null
			|| codigoDigitadoEnterCep.trim().equals(""))) {

			FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo(
					FiltroLogradouroTipo.DESCRICAO);
			filtroLogradouroTipo.setConsultaSemLimites(true);
			filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradouroTipos = fachada.pesquisar(
					filtroLogradouroTipo, LogradouroTipo.class.getName());

			if (logradouroTipos == null || logradouroTipos.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"logradouro tipo");
			} else {
				sessao.setAttribute("logradouroTipos", logradouroTipos);
			}

			FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo(
					FiltroLogradouroTitulo.DESCRICAO);
			filtroLogradouroTitulo.setConsultaSemLimites(true);
			filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradouroTitulos = fachada.pesquisar(
					filtroLogradouroTitulo, LogradouroTitulo.class.getName());

			if (logradouroTitulos == null || logradouroTitulos.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"logradouro título");
			} else {
				sessao.setAttribute("logradouroTitulos", logradouroTitulos);
			}

		}
		
		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		if (pesquisarActionForm.get("tipoPesquisaPopular") == null
				|| pesquisarActionForm.get("tipoPesquisaPopular").equals("")) {

			pesquisarActionForm.set("tipoPesquisaPopular",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		
		httpServletRequest.removeAttribute("i");
		
		String atualizar = httpServletRequest.getParameter("atualizar");
		String menu = httpServletRequest.getParameter("menu");
		
		if(httpServletRequest.getParameter("implog")!=null && 
				httpServletRequest.getParameter("implog").equals("sim")){
			pesquisarActionForm.set("indicadorImportanciaLogradouro", "1");
			sessao.setAttribute("indicadorImportanciaLogradouro",
			"true");
		}
		
		if (atualizar == null && menu == null){
			boolean i = true;
			httpServletRequest.setAttribute("i",i);
		} else{
			boolean i = true;
			httpServletRequest.setAttribute("i",i);
		}

		if (httpServletRequest.getAttribute("nomeCampo") == null){
			httpServletRequest.setAttribute("nomeCampo", "idMunicipioFiltro");
		}
		
		
		return retorno;
	}
}
