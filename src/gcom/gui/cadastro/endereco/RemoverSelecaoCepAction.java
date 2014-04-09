package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade remover os CEPs que foram selecionados pelo usuário na coleção final
 *
 * @author Raphael Rossiter
 * @date 03/05/2006
 */
public class RemoverSelecaoCepAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	
        ActionForward retorno = actionMapping.findForward("exibirSelecionarCep");

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarCepActionForm selecionarCepActionForm = (SelecionarCepActionForm) actionForm;

        if (sessao.getAttribute("colecaoCepSelecionados") != null){
        	
        	Collection colecaoCepSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoCepSelecionados");

            if (selecionarCepActionForm.getIdCepSelecao() != null) {

                Iterator colecaoCepSelecionadosUsuarioIterator;

                Cep cepInserir;

                int indexArray = 0;
                Integer cepID;

                String[] cepSelecionados = (String[]) selecionarCepActionForm.getIdCepSelecao();

                while (cepSelecionados.length > indexArray) {
                    cepID = new Integer(cepSelecionados[indexArray]);

                    colecaoCepSelecionadosUsuarioIterator = colecaoCepSelecionadosUsuario
                            .iterator();

                    while (colecaoCepSelecionadosUsuarioIterator.hasNext()) {

                        cepInserir = (Cep) colecaoCepSelecionadosUsuarioIterator
                                .next();

                        if (cepInserir.getCepId().equals(cepID)) {
                        	colecaoCepSelecionadosUsuario.remove(cepInserir);
                            break;
                        }
                    }

                    indexArray++;
                }
            }
        }

        return retorno;
    }

}
