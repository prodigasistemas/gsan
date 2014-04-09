package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurelio
 * 
 */
public class ExibirAtualizarTabelaAuxiliarFaixaRealAction extends GcomAction {
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
				.findForward("atualizarTabelaAuxiliarFaixaReal");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		int tamMaxCampoVolumeMenorFaixa = 6;

		int tamMaxCampoVolumeMaiorFaixa = 6;

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else {
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
		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();
		
		String tela = (String) sessao.getAttribute("tela");

		if (httpServletRequest.getAttribute("desfazer") == null) {

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarFaixaReal
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaReal.ID, id));

		
			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresFaixaReais = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarFaixaReal,
					pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresFaixaReais == null
					|| tabelasAuxiliaresFaixaReais.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarFaixaReal = tabelasAuxiliaresFaixaReais
					.iterator();

			// A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaReal = (TabelaAuxiliarFaixaReal) iteratorTabelaAuxiliarFaixaReal
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarFaixaReal",
					tabelaAuxiliarFaixaReal);

			

			if (tela.equalsIgnoreCase("piscinaVolumeFaixa") || tela.equalsIgnoreCase("reservatorioVolumeFaixa")) {

				if (tabelaAuxiliarFaixaReal.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorUso", "sim");
				} else {
					sessao.setAttribute("indicadorUso", "nao");
				}

			}
			DadosTelaTabelaAuxiliarFaixaReal dados = (DadosTelaTabelaAuxiliarFaixaReal) DadosTelaTabelaAuxiliarFaixaReal
					.obterDadosTelaTabelaAuxiliar(tela);

			sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaRealFiltrar",
					dados.getFuncionalidadeTabelaFaixaRealFiltrar());

			sessao.setAttribute("tamMaxCampoVolumeMenorFaixa", new Integer(
					tamMaxCampoVolumeMenorFaixa));
			sessao.setAttribute("tamMaxCampoVolumeMaiorFaixa", new Integer(
					tamMaxCampoVolumeMaiorFaixa));
		}

		if (httpServletRequest.getAttribute("desfazer") != null) {

			// Cria o filtro para atividade
			filtroTabelaAuxiliarFaixaReal.limparListaParametros();

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarFaixaReal
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaReal.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresFaixaReaisBase = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarFaixaReal,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresFaixaReaisBase == null
					|| tabelasAuxiliaresFaixaReaisBase.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarFaixaRealBase = tabelasAuxiliaresFaixaReaisBase
					.iterator();

			// A tabela auxiliar abreviada que será atualizada
			TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaRealBase = (TabelaAuxiliarFaixaReal) iteratorTabelaAuxiliarFaixaRealBase
					.next();

			// Manda a tabela auxiliar na sessão
			sessao.setAttribute("tabelaAuxiliarFaixaReal",
					tabelaAuxiliarFaixaRealBase);

		}
		
		//seta o parametro tela a parte de acesso a funcionalidade ou operação
		httpServletRequest.setAttribute("tela",tela);

		//Devolve o mapeamento de retorno
		return retorno;
	}

}
