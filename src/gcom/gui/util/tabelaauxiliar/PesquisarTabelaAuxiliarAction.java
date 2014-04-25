package gcom.gui.util.tabelaauxiliar;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
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
import org.apache.struts.validator.DynaValidatorForm;

public class PesquisarTabelaAuxiliarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisaTabelaAuxiliar");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parametro passado no request
		DadosTelaTabelaAuxiliar dados = (DadosTelaTabelaAuxiliar) sessao
				.getAttribute("dados");

		// Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		// cria o filtro para Tabela Auxiliar 
		FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliar
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliar.ID, id));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroTabelaAuxiliar.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroTabelaAuxiliar.DESCRICAO, descricao));
			} else {
				filtroTabelaAuxiliar.adicionarParametro(
					new ComparacaoTexto(FiltroTabelaAuxiliar.DESCRICAO, descricao));
			}
		}		
		
        if (indicadorUso !=null && !indicadorUso.equalsIgnoreCase( "" )){
        	filtroTabelaAuxiliar.adicionarParametro( new ParametroSimples( FiltroTabelaAuxiliar.INDICADORUSO, indicadorUso ) );
        	peloMenosUmParametroInformado = true;
        }

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoTabelaAuxiliar = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliar tabelaAuxiliar = dados.getTabelaAuxiliar();

		// Faz a busca das empresas
		colecaoTabelaAuxiliar = fachada.pesquisar(
				filtroTabelaAuxiliar, tabelaAuxiliar
						.getClass().getName());

		if (colecaoTabelaAuxiliar == null
				|| colecaoTabelaAuxiliar.isEmpty()) {
			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, dados.getTitulo());
		}
		Map<String,Object> resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroTabelaAuxiliar, tabelaAuxiliar.getClass().getName());
		
		colecaoTabelaAuxiliar = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		sessao.setAttribute("colecaoTabelaAuxiliar", colecaoTabelaAuxiliar);

		// Repassa o tipo da pesquisa para a tela de resultado
		httpServletRequest.setAttribute("tipoPesquisa", httpServletRequest
				.getParameter("tipoPesquisa"));
		
		// Repassa o indicador de Uso para a tela de resultado
		httpServletRequest.setAttribute("indicadorUso", httpServletRequest
				.getParameter("indicadorUso"));

		// Repassa o caminho do resultado para a tela de resultado
		httpServletRequest.setAttribute("caminhoRetorno", httpServletRequest
				.getParameter("caminhoRetorno"));

		// Devolve o mapeamento de retorno
		return retorno;

	}
}
