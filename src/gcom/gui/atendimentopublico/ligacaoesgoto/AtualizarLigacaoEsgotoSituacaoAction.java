package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoEsgotoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarLigacaoEsgotoSituacaoActionForm atualizarLigacaoEsgotoSituacaoActionForm = (AtualizarLigacaoEsgotoSituacaoActionForm) actionForm;

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) sessao.getAttribute("atualizarLigacaoEsgotoSituacao");
		
		ligacaoEsgotoSituacao.setDescricao(atualizarLigacaoEsgotoSituacaoActionForm.getDescricao());
		ligacaoEsgotoSituacao.setDescricaoAbreviado(atualizarLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviada());
		ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short (atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorFaturamentoSituacao()));
		ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short (atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaLigacao()));
		ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short (atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaRede()));
		ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer (atualizarLigacaoEsgotoSituacaoActionForm.getConsumoMinimoFaturamento()));
		ligacaoEsgotoSituacao.setIndicadorUso(new Short (atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorUso()));
		
				
        String descricaoLigacao = atualizarLigacaoEsgotoSituacaoActionForm
        .getDescricao();
        String descricaoAbreviadaLigacao = atualizarLigacaoEsgotoSituacaoActionForm
        .getDescricaoAbreviada();    
        String indicadorFaturamento = atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorFaturamentoSituacao();
        String indicadorExistenciaLigacao = atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaLigacao();
        String indicadorExistenciaRede = atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaRede();
        String consumoMinimo = atualizarLigacaoEsgotoSituacaoActionForm.getConsumoMinimoFaturamento();
        String indicadordeUso = atualizarLigacaoEsgotoSituacaoActionForm.getIndicadorUso();
		
        ligacaoEsgotoSituacao.setDescricao(descricaoLigacao);
        ligacaoEsgotoSituacao.setDescricaoAbreviado(descricaoAbreviadaLigacao);
        ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao( new Short(indicadorFaturamento));
        ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao( new Short(indicadorExistenciaLigacao));
        ligacaoEsgotoSituacao.setIndicadorExistenciaRede( new Short(indicadorExistenciaRede));
        ligacaoEsgotoSituacao.setVolumeMinimoFaturamento( new Integer(consumoMinimo));
        ligacaoEsgotoSituacao.setUltimaAlteracao( new Date() );	
        ligacaoEsgotoSituacao.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(ligacaoEsgotoSituacao);

		montarPaginaSucesso(httpServletRequest, "Situação de Ligação de Esgoto "
				+ atualizarLigacaoEsgotoSituacaoActionForm.getDescricao() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Atividade ",
				"exibirFiltrarLigacaoEsgotoSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}
