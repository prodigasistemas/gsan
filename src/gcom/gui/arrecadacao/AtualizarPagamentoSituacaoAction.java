package gcom.gui.arrecadacao;


import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarPagamentoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarPagamentoSituacaoActionForm atualizarPagamentoSituacaoActionForm = (AtualizarPagamentoSituacaoActionForm) actionForm;

		PagamentoSituacao pagamentoSituacao= (PagamentoSituacao) sessao.getAttribute("atualizarPagamentoSituacao");
		
		pagamentoSituacao.setId(new Integer(atualizarPagamentoSituacaoActionForm.getId()));
		pagamentoSituacao.setDescricao(atualizarPagamentoSituacaoActionForm.getDescricao());
		pagamentoSituacao.setDescricaoAbreviada(atualizarPagamentoSituacaoActionForm.getDescricaoAbreviada());
		pagamentoSituacao.setIndicadorUso(new Short (atualizarPagamentoSituacaoActionForm.getIndicadorUso()));
		
		String idPagamentoSituacao = atualizarPagamentoSituacaoActionForm.getId();		
        String descricaoPagamentoSituacao= atualizarPagamentoSituacaoActionForm
        .getDescricao();
        String descricaoAbreviadaPagamentoSituacao = atualizarPagamentoSituacaoActionForm
        .getDescricaoAbreviada();            
        String indicadordeUso = atualizarPagamentoSituacaoActionForm
        .getIndicadorUso();
		
        pagamentoSituacao.setDescricao(descricaoPagamentoSituacao);
        pagamentoSituacao.setDescricaoAbreviada(descricaoAbreviadaPagamentoSituacao);
        pagamentoSituacao.setId(new Integer( idPagamentoSituacao));
        pagamentoSituacao.setUltimaAlteracao( new Date() );	
        pagamentoSituacao.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(pagamentoSituacao);

		montarPaginaSucesso(httpServletRequest, "Situação de Pagamento "
				+ atualizarPagamentoSituacaoActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Situação de Pagamento ",
				"exibirFiltrarPagamentoSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}
