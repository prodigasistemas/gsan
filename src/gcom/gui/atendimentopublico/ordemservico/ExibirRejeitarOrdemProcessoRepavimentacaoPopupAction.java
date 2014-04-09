package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMotivoRejeicao;
import gcom.atendimentopublico.ordemservico.MotivoRejeicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0648] Exibir Acompanhamento Processo Repavimentação
 * 		[SB0003] - Rejeitar Serviço de Repavimentação
 * 
 * @author Hugo Leonardo		
 * @date 07/12/2010
 */
public class ExibirRejeitarOrdemProcessoRepavimentacaoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("exibirRejeitarOrdemProcessoRepavimentacaoPopup");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarOrdemProcessoRepavimentacaoPopUpActionForm form = (AtualizarOrdemProcessoRepavimentacaoPopUpActionForm) actionForm;
		
		String dataAtual = Util.formatarData(Util.adicionarNumeroDiasDeUmaData(new Date(), 1));
		
		form.setDataAtual(dataAtual);
		
		form.setIdMotivoRejeicao("");
		form.setDescricaoRejeicao("");
		form.setDataRejeicao("");
		
		if(httpServletRequest.getAttribute("colecaoMotivoRejeicao") == null){
			FiltroMotivoRejeicao filtroMotivoRejeicao = new FiltroMotivoRejeicao();
			filtroMotivoRejeicao.setConsultaSemLimites(true);
			filtroMotivoRejeicao.setCampoOrderBy(FiltroMotivoRejeicao.DESCRICAO);
							
			Collection colecaoMotivoRejeicao = fachada.pesquisar(filtroMotivoRejeicao, MotivoRejeicao.class.getName());
			
			Iterator it = colecaoMotivoRejeicao.iterator();
			
			MotivoRejeicao motivoRejeicao = null;
			while(it.hasNext()){
				motivoRejeicao = (MotivoRejeicao) it.next();
				
				if(motivoRejeicao.getDescricao().toUpperCase().trim().equalsIgnoreCase("OUTROS")){
					httpServletRequest.setAttribute("idMotivoObrigatorio", motivoRejeicao.getId());
				}
			}
			
			if(!Util.isVazioOrNulo(colecaoMotivoRejeicao) ){
				sessao.setAttribute("colecaoMotivoRejeicao", colecaoMotivoRejeicao);
			}else{
				throw new ActionServletException("atencao.naocadastrado", null,"Motivo Rejeição");
			}
		}
		
		if (httpServletRequest.getParameter("page.offset") != null 
				&& !httpServletRequest.getParameter("page.offset").equals("")) {
			
        	String numeroPagina = httpServletRequest.getParameter("page.offset");   
        	form.setManterPaginaAux(numeroPagina);
		}
		
		return retorno;
	}
}
