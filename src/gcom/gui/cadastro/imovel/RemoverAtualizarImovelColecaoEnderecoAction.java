package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverAtualizarImovelColecaoEnderecoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("atualizarImovelEndereco");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //DynaValidatorForm inserirClienteActionForm = (DynaValidatorForm) sessao
         //       .getAttribute("InserirImovelActionForm");

        Collection colecaoEnderecos = (Collection) sessao
                .getAttribute("colecaoEnderecos");

        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {

            //String[] codigoEnderecos = (String[])
            //        inserirClienteActionForm.get("enderecoRemoverSelecao");
            String[] codigoEnderecos = httpServletRequest
                    .getParameterValues("enderecoRemoverSelecao");

            Iterator iteratorEnderecos = colecaoEnderecos.iterator();

            while (iteratorEnderecos.hasNext()) {
                Imovel imovel = (Imovel) iteratorEnderecos.next();

                for (int i = 0; i < codigoEnderecos.length; i++) {
                    if (imovel.getUltimaAlteracao().getTime() == 
                        Long.parseLong(codigoEnderecos[i])) {
                    	
                        iteratorEnderecos.remove();
                        sessao.removeAttribute("colecaoEnderecos");
                    }
                }

            }
        }
        return retorno;
    }

}
