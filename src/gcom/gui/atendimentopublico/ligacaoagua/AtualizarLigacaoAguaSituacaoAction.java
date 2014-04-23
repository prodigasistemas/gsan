package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoAguaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarLigacaoAguaSituacaoActionForm atualizarLigacaoAguaSituacaoActionForm = (AtualizarLigacaoAguaSituacaoActionForm) actionForm;

		LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) sessao.getAttribute("atualizarLigacaoAguaSituacao");
		
		ligacaoAguaSituacao.setDescricao(atualizarLigacaoAguaSituacaoActionForm.getDescricao());
		ligacaoAguaSituacao.setDescricaoAbreviado(atualizarLigacaoAguaSituacaoActionForm.getDescricaoAbreviada());
		ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao()));
		ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao()));
		ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede()));
		ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer (atualizarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento()));
		ligacaoAguaSituacao.setIndicadorUso(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorUso()));
		ligacaoAguaSituacao.setIndicadorAbastecimento(new Short (atualizarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento()));
		
				
        String descricaoLigacao = atualizarLigacaoAguaSituacaoActionForm
        .getDescricao();
        String descricaoAbreviadaLigacao = atualizarLigacaoAguaSituacaoActionForm
        .getDescricaoAbreviada();    
        String indicadorFaturamento = atualizarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao();
        String indicadorExistenciaLigacao = atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao();
        String indicadorExistenciaRede = atualizarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede();
        String consumoMinimo = atualizarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
        String indicadordeUso = atualizarLigacaoAguaSituacaoActionForm.getIndicadorUso();
        String indicadordeAbastecimento = atualizarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento();
		
        ligacaoAguaSituacao.setDescricao(descricaoLigacao);
        ligacaoAguaSituacao.setDescricaoAbreviado(descricaoAbreviadaLigacao);
        ligacaoAguaSituacao.setIndicadorFaturamentoSituacao( new Short(indicadorFaturamento));
        ligacaoAguaSituacao.setIndicadorExistenciaLigacao( new Short(indicadorExistenciaLigacao));
        ligacaoAguaSituacao.setIndicadorExistenciaRede( new Short(indicadorExistenciaRede));
        ligacaoAguaSituacao.setConsumoMinimoFaturamento( new Integer(consumoMinimo));
        ligacaoAguaSituacao.setUltimaAlteracao( new Date() );	
        ligacaoAguaSituacao.setIndicadorUso( new Short(indicadordeUso));
        ligacaoAguaSituacao.setIndicadorAbastecimento( new Short(indicadordeAbastecimento));
		
		fachada.atualizar(ligacaoAguaSituacao);

		montarPaginaSucesso(httpServletRequest, "Situação de Ligação de Água "
				+ atualizarLigacaoAguaSituacaoActionForm.getDescricao() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Atividade ",
				"exibirFiltrarLigacaoAguaSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}
