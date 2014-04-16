package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o
 * tipo SistemaAbastecimento>
 * 
 * @author rodrigo
 */
public class ExibirAtualizarTabelaAuxiliarAbreviadaTipoAction extends
		GcomAction {
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
				.findForward("atualizarTabelaAuxiliarAbreviadaTipo");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliarAbreviadaTipoActionForm tabelaAuxiliarAbreviadaTipoActionForm = (TabelaAuxiliarAbreviadaTipoActionForm) actionForm;

		// String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");

		String id = null;

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Código da tabela auxiliar abreviada tipo a ser atualizada
		if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				id = (String) httpServletRequest
						.getParameter("idRegistroAtualizacao");
				sessao.setAttribute("id", id);
			} else {
				id = (String) sessao.getAttribute("id");
			}
		}

		// Cria o filtro para atividade
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		if (httpServletRequest.getAttribute("desfazer") == null) {

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliar.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresAbreviadasTipo = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarAbreviadaTipo,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresAbreviadasTipo == null
					|| tabelasAuxiliaresAbreviadasTipo.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarAbreviadaTipo = tabelasAuxiliaresAbreviadasTipo
					.iterator();

			// A tabela auxiliar abreviada tipo que será atualizada
			TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) iteratorTabelaAuxiliarAbreviadaTipo
					.next();

			if (tabelaAuxiliarAbreviadaTipo.getSistemaAbastecimento() != null) {
				tabelaAuxiliarAbreviadaTipoActionForm
						.setSistemaAbastecimento(tabelaAuxiliarAbreviadaTipo
								.getSistemaAbastecimento().getId().toString());

			}

			if (tabelaAuxiliarAbreviadaTipo == null) {

				if (id != null && !id.equals("")) {

					FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo2 = new FiltroTabelaAuxiliarAbreviadaTipo();

					filtroTabelaAuxiliarAbreviadaTipo2
							.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
					filtroTabelaAuxiliarAbreviadaTipo2
							.adicionarParametro(new ParametroSimples(
									FiltroDistritoOperacional.ID, id));

					Collection colecaoTabelaAuxiliarAbreviadaTipo = fachada
							.pesquisar(filtroTabelaAuxiliarAbreviadaTipo,
									TabelaAuxiliarAbreviadaTipo.class.getName());

					if (colecaoTabelaAuxiliarAbreviadaTipo != null
							&& !colecaoTabelaAuxiliarAbreviadaTipo.isEmpty()) {
						tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) Util
								.retonarObjetoDeColecao(colecaoTabelaAuxiliarAbreviadaTipo);
					}
				}
			}

			// Manda a tabela auxiliar abreviada tipo na sessão
			sessao.setAttribute("tabelaAuxiliarAbreviadaTipo",
					tabelaAuxiliarAbreviadaTipo);

			String tela = (String) sessao.getAttribute("tela");

			if (tela.equalsIgnoreCase("banco")) {

				if (tabelaAuxiliarAbreviadaTipo.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorUso", "sim");
				} else {
					sessao.setAttribute("indicadorUso", "nao");
				}

			}

			DadosTelaTabelaAuxiliarAbreviadaTipo dados = DadosTelaTabelaAuxiliarAbreviadaTipo
					.obterDadosTelaTabelaAuxiliar(tela);

			sessao.setAttribute(
					"funcionalidadeTabelaAuxiliarAbreviadaTipoFiltrar", dados
							.getFuncionalidadeTabelaAuxFiltrar());

			if (httpServletRequest.getAttribute("desfazer") != null) {

				// Cria o filtro para atividade
				filtroTabelaAuxiliarAbreviadaTipo.limparListaParametros();

				// Adiciona o parâmetro no filtro
				filtroTabelaAuxiliarAbreviadaTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliar.ID, id));

				// Pesquisa a tabela auxiliar no sistema
				Collection tabelasAuxiliaresAbreviadasTipoBase = fachada
						.pesquisarTabelaAuxiliar(
								filtroTabelaAuxiliarAbreviadaTipo,
								pacoteNomeObjeto);

				// Caso a coleção esteja vazia, indica erro inesperado
				if (tabelasAuxiliaresAbreviadasTipoBase == null
						|| tabelasAuxiliaresAbreviadasTipoBase.isEmpty()) {
					throw new ActionServletException("erro.sistema");
				}

				Iterator iteratorTabelaAuxiliarAbreviadaTipoBase = tabelasAuxiliaresAbreviadasTipoBase
						.iterator();

				// A tabela auxiliar abreviada tipo que será atualizada
				TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipoBase = (TabelaAuxiliarAbreviadaTipo) iteratorTabelaAuxiliarAbreviadaTipoBase
						.next();

				// Manda a tabela auxiliar abreviada tipo na sessão
				sessao.setAttribute("tabelaAuxiliarAbreviadaTipo",
						tabelaAuxiliarAbreviadaTipoBase);
			}
		}

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
