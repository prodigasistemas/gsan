package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.arrecadacao.banco.Banco;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FonteCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

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
public class ExibirManterTabelaAuxiliarAbreviadaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("manterTabelaAuxiliarAbreviada");

		// Cria a coleção de tabelas auxiliares
		Collection tabelasAuxiliaresAbreviadas = null;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o nome da tela passado no get do request
		String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		String titulo = null;
		TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = null;
		String pacoteNomeObjeto = (String) sessao.getAttribute("pacoteNomeObjeto");
		
		String funcionalidadeTabelaAuxiliarAbreviadaManter = null;
		int tamMaxCampoDescricao = 40;
		int tamMaxCampoDescricaoAbreviada = 3;

		tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) sessao.getAttribute("tabela");

		titulo = (String) sessao.getAttribute("titulo");

		String descricao = "Descrição";
		if (sessao.getAttribute("descricao") != null) {
			descricao = (String) sessao.getAttribute("descricao");
		}
		String descricaoAbreviada = "Sigla";
		
		if (sessao.getAttribute("descricaoAbreviada") != null) {
			descricaoAbreviada = (String) sessao.getAttribute("descricaoAbreviada");
		}
		// Verifica se o exibir manter foi chamado da tela de filtro
		if (httpServletRequest.getAttribute("tela") != null) {
			tela = (String) sessao.getAttribute("tela");
		}
		
		DadosTelaTabelaAuxiliarAbreviada dados = 
			(DadosTelaTabelaAuxiliarAbreviada) this.getSessao(httpServletRequest).getAttribute("dados");
		
		if (dados.getTabela() instanceof FonteCaptacao) {
			tamMaxCampoDescricao = 30;
			tamMaxCampoDescricaoAbreviada = 10;
		}else if (dados.getTabela() instanceof Banco) {
			descricao = "Nome";
			descricaoAbreviada = "Nome Abreviado";
		}

		// Parte da verificação do filtro
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = null;

		// Verifica se o filtro foi informado pela página de filtragem da tabela
		// auxiliar abreviada
		if (httpServletRequest.getAttribute("filtroTabelaAuxiliarAbreviada") != null) {
			
			filtroTabelaAuxiliarAbreviada = 
				(FiltroTabelaAuxiliarAbreviada) httpServletRequest.getAttribute("filtroTabelaAuxiliarAbreviada");
			sessao.setAttribute("filtroTabelaAuxiliarAbreviada",filtroTabelaAuxiliarAbreviada);
		} else {
			// Caso o exibirManterTabelaAuxiliar não tenha passado por algum
			// esquema de filtro,
			// a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem
			if (sessao.getAttribute("filtroTabelaAuxiliarAbreviada") != null) {
				filtroTabelaAuxiliarAbreviada = 
					(FiltroTabelaAuxiliarAbreviada) sessao.getAttribute("filtroTabelaAuxiliarAbreviada");
			} else {
				filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
			}
			if (this.getFachada().registroMaximo(tabelaAuxiliarAbreviada.getClass()) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping.findForward("filtrarTabelaAuxiliarAbreviada");
				sessao.setAttribute("tela", tela);
			}
		}

		// A pesquisa de tabelas auxiliares só será feita se o forward estiver
		// direcionado
		// para a página de manterTabelaAuxiliar
		if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliarAbreviada")) {

			// Seta a ordenação desejada do filtro
			filtroTabelaAuxiliarAbreviada.setCampoOrderBy(FiltroTabelaAuxiliarAbreviada.DESCRICAO);
			// Pesquisa de tabelas auxiliares

			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroTabelaAuxiliarAbreviada, pacoteNomeObjeto);
			
			tabelasAuxiliaresAbreviadas = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (tabelasAuxiliaresAbreviadas != null && !tabelasAuxiliaresAbreviadas.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está
				// retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz
				// uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para
				// a
				// segunda página ele não vá para tela de atualizar.

				if (tabelasAuxiliaresAbreviadas.size() == 1 && 
					(httpServletRequest.getParameter("page.offset") == null || 
					httpServletRequest.getParameter("page.offset").equals("1"))) {

					// Verifica se o usuário marcou o checkbox de atualizar no
					// jsp
					// funcionalidade_filtrar. Caso todas as condições sejam
					// verdadeiras seta o retorno para o
					// ExibirAtualizarFuncionalidadeAction e em caso negativo
					// manda a coleção pelo request.

					if (httpServletRequest.getParameter("atualizar") != null) {
						
						retorno = actionMapping.findForward("atualizarTabelaAuxiliarAbreviada");
						
						TabelaAuxiliarAbreviada tabelaAuxiliarabreviada = 
							(TabelaAuxiliarAbreviada) tabelasAuxiliaresAbreviadas.iterator().next();
						
						httpServletRequest.setAttribute("id",tabelaAuxiliarabreviada.getId().toString());

					} else {
						sessao.setAttribute("tabelasAuxiliaresAbreviadas",tabelasAuxiliaresAbreviadas);
					}
				} else {
					sessao.setAttribute("tabelasAuxiliaresAbreviadas",tabelasAuxiliaresAbreviadas);
				}
			} else {
				// Nenhuma funcionalidade cadastrada
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}

		// Envia os objetos na sessão
		sessao.setAttribute("titulo", titulo);
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaManter",funcionalidadeTabelaAuxiliarAbreviadaManter);
		sessao.setAttribute("tamMaxCampoDescricao", new Integer(tamMaxCampoDescricao));
		sessao.setAttribute("tamMaxCampoDescricaoAbreviada", new Integer(tamMaxCampoDescricaoAbreviada));
		sessao.setAttribute("descricao", descricao);
		sessao.setAttribute("descricaoAbreviada", descricaoAbreviada);
		
		httpServletRequest.setAttribute("tela",tela);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
