package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarAlteracaoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAlteracaoTipoActionForm atualizarAlteracaoTipoActionForm = (AtualizarAlteracaoTipoActionForm) actionForm;

		AlteracaoTipo alteracaoTipo = (AlteracaoTipo) sessao.getAttribute("atualizarAlteracaoTipo");
		
		alteracaoTipo.setDescricao(atualizarAlteracaoTipoActionForm.getDescricao());
		alteracaoTipo.setDescricaoAbreviada(atualizarAlteracaoTipoActionForm.getDescricaoAbreviada());
				
        String descricaoAlteracaoTipo = atualizarAlteracaoTipoActionForm
        .getDescricao();
        String descricaoAbreviadaAlteracaoTipo = atualizarAlteracaoTipoActionForm
        .getDescricaoAbreviada();    
        
        alteracaoTipo.setDescricao(descricaoAlteracaoTipo);
        alteracaoTipo.setDescricaoAbreviada(descricaoAbreviadaAlteracaoTipo);
        alteracaoTipo.setUltimaAlteracao( new Date() );	
        
		fachada.atualizar(alteracaoTipo);

		montarPaginaSucesso(httpServletRequest, "Tipo de Alteração "
				+ atualizarAlteracaoTipoActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo de Alteração ",
				"exibirFiltrarAlteracaoTipoAction.do?menu=sim");        
        
		return retorno;
	}
}
