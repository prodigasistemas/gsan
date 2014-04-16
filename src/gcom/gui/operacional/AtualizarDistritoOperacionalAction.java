package gcom.gui.operacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.SetorAbastecimento;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Distrito Operacional
 *
 * @author Eduardo Bianchi
 * @date 09/02/2007
 */


public class AtualizarDistritoOperacionalAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarDistritoOperacionalActionForm atualizarDistritoOperacionalActionForm = (AtualizarDistritoOperacionalActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DistritoOperacional distritoOperacional = (DistritoOperacional) sessao.getAttribute("distritoOperacional");
		
		String[] idDistritoOperacional = new String[1];
		
		idDistritoOperacional[0] = distritoOperacional.getId().toString();
		
		//Atualiza a entidade com os valores do formulário		
		distritoOperacional.setDescricao(atualizarDistritoOperacionalActionForm.getDescricao());
		distritoOperacional.setDescricaoAbreviada(atualizarDistritoOperacionalActionForm.getDescricaoAbreviada());
		distritoOperacional.setId(new Integer(atualizarDistritoOperacionalActionForm.getCodigoDistritoOperacional()));
		distritoOperacional.setIndicadorUso(new Short(atualizarDistritoOperacionalActionForm.getIndicadorUso()));
		SetorAbastecimento setorAbastecimento = new SetorAbastecimento();
		setorAbastecimento.setId(new Integer(atualizarDistritoOperacionalActionForm.getSetorAbastecimento()));
		distritoOperacional.setSetorAbastecimento(setorAbastecimento);
		//distritoOperacional.setUltimaAlteracao(Util.converteStringParaDateHora(atualizarDistritoOperacionalActionForm.getUltimaAlteracao()));
				
		//atualiza na base de dados de Municipio
		 fachada.atualizarDistritoOperacional(distritoOperacional,usuarioLogado);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Distrito Operacional de código " + idDistritoOperacional[0] + " atualizado com sucesso.", "Realizar outra Manutenção de Distrito Operacional",
				"exibirFiltrarDistritoOperacionalAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



