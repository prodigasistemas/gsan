package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarAtividadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Sera o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarAtividadeActionForm atualizarAtividadeActionForm = (AtualizarAtividadeActionForm) actionForm;

		Atividade atividade = (Atividade) sessao.getAttribute("atualizarAtividade");
		
		// Seta os valores na tela
		atividade.setDescricao(atualizarAtividadeActionForm.getDescricao());
		atividade.setDescricaoAbreviada(atualizarAtividadeActionForm.getDescricaoAbreviada());
		atividade.setIndicadorAtividadeUnica(new Short (atualizarAtividadeActionForm.getIndicadorAtividadeUnica()));
		atividade.setIndicadorUso(new Short (atualizarAtividadeActionForm.getIndicadorUso()));
		
		// Passa os valores informados
		String descricaoAtividade = atualizarAtividadeActionForm
        .getDescricao();
        String descricaoAbreviadaAtividade = atualizarAtividadeActionForm
        .getDescricaoAbreviada();    
        String indicadorAtividadeUnica = atualizarAtividadeActionForm.getIndicadorAtividadeUnica();
        String indicadordeUso = atualizarAtividadeActionForm
        .getIndicadorUso();
		
        // Seta os valores da tela para o banco
        atividade.setDescricao(descricaoAtividade);
        atividade.setDescricaoAbreviada(descricaoAbreviadaAtividade);
        atividade.setIndicadorAtividadeUnica( new Short(indicadorAtividadeUnica));
        atividade.setUltimaAlteracao( new Date() );	
        atividade.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(atividade);

		montarPaginaSucesso(httpServletRequest, "Atividade "
				+ atualizarAtividadeActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Atividade ",
				"exibirFiltrarAtividadeAction.do?menu=sim");        
        
		return retorno;
	}
}
