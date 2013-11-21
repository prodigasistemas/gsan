package gcom.gui.seguranca;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.gui.GcomAction;
import gcom.seguranca.FiltrarConsultaCadastroCdlActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rodrigo Cabral
 * @date 26/10/10
 */



public class ExibirFiltrarConsultaCadastroCdlAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarConsultaCadastroCdl");
		
//		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarConsultaCadastroCdlActionForm form = (FiltrarConsultaCadastroCdlActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR e Passar o valor do Indicador de Uso como "TODOS"
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1"))) {

			// Faz a consulta da matrícula do Funcionário
			this.pesquisarMatriculaFuncionario(form,objetoConsulta, httpServletRequest);
			
		}
		
		

	return retorno;
	
	}	
		
	private void pesquisarMatriculaFuncionario(FiltrarConsultaCadastroCdlActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = null;
			
			if(objetoConsulta.trim().equals("1")){
				local = form.getIdMatriculaFuncionario();
				
			}
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(
				new ParametroSimples(FiltroFuncionario.ID,local));
			
			// Recupera Matrícula do Funcionário
			Collection colecaoMatriculaFuncionario = 
				this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getName());
		
			if (colecaoMatriculaFuncionario != null && !colecaoMatriculaFuncionario.isEmpty()) {

				Funcionario funcionario = 
					(Funcionario) Util.retonarObjetoDeColecao(colecaoMatriculaFuncionario);
				
				if(objetoConsulta.trim().equals("1")){
					form.setIdMatriculaFuncionario(funcionario.getId().toString());
					form.setDesMatriculaFuncionario(funcionario.getNome());
				
				}
			

			} else {
				if(objetoConsulta.trim().equals("1")){
					form.setIdMatriculaFuncionario(null);
					form.setDesMatriculaFuncionario("Funcionario inexistente");
					httpServletRequest.setAttribute("matriculaFuncionarioInexistente","true");
				
			
			}
		}
	}

}



