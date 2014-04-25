package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário os CEPs que serão retornados
 * a partir dos parâmetros que foram passados pelo filtro
 *
 * @author Raphael Rossiter
 * @date 03/05/2006
 */
public class PesquisarSelecionarCepAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarCep");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarCepActionForm selecionarCepActionForm = (SelecionarCepActionForm) actionForm;

        FiltroCep filtroCep = new FiltroCep();
        filtroCep.setConsultaSemLimites(true);
        
        Collection colecaoCep = null;

        //Validando os campos do formulario
        boolean peloMenosUmParametroInformado = false;

        
        //Município
        if (selecionarCepActionForm.getNomeMunicipio() != null && 
        	!selecionarCepActionForm.getNomeMunicipio().equals("")) {

            String nomeMunicipioJSP = selecionarCepActionForm.getNomeMunicipio();

            peloMenosUmParametroInformado = true;

            filtroCep.adicionarParametro(new ComparacaoTexto(
            FiltroCep.MUNICIPIO, nomeMunicipioJSP));
        } 

        
        //Logradouro
        if (selecionarCepActionForm.getNomeLogradouro() != null &&
        	!selecionarCepActionForm.getNomeLogradouro().equals("")) {

            String nomeLogradouroJSP = selecionarCepActionForm.getNomeLogradouro();

            peloMenosUmParametroInformado = true;

            filtroCep.adicionarParametro(new ComparacaoTexto(
            FiltroCep.LOGRADOURO, nomeLogradouroJSP));
        }
        
        
        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        //Retorna o(s) bairro(s)
        colecaoCep = fachada.pesquisar(filtroCep, Cep.class.getName());

        if (colecaoCep == null || colecaoCep.isEmpty()) {
            
        	/*
             * Nenhum CEP cadastrado de acordo com os parâmetros passados
             * Será disponibilizado para o usuário a opção de CEP Padrão
             */
        	httpServletRequest.setAttribute("cepPadrao", "OK");
        	sessao.removeAttribute("colecaoCepSelecionados");
        	
        } else {
        	sessao.setAttribute("colecaoCepSelecionados", colecaoCep);
        }
        
        httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");

        return retorno;
    }

}
