package gcom.gui.util.tabelaauxiliar;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

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
public class InserirTabelaAuxiliarAction extends GcomAction {
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
        TabelaAuxiliarActionForm tabelaAuxiliarActionForm = (TabelaAuxiliarActionForm) actionForm;

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Recebe o objeto Usuario
        TabelaAuxiliar tabelaAuxiliar = (TabelaAuxiliar) sessao
                .getAttribute("tabelaAuxiliar");

        //Seta a descrição informada
        tabelaAuxiliar.setDescricao(tabelaAuxiliarActionForm.getDescricao());

        tabelaAuxiliar.setUltimaAlteracao(new Date());
        tabelaAuxiliar.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

        //Insere objeto
        fachada.inserirTabelaAuxiliar(tabelaAuxiliar);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(
                    httpServletRequest,
                    "Dados incluídos com sucesso",
                    "Inserir outro(a) "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarInserir")));
        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarInserir");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliar");
        sessao.removeAttribute("tamanhoMaximoCampo");

        return retorno;
    }
}
