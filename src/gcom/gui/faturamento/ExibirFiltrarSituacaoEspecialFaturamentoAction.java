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
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = 
			(FiltrarSituacaoEspecialFaturamentoActionForm) actionForm;
				
		setarCategoriasAtributoRequisicao(httpServletRequest);
		
		definirObjetoAPesquisar(httpServletRequest,situacaoEspecialFaturamentoActionForm);

		pesquisarEnderecoImovel(sessao, httpServletRequest,situacaoEspecialFaturamentoActionForm);

		validacaoInscricaoOrigemDestino(situacaoEspecialFaturamentoActionForm);

		if (httpServletRequest.getParameter("bloquear") != null) {
			if (httpServletRequest.getParameter("bloquear").equals("matricula")) {
				httpServletRequest.setAttribute("bloquear", "matricula");
			} else {
				httpServletRequest.setAttribute("bloquear", "todos");
			}

		} else {
			httpServletRequest.setAttribute("bloquear", "");
		}

		if (Util.verificarNaoVazio(httpServletRequest.getParameter("campoDesabilita"))) {			
			httpServletRequest.setAttribute("campoDesabilita" ,
					httpServletRequest.getParameter("campoDesabilita"));
		}
		
		if(httpServletRequest.getParameter("menu") != null){
			situacaoEspecialFaturamentoActionForm.setIndicadorConsumoImovel("3");
			situacaoEspecialFaturamentoActionForm.setIndicadorAtualizar("1");
		}
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

		Collection colecaoFatSituacaoTipo = this.getFachada().pesquisar(
				filtroFaturamentoSituacaoTipo,
				FaturamentoSituacaoTipo.class.getName());

		httpServletRequest.setAttribute("colecaoFatSituacaoTipo",colecaoFatSituacaoTipo);
		

		return 	actionMapping.findForward("exibirFiltrarSituacaoEspecialFaturamento");
	}

	/**
	 * Esse m�todo pesquisa o im�vel informado pelo usu�rio, caso
	 * o mesmo tenha feito isso, e se este imovel existir exibe o endere�o na tela
	 * caso n�o exista exibe uma msg ao usu�rio informando isso.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void pesquisarEnderecoImovel(HttpSession sessao,HttpServletRequest httpServletRequest,
			FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm) {

			String idImovel = situacaoEspecialFaturamentoActionForm.getIdImovel();

			if (Util.verificarNaoVazio(idImovel)) {
				
				ClienteImovel clienteImovel  = pesquisarImovel(idImovel);

				if (clienteImovel !=null ) {
					
					// Obter a quantidade de economias do im�vel escolhido
//					int quantEconomias = 
//						this.getFachada().obterQuantidadeEconomias(clienteImovel.getImovel());
//					sessao.setAttribute("quantEconomias", String.valueOf(quantEconomias));

					situacaoEspecialFaturamentoActionForm.setEndereco(
							clienteImovel.getImovel().getEnderecoFormatado());
					
					situacaoEspecialFaturamentoActionForm.setInscricaoImovel(
						clienteImovel.getImovel().getInscricaoFormatada());
					
					//d� o foco no bot�o de filtrar
					httpServletRequest.setAttribute("nomeCampo", "botaoFiltrar");
					httpServletRequest.setAttribute("enderecoFormatado", "true");
					
				} else {
					situacaoEspecialFaturamentoActionForm.setInscricaoImovel("MATR�CULA INEXISTENTE");
					situacaoEspecialFaturamentoActionForm.setEndereco("");

					httpServletRequest.setAttribute("corImovel", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idImovel");

				}
			}

	}

	/**
	 * Esse m�todo retorna um ClienteImovel a partir 
	 * do id do im�vel informado pelo usu�rio na tela,
	 * caso o im�vel de fato exista.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private ClienteImovel pesquisarImovel(String idImovel) {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		// Objetos que ser�o retornados pelo Hibernate

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

		// adiciona o indicador de exclus�o do imovel
		filtroClienteImovel.adicionarParametro(
			new ParametroSimplesDiferenteDe(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
				Imovel.IMOVEL_EXCLUIDO,FiltroParametro.CONECTOR_OR, 2));

		filtroClienteImovel.adicionarParametro(
			new ParametroNulo(FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

		filtroClienteImovel.adicionarParametro(
			new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,idImovel));

		Collection<ClienteImovel> clientesImoveis = 
			this.getFachada().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());
		
		if(!Util.isVazioOrNulo(clientesImoveis)){
			return clientesImoveis.iterator().next();
		}
		
		return null;
	}

	/**
	 *
	 * Esse m�todo definie qual objeto ele ir� pesquisar para exibir na tela:
	 * localidade, setor, quadra.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void definirObjetoAPesquisar(HttpServletRequest httpServletRequest,
			FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm) {

		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = httpServletRequest.getParameter("inscricaoTipo");

		if ( Util.verificarNaoVazio(objetoConsulta) && 
				Util.verificarNaoVazio(inscricaoTipo)) {

			switch (Integer.parseInt(objetoConsulta)) {
			
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				pesquisarQuadra(inscricaoTipo,situacaoEspecialFaturamentoActionForm,
					httpServletRequest);

				break;
			default:
				break;
			}
		} 
//		else {
//			
//			sessao.removeAttribute("situacaoEspecialFaturamentoActionForm");
//		}
	}

	/**
	 *
	 * Este m�todo consulta as categorias ativas e seta-as como um atributo no request.
	 *
	 *@since 11/08/2009
	 *@author Marlon Patrick
	 */
	private void setarCategoriasAtributoRequisicao(
			HttpServletRequest httpServletRequest) {
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(
			new ParametroSimples(FiltroCategoria.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria,Categoria.class.getName());
		
		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
	}

	/**
	 * Valida todos os campos das inscri��es inicial e final.
	 * Verifica se os mesmos existem e se o destino � >= a origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validacaoInscricaoOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {
	
		validarLocalidadesOrigemDestino(form);

		validarSetorComercialOrigemDestino(form);

		validarQuadraOrigemDestino(form);

		validarLoteOrigemDestino(form);

		validarSubloteOrigemDestino(form);
	}

	/**
	 *Esse m�todo valida se o sublote de destino � >= ao sublote de origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarSubloteOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		if ( Util.verificarNaoVazio(form.getSubloteOrigem())
				&& Util.verificarNaoVazio(form.getSubloteDestino())) {
				
				try {
					int origem = Integer.parseInt(form.getSubloteOrigem());
					int destino = Integer.parseInt(form.getSubloteDestino());

					if (origem > destino) {
						throw new ActionServletException(
								"atencao.sublote.final.maior.sublote.inicial",null, "");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",null,"SubLote(s)");
				}
		}
	}

	/**
	 *Esse m�todo valida se o lote de destino � >= ao lote de origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarLoteOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {

		if (Util.verificarNaoVazio(form.getLoteOrigem()) 
				&& Util.verificarNaoVazio(form.getLoteDestino())) {
			try {
				int origem = Integer.parseInt(form.getLoteOrigem());
				int destino = Integer.parseInt(form.getLoteDestino());
				if (origem > destino) {
					throw new ActionServletException("atencao.lote.final.maior.lote.inical", null,"");
				}
			} catch (NumberFormatException e) {
				throw new ActionServletException("atencao.nao.numerico",null, "Lote(s)");
			}
		}
	}

	/**
	 * Esse m�todo faz a valida��o que verifica se as quadras, tanto de destino quanto de origem,
	 * realmente existem. Al�m disso, valida se a quadra de destino � >= a quadra de origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarQuadraOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {

		if ( Util.verificarNaoVazio(form.getQuadraOrigemNM()) 
				&& Util.verificarNaoVazio(form.getQuadraDestinoNM())) {

				int origem = Integer.parseInt(form.getQuadraOrigemNM());
				int destino = Integer.parseInt(form.getQuadraDestinoNM());
				
				if (origem > destino){
					throw new ActionServletException("atencao.quadra.final.maior.quadra.inical", null,"");
				}
				String quadraNM = form.getQuadraOrigemNM();

				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, 
						form.getSetorComercialOrigemID()));
			
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, 
						quadraNM));
			
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				Collection<Quadra> colecaoPesquisa = 
					this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			
				if ( Util.isVazioOrNulo(colecaoPesquisa)) {
					throw new ActionServletException("atencao.quadra.inexistente");
				}
				Quadra objetoQuadra = colecaoPesquisa.iterator().next();
				form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));

				//caso as quadras de origem e destino sejam diferentes
				//deve-se validar as duas quadras.
				if(origem < destino){
					
					filtroQuadra.limparListaParametros();
					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL, 
							form.getSetorComercialOrigemID()));
				
					// Adiciona o n�mero da quadra que esta no formul�rio para
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
					Collection<Quadra> colecaoPesquisaDestino = 
						this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
				
					if ( Util.isVazioOrNulo(colecaoPesquisaDestino)) {
						throw new ActionServletException("atencao.quadra.inexistente");
					}
					Quadra objetoQuadraDestino = colecaoPesquisaDestino.iterator().next();
					form.setQuadraDestinoID(String.valueOf(objetoQuadraDestino.getId()));
				}else{
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
				}
		}
	}

	/**
	 * Esse m�todo faz a valida��o que verifica se os setores comerciais, tanto de destino quanto de origem,
	 * realmente existem. Al�m disso, valida se o setor comercial de destino 
	 * � >= ao setor comercial de destino de origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarSetorComercialOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		if (Util.verificarNaoVazio(form.getSetorComercialOrigemCD())
				&& Util.verificarNaoVazio(form.getSetorComercialDestinoCD())) {
				
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form.getSetorComercialDestinoCD());
				
				if (origem > destino){
					throw new ActionServletException(
						"atencao.setor.comercial.final.maior.setor.comercial.inicial",null, "");
				}
				
				String setorComercialCD = form.getSetorComercialOrigemCD();
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						form.getLocalidadeOrigemID()));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<SetorComercial> colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (Util.isVazioOrNulo(colecaoPesquisa)) {
					//Setor Comercial inexistente.
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}
		
				SetorComercial objetoSetorComercial = colecaoPesquisa.iterator().next();
				form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
				
				//caso os setores sejam diferentes
				//deve-se validar os dois
				if (origem < destino){
					
					filtroSetorComercial.limparListaParametros();
					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, 
							form.getLocalidadeOrigemID()));

					// Adiciona o c�digo do setor comercial que esta no formul�rio
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
					Collection<SetorComercial> colecaoPesquisaDestino = 
						this.getFachada().pesquisar(filtroSetorComercial,SetorComercial.class.getName());

					if ( Util.isVazioOrNulo(colecaoPesquisaDestino)) {
						//Setor Comercial inexistente.
						throw new ActionServletException("atencao.setor_comercial.inexistente");
					}
					SetorComercial objetoSetorComercialDestino = colecaoPesquisaDestino.iterator().next();		
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercialDestino.getId()));
				
				}else{
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));	
				}					
		}
	}

	/**
	 * Esse m�todo faz a valida��o que verifica se as localidades, tanto de destino quanto de origem,
	 * realmente existem. Al�m disso, valida se a localidade de destino � >= a localidade de origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidadesOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {

		if (Util.verificarNaoVazio(form.getLocalidadeOrigemID())
				&& Util.verificarNaoVazio(form.getLocalidadeDestinoID())) {
				
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				
				if (origem > destino){
					throw new ActionServletException(
							"atencao.localidade.final.maior.localidade.inicial",null, "");
				}
				
				String localidadeID = form.getLocalidadeOrigemID();

				FiltroLocalidade filtroLocalidade =  new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID, 
						localidadeID));
			
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			
				Collection<Localidade> colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
			
				if ( Util.isVazioOrNulo(colecaoPesquisa)) {
					// Localidade nao encontrada
					throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
				}
				
				Localidade objetoLocalidadeOrigem = colecaoPesquisa.iterator().next();			
				form.setLocalidadeOrigemID(String.valueOf(objetoLocalidadeOrigem.getId()));
				
				//nesse caso devemos verificar se a localidade final
				//tamb�m existe
				if (origem < destino){
					
					filtroLocalidade.limparListaParametros();
					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.ID, destino));
				
					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
				
					Collection<Localidade> colecaoPesquisaDestino = 
						this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
				
					if ( Util.isVazioOrNulo(colecaoPesquisaDestino)) {
						// Localidade nao encontrada
						throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
					}
					Localidade objetoLocalidadeDestino = colecaoPesquisaDestino.iterator().next();
					form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeDestino.getId()));

				}else{
					form.setLocalidadeDestinoID(String.valueOf(objetoLocalidadeOrigem.getId()));
				}
			
		}
	}

	private void pesquisarLocalidade(
			String inscricaoTipo,FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest) {

		String localidadeID = null;

		Collection<Localidade> colecaoPesquisa = null;

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");

			localidadeID = situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();

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
				this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoPesquisa)) {
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				situacaoEspecialFaturamentoActionForm.setLocalidadeOrigemID("");
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = colecaoPesquisa.iterator().next();
				
				situacaoEspecialFaturamentoActionForm.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
			}
		} else {//destino
			localidadeID = situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID, 
					localidadeID));

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = 
				this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			if ( Util.isVazioOrNulo(colecaoPesquisa)) {
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID("");
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = colecaoPesquisa.iterator().next();
				
				situacaoEspecialFaturamentoActionForm.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				situacaoEspecialFaturamentoActionForm.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(
			String inscricaoTipo,FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest) {

		String localidadeID = null;
		String setorComercialCD = null;

		Collection<SetorComercial> colecaoPesquisa = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");

			localidadeID = situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();

			if (Util.verificarNaoVazio(localidadeID)) {

				setorComercialCD = situacaoEspecialFaturamentoActionForm.getSetorComercialOrigemCD();

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						localidadeID));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,SetorComercial.class.getName());

				if ( Util.isVazioOrNulo(colecaoPesquisa)) {
					
					// Limpa os campos setorComercialOrigemCD,
					//nomeSetorComercialOrigem e setorComercialOrigemID do

					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemCD("");
					situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemID("");
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem","exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = colecaoPesquisa.iterator().next();

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
				// Limpa o campo setorComercialOrigemCD do formul�rio
				situacaoEspecialFaturamentoActionForm.setSetorComercialOrigemCD("");
				situacaoEspecialFaturamentoActionForm.setNomeSetorComercialOrigem("Informe Localidade Inicial.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			}
		} else {

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			localidadeID = situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();

			if ( Util.verificarNaoVazio(localidadeID)) {

				setorComercialCD = situacaoEspecialFaturamentoActionForm.getSetorComercialDestinoCD();

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
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

				if ( Util.isVazioOrNulo(colecaoPesquisa)) {

					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD("");
					situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoID("");
					situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino("Setor Comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino","exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
				} else {
					
					SetorComercial objetoSetorComercial = colecaoPesquisa.iterator().next();
					
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
				// Limpa o campo setorComercialDestinoCD do formul�rio
				situacaoEspecialFaturamentoActionForm.setSetorComercialDestinoCD("");
				situacaoEspecialFaturamentoActionForm.setNomeSetorComercialDestino("Informe Localidade Final.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino","exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			}
		}

	}

	

	public void pesquisarQuadra(
			String inscricaoTipo,
			FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm, 
			HttpServletRequest httpServletRequest){
		
		String setorComercialCD = null;
		String setorComercialID = null;
		String quadraNM = null;
		
		Collection<Quadra> colecaoPesquisa = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("origem");
			
			setorComercialCD = situacaoEspecialFaturamentoActionForm.getSetorComercialOrigemCD();

			setorComercialID = situacaoEspecialFaturamentoActionForm.getSetorComercialOrigemID();

			if (Util.verificarNaoVazio(setorComercialCD)
					&& Util.verificarNaoVazio(setorComercialID)) {

				quadraNM = situacaoEspecialFaturamentoActionForm.getQuadraOrigemNM();

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

				if ( Util.isVazioOrNulo(colecaoPesquisa)) {
					// Limpa os campos quadraOrigemNM e quadraOrigemID
					situacaoEspecialFaturamentoActionForm.setQuadraOrigemNM("");
					situacaoEspecialFaturamentoActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					situacaoEspecialFaturamentoActionForm.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem","exception");
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
				} else {
					Quadra objetoQuadra = colecaoPesquisa.iterator().next();
					
					situacaoEspecialFaturamentoActionForm
							.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));

					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				situacaoEspecialFaturamentoActionForm.setQuadraOrigemNM("");
				situacaoEspecialFaturamentoActionForm
						.setQuadraMensagemOrigem("Informe Setor Comercial Inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
			}
		} else {

			situacaoEspecialFaturamentoActionForm.setInscricaoTipo("destino");

			setorComercialCD = situacaoEspecialFaturamentoActionForm.getSetorComercialDestinoCD();
			setorComercialID = situacaoEspecialFaturamentoActionForm.getSetorComercialDestinoID();

			if (Util.verificarNaoVazio(setorComercialCD)
					&& Util.verificarNaoVazio(setorComercialID)) {

				quadraNM = situacaoEspecialFaturamentoActionForm.getQuadraDestinoNM();

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

				if ( Util.isVazioOrNulo(colecaoPesquisa)) {
					// Limpa os campos quadraOrigemNM e quadraOrigemID
					
					situacaoEspecialFaturamentoActionForm.setQuadraDestinoNM("");
					situacaoEspecialFaturamentoActionForm.setQuadraDestinoID("");
					// Mensagem de tela
					situacaoEspecialFaturamentoActionForm
							.setQuadraMensagemDestino("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino","exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				} else {
					Quadra objetoQuadra = colecaoPesquisa.iterator().next();
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					situacaoEspecialFaturamentoActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));

					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				situacaoEspecialFaturamentoActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				situacaoEspecialFaturamentoActionForm
						.setQuadraMensagemDestino("Informe Setor Comercial.");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

    }
	
}
