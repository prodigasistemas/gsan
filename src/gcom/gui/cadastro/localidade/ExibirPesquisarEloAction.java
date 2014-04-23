package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
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
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Author: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 18 de Maio de 2005
 * @version 1.0
 */

public class ExibirPesquisarEloAction extends GcomAction {
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

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("pesquisarElo");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        
        if(httpServletRequest.getParameter("objetoConsulta") == null){
        	pesquisarActionForm.set("descricao", "");
        	pesquisarActionForm.set("idGerenciaRegional", String
        			.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        }
        String idGerencia = (String) httpServletRequest
                .getParameter("idGerencia");

        if (idGerencia != null && !idGerencia.trim().equalsIgnoreCase("")) {
            sessao.setAttribute("idGerencia", idGerencia);
        }

        Collection colecaoPesquisa = null;
        FiltroGerenciaRegional filtroGerenciaRegional = null;

        if (sessao.getAttribute("colecaoGerenciaRegional") == null) {

            filtroGerenciaRegional = new FiltroGerenciaRegional();
            
            filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
                    FiltroGerenciaRegional.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna Gerencia_Regional
            colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
                    GerenciaRegional.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Nenhum registro na tabela gerencia_regional foi encontrado
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "Gerencia_Regional");
            } else {
                sessao.setAttribute("colecaoGerenciaRegional", colecaoPesquisa);
            }
        }

        //envia uma flag que será verificado no quadra_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaElo", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }        
        
        //devolve o mapeamento de retorno
        return retorno;
    }
}
