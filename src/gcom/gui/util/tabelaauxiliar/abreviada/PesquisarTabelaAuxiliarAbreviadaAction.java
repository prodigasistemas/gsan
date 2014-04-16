package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
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
import org.apache.struts.validator.DynaValidatorForm;

public class PesquisarTabelaAuxiliarAbreviadaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisaTabelaAuxiliarAbreviada");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parametro passado no request
		DadosTelaTabelaAuxiliar dados = (DadosTelaTabelaAuxiliarAbreviada) sessao
				.getAttribute("dados");

		// Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm.get("descricaoAbreviada");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
		

		// cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, id));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroTabelaAuxiliarAbreviada.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroTabelaAuxiliarAbreviada.DESCRICAO, descricao));
			} else {
				filtroTabelaAuxiliarAbreviada.adicionarParametro(
					new ComparacaoTexto(FiltroTabelaAuxiliarAbreviada.DESCRICAO, descricao));
			}
		}		

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviada.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoTabelaAuxiliarAbreviada = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) dados
				.getTabelaAuxiliar();

		// Faz a busca das empresas
		colecaoTabelaAuxiliarAbreviada = fachada.pesquisar(
				filtroTabelaAuxiliarAbreviada, tabelaAuxiliarAbreviada
						.getClass().getName());

		if (colecaoTabelaAuxiliarAbreviada == null
				|| colecaoTabelaAuxiliarAbreviada.isEmpty()) {
			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, dados.getTitulo());
		} else{
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliarAbreviada, tabelaAuxiliarAbreviada.getClass().getName());
			colecaoTabelaAuxiliarAbreviada = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("colecaoTabelaAuxiliarAbreviada", colecaoTabelaAuxiliarAbreviada);
		}

		// Repassa o tipo da pesquisa para a tela de resultado
		httpServletRequest.setAttribute("tipoPesquisa", httpServletRequest
				.getParameter("tipoPesquisa"));

		// Repassa o caminho do resultado para a tela de resultado
		httpServletRequest.setAttribute("caminhoRetorno", httpServletRequest
				.getParameter("caminhoRetorno"));
		
		// Repassa o caminho do resultado para a tela de resultado
		httpServletRequest.setAttribute("caminhoRetornoTelaPesquisa", sessao
				.getAttribute("caminhoRetornoTelaPesquisa"));

		// Devolve o mapeamento de retorno
		return retorno;

	}
}
