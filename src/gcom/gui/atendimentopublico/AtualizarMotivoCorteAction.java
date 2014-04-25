package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarMotivoCorteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver um esquema de seguranca
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarMotivoCorteActionForm atualizarMotivoCorteActionForm = (AtualizarMotivoCorteActionForm) actionForm;

		MotivoCorte motivoCorte = (MotivoCorte) sessao.getAttribute("motivoCorteAtualizar");

		motivoCorte.setDescricao(atualizarMotivoCorteActionForm.getDescricao());
        motivoCorte.setIndicadorUso(new Short (atualizarMotivoCorteActionForm.getIndicadorUso()));
		
        String descricaoMotivoCorte = atualizarMotivoCorteActionForm
        .getDescricao();
        String indicadordeUso = atualizarMotivoCorteActionForm
        .getIndicadorUso();
		
		motivoCorte.setDescricao(descricaoMotivoCorte);
		motivoCorte.setUltimaAlteracao( new Date() );	
		motivoCorte.setIndicadorUso( new Short(indicadordeUso) );
		
		fachada.atualizar(motivoCorte);
					
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

		montarPaginaSucesso(httpServletRequest, "Motivo de Corte de código "
				+ atualizarMotivoCorteActionForm.getIdMotivoCorte().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Motivo de Corte ",
				"exibirFiltrarMotivoCorteAction.do?menu=sim");
        }
        
        
		return retorno;
	}
}
