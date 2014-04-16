package gcom.gui.operacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Sistema de Abastecimento
 *
 * @author Fernando Fontelles Filho	
 * @date 30/10/2009
 */


public class AtualizarSistemaAbastecimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarSistemaAbastecimentoActionForm form = (AtualizarSistemaAbastecimentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) sessao.getAttribute("atualizarSistemaAbastecimento");
		
		String[] sistemaAbastecimentoId = new String[1];
		
		sistemaAbastecimentoId[0] = sistemaAbastecimento.getId().toString();
		
		//Atualiza a entidade com os valores do formulário		
		sistemaAbastecimento.setDescricao(form.getDescricao());
		sistemaAbastecimento.setDescricaoAbreviada(form.getDescricaoAbreviada());
		sistemaAbastecimento.setIndicadorUso(new Short(form.getIndicadorUso()));
		
		if(form.getFonteCaptacaoId() != null && !form.getFonteCaptacaoId().equals("")){
			
			FonteCaptacao fonteCaptacao = new FonteCaptacao();
			fonteCaptacao.setId(new Integer(form.getFonteCaptacaoId()));
			sistemaAbastecimento.setFonteCaptacao(fonteCaptacao);
			
		}		
				
		//atualiza na base de dados de Municipio
		 fachada.atualizar(sistemaAbastecimento);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Sistema de Abastecimento de código " + sistemaAbastecimentoId[0] + 
				" atualizado com sucesso.", 
				"Realizar outra Manutenção de Sistema de Abastecimento",
				"exibirFiltrarSistemaAbastecimentoAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



