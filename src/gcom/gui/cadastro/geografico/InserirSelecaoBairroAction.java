package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade incluir os bairros que foram selecionados pelo usuário na coleção final
 *
 * @author Raphael Rossiter
 * @date 02/05/2006
 */
public class InserirSelecaoBairroAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarBairro");

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarBairroActionForm selecionarBairroActionForm = (SelecionarBairroActionForm) actionForm;
        
        String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
        //String tipoOperacao = (String) sessao.getAttribute("operacao");

        if (tipoRetorno != null && !tipoRetorno.trim().equalsIgnoreCase("")){
        	
        
        	if (sessao.getAttribute("colecaoBairrosSelecionados") != null) {

            // Coleção retornada pela pesquisa
            Collection colecaoBairrosSelecionados = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionados");

            Collection colecaoBairrosSelecionadosUsuario = new ArrayList();

            if (selecionarBairroActionForm.getIdBairroSelecao() != null) {

                Iterator  colecaoBairrosSelecionadosIterator;

                Bairro bairroInserir;

                int indexArray = 0;
                Integer bairroID;

                // Bairros selecionadas pelo usuário
                String[] bairrosSelecionados = selecionarBairroActionForm.getIdBairroSelecao();

                while (bairrosSelecionados.length > indexArray) {
                    bairroID = new Integer(bairrosSelecionados[indexArray]);

                    colecaoBairrosSelecionadosIterator = colecaoBairrosSelecionados
                    .iterator();

                    while (colecaoBairrosSelecionadosIterator.hasNext()) {

                    	bairroInserir = (Bairro) colecaoBairrosSelecionadosIterator
                        .next();

                        if (bairroInserir.getId().equals(bairroID)) {
                        	colecaoBairrosSelecionadosUsuario.add(bairroInserir);
                            break;
                        }
                    }

                    indexArray++;
                }

                // A coleção pode ser acumulativa ou está sendo gerada pela primeira vez
                if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null) {
                
                	Collection colecaoBairrosSelecionadosUsuarioSessao = (Collection) sessao
                            .getAttribute("colecaoBairrosSelecionadosUsuario");

                    Bairro bairro;
                    Iterator iteratorColecaoBairrosSelecionadosUsuario = 
                    colecaoBairrosSelecionadosUsuario.iterator();
                    
                    while(iteratorColecaoBairrosSelecionadosUsuario.hasNext()){
                    	bairro = (Bairro) iteratorColecaoBairrosSelecionadosUsuario.next();
                    	
                    	if (!colecaoBairrosSelecionadosUsuarioSessao.contains(bairro)){
                    		colecaoBairrosSelecionadosUsuarioSessao.add(bairro);
                    	}
                    	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");
                    	}
                    }
                
                } else {
                    sessao.setAttribute("colecaoBairrosSelecionadosUsuario",
                    		colecaoBairrosSelecionadosUsuario);
                }

                // Remove a coleção gerada pela pesquisa efetuada pelo usuário
                sessao.removeAttribute("colecaoBairrosSelecionados");

                // Flag para indicar o retorno para o caso de uso que chamou a funcionalidade
                httpServletRequest.setAttribute("retornarUseCase", "OK");
                
                
                if (tipoRetorno.trim().equalsIgnoreCase("logradouro")) {
                    httpServletRequest.setAttribute("flagRedirect", "logradouro");
                } else if (tipoRetorno.trim().equalsIgnoreCase("endereco")) {
                    httpServletRequest.setAttribute("flagRedirect", "endereco");
                }
            }

        	}
        }
        return retorno;
    }

}
