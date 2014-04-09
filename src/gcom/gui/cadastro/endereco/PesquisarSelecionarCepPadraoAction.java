package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário o CEP Padrão do município passado
 *
 * @author Raphael Rossiter
 * @date 04/05/2006
 */
public class PesquisarSelecionarCepPadraoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarCep");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarCepActionForm selecionarCepActionForm = (SelecionarCepActionForm) actionForm;

        Municipio municipio = null;
        
        FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        
        filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
        selecionarCepActionForm.getIdMunicipio()));
        
        Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
        
        municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
        
        Cep cep = fachada.obterCepInicialMunicipio(municipio);
        
        if (cep == null) {
            
        	httpServletRequest.setAttribute("cepPadrao", "OK");
        	sessao.removeAttribute("colecaoCepSelecionados");
        	
        } else {
        	Collection colecaoCep = new ArrayList();
        	colecaoCep.add(cep);
        	
        	sessao.setAttribute("colecaoCepSelecionados", colecaoCep);
        }
        
        
        httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");

        
        return retorno;
    }

}
