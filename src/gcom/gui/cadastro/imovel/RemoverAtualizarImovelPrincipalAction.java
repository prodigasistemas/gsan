package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que remove o objeto selecionado de cliente imovel em atualizar imovel
 * 
 * @author Rafael Santos
 * @created 04/02/2006
 */
public class RemoverAtualizarImovelPrincipalAction extends GcomAction {

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
                .findForward("atualizarImovelConclusao");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm atualizarImovelClienteActionForm = (DynaValidatorForm) actionForm;

        //Cria variaveis
        Collection imoveisPrincipaisNovos = (Collection) sessao
                .getAttribute("imoveisPrincipal");

        //atribui os valores q vem pelo request as variaveis
        //String[] clientesImoveis = httpServletRequest
        //        .getParameterValues("idRemocaoPrincipalImovel");

        //instancia cliente
        //Imovel imovel = null;

        atualizarImovelClienteActionForm.set("idImovel", "");
        
        if (imoveisPrincipaisNovos != null
                && !imoveisPrincipaisNovos.equals("")) {

            Iterator ImovelPrincipalIterator = imoveisPrincipaisNovos
                    .iterator();

            while (ImovelPrincipalIterator.hasNext()) {
                /*imovel = (Imovel)*/ ImovelPrincipalIterator.next();
                //for (int i = 0; i < clientesImoveis.length; i++) {
                  //  if (imovel.getId().toString().trim().equalsIgnoreCase(
                    //        clientesImoveis[i])) {
                        ImovelPrincipalIterator.remove();
                        atualizarImovelClienteActionForm.set("idImovel", "");
                        sessao.removeAttribute("idImoveilPrincipal");
                        //verifica se o tipo do cliente é usuário
                    //}
                //}
            }

        }
        return retorno;
    }

}
