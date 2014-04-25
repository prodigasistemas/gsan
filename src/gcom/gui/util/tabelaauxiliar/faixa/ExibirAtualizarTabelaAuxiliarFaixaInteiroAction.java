package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaInteiro;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

import java.util.Collection;
import java.util.Iterator;

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
public class ExibirAtualizarTabelaAuxiliarFaixaInteiroAction extends GcomAction {
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
				.findForward("atualizarTabelaAuxiliarFaixaInteiro");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		int tamMaxCampoMenorFaixa = 6;

		int tamMaxCampoMaiorFaixa = 6;

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else  {
			sessao.removeAttribute("manter");
		}
		
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		String id = null;
		// Código da tabela auxiliar a ser atualizada
		if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (manutencaoRegistroActionForm.getIdRegistroAtualizacao() != null) {
				id = manutencaoRegistroActionForm.getIdRegistroAtualizacao();
				sessao.setAttribute("id", id);
			}
		}

		if (sessao.getAttribute("id") != null) {
			id = (String) sessao.getAttribute("id");
		}

		
		// String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");

		
		// Cria o filtro para atividade
		FiltroTabelaAuxiliarFaixaInteiro filtroTabelaAuxiliarFaixaInteiro = new FiltroTabelaAuxiliarFaixaInteiro();
		
		String tela = (String) sessao.getAttribute("tela");

		if (httpServletRequest.getAttribute("desfazer") == null) {

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarFaixaInteiro
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaInteiro.ID, id));

		
			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresFaixaInteiro = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarFaixaInteiro,
					pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresFaixaInteiro == null
					|| tabelasAuxiliaresFaixaInteiro.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarFaixaInteiro = tabelasAuxiliaresFaixaInteiro
					.iterator();

			// A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiro = (TabelaAuxiliarFaixaInteiro) iteratorTabelaAuxiliarFaixaInteiro
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarFaixaInteiro",
					tabelaAuxiliarFaixaInteiro);

			

			if (tela.equalsIgnoreCase("areaConstruidaFaixa")) {

				if (tabelaAuxiliarFaixaInteiro.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorUso", "sim");
				} else {
					sessao.setAttribute("indicadorUso", "nao");
				}

			}
			DadosTelaTabelaAuxiliarFaixaInteiro dados = (DadosTelaTabelaAuxiliarFaixaInteiro) DadosTelaTabelaAuxiliarFaixaInteiro
					.obterDadosTelaTabelaAuxiliar(tela);

			sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroFiltrar",
					dados.getFuncionalidadeTabelaFaixaInteiroFiltrar());

			sessao.setAttribute("tamMaxCampoMenorFaixa", new Integer(
					tamMaxCampoMenorFaixa));
			sessao.setAttribute("tamMaxCampoMaiorFaixa", new Integer(
					tamMaxCampoMaiorFaixa));
		}

		if (httpServletRequest.getAttribute("desfazer") != null) {

			// Cria o filtro para atividade
			filtroTabelaAuxiliarFaixaInteiro.limparListaParametros();

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarFaixaInteiro
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaReal.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresFaixaInteiroBase = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarFaixaInteiro,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresFaixaInteiroBase == null
					|| tabelasAuxiliaresFaixaInteiroBase.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarFaixaInteiroBase = tabelasAuxiliaresFaixaInteiroBase
					.iterator();

			// A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiroBase = (TabelaAuxiliarFaixaInteiro) iteratorTabelaAuxiliarFaixaInteiroBase
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarFaixaInteiro",
					tabelaAuxiliarFaixaInteiroBase);

		}
		
		httpServletRequest.setAttribute("tela",tela);

		//Devolve o mapeamento de retorno
		return retorno;
	}

}

