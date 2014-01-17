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
	
	private String objetoConsulta;
	private String inscricaoTipo;
	private String setorComercialCD;
	private String setorComercialID;
	
	private String setorComercialOrigemCD;
	private String setorComercialDestinoCD;
	
	private String localidadeOrigemID;
	private String localidadeDestinoID;
	
	private String quadraOrigemNM;
	private String quadraDestinoNM;
	
	private String loteOrigem;
	private String loteDestino;
	
	private String subloteOrigem;
	private String subloteDestino;
	
	private Collection COMSituacaoEspecialCobranca;
	private Collection SEMSituacaoEspecialCobranca;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirSituacaoEspecialCobrancaInformar");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;
		
		objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		
		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		
		if (possuiObjetoConsulta() && possuiInscricaoTipo()) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:
				pesquisarLocalidade(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);

				break;
			// Setor Comercial
			case 2:
				pesquisarLocalidade(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);
				pesquisarSetorComercial(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);

				break;
			// Quadra
			case 3:
				pesquisarLocalidade(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);
				pesquisarSetorComercial(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);
				pesquisarQuadra(inscricaoTipo, situacaoEspecialCobrancaActionForm, fachada, httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("situacaoEspecialCobrancaActionForm");
		}

		if (situacaoEspecialCobrancaActionForm != null) {
			String idImovel = situacaoEspecialCobrancaActionForm.getIdImovel();

			if (idImovel != null && !idImovel.equals("")) {
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

				filtroClienteImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
																						Imovel.IMOVEL_EXCLUIDO,
																						FiltroParametro.CONECTOR_OR, 2));

				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));

				Collection clientesImoveis = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

				if (!clientesImoveis.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis).get(0);

					int quantEconomias = Fachada.getInstancia().obterQuantidadeEconomias(clienteImovel.getImovel());

					sessao.setAttribute("quantEconomias", String.valueOf(quantEconomias));
					situacaoEspecialCobrancaActionForm.setEndereco(clienteImovel.getImovel().getEnderecoFormatado());
					situacaoEspecialCobrancaActionForm.setInscricaoImovel(clienteImovel.getImovel().getInscricaoFormatada());
					
					httpServletRequest.setAttribute("nomeCampo", "selecionar");
					httpServletRequest.setAttribute("enderecoFormatado", "true");
					
				} else {
					situacaoEspecialCobrancaActionForm.setInscricaoImovel("MATRÍCULA INEXISTENTE");
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
			
			COMSituacaoEspecialCobranca = fachada.pesquisarImovelSituacaoEspecialCobranca("COM", situacaoEspecialCobrancaHelper);
			
			sessao.removeAttribute("SEMSituacaoEspecialCobranca");
			
			SEMSituacaoEspecialCobranca = fachada.pesquisarImovelSituacaoEspecialCobranca("SEM", situacaoEspecialCobrancaHelper);
			
			if (!possuiCOMSituacaoEspecialCobranca() && !possuiSEMSituacaoEspecialCobranca()) {
				throw new ActionServletException("atencao.nao.parametro.informado", null, "");
			}

			if (possuiCOMSituacaoEspecialCobranca()) {
				httpServletRequest.setAttribute("liberarBotaoRetirar", "true");
				sessao.setAttribute("COMSituacaoEspecialCobranca", COMSituacaoEspecialCobranca);
			}

			if (possuiSEMSituacaoEspecialCobranca()) {
				httpServletRequest.setAttribute("liberarBotaoInserir", "true");
				sessao.setAttribute("SEMSituacaoEspecialCobranca", SEMSituacaoEspecialCobranca);
			}

			situacaoEspecialCobrancaActionForm.setQuantidadeImoveisCOMSituacaoEspecialCobranca("" + (COMSituacaoEspecialCobranca.size()));
			situacaoEspecialCobrancaActionForm.setQuantidadeImoveisSEMSituacaoEspecialCobranca("" + (SEMSituacaoEspecialCobranca.size()));

			httpServletRequest.setAttribute("nomeCampo", "inserir");

		} else {
			situacaoEspecialCobrancaActionForm.setQuantidadeImoveisCOMSituacaoEspecialCobranca("");
			situacaoEspecialCobrancaActionForm.setQuantidadeImoveisSEMSituacaoEspecialCobranca("");
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

		if (httpServletRequest.getParameter("campoDesabilita") != null && !httpServletRequest.getParameter("campoDesabilita").equals("")) {
			httpServletRequest.setAttribute("campoDesabilita", httpServletRequest.getParameter("campoDesabilita"));
		}

		return retorno;
	}

	private void validacaoFinal(SituacaoEspecialCobrancaActionForm form,Fachada fachada) {
		validarLocalidade(form, fachada);
		validarSetorComercial(form, fachada);
		validarQuadra(form, fachada);
		validarLote(form);
		validarSublote(form);
	}

	private void validarSublote(SituacaoEspecialCobrancaActionForm form) {
		subloteOrigem = form.getSubloteOrigem();
		subloteDestino = form.getSubloteDestino();
		if (possuiSubLoteOrigem() && possuiSubLoteDestino()) {
			try {
				int origem = Integer.parseInt(subloteOrigem);
				int destino = Integer.parseInt(subloteDestino);
				if (origem > destino) {
					throw new ActionServletException("atencao.sublote.final.maior.sublote.inicial", null, "");
				}
			} catch (NumberFormatException e) {
				throw new ActionServletException("atencao.nao.numerico", null, "SubLote(s)");
			}
		}
	}

	private void validarLote(SituacaoEspecialCobrancaActionForm form) {
		loteOrigem = form.getLoteOrigem();
		loteDestino = form.getLoteDestino();
		if (possuiLoteOrigem() && possuiLoteDestino()) {
			try {
				int origem = Integer.parseInt(loteOrigem);
				int destino = Integer.parseInt(loteDestino);
				if (origem > destino) {
					throw new ActionServletException("atencao.lote.final.maior.lote.inical", null, "");
				}
			} catch (NumberFormatException e) {
				throw new ActionServletException("atencao.nao.numerico", null, "Lote(s)");
			}
		}
	}

	private void validarQuadra(SituacaoEspecialCobrancaActionForm form, Fachada fachada) {
		quadraOrigemNM = form.getQuadraOrigemNM();
		quadraDestinoNM = form.getQuadraDestinoNM();
		
		if (possuiQuadraOrigemNM() && possuiQuadraDestinoNM()) {
			int origem = Integer.parseInt(quadraOrigemNM);
			int destino = Integer.parseInt(quadraDestinoNM);
			
			if (origem > destino){
				throw new ActionServletException("atencao.quadra.final.maior.quadra.inical", null, "");
			}
			
			String quadraNM = (String) form.getQuadraOrigemNM();

			// Adiciona o id do setor comercial que está no formulário para
			// compor a pesquisa.
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialOrigemID()));
		
			// Adiciona o número da quadra que esta no formulário para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNM));
		
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
			// Retorna quadra
			Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
		
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException("atencao.quadra.inexistente");
			}else{
				Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
				
				if(origem < destino){
					//se setor comercial inicial < setor comercial final
					//pesquisa p descobrir setor comercial final existe
					//se existir seta o id dele no actionForm
					filtroQuadra.limparListaParametros();
					
					quadraNM = (String) form.getQuadraDestinoNM();
					
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getSetorComercialOrigemID()));
				
					// Adiciona o número da quadra que esta no formulário para
					// compor a pesquisa.
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNM));
				
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
					// Retorna quadra
					Collection colecaoPesquisaDestino = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
					if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
						throw new ActionServletException("atencao.quadra.inexistente");
					}
					
					Quadra objetoQuadraDestino = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
					form.setQuadraDestinoID(String.valueOf(objetoQuadraDestino.getId()));
					
				}else{
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
				}
			}
		}
	}

	private void validarSetorComercial(SituacaoEspecialCobrancaActionForm form, Fachada fachada) {
		setorComercialOrigemCD = form.getSetorComercialOrigemCD();
		setorComercialDestinoCD = form.getSetorComercialDestinoCD();

		if (possuiSetorComercialOrigemCD() && possuiSetorComercialDestinoCD()) {
			
			int origem = Integer.parseInt(setorComercialOrigemCD);
			int destino = Integer.parseInt(setorComercialDestinoCD);
			
			if (origem > destino){
				throw new ActionServletException("atencao.setor.comercial.final.maior.setor.comercial.inicial", null, "");
			}
			
			String setorComercialCD = (String) form.getSetorComercialOrigemCD();

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			// Adiciona o id da localidade que está no formulário para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidadeOrigemID()));

			// Adiciona o código do setor comercial que esta no formulário
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				//Setor Comercial inexistente.
				throw new ActionServletException("atencao.setor_comercial.inexistente");
			}else{
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
				
				if (origem < destino){
					//se setor comercial inicial < setor comercial final
					//pesquisa p descobrir setor comercial final existe
					//se existir seta o id dele no actionForm
					filtroSetorComercial.limparListaParametros();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidadeOrigemID()));

					// Adiciona o código do setor comercial que esta no formulário
					// para compor a pesquisa.
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, destino));

					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

					// Retorna setorComercial
					Collection colecaoPesquisaDestino = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

					if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
						//Setor Comercial inexistente.
						throw new ActionServletException("atencao.setor_comercial.inexistente");
					}
					SetorComercial objetoSetorComercialDestino = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
				
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercialDestino.getId()));
				
				}else{
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));	
				}
			}
		}
	}

	private void validarLocalidade(SituacaoEspecialCobrancaActionForm form, Fachada fachada) {
		localidadeOrigemID = form.getLocalidadeOrigemID();
		localidadeDestinoID = form.getLocalidadeDestinoID();
		
		if (possuiLocalidadeOrigem() && possuiLocalidadeDestinoID()) {
				
			int origem = Integer.parseInt(localidadeOrigemID);
			int destino = Integer.parseInt(localidadeDestinoID);
			
			if (origem > destino){
				throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial", null, "");
			}	
			
			FiltroLocalidade filtroLocalidade =  new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeOrigemID));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
			}else{
				Localidade objetoLocalidadeOrigem = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
				form.setLocalidadeOrigemID(String.valueOf(objetoLocalidadeOrigem.getId()));
				
				if (origem < destino){
					// se localidade inicial < localidade final
					//pesquisa p descobrir localidade final existe
					//se existir seta o id dela no actionForm
					
					filtroLocalidade.limparListaParametros();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, destino));
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
					Collection colecaoPesquisaDestino = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
					if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
						throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
					}
					
					Localidade objetoLocalidadeDestino = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
				
					form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeDestino.getId()));
					
				}else{
					form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeOrigem.getId()));
				}
			}
		}
	}

	private void pesquisarLocalidade(String inscricaoTipo, SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm, 
										Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			localidadeID = (String) situacaoEspecialCobrancaActionForm.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				situacaoEspecialCobrancaActionForm.setLocalidadeOrigemID("");
				situacaoEspecialCobrancaActionForm.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				situacaoEspecialCobrancaActionForm.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialCobrancaActionForm.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				situacaoEspecialCobrancaActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialCobrancaActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				situacaoEspecialCobrancaActionForm.setLocalidadeDestinoID("");
				situacaoEspecialCobrancaActionForm.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				situacaoEspecialCobrancaActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialCobrancaActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
			}
		}
	}

	private void pesquisarSetorComercial(String inscricaoTipo, SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm, 
											Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		String setorComercialCD = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) situacaoEspecialCobrancaActionForm.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm.setSetorComercialOrigemCD("");
					situacaoEspecialCobrancaActionForm.setSetorComercialOrigemID("");
					situacaoEspecialCobrancaActionForm.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);

					situacaoEspecialCobrancaActionForm.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());

					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());

					httpServletRequest.setAttribute("corSetorComercialOrigem", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				situacaoEspecialCobrancaActionForm.setSetorComercialOrigemCD("");
				situacaoEspecialCobrancaActionForm.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeOrigemID");
			}
		} else {

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) situacaoEspecialCobrancaActionForm.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) situacaoEspecialCobrancaActionForm.getSetorComercialDestinoCD();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoCD("");
					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoID("");
					situacaoEspecialCobrancaActionForm.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialCobrancaActionForm.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialCobrancaActionForm.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				}
			} else {
				situacaoEspecialCobrancaActionForm.setSetorComercialDestinoCD("");
				situacaoEspecialCobrancaActionForm.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
			}
		}

	}

	private void pesquisarQuadra(String inscricaoTipo, SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
									Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String quadraNM = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) situacaoEspecialCobrancaActionForm.getSetorComercialOrigemCD();
			setorComercialID = (String) situacaoEspecialCobrancaActionForm.getSetorComercialOrigemID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (possuiSetorComercialCD() && possuiSetorComercialID()) {

				quadraNM = (String) situacaoEspecialCobrancaActionForm.getQuadraOrigemNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm.setQuadraOrigemNM("");
					situacaoEspecialCobrancaActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					situacaoEspecialCobrancaActionForm.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					
					httpServletRequest.setAttribute("corQuadraOrigem","exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
					situacaoEspecialCobrancaActionForm.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
	
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				situacaoEspecialCobrancaActionForm.setQuadraOrigemNM("");
				situacaoEspecialCobrancaActionForm.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
			}
		} else {

			situacaoEspecialCobrancaActionForm.setInscricaoTipo("destino");

			setorComercialCD = (String) situacaoEspecialCobrancaActionForm.getSetorComercialDestinoCD();
			setorComercialID = (String) situacaoEspecialCobrancaActionForm.getSetorComercialDestinoID();

			if (possuiSetorComercialCD() && possuiSetorComercialID()) {

				quadraNM = (String) situacaoEspecialCobrancaActionForm.getQuadraDestinoNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					situacaoEspecialCobrancaActionForm.setQuadraDestinoNM("");
					situacaoEspecialCobrancaActionForm.setQuadraDestinoID("");
					// Mensagem de tela
					situacaoEspecialCobrancaActionForm.setQuadraMensagemDestino("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialCobrancaActionForm.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialCobrancaActionForm.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				situacaoEspecialCobrancaActionForm.setQuadraDestinoNM("");
				situacaoEspecialCobrancaActionForm.setQuadraMensagemDestino("Informe o setor comercial da inscrição.");
				
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

	}

	private SituacaoEspecialCobrancaHelper transferirActionFormParaHelper(SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm) {

		SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = new SituacaoEspecialCobrancaHelper();

		situacaoEspecialCobrancaHelper.setIdImovel(situacaoEspecialCobrancaActionForm.getIdImovel() == null ? "" 
													: situacaoEspecialCobrancaActionForm.getIdImovel());
		situacaoEspecialCobrancaHelper.setInscricaoTipo(situacaoEspecialCobrancaActionForm.getInscricaoTipo() == null ? ""
														: situacaoEspecialCobrancaActionForm.getInscricaoTipo());
		situacaoEspecialCobrancaHelper.setLoteDestino(situacaoEspecialCobrancaActionForm.getLoteDestino() == null ? "" 
														: situacaoEspecialCobrancaActionForm.getLoteDestino());
		situacaoEspecialCobrancaHelper.setQuadraDestinoNM(situacaoEspecialCobrancaActionForm.getQuadraDestinoNM() == null ? "" 
															: situacaoEspecialCobrancaActionForm.getQuadraDestinoNM());
		situacaoEspecialCobrancaHelper.setLoteOrigem(situacaoEspecialCobrancaActionForm.getLoteOrigem() == null ? ""
														: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper.setNomeLocalidadeOrigem(situacaoEspecialCobrancaActionForm.getNomeLocalidadeOrigem() == null ? ""
																: situacaoEspecialCobrancaActionForm.getNomeLocalidadeOrigem());
		
		situacaoEspecialCobrancaHelper.setNomeSetorComercialOrigem(situacaoEspecialCobrancaActionForm.getNomeSetorComercialOrigem() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getNomeSetorComercialOrigem());
		
		situacaoEspecialCobrancaHelper.setQuadraOrigemNM(situacaoEspecialCobrancaActionForm.getQuadraOrigemNM() == null ? ""
															: situacaoEspecialCobrancaActionForm.getQuadraOrigemNM());

		situacaoEspecialCobrancaHelper.setQuadraMensagemOrigem(situacaoEspecialCobrancaActionForm.getQuadraMensagemOrigem() == null ? ""
																: situacaoEspecialCobrancaActionForm.getQuadraMensagemOrigem());

		situacaoEspecialCobrancaHelper.setNomeLocalidadeDestino(situacaoEspecialCobrancaActionForm.getNomeLocalidadeDestino() == null ? ""
																: situacaoEspecialCobrancaActionForm.getNomeLocalidadeDestino());

		situacaoEspecialCobrancaHelper.setSetorComercialDestinoCD(situacaoEspecialCobrancaActionForm.getSetorComercialDestinoCD() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getSetorComercialDestinoCD());

		situacaoEspecialCobrancaHelper.setSetorComercialOrigemCD(situacaoEspecialCobrancaActionForm.getSetorComercialOrigemCD() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getSetorComercialOrigemCD());

		situacaoEspecialCobrancaHelper.setSetorComercialOrigemID(situacaoEspecialCobrancaActionForm.getSetorComercialOrigemID() == null ? ""
																: situacaoEspecialCobrancaActionForm.getSetorComercialOrigemID());

		situacaoEspecialCobrancaHelper.setQuadraOrigemID(situacaoEspecialCobrancaActionForm.getQuadraOrigemID() == null ? ""
															: situacaoEspecialCobrancaActionForm.getQuadraOrigemID());

		situacaoEspecialCobrancaHelper.setLocalidadeDestinoID(situacaoEspecialCobrancaActionForm.getLocalidadeDestinoID() == null ? ""
																: situacaoEspecialCobrancaActionForm.getLocalidadeDestinoID());

		situacaoEspecialCobrancaHelper.setLocalidadeOrigemID(situacaoEspecialCobrancaActionForm.getLocalidadeOrigemID() == null ? ""
																: situacaoEspecialCobrancaActionForm.getLocalidadeOrigemID());

		situacaoEspecialCobrancaHelper.setNomeSetorComercialDestino(situacaoEspecialCobrancaActionForm.getNomeSetorComercialDestino() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getNomeSetorComercialDestino());

		situacaoEspecialCobrancaHelper.setSetorComercialDestinoID(situacaoEspecialCobrancaActionForm.getSetorComercialDestinoID() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getSetorComercialDestinoID());

		situacaoEspecialCobrancaHelper.setQuadraMensagemDestino(situacaoEspecialCobrancaActionForm.getQuadraMensagemDestino() == null ? ""
																: situacaoEspecialCobrancaActionForm.getQuadraMensagemDestino());

		situacaoEspecialCobrancaHelper.setQuadraDestinoID(situacaoEspecialCobrancaActionForm.getQuadraDestinoID() == null ? ""
															: situacaoEspecialCobrancaActionForm.getQuadraDestinoID());

		situacaoEspecialCobrancaHelper.setTipoSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm.getTipoSituacaoEspecialCobranca() == null ? ""
																		: situacaoEspecialCobrancaActionForm.getTipoSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper.setLoteOrigem(situacaoEspecialCobrancaActionForm.getLoteOrigem() == null ? ""
														: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper.setLoteDestino(situacaoEspecialCobrancaActionForm.getLoteDestino() == null ? ""
														: situacaoEspecialCobrancaActionForm.getLoteDestino());

		situacaoEspecialCobrancaHelper.setSubloteOrigem(situacaoEspecialCobrancaActionForm.getSubloteOrigem() == null ? ""
														: situacaoEspecialCobrancaActionForm.getSubloteOrigem());

		situacaoEspecialCobrancaHelper.setSubloteDestino(situacaoEspecialCobrancaActionForm.getSubloteDestino() == null ? ""
															: situacaoEspecialCobrancaActionForm.getSubloteDestino());

		situacaoEspecialCobrancaHelper.setIdCobrancaSituacaoMotivo(situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoMotivo() == null ? ""
																	: situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoMotivo());

		situacaoEspecialCobrancaHelper.setIdCobrancaSituacaoTipo(situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoTipo() == null ? ""
																: situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoTipo());

		situacaoEspecialCobrancaHelper.setMesAnoReferenciaCobrancaInicial(situacaoEspecialCobrancaActionForm.getMesAnoReferenciaCobrancaInicial() == null ? ""
																			: situacaoEspecialCobrancaActionForm.getMesAnoReferenciaCobrancaInicial());

		situacaoEspecialCobrancaHelper.setMesAnoReferenciaCobrancaFinal(situacaoEspecialCobrancaActionForm.getMesAnoReferenciaCobrancaFinal() == null ? ""
																		: situacaoEspecialCobrancaActionForm.getMesAnoReferenciaCobrancaFinal());

		situacaoEspecialCobrancaHelper.setQuantidadeImoveisCOMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca() == null ? ""
																						: situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper.setQuantidadeImoveisSEMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm.getQuantidadeImoveisSEMSituacaoEspecialCobranca() == null ? ""
																						: situacaoEspecialCobrancaActionForm.getQuantidadeImoveisSEMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper.setQuantidadeImoveisAtualizados(situacaoEspecialCobrancaActionForm.getQuantidadeImoveisAtualizados() == null ? ""
																		: situacaoEspecialCobrancaActionForm.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialCobrancaHelper.setCodigoRotaInicial(situacaoEspecialCobrancaActionForm.getCdRotaInicial() == null ? ""
																: situacaoEspecialCobrancaActionForm.getCdRotaInicial());
		
		situacaoEspecialCobrancaHelper.setCodigoRotaFinal(situacaoEspecialCobrancaActionForm.getCdRotaFinal() == null ? ""
															: situacaoEspecialCobrancaActionForm.getCdRotaFinal());
		
		situacaoEspecialCobrancaHelper.setSequencialRotaInicial(situacaoEspecialCobrancaActionForm.getSequencialRotaInicial() == null ? ""
																: situacaoEspecialCobrancaActionForm.getSequencialRotaInicial());
		
		situacaoEspecialCobrancaHelper.setSequencialRotaFinal(situacaoEspecialCobrancaActionForm.getSequencialRotaFinal() == null ? ""
																: situacaoEspecialCobrancaActionForm.getSequencialRotaFinal());
		
		
		if (situacaoEspecialCobrancaActionForm.getIdsCategoria() != null) {
			
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
	
	private boolean possuiCOMSituacaoEspecialCobranca(){
		return COMSituacaoEspecialCobranca != null && !COMSituacaoEspecialCobranca.isEmpty();
	}
	
	private boolean possuiSEMSituacaoEspecialCobranca(){
		return SEMSituacaoEspecialCobranca != null && !SEMSituacaoEspecialCobranca.isEmpty();
	}
	
	private boolean possuiLocalidadeOrigem(){
		return localidadeOrigemID != null && !localidadeOrigemID.equals("");
	}
	
	private boolean possuiLocalidadeDestinoID(){
		return localidadeDestinoID != null && !localidadeDestinoID.equals("");
	}
	
	private boolean possuiSetorComercialOrigemCD(){
		return setorComercialOrigemCD != null && !setorComercialOrigemCD.equals("");
	}
	
	private boolean possuiSetorComercialDestinoCD(){
		return setorComercialDestinoCD != null && !setorComercialDestinoCD.equals("");
	}
	
	private boolean possuiQuadraOrigemNM(){
		return quadraOrigemNM != null && !quadraOrigemNM.equals("");
	}
	
	private boolean possuiQuadraDestinoNM(){
		return quadraDestinoNM != null && !quadraDestinoNM.equals("");
	}
	
	private boolean possuiLoteOrigem(){
		return loteOrigem != null && !loteOrigem.equals("");
	}
	
	private boolean possuiLoteDestino(){
		return loteDestino != null && !loteDestino.equals("");
	}
	
	private boolean possuiSubLoteOrigem(){
		return subloteOrigem != null && !subloteOrigem.equals("");
	}
	
	private boolean possuiSubLoteDestino(){
		return subloteDestino != null && !subloteDestino.equals("");
	}
	
	private boolean possuiInscricaoTipo(){
		return inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("");
	}
	
	private boolean possuiObjetoConsulta(){
		return objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("");
	}
	
	private boolean possuiSetorComercialCD(){
		return setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("");
	}
	
	private boolean possuiSetorComercialID(){
		return setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("");
	}
}
