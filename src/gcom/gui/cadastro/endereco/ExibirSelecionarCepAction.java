package gcom.gui.cadastro.endereco;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da filtragem dos CEPs para seleção 
 *
 * @author Raphael Rossiter
 * @date 03/05/2006
 */
public class ExibirSelecionarCepAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarCep");
        
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarCepActionForm selecionarCepActionForm = (SelecionarCepActionForm) actionForm;
        
        
        String tipoRetorno = (String) httpServletRequest.getParameter("tipoPesquisaEndereco");
        String tipoOperacao = (String) httpServletRequest.getParameter("operacao");
        
        if (tipoRetorno != null && !tipoRetorno.trim().equalsIgnoreCase("")) {
			sessao.setAttribute("tipoPesquisaRetorno", tipoRetorno);
			sessao.setAttribute("operacao", tipoOperacao);
		}
        
        
        //Limpar formulário, caso necessário
        if (httpServletRequest.getParameter("limparForm") != null){

        	selecionarCepActionForm.setIdMunicipio("");
        	selecionarCepActionForm.setNomeMunicipio("");
        	selecionarCepActionForm.setNomeLogradouro("");
        	
        	//Retira da sessão a coleção de CEPs que foi selecionada anteriormente
        	sessao.removeAttribute("municipioInformado");
        	sessao.removeAttribute("colecaoCepSelecionados");
        }
        
        /*
         * Caso o parâmetro "Município" seja previamente definido pelo caso de uso que chama está funcionalidade,
         * o mesmo deverá ser mantido para realização da filtragem dos bairros
         */
        String idMunicipio = httpServletRequest.getParameter("idMunicipio");
        Collection colecaoMunicipio = null;
        Municipio municipio = null;
        
        if (idMunicipio != null && !idMunicipio.equals("")){
        	
        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        	
        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
        	
        	colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
        	
        	if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
        		
        		sessao.setAttribute("municipioInformado", idMunicipio);
        		
        		municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
        		
        		selecionarCepActionForm.setIdMunicipio(municipio.getId().toString());
            	selecionarCepActionForm.setNomeMunicipio(municipio.getNome());
        	}
        }
        
        
        return retorno;
	}

}
