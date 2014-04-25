package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoCampos;
import gcom.util.filtro.ComparacaoTexto;
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
 * Description of the Class
 * 
 * @author Compesa
 * @created 19 de Maio de 2005
 */
public class PesquisarEloAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("listaElo");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //cria variaveis
        String descricao = (String) pesquisarActionForm.get("descricao");
        String idGerencia;

        if (sessao.getAttribute("idGerencia") != null) {
            idGerencia = sessao.getAttribute("idGerencia").toString();
        } else {
            idGerencia = (String) pesquisarActionForm.get("idGerenciaRegional");
        }

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
        
        filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

        boolean peloMenosUmParametroInformado = false;

        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroLocalidade.adicionarParametro(new ComparacaoTexto(
                    FiltroLocalidade.DESCRICAO, descricao));
        }

        if (idGerencia != null
                && !idGerencia.trim().equalsIgnoreCase("")
                && !idGerencia.trim().equalsIgnoreCase(
                        String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

            peloMenosUmParametroInformado = true;
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_GERENCIA, new Integer(idGerencia)));
        }

        filtroLocalidade.adicionarParametro(new ComparacaoCampos(
                FiltroLocalidade.ID, "localidade"));

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        filtroLocalidade.adicionarParametro(new ParametroSimples(
        		FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroLocalidade, Localidade.class.getName());
		Collection elos = (Collection) resultado.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");        
        
         
        if (elos != null && !elos.isEmpty()) {
            sessao.setAttribute("elos", elos);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Elo");
        }

        return retorno;
    }
}
