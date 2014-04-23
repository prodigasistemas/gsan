package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o processamento da página de pesquisa de Setor Comercial
 * 
 * @author Flávio
 */
public class PesquisarSetorComercialAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("listaSetorComercial");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        String codigoSetorComercial = (String) pesquisarActionForm
                .get("codigoSetorComercial");
        String descricao = (String) pesquisarActionForm.get("descricao");
        String descricaoMunicipio = (String) pesquisarActionForm
                .get("descricaoMunicipio");
        String tipoPesquisaDescricao = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
        String tipoPesquisaMunicipio = (String) pesquisarActionForm.get("tipoPesquisaMunicipio");
        String indicadorSetorAlternativo = (String) pesquisarActionForm.get("indicadorSetorAlternativo");
        
        boolean peloMenosUmParametroInformado = false;

        String idLocalidade = null;
        String tipoPesquisa = null;
        
        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
        
        //Objetos que serão retornados pelo Hibernate
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
        
        filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL);
        
        Fachada fachada = Fachada.getInstancia();

        if (sessao.getAttribute("idLocalidade") != null) {
            idLocalidade = sessao.getAttribute("idLocalidade").toString();
        }
        
        if (sessao.getAttribute("indicadorSetorAlternativo") != null) {
        	indicadorSetorAlternativo = sessao.getAttribute("indicadorSetorAlternativo").toString();
        }

        if (sessao.getAttribute("tipoPesquisa") != null) {
            tipoPesquisa = sessao.getAttribute("tipoPesquisa").toString();
        }
        if( sessao.getAttribute("indicadorUsoTodos") == null )
        {
    	    filtroSetorComercial.adicionarParametro(new ParametroSimples(
                FiltroSetorComercial.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        if (codigoSetorComercial != null
                && !codigoSetorComercial.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
                            codigoSetorComercial)));
            peloMenosUmParametroInformado = true;
        }
        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            if (tipoPesquisaDescricao != null
					&& tipoPesquisaDescricao
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
            	filtroSetorComercial.adicionarParametro(new ComparacaoTextoCompleto(
                        FiltroSetorComercial.DESCRICAO, descricao));
			} else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
	                    FiltroSetorComercial.DESCRICAO, descricao));
			}
            peloMenosUmParametroInformado = true;
        }
        if (descricaoMunicipio != null
                && !descricaoMunicipio.trim().equalsIgnoreCase("")) {
            if (tipoPesquisaMunicipio != null
					&& tipoPesquisaMunicipio
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
            	filtroSetorComercial.adicionarParametro(new ComparacaoTextoCompleto(
                        FiltroSetorComercial.DESCRICAO_MUNICIPIO,
                        descricaoMunicipio));
             } else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
	                    FiltroSetorComercial.DESCRICAO_MUNICIPIO,
	                    descricaoMunicipio));
			}
            peloMenosUmParametroInformado = true;
        }
        if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, new Integer(
                            idLocalidade)));
            peloMenosUmParametroInformado = true;
        }
        
        if ( indicadorSetorAlternativo != null && !indicadorSetorAlternativo.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO, new Integer(
                    		indicadorSetorAlternativo)));
            peloMenosUmParametroInformado = true;
        }

        if (!peloMenosUmParametroInformado) {
            //throw new ActionServletException(
              //      "atencao.filtro.nenhum_parametro_informado");
        }
        

        Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        if (setorComerciais != null && !setorComerciais.isEmpty()) {
//       	 Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroSetorComercial, SetorComercial.class.getName());
			setorComerciais = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
                sessao.setAttribute("setorComerciais", setorComerciais);

        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "setor comercial");
        }

        //Passa o parametro para o tipo de resultado e o tira da sessão
        httpServletRequest.setAttribute("tipoPesquisa", tipoPesquisa);
        
        return retorno;
    }

}
