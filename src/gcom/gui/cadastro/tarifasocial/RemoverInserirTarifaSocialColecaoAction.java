package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class RemoverInserirTarifaSocialColecaoAction extends GcomAction {

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
                .findForward("inserirTarifaSocialDadosUmaEconomia");

        HttpSession sessao = httpServletRequest.getSession(false);

        String remover = httpServletRequest.getParameter("remover");

        if (remover != null && !remover.equals("")) {

            Collection colecaoRemocao = new Vector();

            if (sessao.getAttribute("colecaoDadosTarifaSocial") != null) {
                colecaoRemocao = (Collection) sessao
                        .getAttribute("colecaoDadosTarifaSocial");
            } else if (sessao.getAttribute("colecaoClienteImovelEconomia") != null) {
                colecaoRemocao = (Collection) sessao
                        .getAttribute("colecaoClienteImovelEconomia");

                retorno = actionMapping
                        .findForward("inserirTarifaSocialDadosMultiplasEconomias");
            }

            ClienteImovelEconomia clienteImovelEconomia;
            TarifaSocialDadoEconomia tarifaSocialDadoEconomia;
            Iterator itColecaoRemocao = colecaoRemocao.iterator();

            //Para apenas uma economia
            if (colecaoRemocao.size() <= 1) {
                while (itColecaoRemocao.hasNext()) {
                    tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) itColecaoRemocao
                            .next();

                    if (tarifaSocialDadoEconomia.getUltimaAlteracao()
                            .toString().equals(remover)) {
                        colecaoRemocao.remove(tarifaSocialDadoEconomia);
                        break;
                    }
                }
            }
            //Para mais de uma economia
            else {

                Collection colecaoTarifaSocialDadoMultiplasEconomia;

                while (itColecaoRemocao.hasNext()) {

                    clienteImovelEconomia = (ClienteImovelEconomia) itColecaoRemocao
                            .next();
                    colecaoTarifaSocialDadoMultiplasEconomia = clienteImovelEconomia
                            .getImovelEconomia().getTarifaSocialDadoEconomias();

                    if (colecaoTarifaSocialDadoMultiplasEconomia != null
                            && !colecaoTarifaSocialDadoMultiplasEconomia
                                    .isEmpty()) {

                        tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) Util
                                .retonarObjetoDeColecao(colecaoTarifaSocialDadoMultiplasEconomia);

                        if (tarifaSocialDadoEconomia.getUltimaAlteracao()
                                .toString().equals(remover)) {
                            colecaoTarifaSocialDadoMultiplasEconomia
                                    .remove(tarifaSocialDadoEconomia);
                            break;
                        }
                    }
                }

            }
        }

        return retorno;
    }
}
