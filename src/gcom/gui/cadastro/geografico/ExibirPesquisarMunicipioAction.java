package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de responsavel
 * superior
 * 
 * @author Sávio Luiz
 * @created 26 de Abril de 2005
 */

public class ExibirPesquisarMunicipioAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarMunicipio");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("objetoConsulta") == null) {
			pesquisarActionForm.set("nomeMunicipio", "");
			pesquisarActionForm.set("idRegiaoDesenvolvimento", null);
			pesquisarActionForm.set("idRegiao", null);
			pesquisarActionForm.set("idMicrorregiao", null);
			pesquisarActionForm.set("idUnidadeFederacao", null);

		}
		
		// Verifica se o pesquisar município foi chamado a partir do inserir município
		// e em caso afirmativo recebe o parâmetro e manda-o pela sessão para
		// ser verificado no municipio_resultado_pesquisa e depois retirado da
		// sessão no ExibirFiltrarMunicipioAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}else{
			sessao.removeAttribute("consulta");
		}

		
		// cria os filtros para pesquisar os objetos
		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();

		FiltroRegiao filtroRegiao = new FiltroRegiao();

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		// inicializa a coleção
		Collection regiaoDesenvolvimentos = null;

		// inicializa a coleção
		Collection regioes = null;

		// inicializa a coleção
		Collection microrregioes = null;

		// inicializa a coleção
		Collection unidadesFederacao = null;

		// INICIO-------Parte que trata do pesquisa de dependencia

		// caso seja escolhido a região
		Integer idRegiao = (Integer) pesquisarActionForm.get("idRegiao");

		if (idRegiao != null
				&& idRegiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.REGIAO_ID, idRegiao));
			
			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

			if( httpServletRequest.getParameter("indicadorUsoTodos") == null){
				sessao.removeAttribute("indicadorUsoTodos");
				filtroMicrorregiao.adicionarParametro(new ParametroSimples(
						FiltroMicrorregiao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}

			microrregioes = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (microrregioes == null || microrregioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"microrregioes");
			} else {
				sessao.setAttribute("microrregioes", microrregioes);
				httpServletRequest.setAttribute("nomeCampo", "idMicrorregiao");
			}

			// FIM-------Parte que trata do pesquisa de dependencia
		} else {
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null){
				sessao.removeAttribute("indicadorUsoTodos");
				filtroRegiaoDesenvolvimento
						.adicionarParametro(new ParametroSimples(
								FiltroRegiaoDesenvolvimento.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			}	
			
			filtroRegiaoDesenvolvimento.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);
			
			regiaoDesenvolvimentos = fachada.pesquisar(
					filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class
							.getName());

			if (regiaoDesenvolvimentos == null
					|| regiaoDesenvolvimentos.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"regiao desenvolvimento");
			} else {
				sessao.setAttribute("regiaoDesenvolvimentos",
						regiaoDesenvolvimentos);
			}

			filtroRegiao.adicionarParametro(new ParametroSimples(
					FiltroRegiao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);
			
			regioes = fachada.pesquisar(filtroRegiao, Regiao.class.getName());

			if (regioes == null || regioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "regiao");
			} else {
				sessao.setAttribute("regioes", regioes);
			}

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
			microrregioes = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (microrregioes == null || microrregioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"microrregiao");
			} else {
				sessao.setAttribute("microrregioes", microrregioes);
			}

			filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
			
			unidadesFederacao = fachada.pesquisar(filtroUnidadeFederacao,
					UnidadeFederacao.class.getName());

			if (unidadesFederacao == null || unidadesFederacao.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"unidade federacao");
			} else {
				sessao.setAttribute("unidadesFederacao", unidadesFederacao);
			}

			// envia uma flag que será verificado no
			// municipio_resultado_pesquisa.jsp
			// para saber se irá usar o enviar dados ou o enviar dados
			// parametros
			if (httpServletRequest
					.getParameter("caminhoRetornoTelaPesquisaMunicipio") != null) {
				sessao
						.setAttribute(
								"caminhoRetornoTelaPesquisaMunicipio",
								httpServletRequest
										.getParameter("caminhoRetornoTelaPesquisaMunicipio"));
				if(sessao.getAttribute("caminhoRetornoTelaPesquisaBairro") != null)
				{
					sessao.setAttribute("caminho",sessao.getAttribute("caminhoRetornoTelaPesquisaBairro")); 
				}
			}else{
				
				if(sessao.getAttribute("caminhoRetornoTelaPesquisaMunicipio") != null){
					sessao.setAttribute("caminho",sessao.getAttribute("caminhoRetornoTelaPesquisaMunicipio")); 
				}else{
					sessao
					.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
				}
			}
		}
		
		pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		if( httpServletRequest.getParameter("indicadorUsoTodos") != null){
			sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
		}
		return retorno;
	}
}
