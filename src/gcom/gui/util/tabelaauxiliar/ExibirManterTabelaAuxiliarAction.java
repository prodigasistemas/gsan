package gcom.gui.util.tabelaauxiliar;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterTabelaAuxiliarAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterTabelaAuxiliar");

		// Obtém a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria a coleção de tabelas auxiliares
		Collection tabelasAuxiliares = null;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o nome da tela passado no get do request
		String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		String titulo = null;
		TabelaAuxiliar tabelaAuxiliar = null;
		String pacoteNomeObjeto = (String) sessao
		.getAttribute("pacoteNomeObjeto");
		String funcionalidadeTabelaAuxiliarManter = null;
		int tamMaxCampoDescricao = 40;

		tabelaAuxiliar = (TabelaAuxiliar) sessao
				.getAttribute("tabela");

		// sessao.getAttribute("dados", dados);
		titulo = (String) sessao.getAttribute("titulo");

		String descricao = "Descrição";
		if (sessao.getAttribute("descricao") != null) {
			descricao = (String) sessao.getAttribute("descricao");
		}

		// Verifica se o exibir manter foi chamado da tela de filtro
		if (httpServletRequest.getAttribute("tela") != null) {
			tela = (String) sessao.getAttribute("tela");
		}

		// Parte da verificação do filtro
		FiltroTabelaAuxiliar filtroTabelaAuxiliar = null;

		// Verifica se o filtro foi informado pela página de filtragem da tabela
		// auxiliar 
		if (httpServletRequest.getAttribute("filtroTabelaAuxiliar") != null) {
			filtroTabelaAuxiliar = (FiltroTabelaAuxiliar) httpServletRequest
					.getAttribute("filtroTabelaAuxiliar");
			sessao.setAttribute("filtroTabelaAuxiliar",filtroTabelaAuxiliar);
		} else {
			// Caso o exibirManterTabelaAuxiliar não tenha passado por algum
			// esquema de filtro,
			// a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem
			if (sessao.getAttribute("filtroTabelaAuxiliar") != null) {
				filtroTabelaAuxiliar = (FiltroTabelaAuxiliar) sessao
						.getAttribute("filtroTabelaAuxiliar");
			} else {
				filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
			}
			if (fachada.registroMaximo(tabelaAuxiliar.getClass()) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping
						.findForward("filtrarTabelaAuxiliar");
				sessao.setAttribute("tela", tela);
			}
		}

		// A pesquisa de tabelas auxiliares só será feita se o forward estiver
		// direcionado
		// para a página de manterTabelaAuxiliar
		if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliar")) {
			// Seta a ordenação desejada do filtro
			filtroTabelaAuxiliar
					.setCampoOrderBy(FiltroTabelaAuxiliar.DESCRICAO);
			// Pesquisa de tabelas auxiliares

			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliar, pacoteNomeObjeto);
			tabelasAuxiliares = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (tabelasAuxiliares != null
					&& !tabelasAuxiliares.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está
				// retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz
				// uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para
				// a
				// segunda página ele não vá para tela de atualizar.

				if (tabelasAuxiliares.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {

					// Verifica se o usuário marcou o checkbox de atualizar no
					// jsp
					// funcionalidade_filtrar. Caso todas as condições sejam
					// verdadeiras seta o retorno para o
					// ExibirAtualizarFuncionalidadeAction e em caso negativo
					// manda a coleção pelo request.

					if (httpServletRequest.getParameter("atualizar") != null) {
						retorno = actionMapping
								.findForward("atualizarTabelaAuxiliar");
						TabelaAuxiliar tabelaAuxiliar2 = (TabelaAuxiliar) tabelasAuxiliares
								.iterator().next();
						httpServletRequest.setAttribute("id",
								tabelaAuxiliar2.getId().toString());

					} else {
						sessao.setAttribute("tabelasAuxiliares",
								tabelasAuxiliares);
					}
				} else {
					sessao.setAttribute("tabelasAuxiliares",
							tabelasAuxiliares);
				}
			} else {
				// Nenhuma funcionalidade cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
		}

		// Envia os objetos na sessão
		sessao.setAttribute("titulo", titulo);
		sessao.setAttribute("funcionalidadeTabelaAuxiliarManter",
				funcionalidadeTabelaAuxiliarManter);
		sessao.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		sessao.setAttribute("descricao", descricao);
		httpServletRequest.setAttribute("tela",tela);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
