package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
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
 * Action que define o processamento da página de pesquisa de Localidade
 * 
 * @author Administrador
 */

public class PesquisarLocalidadeAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("listaLocalidade");
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //cria variaveis
        String idLocalidade = (String) pesquisarActionForm.get("idLocalidade");
        String descricao = (String) pesquisarActionForm
                .get("descricaoLocalidade");
        String idGerencia = (String) pesquisarActionForm
                .get("idGerenciaRegional");
        String idElo = (String)sessao.getAttribute("idElo");
        String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

        boolean peloMenosUmParametroInformado = false;

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
        
        //Objetos que serão retornados pelo Hibernate
        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

        if( sessao.getAttribute("indicadorUsoTodos") == null ){
    	    filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, new Integer(idLocalidade)));
            peloMenosUmParametroInformado = true;
        }
        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
        
    			if (tipoPesquisa != null
    					&& tipoPesquisa
    							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
    									.toString())) {
    				filtroLocalidade.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroLocalidade.DESCRICAO, descricao));
    			} else {
    				filtroLocalidade.adicionarParametro(new ComparacaoTexto(
    						FiltroLocalidade.DESCRICAO, descricao));
    			}
        
        }
        
        
        if (idGerencia != null && !idGerencia.trim().equalsIgnoreCase("")
                && !idGerencia.trim().equalsIgnoreCase("-1")) {
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_GERENCIA, new Integer(idGerencia)));
            peloMenosUmParametroInformado = true;
        }
        if(idElo != null && !idElo.equals("")){
        	filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_ELO, new Integer(idElo)));
        	 peloMenosUmParametroInformado = true;
        }

        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }
        
        //          filtroLocalidade.adicionarParametro(new
        // ComparacaoCampos(FiltroLocalidade.ID, "localidade"));
        Collection localidades = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (localidades != null && !localidades.isEmpty()) {
//        	 Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLocalidade, Localidade.class.getName());
			localidades = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("localidades", localidades);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "localidade");
        }

       // httpServletRequest.setAttribute("tipoPesquisa", sessao
        //       .getAttribute("tipoPesquisa"));

        //sessao.removeAttribute("tipoPesquisa");
        
        return retorno;
    }

}
