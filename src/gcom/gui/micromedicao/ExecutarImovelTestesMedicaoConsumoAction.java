package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExecutarImovelTestesMedicaoConsumoAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

        faturamentoGrupo.setId(new Integer("34"));
        faturamentoGrupo.setAnoMesReferencia(new Integer("200408"));

        Imovel imovel = new Imovel();

        // fachada.executarImovelTestesMedicaoConsumo(faturamentoGrupo,imovel);

        //Imovel imovel = new Imovel();

        //    imovel.setId(new Integer("54257708"));

        fachada.executarImovelTestesMedicaoConsumo(faturamentoGrupo, imovel);

        //Método utilizado para montar a página de sucesso
        montarPaginaSucesso(httpServletRequest,
                "Execução do teste realizado com sucesso",
                "Realizar outro teste",
                "executarImovelTestesMedicaoConsumoAction.do");

        return retorno;
    }
}
