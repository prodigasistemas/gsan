package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class PesquisarTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisaTabelaAuxiliarAbreviadaTipo");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parametro passado no request
		DadosTelaTabelaAuxiliarAbreviadaTipo dados = (DadosTelaTabelaAuxiliarAbreviadaTipo) sessao
				.getAttribute("dados");

		// Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm.get("descricaoAbreviada");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
		

		// cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviadaTipo.ID, id));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			} else {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(
					new ComparacaoTexto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			}
		}		

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoTabelaAuxiliarAbreviadaTipo = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) dados
				.getTabelaAuxiliar();

		// Faz a busca das empresas
		colecaoTabelaAuxiliarAbreviadaTipo = fachada.pesquisar(
				filtroTabelaAuxiliarAbreviadaTipo, tabelaAuxiliarAbreviadaTipo
						.getClass().getName());

		if (colecaoTabelaAuxiliarAbreviadaTipo == null
				|| colecaoTabelaAuxiliarAbreviadaTipo.isEmpty()) {
			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, dados.getTitulo());
		} else{
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliarAbreviadaTipo, tabelaAuxiliarAbreviadaTipo.getClass().getName());
			colecaoTabelaAuxiliarAbreviadaTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("colecaoTabelaAuxiliarAbreviadaTipo", colecaoTabelaAuxiliarAbreviadaTipo);
		}

		// Repassa o tipo da pesquisa para a tela de resultado
		httpServletRequest.setAttribute("tipoPesquisa", httpServletRequest
				.getParameter("tipoPesquisa"));

		// Repassa o caminho do resultado para a tela de resultado
		httpServletRequest.setAttribute("caminhoRetorno", httpServletRequest
				.getParameter("caminhoRetorno"));

		// Devolve o mapeamento de retorno
		return retorno;

	}
}
