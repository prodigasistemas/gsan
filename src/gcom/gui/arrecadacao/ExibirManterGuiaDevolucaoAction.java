package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resoluções de diretoria [UC0219] Filtrar Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirManterGuiaDevolucaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterGuiaDevolucaoCliente");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		ManterGuiaDevolucaoActionForm manterGuiaDevolucaoActionForm = (ManterGuiaDevolucaoActionForm) actionForm;

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("guiaDevolucaoAtualizar") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}
		FiltroGuiaDevolucao filtroGuiaDevolucao = null;

		if (sessao.getAttribute("filtroGuiaDevolucaoCliente") != null) {
			filtroGuiaDevolucao = (FiltroGuiaDevolucao) sessao
					.getAttribute("filtroGuiaDevolucaoCliente");
			sessao.setAttribute("filtroGuiaDevolucaoCliente", filtroGuiaDevolucao);
			Cliente cliente = (Cliente) sessao.getAttribute("cliente");
			sessao.setAttribute("cliente", cliente);

			manterGuiaDevolucaoActionForm.setIdCliente(cliente.getId()
					.toString());
			manterGuiaDevolucaoActionForm.setNomeCliente(cliente.getNome());

			// Verifica o tipo do cliente para setar o cpf ou cnpj e profissão
			// ou ramo atividade
			if (cliente.getClienteTipo().getId().equals(
					ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				manterGuiaDevolucaoActionForm.setCpfCnpj(cliente
						.getCpfFormatado());
				if (cliente.getProfissao() != null) {
					manterGuiaDevolucaoActionForm.setProfissao(cliente
							.getProfissao().getDescricao());
				}
			} else {
				manterGuiaDevolucaoActionForm.setCpfCnpj(cliente
						.getCnpjFormatado());
				if (cliente.getRamoAtividade() != null) {
					manterGuiaDevolucaoActionForm.setRamoAtividade(cliente
							.getRamoAtividade().getDescricao());
				}
			}

		} else {
			filtroGuiaDevolucao = (FiltroGuiaDevolucao) sessao
					.getAttribute("filtroGuiaDevolucaoImovel");
			sessao.setAttribute("filtroGuiaDevolucaoImovel", filtroGuiaDevolucao);
			Imovel imovel = (Imovel) sessao.getAttribute("imovel");
			sessao.setAttribute("imovel", imovel);

			manterGuiaDevolucaoActionForm
					.setIdImovel(imovel.getId().toString());
			manterGuiaDevolucaoActionForm.setInscricao(imovel
					.getInscricaoFormatada());

			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
					ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(
					FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoClienteImovel = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
						.iterator().next();
				manterGuiaDevolucaoActionForm.setNomeCliente(clienteImovel
						.getCliente().getNome());
			}

			if (imovel.getLigacaoAguaSituacao() != null) {
				manterGuiaDevolucaoActionForm.setSituacaoLigacaoAgua(imovel
						.getLigacaoAguaSituacao().getDescricao());
			}

			if (imovel.getLigacaoEsgotoSituacao() != null) {
				manterGuiaDevolucaoActionForm.setSituacaoLigacaoEsgoto(imovel
						.getLigacaoEsgotoSituacao().getDescricao());
			}

			retorno = actionMapping
					.findForward("exibirManterGuiaDevolucaoImovel");
		}


		// 1º Passo - Pegar o total de registros através de um count da consulta
		// que aparecerá na tela
		Integer totalRegistros = fachada
				.pesquisarGuiaDevolucaoCount(filtroGuiaDevolucao);

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando
		// o numero de paginas
		// da pesquisa que está no request
		// Faz os carregamentos necessários
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
//		filtroGuiaDevolucao
//				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("ordemServico.servicoTipo.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		
		filtroGuiaDevolucao.setCampoOrderBy(
				FiltroGuiaDevolucao.CREDITO_TIPO_ID,
				FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID);

		
		Collection colecaoGuiaDevolucao = fachada.pesquisarGuiaDevolucao(
				filtroGuiaDevolucao, ((Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa")));
		
		filtroGuiaDevolucao.limparCamposOrderBy();

		if (colecaoGuiaDevolucao != null && !colecaoGuiaDevolucao.isEmpty()) {
//			if (colecaoGuiaDevolucao.size() == 1
//					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
//							.getParameter("page.offset").equals("1"))) {
//				if (httpServletRequest.getParameter("atualizar") != null) {
//					retorno = actionMapping
//							.findForward("exibirAtualizarGuiaDevolucao");
//					GuiaDevolucao guiaDevolucao = (GuiaDevolucao) colecaoGuiaDevolucao
//							.iterator().next();
//					sessao.setAttribute("guiaDevolucao", guiaDevolucao);
//				} else {
//					httpServletRequest.setAttribute("colecaoGuiaDevolucao",
//							colecaoGuiaDevolucao);
//				}
//			} else {
				httpServletRequest.setAttribute("colecaoGuiaDevolucao",
						colecaoGuiaDevolucao);
//			}
		} else {
			// Nenhuma resolução de diretoria cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
