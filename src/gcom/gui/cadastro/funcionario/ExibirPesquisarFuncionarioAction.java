package gcom.gui.cadastro.funcionario;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a pesquisa de funcionario de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 02/03/2006
 */

public class ExibirPesquisarFuncionarioAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("funcionarioPesquisar");
        
        //PesquisarFuncionarioActionForm pesquisarFuncionarioActionForm = (PesquisarFuncionarioActionForm) actionForm;
        
        PesquisarFuncionarioActionForm pesquisarFuncionarioActionForm = (PesquisarFuncionarioActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
            
        if ((httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
        	
        	pesquisarFuncionarioActionForm.setId("");
        	pesquisarFuncionarioActionForm.setNome("");
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("");
        }
        
        //-------Parte que trata do código quando o usuário tecla enter        

        String idDigitadoEnterUnidadeEmpresa = pesquisarFuncionarioActionForm.getIdUnidadeEmpresa();
        
        //Verifica se o código da Unidade Empresa foi digitado
        if (idDigitadoEnterUnidadeEmpresa != null && 
        	!idDigitadoEnterUnidadeEmpresa.trim().equals("") && 
        	Integer.parseInt(idDigitadoEnterUnidadeEmpresa) > 0) {
        	
        	FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

        	filtroUnidadeEmpresa.adicionarParametro(
        		new ParametroSimples(
        			FiltroUnidadeOrganizacional.ID, 
        			idDigitadoEnterUnidadeEmpresa));
			
			Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = 
				this.getFachada().pesquisar(filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getName());

			if (unidadeEmpresaEncontrada != null && !unidadeEmpresaEncontrada.isEmpty()) {

				//a unidade de Unidade Empresa foi encontrado
				pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("" + 
					((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0)).getId());
				
				pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa(
					((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0)).getDescricao());
				
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada","true");
				httpServletRequest.setAttribute("nomeCampo", "descricaoUnidadeEmpresa");

			} else {

				pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada","exception");
				pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("UNIDADE DE LOTAÇÃO INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeEmpresa");

			}

        }
        //-------Fim de parte que trata do código quando o usuário tecla enter    
        
        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {
            //se for os parametros de enviarDadosParametros serão mandados para
            // a pagina usuario_pesquisar.jsp
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa(
                        httpServletRequest.getParameter("idCampoEnviarDados"));
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa(
        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        }
        
        //envia uma flag que será verificado no funcionario_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaFuncionario") != null) {
        	  sessao.setAttribute("caminhoRetornoTelaPesquisaFuncionario",
        	          httpServletRequest
        	                  .getParameter("caminhoRetornoTelaPesquisaFuncionario"));
        	}
        sessao.removeAttribute("caminhoRetornoTelaPesquisaUnidadeEmpresa");
        
        if (httpServletRequest.getAttribute("nomeCampo") == null){
        	httpServletRequest.setAttribute("nomeCampo", "id");
        }
        
        if (httpServletRequest.getParameter("limpaForm") != null){
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setId("");
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setNome("");
        }
        

        pesquisarFuncionarioActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        
        if(httpServletRequest.getParameter("tipo") != null){
			sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
		}
        
        return retorno;
        
    }

}
