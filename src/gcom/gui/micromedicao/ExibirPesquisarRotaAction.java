package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que define o pré-processamento da página de pesquisa de Rota
 * 
 * @author Rafael Santos
 * @since 23/08/2006
 */
public class ExibirPesquisarRotaAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("pesquisarRota");
        
        DynaValidatorActionForm form = (DynaValidatorActionForm) actionForm;

		
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		//DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		String codigoSetorComercial = null;
		String idLocalidade = null;

		if (httpServletRequest.getParameter("destino") != null
				&& !httpServletRequest.getParameter("destino").trim()
						.equalsIgnoreCase("")) {

			sessao.setAttribute("destino",(String) httpServletRequest
					.getParameter("destino"));
		}

		
		if (httpServletRequest.getParameter("codigoSetorComercial") != null
				&& !httpServletRequest.getParameter("codigoSetorComercial").trim()
						.equalsIgnoreCase("")) {

			codigoSetorComercial = (String) httpServletRequest
					.getParameter("codigoSetorComercial");
            
            form.set( "codigoSetorComercial", codigoSetorComercial );
		}

		if (httpServletRequest.getParameter("idLocalidade") != null
				&& !httpServletRequest.getParameter("idLocalidade").trim()
						.equalsIgnoreCase("")) {

			idLocalidade = (String) httpServletRequest
					.getParameter("idLocalidade");
            
            form.set( "idLocalidade", idLocalidade );
        }
        
        codigoSetorComercial = ( codigoSetorComercial == null ? (String) form.get( "codigoSetorComercial" ) : codigoSetorComercial );
        idLocalidade = ( idLocalidade == null ? (String) form.get( "idLocalidade" ) : idLocalidade );

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

        /** 
         * carregamento do filtro comentado por pedro alexandre dia :22/01/2007
         */
		/*filtroRota
				.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL_ID);*/
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.LOCALIDADE_ID, idLocalidade));
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));

		filtroRota.setCampoOrderBy(FiltroRota.CODIGO_ROTA);

		if (httpServletRequest.getParameter("indicadorUso") != null
				&& !httpServletRequest.getParameter("indicadorUso").trim()
						.equalsIgnoreCase("")) {
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}
		
		if(httpServletRequest.getParameter("indicadorRotaAlternativa") != null && !httpServletRequest.getParameter("indicadorRotaAlternativa").trim().equalsIgnoreCase("") ){
		  filtroRota.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADOR_ROTA_ALTERNATIVA,ConstantesSistema.NAO));		  
		}

		Collection rotas = null;
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroRota, Rota.class.getName());
		rotas = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");	

		if (rotas == null || rotas.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "rota");
		}

		sessao.setAttribute("rotas", rotas);
		
		return retorno;
	}

}
