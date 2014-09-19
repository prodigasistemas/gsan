package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.ControladorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverManterFaturamentoCronogramaAction extends GcomAction {

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

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        //mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum
        //registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }

        try {
			fachada.removerFaturamentoCronograma(ids, FaturamentoGrupoCronogramaMensal.class.getName());
		} catch (ControladorException e) {
			
			e.printStackTrace();
		}

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(
                    httpServletRequest,
                    ids.length + " Cronograma(s) de Faturamento removido(s) com sucesso",
                    "Realizar outra Manutenção de Cronograma de Faturamento",
                    "exibirFiltrarFaturamentoCronogramaAction.do");
        }

        return retorno;
    }

}
