package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaInteiro;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * 
 */
public class ExibirManterTabelaAuxiliarFaixaInteiroAction extends GcomAction {
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
				.findForward("manterTabelaAuxiliarFaixaInteiro");

		// Obtém a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria a coleção de tabelas auxiliares
		Collection tabelasAuxiliaresFaixaInteiro = null;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o nome da tela passado no get do request
		String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		String titulo = null;
		TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiro = null;

		if (sessao.getAttribute("tabela") != null) {
			tabelaAuxiliarFaixaInteiro = (TabelaAuxiliarFaixaInteiro) sessao
					.getAttribute("tabela");
		}
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");
		String funcionalidadeTabelaAuxiliarFaixaInteiroManter = null;

		// sessao.getAttribute("dados", dados);
		titulo = (String) sessao.getAttribute("titulo");
		// sessao.setAttribute("tabela", dados.getTabela());
		// sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaInserir",
		// dados.getFuncionalidadeTabelaAuxInserir());
		// sessao.setAttribute("nomeParametroFuncionalidade", dados
		// .getNomeParametroFuncionalidade());

		/*
		 * String descricao = "Descrição"; if (sessao.getAttribute("descricao") !=
		 * null) { descricao = (String) sessao.getAttribute("descricao"); }
		 * String descricaoAbreviada = "Sigla"; if
		 * (sessao.getAttribute("descricaoAbreviada") != null) {
		 * descricaoAbreviada = (String) sessao
		 * .getAttribute("descricaoAbreviada"); }
		 */
		// Verifica se o exibir manter foi chamado da tela de filtro
		if (httpServletRequest.getAttribute("tela") != null) {
			tela = (String) sessao.getAttribute("tela");
		}

		// Parte da verificação do filtro
		FiltroTabelaAuxiliarFaixaInteiro filtroTabelaAuxiliarFaixaInteiro = null;

		// Verifica se o filtro foi informado pela página de filtragem da tabela
		// auxiliar abreviada
		if (httpServletRequest.getAttribute("filtroTabelaAuxiliarFaixaInteiro") != null) {
			filtroTabelaAuxiliarFaixaInteiro = (FiltroTabelaAuxiliarFaixaInteiro) httpServletRequest
					.getAttribute("filtroTabelaAuxiliarFaixaInteiro");
			sessao.setAttribute("filtroTabelaAuxiliarFaixaInteiro",
					filtroTabelaAuxiliarFaixaInteiro);
		} else {
			// Caso o exibirManterTabelaAuxiliar não tenha passado por algum
			// esquema de filtro,
			// a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem

			if (sessao.getAttribute("filtroTabelaAuxiliarFaixaInteiro") != null) {
				filtroTabelaAuxiliarFaixaInteiro = (FiltroTabelaAuxiliarFaixaInteiro) sessao
						.getAttribute("filtroTabelaAuxiliarFaixaInteiro");
			} else {
				filtroTabelaAuxiliarFaixaInteiro = new FiltroTabelaAuxiliarFaixaInteiro();
			}

			if (fachada.registroMaximo(tabelaAuxiliarFaixaInteiro.getClass()) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping
						.findForward("filtroTabelaAuxiliarFaixaInteiro");
				sessao.setAttribute("tela", tela);
			}
		}

		// A pesquisa de tabelas auxiliares só será feita se o forward estiver
		// direcionado
		// para a página de manterTabelaAuxiliar
		if (retorno.getName().equalsIgnoreCase(
				"manterTabelaAuxiliarFaixaInteiro")) {
			// Seta a ordenação desejada do filtro
			filtroTabelaAuxiliarFaixaInteiro
					.setCampoOrderBy(FiltroTabelaAuxiliarFaixaInteiro.MENOR_FAIXA);
			// Pesquisa de tabelas auxiliares

			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliarFaixaInteiro, pacoteNomeObjeto);
			tabelasAuxiliaresFaixaInteiro = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (tabelasAuxiliaresFaixaInteiro != null
					&& !tabelasAuxiliaresFaixaInteiro.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está
				// retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz
				// uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para
				// a
				// segunda página ele não vá para tela de atualizar.

				if (tabelasAuxiliaresFaixaInteiro.size() == 1
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
								.findForward("atualizarTabelaAuxiliarFaixaInteiro");
						TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiroAux = (TabelaAuxiliarFaixaInteiro) tabelasAuxiliaresFaixaInteiro
								.iterator().next();
						httpServletRequest.setAttribute("id",
								tabelaAuxiliarFaixaInteiroAux.getId()
										.toString());

					} else {
						sessao.setAttribute("tabelasAuxiliaresFaixaInteiro",
								tabelasAuxiliaresFaixaInteiro);
					}
				} else {
					sessao.setAttribute("tabelasAuxiliaresFaixaInteiro",
							tabelasAuxiliaresFaixaInteiro);
				}
			} else {
				// Nenhuma funcionalidade cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
		}

		// Envia os objetos na sessão
		sessao.setAttribute("titulo", titulo);
		sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroManter",
				funcionalidadeTabelaAuxiliarFaixaInteiroManter);
		
		//seta o parametro tela a parte de acesso a funcionalidade ou operação
		httpServletRequest.setAttribute("tela",tela);
		// sessao.setAttribute("descricao", descricao);
		// sessao.setAttribute("descricaoAbreviada", descricaoAbreviada);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
