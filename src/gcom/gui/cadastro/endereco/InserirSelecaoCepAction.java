package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
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
 * Esta classe tem por finalidade incluir os CEPs que foram selecionados pelo usuário na coleção final
 *
 * @author Raphael Rossiter
 * @date 03/05/2006
 */
public class InserirSelecaoCepAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarCep");

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarCepActionForm selecionarCepActionForm = (SelecionarCepActionForm) actionForm;
        
        String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
        //String tipoOperacao = (String) sessao.getAttribute("operacao");

        
        if (tipoRetorno != null && !tipoRetorno.trim().equalsIgnoreCase("")) {
        
	        if (sessao.getAttribute("colecaoCepSelecionados") != null) {
	
	            // Coleção retornada pela pesquisa
	            Collection colecaoCepSelecionados = (Collection) sessao
	            .getAttribute("colecaoCepSelecionados");
	
	            Collection colecaoCepSelecionadosUsuario = new ArrayList();
	
	            if (selecionarCepActionForm.getIdCepSelecao() != null) {
	
	                Iterator  colecaoCepSelecionadosIterator;
	
	                Cep cepInserir;
	
	                int indexArray = 0;
	                Integer cepID;
	
	                // CEPs selecionadas pelo usuário
	                String[] cepSelecionados = (String[]) selecionarCepActionForm.getIdCepSelecao();
	
	                while (cepSelecionados.length > indexArray) {
	                    cepID = new Integer(cepSelecionados[indexArray]);
	
	                    colecaoCepSelecionadosIterator = colecaoCepSelecionados
	                    .iterator();
	
	                    while (colecaoCepSelecionadosIterator.hasNext()) {
	
	                    	cepInserir = (Cep) colecaoCepSelecionadosIterator
	                        .next();
	
	                        if (cepInserir.getCepId().equals(cepID)) {
	                        	colecaoCepSelecionadosUsuario.add(cepInserir);
	                            break;
	                        }
	                    }
	
	                    indexArray++;
	                }
	
	                // A coleção pode ser acumulativa ou está sendo gerada pela primeira vez
	                if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null) {
	                
	                	Collection colecaoCepSelecionadosUsuarioSessao = (Collection) sessao
	                            .getAttribute("colecaoCepSelecionadosUsuario");
	
	                    Cep cep;
	                    Iterator iteratorColecaoCepSelecionadosUsuario = 
	                    colecaoCepSelecionadosUsuario.iterator();
	                    
	                    while(iteratorColecaoCepSelecionadosUsuario.hasNext()){
	                    	cep = (Cep) iteratorColecaoCepSelecionadosUsuario.next();
	                    	
	                    	if (!colecaoCepSelecionadosUsuarioSessao.contains(cep)){
	                    		colecaoCepSelecionadosUsuarioSessao.add(cep);
	                    	}
	                    	else{
	                    		throw new ActionServletException(
	                                    "atencao.objeto_ja_selecionado", null, "Cep");
	                    	}
	                    }
	                
	                } else {
	                    sessao.setAttribute("colecaoCepSelecionadosUsuario",
	                    		colecaoCepSelecionadosUsuario);
	                }
	
	                // Remove a coleção gerada pela pesquisa efetuada pelo usuário
	                sessao.removeAttribute("colecaoCepSelecionados");
	
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
