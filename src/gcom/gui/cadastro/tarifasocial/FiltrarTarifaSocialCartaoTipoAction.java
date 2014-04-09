package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarTarifaSocialCartaoTipoAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Define a ação de retorno
        ActionForward retorno = actionMapping
                .findForward("retornarFiltroTarifaSocialCartaoTipo");

        //Mudar isso quando tiver esquema de segurança
        //HttpSession sessao = httpServletRequest.getSession(false);

        //DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
/**
        //Recupera os parâmetros do form
        String id = (String) pesquisarActionForm.get("id");
        String descricao = (String) pesquisarActionForm.get("descricao");
        String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
*/
        FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo(
                FiltroTarifaSocialCartaoTipo.DESCRICAO);
/**
        boolean peloMenosUmParametroInformado = false;

        //Insere os parâmetros informados no filtro

        if (id != null && !id.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroTarifaSocialCartaoTipo
                    .adicionarParametro(new ParametroSimples(
                            FiltroTarifaSocialCartaoTipo.ID, id));
        }
        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroTarifaSocialCartaoTipo
                    .adicionarParametro(new ComparacaoTexto(
                            FiltroTarifaSocialCartaoTipo.DESCRICAO, descricao));
        }

        if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroTarifaSocialCartaoTipo
                    .adicionarParametro(new ParametroSimples(
                            FiltroTarifaSocialCartaoTipo.INDICADOR_USO,
                            indicadorUso));
        }

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
//        if (!peloMenosUmParametroInformado) {
//            throw new ActionServletException(
//                    "atencao.filtro.nenhum_parametro_informado");
//        }*/

        //Manda o filtro pelo request para o
        // ExibirManterTarifaSocialCartaoTipoAction
        httpServletRequest.setAttribute("filtroTarifaSocialCartaoTipo",
                filtroTarifaSocialCartaoTipo);

        return retorno;

    }
}
