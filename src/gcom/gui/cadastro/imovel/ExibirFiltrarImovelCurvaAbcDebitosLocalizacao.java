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

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelCurvaAbcDebitosLocalizacao extends
		GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;
	private String municipioID = null;
	private String nomeMunicipio = "";
	private String setorComercialCD = null;
	private String localidadeInicial = "";
	private String localidadeFinal   = "";
	private String setorComercialInicial = "";
	private String setorComercialFinal   = "";

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("filtrarImovelCurvaAbcDebitosLocalizacao");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		
		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;
		
		// Caso o usuário selecionar ESTADO esconder a Aba de Localização
		// Vai direto para a terceira aba
		if (imovelCurvaAbcDebitosActionForm.getClassificacao().equalsIgnoreCase("ESTADO")) {
			FiltrarImovelCurvaAbcDebitosWizardAction filtrar = new FiltrarImovelCurvaAbcDebitosWizardAction();
			
			if (sessao.getAttribute("abaAtual") == "PARAMETROS") {
				retorno = actionMapping.findForward("filtrarImovelCurvaAbcDebitosLigacaoAguaEsgoto");
				filtrar.exibirFiltrarImovelCurvaAbcDebitosLigacaoAguaEsgoto(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}else if (sessao.getAttribute("abaAtual") == "LIGACAOAGUAESGOTO") {
				retorno = actionMapping.findForward("filtrarImovelCurvaAbcDebitosParametros");
				filtrar.exibirFiltrarImovelCurvaAbcDebitosParametros(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}
			
		}else {
			// Pesquisar Gerências Regionais
			if(sessao.getAttribute("colecaoGerenciasRegionais") == null){
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		
				Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada
						.pesquisar(filtroGerenciaRegional, GerenciaRegional.class
								.getName());
				
				// [FS0001 - Verificar Existencia de dados]
				if ( (colecaoGerenciasRegionais == null) || (colecaoGerenciasRegionais.size() == 0) ) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null, GerenciaRegional.class.getName());
				}else {
					sessao.setAttribute("colecaoGerenciasRegionais", colecaoGerenciasRegionais);
				}
			}
			
			String objetoConsulta = (String) httpServletRequest
					.getParameter("objetoConsulta");
			
			String inscricaoTipo = (String) httpServletRequest
					.getParameter("inscricaoTipo");
			
			if (objetoConsulta != null
					&& !objetoConsulta.trim().equalsIgnoreCase("")
					&& inscricaoTipo != null
					&& !inscricaoTipo.trim().equalsIgnoreCase("")) {
			
				switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					pesquisarLocalidade(inscricaoTipo, imovelCurvaAbcDebitosActionForm, fachada, httpServletRequest);
					break;

					// Setor Comercial
				case 2:
					pesquisarLocalidade(inscricaoTipo, imovelCurvaAbcDebitosActionForm, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, imovelCurvaAbcDebitosActionForm, fachada, httpServletRequest);
					break;

				case 3:
					pesquisarMunicipio(imovelCurvaAbcDebitosActionForm, fachada, httpServletRequest);
					break;
				default:
					break;
				}
			} else {
				sessao.removeAttribute("imovelCurvaAbcDebitosActionForm");
			}
		}
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null) {
			httpServletRequest.setAttribute("classificacao", imovelCurvaAbcDebitosActionForm.getClassificacao());
		}
		
		sessao.setAttribute("localidadeInicial",localidadeInicial);
		sessao.setAttribute("localidadeFinal",localidadeFinal);
		sessao.setAttribute("setorComercialInicial",setorComercialInicial);
		sessao.setAttribute("setorComercialFinal",setorComercialFinal);
		sessao.setAttribute("nomeMunicipio",nomeMunicipio);
		
		return retorno;
	}

	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarLocalidade(String inscricaoTipo,
			ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelCurvaAbcDebitosActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) imovelCurvaAbcDebitosActionForm.getIdLocalidadeInicial();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeInicial("");
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeInicial("Localidade inexistente.");
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeFinal("");
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeFinal("");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeInicial(String.valueOf(objetoLocalidade.getId()));
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeInicial(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				localidadeInicial = objetoLocalidade.getDescricao();
				
				//destino
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				
				
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) imovelCurvaAbcDebitosActionForm.getIdLocalidadeFinal();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			imovelCurvaAbcDebitosActionForm.setInscricaoTipo("destino");
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeFinal("");
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeFinal("Localidade inexistente.");
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				imovelCurvaAbcDebitosActionForm.setIdLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelCurvaAbcDebitosActionForm.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				localidadeFinal = objetoLocalidade.getDescricao();
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}

	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarSetorComercial(String inscricaoTipo,
			ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelCurvaAbcDebitosActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) imovelCurvaAbcDebitosActionForm.getIdLocalidadeInicial();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = (String) imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialInicial();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialInicial("");
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialInicial("");
					imovelCurvaAbcDebitosActionForm.setNomeSetorComercialInicial("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");

					//destino
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialFinal("");
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialFinal("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialInicial(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialInicial(String
									.valueOf(objetoSetorComercial.getId()));
					imovelCurvaAbcDebitosActionForm.setNomeSetorComercialInicial(objetoSetorComercial
									.getDescricao());
					setorComercialInicial = objetoSetorComercial.getDescricao();
					
					//httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//setorComercialOrigem
					
					//setorComercialDestino
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialFinal(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialFinal(String
									.valueOf(objetoSetorComercial.getId()));
					imovelCurvaAbcDebitosActionForm.setNomeSetorComercialFinal(objetoSetorComercial
									.getDescricao());

					//setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialInicial("");
				imovelCurvaAbcDebitosActionForm
					.setNomeSetorComercialInicial("Informe a localidade da inscrição de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			
			imovelCurvaAbcDebitosActionForm.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) imovelCurvaAbcDebitosActionForm.getIdLocalidadeFinal();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = (String) imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialFinal();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialFinal("");
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialFinal("");
					imovelCurvaAbcDebitosActionForm.setNomeSetorComercialFinal("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					
					imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialFinal(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelCurvaAbcDebitosActionForm.setIdSetorComercialFinal(String
									.valueOf(objetoSetorComercial.getId()));
					imovelCurvaAbcDebitosActionForm.setNomeSetorComercialFinal(objetoSetorComercial
									.getDescricao());
					setorComercialFinal = objetoSetorComercial.getDescricao();
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					//httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				imovelCurvaAbcDebitosActionForm.setCodigoSetorComercialFinal("");
				imovelCurvaAbcDebitosActionForm.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	/***
	 * 
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarMunicipio(ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		municipioID = (String) imovelCurvaAbcDebitosActionForm.getIdMunicipio();
		
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, municipioID));
		
		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			Municipio objetoMunicipio = (Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
			imovelCurvaAbcDebitosActionForm.setIdMunicipio(String.valueOf(objetoMunicipio.getId()));
			imovelCurvaAbcDebitosActionForm.setNomeMunicipio(objetoMunicipio.getNome());
			
			httpServletRequest.setAttribute("corMunicipio", "valor");
			nomeMunicipio = objetoMunicipio.getNome();
		} else {
			//Município não encontrado
			imovelCurvaAbcDebitosActionForm.setIdMunicipio("");
			imovelCurvaAbcDebitosActionForm.setNomeMunicipio("Município inexistente.");
			
			httpServletRequest.setAttribute("corMunicipio", "exception");
			httpServletRequest.setAttribute("nomeCampo","idMunicipio");				
		}
	}
}
