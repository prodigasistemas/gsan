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
package gcom.gui.cobranca;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da informação da situação especial da cobrança
 * 
 * @author Sávio Luiz
 * @date 17/03/2006
 */
public class ExibirSituacaoEspecialCobrancaInformarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSituacaoEspecialCobrancaInformar");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");
		
//		 ----------- Colecao de categoria -----------------
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		
		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		
		// ----------- Colecao de categoria -----------------

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarQuadra(inscricaoTipo,
						situacaoEspecialCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("situacaoEspecialCobrancaActionForm");
		}
		//

		if (situacaoEspecialCobrancaActionForm != null) {
			String idImovel = situacaoEspecialCobrancaActionForm.getIdImovel();

			if (idImovel != null && !idImovel.equals("")) {
				// Pesquisa o imovel para mandar para a pagina informacoes sobre
				// o endereco
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");

				// adiciona o indicador de exclusão do imovel
				filtroClienteImovel
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
								Imovel.IMOVEL_EXCLUIDO,
								FiltroParametro.CONECTOR_OR, 2));

				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));

				Collection clientesImoveis = Fachada.getInstancia().pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (!clientesImoveis.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
							.get(0);

					int quantEconomias = Fachada
							.getInstancia()
							.obterQuantidadeEconomias(clienteImovel.getImovel());

					sessao.setAttribute("quantEconomias", String
							.valueOf(quantEconomias));
					situacaoEspecialCobrancaActionForm
							.setEndereco(clienteImovel.getImovel()
									.getEnderecoFormatado());
					situacaoEspecialCobrancaActionForm
							.setInscricaoImovel(clienteImovel.getImovel()
									.getInscricaoFormatada());
					httpServletRequest.setAttribute("nomeCampo", "selecionar");
					httpServletRequest.setAttribute("enderecoFormatado", "true");
				} else {
					situacaoEspecialCobrancaActionForm
							.setInscricaoImovel("MATRÍCULA INEXISTENTE");
					situacaoEspecialCobrancaActionForm.setEndereco("");

					httpServletRequest.setAttribute("corImovel", "exception");

					httpServletRequest.setAttribute("nomeCampo", "idImovel");

				}
			}
		}

		validacaoFinal(situacaoEspecialCobrancaActionForm,fachada);

		// Exibir quantidade de Imoveis com situacao especial de faturamento
		if (httpServletRequest.getParameter("consultaQuantidadeImoveis") != null) {
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = transferirActionFormParaHelper(situacaoEspecialCobrancaActionForm);
			
			sessao.removeAttribute("COMSituacaoEspecialCobranca");
			
			Collection COMSituacaoEspecialCobranca = fachada
					.pesquisarImovelSituacaoEspecialCobranca("COM",
							situacaoEspecialCobrancaHelper);
			
			sessao.removeAttribute("SEMSituacaoEspecialCobranca");
			
			Collection SEMSituacaoEspecialCobranca = fachada
					.pesquisarImovelSituacaoEspecialCobranca("SEM",
							situacaoEspecialCobrancaHelper);
			
			
			
			if (COMSituacaoEspecialCobranca == null
					|| COMSituacaoEspecialCobranca.isEmpty()
					&& (SEMSituacaoEspecialCobranca == null || SEMSituacaoEspecialCobranca
							.isEmpty())) {
				throw new ActionServletException(
						"atencao.nao.parametro.informado", null, "");
			}

			if (COMSituacaoEspecialCobranca != null
					&& !COMSituacaoEspecialCobranca.isEmpty()) {
				httpServletRequest.setAttribute("liberarBotaoRetirar", "true");
				sessao.setAttribute("COMSituacaoEspecialCobranca", COMSituacaoEspecialCobranca);

			}

			if (SEMSituacaoEspecialCobranca != null
					&& !SEMSituacaoEspecialCobranca.isEmpty()) {
				httpServletRequest.setAttribute("liberarBotaoInserir", "true");
				sessao.setAttribute("SEMSituacaoEspecialCobranca", SEMSituacaoEspecialCobranca);

			}

			situacaoEspecialCobrancaActionForm
					.setQuantidadeImoveisCOMSituacaoEspecialCobranca(""
							+ (COMSituacaoEspecialCobranca.size()));
			situacaoEspecialCobrancaActionForm
					.setQuantidadeImoveisSEMSituacaoEspecialCobranca(""
							+ (SEMSituacaoEspecialCobranca.size()));

			httpServletRequest.setAttribute("nomeCampo", "inserir");

		} else {
			situacaoEspecialCobrancaActionForm
					.setQuantidadeImoveisCOMSituacaoEspecialCobranca("");

			situacaoEspecialCobrancaActionForm
					.setQuantidadeImoveisSEMSituacaoEspecialCobranca("");
		}

		if (httpServletRequest.getParameter("bloquear") != null) {
			if (httpServletRequest.getParameter("bloquear").equals("matricula")) {
				httpServletRequest.setAttribute("bloquear", "matricula");
			} else {
				httpServletRequest.setAttribute("bloquear", "todos");
			}

		} else {
			httpServletRequest.setAttribute("bloquear", "");
		}

		// manda o parametro que veio do validar enter
		// para ,se preciso, desabilitar os campos posterior ao intervalo, que
		// não
		// são iguais.
		if (httpServletRequest.getParameter("campoDesabilita") != null
				&& !httpServletRequest.getParameter("campoDesabilita").equals(
						"")) {
			httpServletRequest.setAttribute("campoDesabilita",
					httpServletRequest.getParameter("campoDesabilita"));
		}

		return retorno;
	}

	private void validacaoFinal(SituacaoEspecialCobrancaActionForm form,Fachada fachada) {


		// validar localidade inicial sendo maior que localidade final
		if (form.getLocalidadeOrigemID() != null
				&& form.getLocalidadeDestinoID() != null) {
			if (!form.getLocalidadeOrigemID().equals("")
					&& !form.getLocalidadeDestinoID().equals("")) {
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				if (origem > destino){
					throw new ActionServletException(
							"atencao.localidade.final.maior.localidade.inicial",
							null, "");
				}	
				String localidadeID = (String) form.getLocalidadeOrigemID();

				FiltroLocalidade filtroLocalidade =  new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidadeID));
			
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				// Retorna localidade
				Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());
			
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Localidade nao encontrada
					throw new ActionServletException(
					"atencao.pesquisa.localidade_inexistente");
				}else{
					Localidade objetoLocalidadeOrigem = (Localidade) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
				
					form.setLocalidadeOrigemID(String.valueOf(objetoLocalidadeOrigem
							.getId()));
					
					if (origem < destino){
						// se localidade inicial < localidade final
						//pesquisa p descobrir localidade final existe
						//se existir seta o id dela no actionForm
						
						filtroLocalidade.limparListaParametros();
						filtroLocalidade.adicionarParametro(new ParametroSimples(
								FiltroLocalidade.ID, destino));
					
						filtroLocalidade.adicionarParametro(new ParametroSimples(
								FiltroLocalidade.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
					
						// Retorna localidade
						Collection colecaoPesquisaDestino = fachada.pesquisar(filtroLocalidade,
								Localidade.class.getName());
					
						if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
							// Localidade nao encontrada
							throw new ActionServletException(
							"atencao.pesquisa.localidade_inexistente");
						}
						Localidade objetoLocalidadeDestino = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisaDestino);
					
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeDestino
								.getId()));
						
					}else{
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeOrigem
								.getId()));
					}
					
		
				}
			}
		}
	

		// validar setor comercial sendo maior que setor comercial final
		if (form.getSetorComercialOrigemCD() != null
				&& form.getSetorComercialDestinoCD() != null) {
			if (!form.getSetorComercialOrigemCD().equals("")
					&& !form.getSetorComercialDestinoCD().equals("")) {
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form
						.getSetorComercialDestinoCD());
				if (origem > destino){
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}
				String setorComercialCD = (String) form.getSetorComercialOrigemCD();

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidadeOrigemID()));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					//Setor Comercial inexistente.
					throw new ActionServletException(
					"atencao.setor_comercial.inexistente");
				}else{
			
					SetorComercial objetoSetorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
					form.setSetorComercialOrigemID(String
							.valueOf(objetoSetorComercial.getId()));
					
					if (origem < destino){
						//se setor comercial inicial < setor comercial final
						//pesquisa p descobrir setor comercial final existe
						//se existir seta o id dele no actionForm
						filtroSetorComercial.limparListaParametros();
						filtroSetorComercial.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidadeOrigemID()));

						// Adiciona o código do setor comercial que esta no formulário
						// para compor a pesquisa.
						filtroSetorComercial.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
								destino));

						filtroSetorComercial.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						// Retorna setorComercial
						Collection colecaoPesquisaDestino = fachada.pesquisar(filtroSetorComercial,
								SetorComercial.class.getName());

						if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
							//Setor Comercial inexistente.
							throw new ActionServletException(
							"atencao.setor_comercial.inexistente");
						}
						SetorComercial objetoSetorComercialDestino = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoPesquisaDestino);
					
						form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercialDestino
								.getId()));
					
					}else{
						form.setSetorComercialDestinoID(String
								.valueOf(objetoSetorComercial.getId()));	
					}
					
				}
			}
		}

		// validar quadra sendo maior que localidade final

		if (form.getQuadraOrigemNM() != null
				&& form.getQuadraDestinoNM() != null) {
			if (!form.getQuadraOrigemNM().equals("")
					&& !form.getQuadraDestinoNM().equals("")) {
				int origem = Integer.parseInt(form.getQuadraOrigemNM());
				int destino = Integer.parseInt(form.getQuadraDestinoNM());
				if (origem > destino){
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,
							"");
				}
				String quadraNM = (String) form.getQuadraOrigemNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialOrigemID()));
			
				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));
			
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				// Retorna quadra
				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());
			
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException("atencao.quadra.inexistente");
				}else{
					Quadra objetoQuadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraOrigemID(String
							.valueOf(objetoQuadra.getId()));
					
					if(origem < destino){
						//se setor comercial inicial < setor comercial final
						//pesquisa p descobrir setor comercial final existe
						//se existir seta o id dele no actionForm
						filtroQuadra.limparListaParametros();
						
						quadraNM = (String) form.getQuadraDestinoNM();
						
						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialOrigemID()));
					
						// Adiciona o número da quadra que esta no formulário para
						// compor a pesquisa.
						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.NUMERO_QUADRA, quadraNM));
					
						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
					
						// Retorna quadra
						Collection colecaoPesquisaDestino = fachada.pesquisar(filtroQuadra, Quadra.class
								.getName());
					
						if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
							throw new ActionServletException("atencao.quadra.inexistente");
						}
						Quadra objetoQuadraDestino = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
						form.setQuadraDestinoID(String
								.valueOf(objetoQuadraDestino.getId()));
					}else{

					form.setQuadraDestinoID(String
							.valueOf(objetoQuadra.getId()));
					}
				}
			}
		}


		// validar lote sendo maior que localidade final

		if (form.getLoteOrigem() != null && form.getLoteDestino() != null) {
			if (!form.getLoteOrigem().equals("")
					&& !form.getLoteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getLoteOrigem());
					int destino = Integer.parseInt(form.getLoteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.lote.final.maior.lote.inical", null,
								"");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "Lote(s)");
				}
			}
		}


		// validar sublote sendo maior que localidade final
		if (form.getSubloteOrigem() != null && form.getSubloteDestino() != null) {
			if (!form.getSubloteOrigem().equals("")
					&& !form.getSubloteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getSubloteOrigem());
					int destino = Integer.parseInt(form.getSubloteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.sublote.final.maior.sublote.inicial",
								null, "");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "SubLote(s)");
				}
			}
		}
		// validar Sublote sendo maior que localidade final


	}

	private void pesquisarLocalidade(
			String inscricaoTipo,
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				situacaoEspecialCobrancaActionForm.setLocalidadeOrigemID("");
				situacaoEspecialCobrancaActionForm
						.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				situacaoEspecialCobrancaActionForm.setLocalidadeOrigemID(String
						.valueOf(objetoLocalidade.getId()));
				situacaoEspecialCobrancaActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				situacaoEspecialCobrancaActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				situacaoEspecialCobrancaActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				situacaoEspecialCobrancaActionForm.setLocalidadeDestinoID("");
				situacaoEspecialCobrancaActionForm
						.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				situacaoEspecialCobrancaActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				situacaoEspecialCobrancaActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest
						.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(
			String inscricaoTipo,
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		String setorComercialCD = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemCD();

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
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm
							.setSetorComercialOrigemCD("");
					situacaoEspecialCobrancaActionForm
							.setSetorComercialOrigemID("");
					situacaoEspecialCobrancaActionForm
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialOrigem
					situacaoEspecialCobrancaActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					// setorComercialOrigem

					// setorComercialDestino
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					// setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				situacaoEspecialCobrancaActionForm
						.setSetorComercialOrigemCD("");
				situacaoEspecialCobrancaActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			}
		} else {

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoCD();

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
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoCD("");
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoID("");
					situacaoEspecialCobrancaActionForm
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				situacaoEspecialCobrancaActionForm
						.setSetorComercialDestinoCD("");
				situacaoEspecialCobrancaActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			}
		}

	}

	private void pesquisarQuadra(
			String inscricaoTipo,
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String setorComercialCD = null;

		String setorComercialID = null;

		String quadraNM = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) situacaoEspecialCobrancaActionForm
					.getSetorComercialOrigemCD();

			setorComercialID = (String) situacaoEspecialCobrancaActionForm
					.getSetorComercialOrigemID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) situacaoEspecialCobrancaActionForm
						.getQuadraOrigemNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm.setQuadraOrigemNM("");
					situacaoEspecialCobrancaActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					situacaoEspecialCobrancaActionForm
							.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm.setQuadraOrigemNM(String
							.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm.setQuadraOrigemID(String
							.valueOf(objetoQuadra.getId()));
					situacaoEspecialCobrancaActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
	
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				situacaoEspecialCobrancaActionForm.setQuadraOrigemNM("");
				situacaoEspecialCobrancaActionForm
						.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) situacaoEspecialCobrancaActionForm
					.getSetorComercialDestinoCD();
			setorComercialID = (String) situacaoEspecialCobrancaActionForm
					.getSetorComercialDestinoID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) situacaoEspecialCobrancaActionForm
						.getQuadraDestinoNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm.setQuadraDestinoNM("");
					situacaoEspecialCobrancaActionForm.setQuadraDestinoID("");
					// Mensagem de tela
					situacaoEspecialCobrancaActionForm
							.setQuadraMensagemDestino("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
					httpServletRequest
							.setAttribute("corQuadraDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				situacaoEspecialCobrancaActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				situacaoEspecialCobrancaActionForm
						.setQuadraMensagemDestino("Informe o setor comercial da inscrição.");
				httpServletRequest
						.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

	private SituacaoEspecialCobrancaHelper transferirActionFormParaHelper(
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm) {

		SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = new SituacaoEspecialCobrancaHelper();

		situacaoEspecialCobrancaHelper
				.setIdImovel(situacaoEspecialCobrancaActionForm.getIdImovel() == null ? ""
						: situacaoEspecialCobrancaActionForm.getIdImovel());

		situacaoEspecialCobrancaHelper
				.setInscricaoTipo(situacaoEspecialCobrancaActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm.getInscricaoTipo());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoNM(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoNM());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeOrigem(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialOrigem(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemNM(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemNM());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemOrigem(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeDestino(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemID(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemID(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeDestinoID(situacaoEspecialCobrancaActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeOrigemID(situacaoEspecialCobrancaActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialDestino(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoID(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemDestino(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoID(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoID());

		situacaoEspecialCobrancaHelper
				.setTipoSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getTipoSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getTipoSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setSubloteOrigem(situacaoEspecialCobrancaActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getSubloteOrigem());

		situacaoEspecialCobrancaHelper
				.setSubloteDestino(situacaoEspecialCobrancaActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSubloteDestino());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoMotivo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoMotivo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoMotivo());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoTipo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoTipo());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaInicial(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaInicial() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaInicial());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaFinal(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaFinal() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaFinal());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisCOMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisSEMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaInicial(situacaoEspecialCobrancaActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaFinal(situacaoEspecialCobrancaActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaFinal());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaInicial(situacaoEspecialCobrancaActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaFinal(situacaoEspecialCobrancaActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaFinal());
		
		
		/*if (situacaoEspecialCobrancaActionForm.getCategoria() != null
				|| !situacaoEspecialCobrancaActionForm
						.getCategoria().trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			situacaoEspecialCobrancaHelper.setIdCategoria(new Integer(
					situacaoEspecialCobrancaActionForm.getCategoria()));
		} */
		
		if (situacaoEspecialCobrancaActionForm.getIdsCategoria() != null) {
			/*situacaoEspecialFaturamentoHelper.setIdCategoria(new Integer(
					situacaoEspecialFaturamentoActionForm.getCategoria()));*/
			
			String [] idsCategoria = situacaoEspecialCobrancaActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		} 

		return situacaoEspecialCobrancaHelper;
	}

}
