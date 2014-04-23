package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirAtualizarTarifaSocialCartaoTipoAction extends GcomAction {
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("atualizarTarifaSocialCartaoTipo");

        TarifaSocialCartaoTipoActionForm tarifaSocialCartaoTipoActionForm = (TarifaSocialCartaoTipoActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String id = httpServletRequest.getParameter("idRegistroAtualizacao");

        //------Inicio da parte que verifica se vem da página de
        // manter_bairro.jsp

        if (id != null && !id.equals("")) {

            FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

            filtroTarifaSocialCartaoTipo
                    .adicionarParametro(new ParametroSimples(
                            FiltroTarifaSocialCartaoTipo.ID, id));

            Collection colecaoTarifaSocialCartaoTipo = fachada.pesquisar(
                    filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class
                            .getName());

            if (colecaoTarifaSocialCartaoTipo != null
                    && !colecaoTarifaSocialCartaoTipo.isEmpty()) {
                //a tarifa social cartao tipo foi encontrado
                tarifaSocialCartaoTipoActionForm
                        .setId(Util
                                .formatarResultado(((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                                        .get(0)).getId().toString()));

                tarifaSocialCartaoTipoActionForm
                        .setDescricao(Util
                                .formatarResultado(((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                                        .get(0)).getDescricao()));

                tarifaSocialCartaoTipoActionForm
                        .setDescricaoAbreviada(Util
                                .formatarResultado(""
                                        + ((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                                                .get(0))
                                                .getDescricaoAbreviada()));

                tarifaSocialCartaoTipoActionForm
                        .setValidade(Util
                                .formatarResultado(((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                                        .get(0)).getIndicadorValidade()
                                        .toString()));

                if ((((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                        .get(0)).getNumeroMesesAdesao()) != null) {

                    tarifaSocialCartaoTipoActionForm
                            .setNumeroMaximoMeses(Util
                                    .formatarResultado(""
                                            + ((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                                                    .get(0))
                                                    .getNumeroMesesAdesao()
                                                    .toString()));
                }

                if ( ((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo).get(0)).getIndicadorUso() != null){
	                tarifaSocialCartaoTipoActionForm
	                        .setIndicadorUso(Util
	                                .formatarResultado(""
	                                        + ((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
	                                                .get(0)).getIndicadorUso()
	                                                .toString()));
                }

                TarifaSocialCartaoTipo tarifaSocialCartaoTipo = ((TarifaSocialCartaoTipo) ((List) colecaoTarifaSocialCartaoTipo)
                        .get(0));

                sessao.setAttribute("tarifaSocialCartaoTipo",
                        tarifaSocialCartaoTipo);

            }
        }

        return retorno;
    }

}
