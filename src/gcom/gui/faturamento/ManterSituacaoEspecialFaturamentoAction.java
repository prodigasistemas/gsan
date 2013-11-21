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
package gcom.gui.faturamento;

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
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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

public class ManterSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = 
			actionMapping.findForward("exibirSituacaoEspecialFaturamentoInformar");


		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = 
			(SituacaoEspecialFaturamentoActionForm) actionForm;
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");
		
		// ----------- Colecao de categoria -----------------
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(
			new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria,
				Categoria.class.getName());
		
		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		
		// ----------- Colecao de categoria -----------------
		
		if (objetoConsulta != null && 
			!objetoConsulta.trim().equalsIgnoreCase("") && 
			inscricaoTipo != null && 
			!inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarQuadra(inscricaoTipo,
					situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("situacaoEspecialFaturamentoActionForm");
		}

		if (situacaoEspecialFaturamentoActionForm != null) {
			
			String idImovel = 
				situacaoEspecialFaturamentoActionForm.getIdImovel();

			if (idImovel != null && !idImovel.equals("")) {
				
				// Pesquisa o imovel para mandar para a pagina informacoes sobre
				// o endereco
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				// Objetos que serão retornados pelo Hibernate

				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.areaConstruidaFaixa");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");

				// adiciona o indicador de exclusão do imovel
				filtroClienteImovel.adicionarParametro(
					new ParametroSimplesDiferenteDe(
						FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
						Imovel.IMOVEL_EXCLUIDO,
						FiltroParametro.CONECTOR_OR, 2));

				filtroClienteImovel.adicionarParametro(
					new ParametroNulo(
						FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

				filtroClienteImovel.adicionarParametro(
					new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, 
						idImovel));

				Collection clientesImoveis = 
					this.getFachada().pesquisar(
						filtroClienteImovel, 
						ClienteImovel.class.getName());

				if (!clientesImoveis.isEmpty()) {
					
					ClienteImovel clienteImovel = 
						(ClienteImovel) ((List) clientesImoveis).get(0);

					// Obter a quantidade de economias do imóvel escolhido
					int quantEconomias = 
						this.getFachada().obterQuantidadeEconomias(
							clienteImovel.getImovel());

					// Seta no request
					sessao.setAttribute("quantEconomias", String.valueOf(quantEconomias));
					situacaoEspecialFaturamentoActionForm.setEndereco(
						clienteImovel.getImovel().getEnderecoFormatado());
					
					situacaoEspecialFaturamentoActionForm.setInscricaoImovel(
						clienteImovel.getImovel().getInscricaoFormatada());
					
					httpServletRequest.setAttribute("nomeCampo", "selecionar");
					httpServletRequest.setAttribute("enderecoFormatado", "true");
					
				} else {
					situacaoEspecialFaturamentoActionForm.setInscricaoImovel("MATRÍCULA INEXISTENTE");
					situacaoEspecialFaturamentoActionForm.setEndereco("");

					httpServletRequest.setAttribute("corImovel", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idImovel");

				}
			}
		}

		validacaoFinal(situacaoEspecialFaturamentoActionForm);

		// Exibir quantidade de Imoveis com situacao especial de faturamento
		if (httpServletRequest.getParameter("consultaQuantidadeImoveis") != null) {
			
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = 
				transferirActionFormParaHelper(
					situacaoEspecialFaturamentoActionForm,usuarioLogado);

			sessao.removeAttribute("COMSituacaoEspecialFaturamento");
			
			Collection COMSituacaoEspecialFaturamento = 
				this.getFachada()
					.pesquisarImovelSituacaoEspecialFaturamento("COM",situacaoEspecialFaturamentoHelper);
			
			sessao.removeAttribute("SEMSituacaoEspecialFaturamento");
			
			Collection SEMSituacaoEspecialFaturamento = 
				this.getFachada().pesquisarImovelSituacaoEspecialFaturamento("SEM",
					situacaoEspecialFaturamentoHelper);

			if ((COMSituacaoEspecialFaturamento == null || COMSituacaoEspecialFaturamento.isEmpty())
					&& (SEMSituacaoEspecialFaturamento == null || SEMSituacaoEspecialFaturamento.isEmpty())) {
				
				throw new ActionServletException("atencao.nao.parametro.informado", null, "");
			}

			if (COMSituacaoEspecialFaturamento != null && 
				!COMSituacaoEspecialFaturamento.isEmpty()) {
				
				httpServletRequest.setAttribute("liberarBotaoRetirar", "true");
				sessao.setAttribute("COMSituacaoEspecialFaturamento", COMSituacaoEspecialFaturamento);
			}

			if (SEMSituacaoEspecialFaturamento != null && !SEMSituacaoEspecialFaturamento.isEmpty()) {
				httpServletRequest.setAttribute("liberarBotaoInserir", "true");
				sessao.setAttribute("SEMSituacaoEspecialFaturamento",SEMSituacaoEspecialFaturamento);
			}

			situacaoEspecialFaturamentoActionForm.setQuantidadeImoveisCOMSituacaoEspecialFaturamento(
				""+ COMSituacaoEspecialFaturamento.size());
			situacaoEspecialFaturamentoActionForm.setQuantidadeImoveisSEMSituacaoEspecialFaturamento(
				""+ SEMSituacaoEspecialFaturamento.size());

			httpServletRequest.setAttribute("nomeCampo", "inserir");

		} else {
			situacaoEspecialFaturamentoActionForm.setQuantidadeImoveisCOMSituacaoEspecialFaturamento("");
			situacaoEspecialFaturamentoActionForm.setQuantidadeImoveisSEMSituacaoEspecialFaturamento("");
		}

		// Exibir quantidade de Imoveis com situacao especial de faturamento
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
		if (httpServletRequest.getParameter("campoDesabilita") != null && 
			!httpServletRequest.getParameter("campoDesabilita").equals("")) {
			
			httpServletRequest.setAttribute("campoDesabilita",
				httpServletRequest.getParameter("campoDesabilita"));
		}
		
		if(httpServletRequest.getParameter("menu") != null){
			situacaoEspecialFaturamentoActionForm.setIndicadorConsumoImovel("3");
		}

		return retorno;
	}

	private void validacaoFinal(SituacaoEspecialFaturamentoActionForm form) {

	
		// validar localidade inicial sendo maior que localidade final
		if (form.getLocalidadeOrigemID() != null && form.getLocalidadeDestinoID() != null) {
			
			if (!form.getLocalidadeOrigemID().equals("") && 
				!form.getLocalidadeDestinoID().equals("")) {
				
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				
				if (origem > destino){
					throw new ActionServletException(
							"atencao.localidade.final.maior.localidade.inicial",
							null, "");
				}
				
				String localidadeID = (String) form.getLocalidadeOrigemID();

				FiltroLocalidade filtroLocalidade =  new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID, 
						localidadeID));
			
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				// Retorna localidade
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,
						Localidade.class.getName());
			
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Localidade nao encontrada
					throw new ActionServletException(
					"atencao.pesquisa.localidade_inexistente");
				}else{
					Localidade objetoLocalidadeOrigem = 
						(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
					form.setLocalidadeOrigemID(String.valueOf(objetoLocalidadeOrigem.getId()));
					
					if (origem < destino){

						// se localidade inicial < localidade final
						//pesquisa p descobrir localidade final existe
						//se existir seta o id dela no actionForm
						
						filtroLocalidade.limparListaParametros();
						filtroLocalidade.adicionarParametro(
							new ParametroSimples(
								FiltroLocalidade.ID, destino));
					
						filtroLocalidade.adicionarParametro(
							new ParametroSimples(
								FiltroLocalidade.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
					
						// Retorna localidade
						Collection colecaoPesquisaDestino = 
							this.getFachada().pesquisar(filtroLocalidade,
								Localidade.class.getName());
					
						if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {

							// Localidade nao encontrada
							throw new ActionServletException(
							"atencao.pesquisa.localidade_inexistente");
						}
						Localidade objetoLocalidadeDestino = 
							(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
					
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeDestino.getId()));
					}else{
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeOrigem.getId()));
					}
				}
			}
		}

		// validar setor comercial sendo maior que setor comercial final
		if (form.getSetorComercialOrigemCD() != null && 
			form.getSetorComercialDestinoCD() != null) {
			
			if (!form.getSetorComercialOrigemCD().equals("") && 
				!form.getSetorComercialDestinoCD().equals("")) {
				
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form.getSetorComercialDestinoCD());
				
				if (origem > destino){
					throw new ActionServletException(
						"atencao.setor.comercial.final.maior.setor.comercial.inicial",
						null, 
						"");
				}
				
				String setorComercialCD = (String) form.getSetorComercialOrigemCD();

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						form.getLocalidadeOrigemID()));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					//Setor Comercial inexistente.
					throw new ActionServletException(
					"atencao.setor_comercial.inexistente");
				}else{
			
					SetorComercial objetoSetorComercial = 
						(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					
					if (origem < destino){
						
						//se setor comercial inicial < setor comercial final
						//pesquisa p descobrir setor comercial final existe
						//se existir seta o id dele no actionForm
						filtroSetorComercial.limparListaParametros();
						filtroSetorComercial.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, 
								form.getLocalidadeOrigemID()));

						// Adiciona o código do setor comercial que esta no formulário
						// para compor a pesquisa.
						filtroSetorComercial.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
								destino));

						filtroSetorComercial.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						// Retorna setorComercial
						Collection colecaoPesquisaDestino = 
							this.getFachada().pesquisar(filtroSetorComercial,
								SetorComercial.class.getName());

						if (colecaoPesquisaDestino == null || colecaoPesquisaDestino.isEmpty()) {
							
							//Setor Comercial inexistente.
							throw new ActionServletException(
							"atencao.setor_comercial.inexistente");
						}
						SetorComercial objetoSetorComercialDestino = 
							(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisaDestino);
					
						form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercialDestino.getId()));
					
					}else{
						form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));	
					}
					
				}
			}
		}

		// validar quadra sendo maior que localidade final

		if (form.getQuadraOrigemNM() != null && form.getQuadraDestinoNM() != null) {
			
			if (!form.getQuadraOrigemNM().equals("") && !form.getQuadraDestinoNM().equals("")) {
				
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
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, 
						form.getSetorComercialOrigemID()));
			
				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, 
						quadraNM));
			
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				// Retorna quadra
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			
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
						filtroQuadra.adicionarParametro(
							new ParametroSimples(
								FiltroQuadra.ID_SETORCOMERCIAL, 
								form.getSetorComercialOrigemID()));
					
						// Adiciona o número da quadra que esta no formulário para
						// compor a pesquisa.
						filtroQuadra.adicionarParametro(
							new ParametroSimples(
								FiltroQuadra.NUMERO_QUADRA, 
								quadraNM));
					
						filtroQuadra.adicionarParametro(
							new ParametroSimples(
								FiltroQuadra.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
					
						// Retorna quadra
						Collection colecaoPesquisaDestino = 
							this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
					
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


		// validar lote sendo maior que localidade final

		if (form.getLoteOrigem() != null && form.getLoteDestino() != null) {
			if (!form.getLoteOrigem().equals("") && !form.getLoteDestino().equals("")) {
				
				try {
					int origem = Integer.parseInt(form.getLoteOrigem());
					int destino = Integer.parseInt(form.getLoteDestino());
					if (origem > destino) {
						throw new ActionServletException(
							"atencao.lote.final.maior.lote.inical", null,"");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",null, "Lote(s)");
				}
			}
		}


		// validar sublote sendo maior que localidade final
		if (form.getSubloteOrigem() != null && form.getSubloteDestino() != null) {
			if (!form.getSubloteOrigem().equals("") && 
				!form.getSubloteDestino().equals("")) {
				
				try {
					int origem = Integer.parseInt(form.getSubloteOrigem());
					int destino = Integer.parseInt(form.getSubloteDestino());
					if (origem > destino) {
						throw new ActionServletException(
							"atencao.sublote.final.maior.sublote.inicial",
							null, 
							"");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException(
						"atencao.nao.numerico",
						null, 
						"SubLote(s)");
				}
			}
		}
		// validar Sublote sendo maior que localidade final

	}

	private void pesquisarLocalidade(
			String inscricaoTipo,
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.ID, 
					localidadeID));

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = 
				this.getFachada().pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				situacaoEspecialFaturamentoActionForm.setLocalidadeOrigemID("");
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				situacaoEspecialFaturamentoActionForm.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = 
				(String) situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID, 
					localidadeID));

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = 
				this.getFachada().pesquisar(filtroLocalidade,
					Localidade.class.getName());

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID("");
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(
			String inscricaoTipo,
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		String setorComercialCD = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = 
					(String) situacaoEspecialFaturamentoActionForm.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemCD("");
					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemID("");
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem","exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = 
						(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);

					// setorComercialOrigem
					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
					// setorComercialOrigem

					// setorComercialDestino
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					
					// setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialOrigem","valor");
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemCD("");
				situacaoEspecialFaturamentoActionForm.setNomeSetorComercialOrigem("Informe Localidade Inicial.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			}
		} else {

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && 
				!localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) situacaoEspecialFaturamentoActionForm.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD("");
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoID("");
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino("Setor Comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino","exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
				} else {
					
					SetorComercial objetoSetorComercial = 
						(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD(
						String.valueOf(objetoSetorComercial.getCodigo()));
					
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoID(
						String.valueOf(objetoSetorComercial.getId()));
					
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino(
						objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("corSetorComercialDestino","valor");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD("");
				situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino("Informe Localidade Final.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			}
		}

	}

	

	private SituacaoEspecialFaturamentoHelper transferirActionFormParaHelper(
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = 
			new SituacaoEspecialFaturamentoHelper();

		situacaoEspecialFaturamentoHelper
				.setIdImovel(situacaoEspecialFaturamentoActionForm
						.getIdImovel() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getIdImovel());

		situacaoEspecialFaturamentoHelper
				.setInscricaoTipo(situacaoEspecialFaturamentoActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getInscricaoTipo());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoNM(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoNM());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemNM(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemNM());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemOrigem(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeDestino(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemID(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeDestinoID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeOrigemID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialDestino(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemDestino(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoID(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoID());

		situacaoEspecialFaturamentoHelper
				.setTipoSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getTipoSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getTipoSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setSubloteOrigem(situacaoEspecialFaturamentoActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteOrigem());

		situacaoEspecialFaturamentoHelper
				.setSubloteDestino(situacaoEspecialFaturamentoActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteDestino());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoMotivo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoMotivo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoMotivo());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoTipo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoTipo());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoInicial(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoInicial() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoInicial());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoFinal(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoFinal() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoFinal());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisCOMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisSEMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisAtualizados());
		
		if (situacaoEspecialFaturamentoActionForm
				.getCdRotaInicial() == null || situacaoEspecialFaturamentoActionForm
				.getCdRotaInicial().trim().equals("")) {
			situacaoEspecialFaturamentoHelper
			.setCodigoRotaInicial("");
		} else {
			situacaoEspecialFaturamentoHelper.setCodigoRotaInicial(new Integer(
					situacaoEspecialFaturamentoActionForm.getCdRotaInicial())
					.toString());
		}
		
		if (situacaoEspecialFaturamentoActionForm
				.getCdRotaFinal() == null || situacaoEspecialFaturamentoActionForm
				.getCdRotaFinal().trim().equals("")) {
			situacaoEspecialFaturamentoHelper
			.setCodigoRotaFinal("");
		} else {
			situacaoEspecialFaturamentoHelper.setCodigoRotaFinal(new Integer(
					situacaoEspecialFaturamentoActionForm.getCdRotaFinal())
					.toString());
		}
		
		if (situacaoEspecialFaturamentoActionForm.getSequencialRotaInicial() == null
				|| situacaoEspecialFaturamentoActionForm
						.getSequencialRotaInicial().trim().equals("")) {
			situacaoEspecialFaturamentoHelper.setSequencialRotaInicial("");
		} else {
			situacaoEspecialFaturamentoHelper
					.setSequencialRotaInicial(new Integer(
							situacaoEspecialFaturamentoActionForm
									.getSequencialRotaInicial()).toString());
		}

		if (situacaoEspecialFaturamentoActionForm.getSequencialRotaFinal() == null
				|| situacaoEspecialFaturamentoActionForm
						.getSequencialRotaFinal().trim().equals("")) {
			situacaoEspecialFaturamentoHelper.setSequencialRotaFinal("");
		} else {
			situacaoEspecialFaturamentoHelper
					.setSequencialRotaFinal(new Integer(
							situacaoEspecialFaturamentoActionForm
									.getSequencialRotaFinal()).toString());
		}
		
		if (situacaoEspecialFaturamentoActionForm.getIdsCategoria() != null) {
			/*situacaoEspecialFaturamentoHelper.setIdCategoria(new Integer(
					situacaoEspecialFaturamentoActionForm.getCategoria()));*/
			
			String [] idsCategoria = situacaoEspecialFaturamentoActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		} 
		
		situacaoEspecialFaturamentoHelper.setIndicadorConsumoImovel(
				situacaoEspecialFaturamentoActionForm.getIndicadorConsumoImovel());
		
		situacaoEspecialFaturamentoHelper.setIdUsuario(usuarioLogado.getId().toString());
		
		return situacaoEspecialFaturamentoHelper;
	}

	
	public void pesquisarQuadra(
			String inscricaoTipo,
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest){
		
		
		Collection colecaoPesquisa = null;

		String setorComercialCD = null;

		String setorComercialID = null;

		String quadraNM = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) situacaoEspecialFaturamentoActionForm
					.getSetorComercialOrigemCD();

			setorComercialID = (String) situacaoEspecialFaturamentoActionForm
					.getSetorComercialOrigemID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) situacaoEspecialFaturamentoActionForm
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
				colecaoPesquisa = this.getFachada().pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NÃO ENCONTRADA !!!!!!!!!!!!!!!!!!!!!!!!
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					
					situacaoEspecialFaturamentoActionForm.setQuadraOrigemNM("");
					situacaoEspecialFaturamentoActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					situacaoEspecialFaturamentoActionForm
							.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				} else {
					
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ENCONTRADA !!!!!!!!!!!!!!!!!!!!!!!!!
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialFaturamentoActionForm
							.setQuadraOrigemNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraOrigemID(String.valueOf(objetoQuadra
									.getId()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));

					//httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				situacaoEspecialFaturamentoActionForm.setQuadraOrigemNM("");
				situacaoEspecialFaturamentoActionForm
						.setQuadraMensagemOrigem("Informe Setor Comercial Inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) situacaoEspecialFaturamentoActionForm
					.getSetorComercialDestinoCD();
			setorComercialID = (String) situacaoEspecialFaturamentoActionForm
					.getSetorComercialDestinoID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) situacaoEspecialFaturamentoActionForm
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
				colecaoPesquisa = this.getFachada().pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NÃO ENCONTRADA !!!!!!!!!!!!!!!!!!!!!!!!
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoNM("");
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoID("");
					// Mensagem de tela
					situacaoEspecialFaturamentoActionForm
							.setQuadraMensagemDestino("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				} else {
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ENCONTRADA !!!!!!!!!!!!!!!!!!!!!!!!!
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
					//httpServletRequest
					//		.setAttribute("corQuadraDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				situacaoEspecialFaturamentoActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				situacaoEspecialFaturamentoActionForm
						.setQuadraMensagemDestino("Informe Setor Comercial.");
				//httpServletRequest
						//.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

    }
	
}
