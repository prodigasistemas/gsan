package gcom.gui.cadastro.localidade;

import java.util.Collection;
import java.util.Map;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de Quadra
 * 
 * @author Flávio
 */
public class ExibirPesquisarQuadraAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("pesquisarQuadra");

		Fachada fachada = Fachada.getInstancia();
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
        
		// Verifica se o pesquisar quadra foi chamado a partir do inserir quadra
		// e em caso afirmativo recebe o parâmetro e manda-o pela sessão para
		// ser verificado no quadra_resultado_pesquisa e depois retirado da
		// sessão no ExibirFiltrarQuadraAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}        
		
        String tipo = (String) httpServletRequest.getParameter("tipo");
        String idSetorComercial = null;
        String codigoSetorComercial = null;
        String idLocalidade = null;
        
        
        if(httpServletRequest
                .getParameter("idSetorComercial") != null 
                && !httpServletRequest
                .getParameter("idSetorComercial").trim().equalsIgnoreCase("")){
        	
        	idSetorComercial = (String) httpServletRequest.getParameter("idSetorComercial");
        	sessao.setAttribute("idSetorComercial", idSetorComercial);
        }else{
        	idSetorComercial = (String) sessao.getAttribute("idSetorComercial");
        }
        
        if(httpServletRequest
                .getParameter("idLocalidade") != null 
                && !httpServletRequest
                .getParameter("idLocalidade").trim().equalsIgnoreCase("")){
        	
        	idLocalidade = (String) httpServletRequest.getParameter("idLocalidade");
        	sessao.setAttribute("idLocalidade", idLocalidade);
        }else{
        	idLocalidade = (String) sessao.getAttribute("idLocalidade");
        }
        
        if(httpServletRequest
                .getParameter("codigoSetorComercial") != null 
                && !httpServletRequest
                .getParameter("codigoSetorComercial").trim().equalsIgnoreCase("")){
        	
        	codigoSetorComercial = (String) httpServletRequest
             .getParameter("codigoSetorComercial");
        	sessao.setAttribute("codigoSetorComercial", codigoSetorComercial);
        }else{
        	codigoSetorComercial = (String) sessao.getAttribute("codigoSetorComercial");
        }
        
        if(tipo != null && !tipo.trim().equalsIgnoreCase("")){
        	sessao.setAttribute("tipoPesquisa", tipo);
        }else{
        	 pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        }
        
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo Hibernate

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
        
		filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
        if (idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, new Integer(
							idSetorComercial)));
		}
        
        if (idLocalidade != null
				&& !idLocalidade.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidade)));
		}
        
		if (codigoSetorComercial != null
				&& !codigoSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
							codigoSetorComercial)));
		}

		if (sessao.getAttribute("indicadorUsoTodos") == null) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}
                
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());

		if (quadras != null && !quadras.isEmpty()) {
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroQuadra, Quadra.class.getName());
			quadras = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("quadras", quadras);
		} else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "quadra");
		}

		// Passa parametros para distinguir o tipo de retorno
		sessao.setAttribute("tipoPesquisa", sessao
						.getAttribute("tipoPesquisa"));
        
		String idRota = (String) pesquisarActionForm.get("idRota");
		
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		pesquisarActionForm.set("codigoRota", rota.getCodigo().toString());
        	}
        }
        
        //envia uma flag que será verificado no quadra_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaQuadra", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }
        
        if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
	    }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }

        return retorno;
    }

}
