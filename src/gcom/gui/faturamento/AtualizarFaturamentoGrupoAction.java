package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarFaturamentoGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFaturamentoGrupoActionForm atualizarFaturamentoGrupoActionForm = (AtualizarFaturamentoGrupoActionForm) actionForm;

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) sessao.getAttribute("faturamentoGrupoAtualizar");

		String anoMesReferencia = atualizarFaturamentoGrupoActionForm.getAnoMesReferencia();
        if(anoMesReferencia == null){
        	anoMesReferencia = (String)sessao.getAttribute("mesAno");
        }
		
		faturamentoGrupo.setDescricao(atualizarFaturamentoGrupoActionForm.getDescricao());
		faturamentoGrupo.setDescricaoAbreviada(atualizarFaturamentoGrupoActionForm.getDescricaoAbreviada());
		faturamentoGrupo.setDiaVencimento(new Short (atualizarFaturamentoGrupoActionForm.getDiaVencimento()));
		faturamentoGrupo.setIndicadorVencimentoMesFatura(new Short (atualizarFaturamentoGrupoActionForm.getIndicadorVencimentoMesFatura()));
		faturamentoGrupo.setIndicadorUso(new Short (atualizarFaturamentoGrupoActionForm.getIndicadorUso()));
		
		//	Concatena ano mes para insercao
        String mes = anoMesReferencia.substring(0, 2);
        String ano = anoMesReferencia.substring(3, 7);

        anoMesReferencia = ano + "" + mes;
		
        String descricaoFaturamento = atualizarFaturamentoGrupoActionForm
        .getDescricao();
        String descricaoAbreviadaFaturamento = atualizarFaturamentoGrupoActionForm
        .getDescricaoAbreviada();    
        String diaVencimentoFaturamento = atualizarFaturamentoGrupoActionForm
        .getDiaVencimento();
        String indVencMesFaturaFaturamento = atualizarFaturamentoGrupoActionForm.getIndicadorVencimentoMesFatura();
        String indicadordeUso = atualizarFaturamentoGrupoActionForm
        .getIndicadorUso();
		
        faturamentoGrupo.setDescricao(descricaoFaturamento);
        faturamentoGrupo.setDescricaoAbreviada(descricaoAbreviadaFaturamento);
        faturamentoGrupo.setDiaVencimento(new Short (diaVencimentoFaturamento));
        faturamentoGrupo.setAnoMesReferencia(new Integer(anoMesReferencia));
        faturamentoGrupo.setIndicadorVencimentoMesFatura( new Short(indVencMesFaturaFaturamento));
        faturamentoGrupo.setUltimaAlteracao( new Date() );	
        faturamentoGrupo.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(faturamentoGrupo);

		montarPaginaSucesso(httpServletRequest, "Grupo de Faturamento "
				+ atualizarFaturamentoGrupoActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Grupo de Faturamento ",
				"exibirFiltrarFaturamentoGrupoAction.do?menu=sim");        
        
		return retorno;
	}
}
