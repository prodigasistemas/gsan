package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RemoverSelecaoBairroAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	
        ActionForward retorno = actionMapping.findForward("exibirSelecionarBairro");

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarBairroActionForm selecionarBairroActionForm = (SelecionarBairroActionForm) actionForm;

        if (sessao.getAttribute("colecaoBairrosSelecionados") != null){
        	
        	Collection colecaoBairrosSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionados");

            if (selecionarBairroActionForm.getIdBairroSelecao() != null) {

                Iterator colecaoBairrosSelecionadosUsuarioIterator;

                Bairro bairroInserir;

                int indexArray = 0;
                Integer bairroID;

                String[] bairrosSelecionados = (String[]) selecionarBairroActionForm.getIdBairroSelecao();

                while (bairrosSelecionados.length > indexArray) {
                    bairroID = new Integer(bairrosSelecionados[indexArray]);

                    colecaoBairrosSelecionadosUsuarioIterator = colecaoBairrosSelecionadosUsuario
                            .iterator();

                    while (colecaoBairrosSelecionadosUsuarioIterator.hasNext()) {

                        bairroInserir = (Bairro) colecaoBairrosSelecionadosUsuarioIterator
                                .next();

                        if (bairroInserir.getId().equals(bairroID)) {
                        	colecaoBairrosSelecionadosUsuario.remove(bairroInserir);
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
