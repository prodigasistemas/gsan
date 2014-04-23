package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarServicoTipoMotivoEncerramentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarServicoTipoMotivoEncerramentoAction");
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();	
		
		String method = httpServletRequest.getParameter("method");
		
		if(method != null && method.equals("removeServicoTipoMotivoEncerramento")){
			String idServicoTipoMotivoEncerramento = httpServletRequest.getParameter("idServicoTipoMotivoEncerramento");
			List<AtendimentoMotivoEncerramento> motivosEncerramento = new ArrayList<AtendimentoMotivoEncerramento>((Collection<AtendimentoMotivoEncerramento>) sessao.
					getAttribute("motivosEncerramentoEscolhidos")); 
			for (int i = 0; i < motivosEncerramento.size(); i++) {
				if(motivosEncerramento.get(i).getId().intValue() == Integer.parseInt(idServicoTipoMotivoEncerramento)){
					motivosEncerramento.remove(i);
					i = motivosEncerramento.size();
				}
			}
			sessao.setAttribute("motivosEncerramentoEscolhidos", motivosEncerramento);
			retorno = actionMapping.findForward("inserirServicoTipoReload");
		}else{
			
			httpServletRequest.setAttribute("fecharPopup", true);
			// Form
			PesquisarServicoTipoMotivoEncerramentoActionForm form 
			= (PesquisarServicoTipoMotivoEncerramentoActionForm) actionForm;
			
			String[] idMotivosEncerramentos = form.getMotivosEncerramento();
			
			Collection<AtendimentoMotivoEncerramento> motivosEncerramento = null;
			if(sessao.getAttribute("motivosEncerramentoEscolhidos") != null){
				motivosEncerramento = (Collection<AtendimentoMotivoEncerramento>) sessao.
				getAttribute("motivosEncerramentoEscolhidos"); 
				for (AtendimentoMotivoEncerramento motivo : motivosEncerramento) {
					for (String idMotivoEncerrado : idMotivosEncerramentos) {
						if(motivo.getId().intValue() == Integer.parseInt(idMotivoEncerrado)){
							throw new ActionServletException(
									"atencao.motivo.encerramento.ja.existe", null, "");
						}
					}
				}
			}
			
			FiltroAtendimentoMotivoEncerramento filtro = new FiltroAtendimentoMotivoEncerramento();
			Collection<Integer> collIds = new ArrayList<Integer>();
			for (String idMotivoEncerrado : idMotivosEncerramentos) {
				collIds.add(Integer.parseInt(idMotivoEncerrado));
			}
			Collection motivosEncerramentoEscolhidos = (Collection) fachada.pesquisar(collIds, filtro, AtendimentoMotivoEncerramento.class.getName());
			
			if(motivosEncerramento != null){
				motivosEncerramentoEscolhidos.addAll(motivosEncerramento);
			}
			sessao.setAttribute("motivosEncerramentoEscolhidos", motivosEncerramentoEscolhidos);
		}
		
		
		
		return retorno;
	}	
}
