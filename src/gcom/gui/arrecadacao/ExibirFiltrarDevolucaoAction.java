package gcom.gui.arrecadacao;

import gcom.arrecadacao.DevolucaoSituacao;
import gcom.arrecadacao.FiltroDevolucaoSituacao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Filtrar Devoluções - Exibir
 * 
 * @author Rafael Corrêa - 31/01/2006
 */
public class ExibirFiltrarDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarDevolucoes");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDevolucaoActionForm filtrarDevolucaoActionForm = (FiltrarDevolucaoActionForm) actionForm;

		String tela = httpServletRequest.getParameter("tela");

		//indicador de Devolução deve vir selecionado a opção Atual
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			filtrarDevolucaoActionForm.setIndicadorOpcaoDevolucao("1");
		}
		
		// Pegando valores para Tipo de Relacao do Cliente
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		/*filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));*/
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

		// Pegando valores para o combo Tipo de Situacao de Devoluções
		FiltroDevolucaoSituacao filtroDevolucaoSituacao = new FiltroDevolucaoSituacao();
		/*filtroDevolucaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroDevolucaoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));*/
		filtroDevolucaoSituacao
				.setCampoOrderBy(FiltroDevolucaoSituacao.DESCRICAO);
		Collection colecaoDevolucaoSituacao = fachada.pesquisar(
				filtroDevolucaoSituacao, DevolucaoSituacao.class.getName());

		if (colecaoDevolucaoSituacao != null
				&& !colecaoDevolucaoSituacao.isEmpty()) {

			httpServletRequest.setAttribute("colecaoDevolucaoSituacao",
					colecaoDevolucaoSituacao);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"devolução situação");
		}

		// Pegando valores pra o combo Tipo de Crédito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		/*filtroCreditoTipo.adicionarParametro(new ParametroSimples(
				FiltroCreditoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));*/
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

		// Pegando valores pra o combo Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		/*filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));*/
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		if (colecaoDocumentoTipo != null && !colecaoDocumentoTipo.isEmpty()) {

			httpServletRequest.setAttribute("colecaoDocumentoTipo",
					colecaoDocumentoTipo);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"documento tipo");
		}

		// -------Parte que trata do código quando o usuário tecla enter

		// Imóvel
		String idImovel = (String) filtrarDevolucaoActionForm.getIdImovel();

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
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				filtrarDevolucaoActionForm.setDescricaoImovel(imovel
						.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				filtrarDevolucaoActionForm.setIdImovel("");
				filtrarDevolucaoActionForm
						.setDescricaoImovel("MATRÍCULA INEXISTENTE");
			}
		} else {
			filtrarDevolucaoActionForm.setDescricaoImovel("");
		}

		// Cliente
		String idCliente = (String) filtrarDevolucaoActionForm.getIdCliente();

		if (idCliente != null && !idCliente.equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection clientes = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clientes != null && !clientes.isEmpty()) {
				Cliente cliente = (Cliente) ((List) clientes).get(0);
				filtrarDevolucaoActionForm.setNomeCliente(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("clienteInexistente", "true");
				filtrarDevolucaoActionForm.setIdCliente("");
				filtrarDevolucaoActionForm
						.setNomeCliente("CLIENTE INEXISTENTE");
			}
		} else {
			filtrarDevolucaoActionForm.setNomeCliente("");
		}

		// Aviso Bancário

		AvisoBancario avisoBancario = (AvisoBancario) sessao
				.getAttribute("avisoBancario");

		if (avisoBancario != null) {
			filtrarDevolucaoActionForm.setCodigoAgenteArrecadador(""
					+ avisoBancario.getArrecadador().getCodigoAgente());
			if (avisoBancario.getDataLancamento() != null) {
				filtrarDevolucaoActionForm.setDataLancamentoAviso(Util
						.formatarData(avisoBancario.getDataLancamento()));
			}
			filtrarDevolucaoActionForm.setNumeroSequencialAviso(""
					+ avisoBancario.getNumeroSequencial());
			filtrarDevolucaoActionForm.setIdAvisoBancario(avisoBancario.getId().toString());
			sessao.removeAttribute("avisoBancario");
		}

		// Localidade Inicial
		String idLocalidadeInicial = filtrarDevolucaoActionForm
				.getIdLocalidadeInicial();

		Localidade localidadeInicial = null;

		if (idLocalidadeInicial != null
				&& !idLocalidadeInicial.trim().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(
					filtroLocalidadeInicial, Localidade.class.getName());

			if (colecaoLocalidadeInicial != null
					&& !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) colecaoLocalidadeInicial
						.iterator().next();
				filtrarDevolucaoActionForm
						.setDescricaoLocalidadeInicial(localidadeInicial
								.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeInicialInexistente","true");
				filtrarDevolucaoActionForm.setIdLocalidadeInicial("");
				filtrarDevolucaoActionForm
						.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
			}
		} else {
			filtrarDevolucaoActionForm.setDescricaoLocalidadeInicial("");
		}

		// Localidade Final

		String idLocalidadeFinal = filtrarDevolucaoActionForm
				.getIdLocalidadeFinal();

		Localidade localidadeFinal = null;

		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			if (idLocalidadeInicial != null
					&& !idLocalidadeInicial.trim().equals("")) {
				if ((new Integer(idLocalidadeInicial)).compareTo(new Integer(
						idLocalidadeFinal)) > 0) {

				}
			}

			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = fachada.pesquisar(
					filtroLocalidadeFinal, Localidade.class.getName());

			if (colecaoLocalidadeFinal != null
					&& !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) colecaoLocalidadeFinal
						.iterator().next();
				filtrarDevolucaoActionForm
						.setDescricaoLocalidadeFinal(localidadeFinal
								.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeFinalInexistente",
						"true");
				filtrarDevolucaoActionForm.setIdLocalidadeFinal("");
				filtrarDevolucaoActionForm
						.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
			}
		} else {
			filtrarDevolucaoActionForm.setDescricaoLocalidadeFinal("");
		}

		if (httpServletRequest.getParameter("tela") != null) {
			sessao.setAttribute("tela", tela);
		}
		if(sessao.getAttribute("tela") != null || httpServletRequest.getParameter("tela") != null)
		{
			httpServletRequest.setAttribute("tela",tela);
			httpServletRequest.setAttribute("botaoAtualizar","botaoAtualizar");
		}
		
		String atualizar = httpServletRequest.getParameter("atualizar");
		String menu = httpServletRequest.getParameter("menu");
		
		if (atualizar == null && menu == null){
			boolean i = true;
			httpServletRequest.setAttribute("i",i);
		}
		
		httpServletRequest.setAttribute("nomeCampo","idImovel");
		return retorno;
	}

}
