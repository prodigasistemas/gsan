package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ErroRepositorioException;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;

import java.rmi.RemoteException;
import java.util.Date;

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
public class InserirTabelaAuxiliarFaixaAction extends GcomAction {
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
     * @exception RemoteException
     *                Descrição da exceção
     * @exception ErroRepositorioException
     *                Descrição da exceção
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws RemoteException,
            ErroRepositorioException {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        // Prepara o retorno da Ação
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém o action form
        TabelaAuxiliarFaixaActionForm tabelaAuxiliarFaixaActionForm = (TabelaAuxiliarFaixaActionForm) actionForm;

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Recebe o objeto TabelaAuxiliarFaixa
        TabelaAuxiliarFaixa tabelaAuxiliarFaixa = (TabelaAuxiliarFaixa) sessao
                .getAttribute("tabelaAuxiliarFaixa");

        //Seta faixa inicial informada
        tabelaAuxiliarFaixa.setFaixaInicial(tabelaAuxiliarFaixaActionForm
                .getFaixaInicial());

        //Seta faixa final faixa informada
        tabelaAuxiliarFaixa.setFaixaFinal(tabelaAuxiliarFaixaActionForm
                .getFaixaFinal());

        //Seta a data atual
        tabelaAuxiliarFaixa.setUltimaAlteracao(new Date());

        //Insere objeto
        fachada.inserirTabelaAuxiliar(tabelaAuxiliarFaixa);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(
                    httpServletRequest,
                    "Dados incluídos com sucesso",
                    "Inserir outro(a) "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarFaixaInserir")));
        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaInserir");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliarFaixa");

        return retorno;
    }
}
