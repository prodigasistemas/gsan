package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorOr;
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
 * Filtrar Devoluções - Exibir
 * 
 * @author Rafael Corrêa - 03/05/2006
 */
public class ExibirFiltrarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarGuiaDevolucao");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarGuiaDevolucaoActionForm filtrarGuiaDevolucaoActionForm = (FiltrarGuiaDevolucaoActionForm) actionForm;

		// Limpa o atributo se o usuário voltou o filtrar
		if (sessao.getAttribute("guiaDevolucaoAtualizar") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}

		// Pegando valores para Tipo de Relacao do Cliente
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroClienteRelacaoTipo
				.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);
		Collection colecaoClienteRelacaoTipo = fachada.pesquisar(
				filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());

		if (colecaoClienteRelacaoTipo != null
				&& !colecaoClienteRelacaoTipo.isEmpty()) {

			httpServletRequest.setAttribute("colecaoClienteRelacaoTipo",
					colecaoClienteRelacaoTipo);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"cliente relação tipo");
		}

		// Pegando valores para o combo Tipo de Crédito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(
				FiltroCreditoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCreditoTipo.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		Collection colecaoCreditoTipo = fachada.pesquisar(filtroCreditoTipo,
				CreditoTipo.class.getCanonicalName());

		if (colecaoCreditoTipo != null && !colecaoCreditoTipo.isEmpty()) {

			httpServletRequest.setAttribute("colecaoCreditoTipo",
					colecaoCreditoTipo);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"crédito tipo");
		}

		// Pegando valores para o combo Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
				ConectorOr.CONECTOR_OR, 4));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEVOLUCAO_VALOR,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);

		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		if (colecaoDocumentoTipo != null && !colecaoDocumentoTipo.isEmpty()) {
			httpServletRequest.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		}

		// Pegando valores para o combo Tipo de Crédito
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.setConsultaSemLimites(true);
		filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

		// Pesquisa os tipos de débito no sistema
		Collection colecaoTipoDebito = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());

		// Caso nenhum tipo de débito
		if (colecaoTipoDebito == null || colecaoTipoDebito.isEmpty()) {
			// Levanta a exceção para a próxima camada
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Débito");

		} else {
			// Manda a coleção de tipo de débito de guia de pagamento no request
			// para a página de pesquisar
			httpServletRequest.setAttribute("colecaoTipoDebito",
					colecaoTipoDebito);
		}

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
						"sim")) {
			filtrarGuiaDevolucaoActionForm.setAtualizar("1");
		}
		
		// Seta o cursor para o primeiro campo do tipo texto
		httpServletRequest.setAttribute("nomeCampo", "idImovel");
		
		// -------Parte que trata do código quando o usuário tecla enter

		// Imóvel
		String idImovel = "";
		
		if (httpServletRequest.getParameter("paginacao") != null) {
			idImovel = (String) filtrarGuiaDevolucaoActionForm.getIdImovelHidden();
		} else {
			idImovel = (String) filtrarGuiaDevolucaoActionForm.getIdImovel();
		}

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				filtrarGuiaDevolucaoActionForm.setDescricaoImovel(imovel
						.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				filtrarGuiaDevolucaoActionForm.setIdImovel("");
				filtrarGuiaDevolucaoActionForm
						.setDescricaoImovel("MATRÍCULA INEXISTENTE");
			}
		} else {
			filtrarGuiaDevolucaoActionForm.setIdImovel("");
			filtrarGuiaDevolucaoActionForm.setDescricaoImovel("");
		}

		// Cliente
		String idCliente = "";
		
		if (httpServletRequest.getParameter("paginacao") != null) {
			idCliente = (String) filtrarGuiaDevolucaoActionForm.getIdClienteHidden();
		} else {
			idCliente = (String) filtrarGuiaDevolucaoActionForm.getIdCliente();
		}

		if (idCliente != null && !idCliente.equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection clientes = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clientes != null && !clientes.isEmpty()) {
				Cliente cliente = (Cliente) ((List) clientes).get(0);
				filtrarGuiaDevolucaoActionForm
						.setNomeCliente(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("clienteInexistente", "true");
				filtrarGuiaDevolucaoActionForm.setIdCliente("");
				filtrarGuiaDevolucaoActionForm
						.setNomeCliente("CLIENTE INEXISTENTE");
			}
		} else {
			filtrarGuiaDevolucaoActionForm.setIdCliente("");
			filtrarGuiaDevolucaoActionForm.setNomeCliente("");
		}

		return retorno;
	}

}
