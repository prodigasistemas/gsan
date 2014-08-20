package gcom.gui.faturamento.conta;

import gcom.arrecadacao.banco.Banco;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
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

/**
 * [UC0407] Filtrar Im�veis para Inserir ou Manter Conta
 * 
 * @author Ana Maria
 * @created 12/03/2007
 */
public class ExibirFiltrarImovelInserirManterContaAction extends GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeIDOrigem = null;

	private String localidadeIDDestino = null;

	private String setorComercialCDOrigem = null;

	private String setorComercialCDDestino = null;

	private String setorComercialIDOrigem = null;

	private String setorComercialIDDestino = null;

	private String quadraNMOrigem = null;

	private String quadraNMDestino = null;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarImovelInserirManterConta");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// novo CRC1258 - Fl�vio Leonardo
		Collection<Banco> colecaoBancos = fachada.pesquisarBancoDebitoAutomatico();
		sessao.setAttribute("colecaoBancos", colecaoBancos);

		/**
		 * TODO:COSANPA
		 * 
		 * @author Adriana Muniz
		 * @date: 23/11/2011 Pesquisa a esfera de poder
		 * */
		Collection<EsferaPoder> colecaoEsferasPoder = fachada.pesquisarEsferaPoder();
		sessao.setAttribute("colecaoEsferasPoder", colecaoEsferasPoder);

		boolean habilitaBanco = fachada.verificarPermissaoEspecial(PermissaoEspecial.HABILITAR_BANCO_MANTER_CONTA,
				this.getUsuarioLogado(httpServletRequest));

		sessao.setAttribute("habilitaBanco", habilitaBanco);

		FiltrarImovelContaActionForm filtrarImovelContaActionForm = (FiltrarImovelContaActionForm) actionForm;

		String codigoCliente = (String) filtrarImovelContaActionForm.getCodigoCliente();

		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo,
				ClienteRelacaoTipo.class.getName());

		if (collectionClienteRelacaoTipo != null && !collectionClienteRelacaoTipo.isEmpty()) {
			httpServletRequest.setAttribute("collectionClienteRelacaoTipo", collectionClienteRelacaoTipo);
		} else {
			throw new ActionServletException("atencao.collectionClienteRelacaoTipo_inexistente", null, "id");
		}

		String idDigitadoEnterClienteSuperior = (String) filtrarImovelContaActionForm.getCodigoClienteSuperior();
		// verifica se o codigo do cliente superior foi digitado
		if (idDigitadoEnterClienteSuperior != null && !idDigitadoEnterClienteSuperior.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterClienteSuperior) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idDigitadoEnterClienteSuperior));
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo", null, ""
							+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
				}

				filtrarImovelContaActionForm.setCodigoClienteSuperior(((Cliente) ((List) clienteEncontrado).get(0)).getId().toString());
				filtrarImovelContaActionForm.setNomeClienteSuperior(((Cliente) ((List) clienteEncontrado).get(0)).getNome());

			} else {
				httpServletRequest.setAttribute("corClienteSuperior", "exception");
				filtrarImovelContaActionForm.setCodigoClienteSuperior("");
				filtrarImovelContaActionForm.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);

			}
		}

		// PESQUISAR CLIENTE
		if (codigoCliente != null && !codigoCliente.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCliente(codigoCliente, filtrarImovelContaActionForm, fachada, httpServletRequest);
		}

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				pesquisarQuadra(inscricaoTipo, filtrarImovelContaActionForm, fachada, httpServletRequest);

				break;
			default:
				break;
			}
		}

		return retorno;
	}

	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente(String idCliente, FiltrarImovelContaActionForm form, Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente, Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O Cliente foi encontrado
			if (((Cliente) ((List) clienteEncontrado).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				throw new ActionServletException("atencao.cliente.inativo", null, ""
						+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			}

			form.setCodigoCliente(((Cliente) ((List) clienteEncontrado).get(0)).getId().toString());
			form.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());

		} else {
			httpServletRequest.setAttribute("corCliente", "exception");
			form.setCodigoCliente("");
			form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
		}
	}

	private void pesquisarLocalidade(String inscricaoTipo, FiltrarImovelContaActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo localidadeOrigemID do formul�rio.
		localidadeIDOrigem = (String) form.getLocalidadeOrigemID();

		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeIDOrigem));

		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !localidadeIDOrigem.equalsIgnoreCase("")) {
			// Localidade nao encontrada
			// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
			// formul�rio
			if (form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID())) {
				form.setLocalidadeDestinoID("");
			}
			form.setLocalidadeOrigemID("");
			form.setNomeLocalidadeOrigem("Localidade inexistente");
			httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
		} else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
			if (form.getLocalidadeDestinoID() == null || form.getLocalidadeDestinoID().equals("")
					|| form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID())) {
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
			}
			httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
			httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
		}
		// Recebe o valor do campo localidadeDestinoID do formul�rio.
		localidadeIDDestino = (String) form.getLocalidadeDestinoID();

		/*
		 * Alterado por Raphael Rossiter em 26/12/2007 OBJ: Corrigir bug aberto
		 * por Rosana Carvalho por email em 26/12/2007
		 */
		if (localidadeIDDestino != null && !localidadeIDDestino.trim().equalsIgnoreCase("")) {

			// Limpa os parametros do filtro
			filtroLocalidade.limparListaParametros();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeIDDestino));

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				if (!form.getSetorComercialOrigemCD().equals("")) {
					httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
				}
			}
		}

	}

	private void pesquisarSetorComercial(String inscricaoTipo, FiltrarImovelContaActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Recebe o valor do campo localidadeOrigemID do formul�rio.
		localidadeIDOrigem = (String) form.getLocalidadeOrigemID();

		// O campo localidadeOrigemID ser� obrigat�rio
		if (localidadeIDOrigem != null && !localidadeIDOrigem.trim().equalsIgnoreCase("")) {

			setorComercialCDOrigem = (String) form.getSetorComercialOrigemCD();

			// Adiciona o id da localidade que est� no formul�rio para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeIDOrigem));

			// Adiciona o c�digo do setor comercial que esta no formul�rio
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercialCDOrigem));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !form.getSetorComercialOrigemCD().equals("")) {
				// Setor Comercial nao encontrado
				// Limpa os campos setorComercialOrigemCD,
				// nomeSetorComercialOrigem e setorComercialOrigemID do
				// formul�rio
				if (form.getSetorComercialOrigemCD().equals(form.getSetorComercialDestinoCD())) {
					form.setSetorComercialDestinoCD("");
				}
				form.setSetorComercialOrigemCD("");
				form.setSetorComercialOrigemID("");
				form.setNomeSetorComercialOrigem("Setor comercial inexistente");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			} else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
				form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
				form.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
				if (form.getSetorComercialDestinoCD() == null || form.getSetorComercialDestinoCD().equals("")
						|| form.getSetorComercialDestinoCD().equals(form.getSetorComercialOrigemCD())) {
					form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
				}
				httpServletRequest.setAttribute("corSetorComercialOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
			}
		} else {
			// Limpa o campo setorComercialOrigemCD do formul�rio
			if (!form.getSetorComercialOrigemCD().equals("")) {
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		}
		// Recebe o valor do campo localidadeDestinoID do formul�rio.
		localidadeIDDestino = (String) form.getLocalidadeDestinoID();

		// O campo localidadeOrigem ser� obrigat�rio
		if (localidadeIDDestino != null && !localidadeIDDestino.trim().equalsIgnoreCase("")) {

			setorComercialCDDestino = (String) form.getSetorComercialDestinoCD();

			// limpa o filtro
			filtroSetorComercial.limparListaParametros();

			// Adiciona o id da localidade que est� no formul�rio para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeIDDestino));

			// Adiciona o c�digo do setor comercial que esta no formul�rio
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercialCDDestino));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Setor Comercial nao encontrado
				// Limpa os campos setorComercialDestinoCD,
				// nomeSetorComercialDestino e setorComercialDestinoID do
				// formul�rio
				form.setSetorComercialDestinoCD("");
				form.setSetorComercialDestinoID("");
				form.setNomeSetorComercialDestino("Setor comercial inexistente");
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			} else {
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
				form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
				form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
				httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				if (!form.getQuadraOrigemNM().equals("")) {
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				}
			}
		} else {
			// Limpa o campo setorComercialDestinoCD do formul�rio
			form.setSetorComercialDestinoCD("");
			form.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
			httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
		}
	}

	private void pesquisarQuadra(String inscricaoTipo, FiltrarImovelContaActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que ser�o retornados pelo hibernate.
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		// Recebe os valores dos campos setorComercialOrigemCD e
		// setorComercialOrigemID do formul�rio.
		setorComercialCDOrigem = (String) form.getSetorComercialOrigemCD();

		setorComercialIDOrigem = (String) form.getSetorComercialOrigemID();

		// Os campos setorComercialOrigemCD e setorComercialID ser�o
		// obrigat�rios
		if (setorComercialCDOrigem != null && !setorComercialCDOrigem.trim().equalsIgnoreCase("") && setorComercialIDOrigem != null
				&& !setorComercialIDOrigem.trim().equalsIgnoreCase("")) {

			quadraNMOrigem = (String) form.getQuadraOrigemNM();

			// Adiciona o id do setor comercial que est� no formul�rio para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDOrigem));

			// Adiciona o n�mero da quadra que esta no formul�rio para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNMOrigem));

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna quadra
			colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !form.getQuadraOrigemNM().equals("")) {
				// Quadra nao encontrada
				// Limpa os campos quadraOrigemNM e quadraOrigemID do
				// formul�rio
				if (form.getQuadraOrigemNM().equals(form.getQuadraDestinoNM())) {
					form.setQuadraDestinoNM("");
				}
				form.setQuadraOrigemNM("");
				form.setQuadraOrigemID("");
				// Mensagem de tela
				form.setQuadraMensagemOrigem("Quadra inexistente");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			} else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
				form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
				if (form.getQuadraDestinoNM() == null || form.getQuadraDestinoNM().equals("")
						|| form.getQuadraOrigemNM().equals(form.getQuadraDestinoNM())) {
					form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
				}
				httpServletRequest.setAttribute("corQuadraOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
			}
		} else {
			if (!form.getQuadraOrigemNM().equals("")) {
				// Limpa o campo quadraOrigemNM do formul�rio
				form.setQuadraOrigemNM("");
				form.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		}
		// Recebe os valores dos campos setorComercialOrigemCD e
		// setorComercialOrigemID do formul�rio.
		setorComercialCDDestino = (String) form.getSetorComercialDestinoCD();
		setorComercialIDDestino = (String) form.getSetorComercialDestinoID();

		// Os campos setorComercialOrigemCD e setorComercialID ser�o
		// obrigat�rios
		if (setorComercialCDDestino != null && !setorComercialCDDestino.trim().equalsIgnoreCase("") && setorComercialIDDestino != null
				&& !setorComercialIDDestino.trim().equalsIgnoreCase("")) {

			quadraNMDestino = (String) form.getQuadraDestinoNM();

			// Limpa os parametros do filtro
			filtroQuadra.limparListaParametros();

			// Adiciona o id do setor comercial que est� no formul�rio para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDDestino));

			// Adiciona o n�mero da quadra que esta no formul�rio para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraNMDestino));

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna quadra
			colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Quadra nao encontrada
				// Limpa os campos quadraOrigemNM e quadraOrigemID do
				// formul�rio
				form.setQuadraDestinoNM("");
				form.setQuadraDestinoID("");
				// Mensagem de tela
				form.setQuadraMensagemDestino("Quadra inexistente");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			} else {
				Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
				form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
				httpServletRequest.setAttribute("corQuadraDestino", "valor");
				if (!form.getLoteOrigem().equals("")) {
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			}
		} else {
			// Limpa o campo setorComercialOrigemCD do formul�rio
			form.setQuadraDestinoNM("");
			// Mensagem de tela
			form.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
			httpServletRequest.setAttribute("corQuadraDestino", "exception");
		}
	}
}
