package gcom.gui.seguranca.acesso;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroControleLiberacaoPermissaoEspecial;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterControleLiberacaoPMEPAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterControleLiberacaoPMEP");
		
		// Form
		ExibirManterControleLiberacaoPMEPActionForm form = 
			(ExibirManterControleLiberacaoPMEPActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoResultadoPesquisaControleLiberacaoPMEP = (Collection) sessao.getAttribute("colecaoResultadoPesquisaControleLiberacaoPMEP");
		
		//variável controle que vai ser atualizada
		ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial = null;
		
		//código do controle (que vem da tela de resultado de pesquisa)
        String idControle = httpServletRequest.getParameter("idControle");
		
		if(idControle != null && !idControle.equals("")){
			
			controleLiberacaoPermissaoEspecial = (ControleLiberacaoPermissaoEspecial)this.pesquisarControleLiberacaoPMEP(idControle, form);
			
			if(controleLiberacaoPermissaoEspecial != null){
				this.preencherForm(form, controleLiberacaoPermissaoEspecial);
			}
			
		//se o objeto vier pela sessao	
		}else if(colecaoResultadoPesquisaControleLiberacaoPMEP != null && colecaoResultadoPesquisaControleLiberacaoPMEP.size() != 0){
				
			controleLiberacaoPermissaoEspecial = 
				(ControleLiberacaoPermissaoEspecial) Util.retonarObjetoDeColecao(colecaoResultadoPesquisaControleLiberacaoPMEP);
			
			this.preencherForm(form, controleLiberacaoPermissaoEspecial);
			
			sessao.removeAttribute("colecaoResultadoPesquisaControleLiberacaoPMEP");
			
		}else{
			
			form.setIdFuncionalidade(null);
			
			form.setFuncionalidade("Funcionalidade inexistente");
			
			form.setIdPermissaoEspecial(null);
			
			form.setPermissaoEspecial("Permissão Especial inexistente");
			
		}
		
		return retorno;
		
	}
					
	
	/**
	 * Pesquisa ControleLiberacaoPMEP
	 *
	 * @author Daniel Alves
	 * @date 16/08/2010
	 */
	private ControleLiberacaoPermissaoEspecial pesquisarControleLiberacaoPMEP(String idControle, ExibirManterControleLiberacaoPMEPActionForm form) {

		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();
		
		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroControleLiberacaoPermissaoEspecial.ID, idControle));
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		
		// Recupera ControleLiberacaoPermissaoEspecial
		Collection colecaoControleLiberacaoPermissaoEspecial = 
			this.getFachada().pesquisar(filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());
	
		if (colecaoControleLiberacaoPermissaoEspecial != null && !colecaoControleLiberacaoPermissaoEspecial.isEmpty()) {

			return (ControleLiberacaoPermissaoEspecial) Util.retonarObjetoDeColecao(colecaoControleLiberacaoPermissaoEspecial);		
			
		}else{
			return null;
		}
		
	}
	
	
	/**
	 * Preencher Form
	 *
	 * @author Daniel Alves
	 * @date 17/08/2010
	 */
	private void preencherForm(ExibirManterControleLiberacaoPMEPActionForm form, ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial){
        
		form.setIdFuncionalidade(controleLiberacaoPermissaoEspecial.getFuncionalidade().getId().toString());
		
		form.setFuncionalidade(controleLiberacaoPermissaoEspecial.getFuncionalidade().getDescricao());
		
		form.setIdPermissaoEspecial(controleLiberacaoPermissaoEspecial.getPermissaoEspecial().getId().toString());
		
		form.setPermissaoEspecial(controleLiberacaoPermissaoEspecial.getPermissaoEspecial().getDescricao());
		
		form.setIndicadorUso(controleLiberacaoPermissaoEspecial.getIndicadorUso().toString());
		
	}
	
}
