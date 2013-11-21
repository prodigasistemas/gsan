package gcom.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

/**
 * <<>>
 * 
 * @author Raimundo Martins
 * @date 21/07/2011
 */
public class ExibirPesquisarMotivoDeEncerramentoAction extends GcomAction{
	
	/**
	 * Efetua pesquisa, adição e remoção do motivo do encerramento
	 * 
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * @author Raimundo Martins
	 * @date 21/07/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			 ActionForm actionForm, 
			 HttpServletRequest httpServletRequest,
			 HttpServletResponse httpServletResponse) {
		HttpSession sessao = httpServletRequest.getSession(false);		
		ActionForward retorno = actionMapping.findForward("exibirMotivoEncerramentoPopup");
		Fachada fachada = Fachada.getInstancia();
		Collection colecaoAtendimentoMotivosEncerramentoInseridos = (Collection) sessao.getAttribute("colecaoAtendimentoMotivosEncerramentoInseridos");
		
		if(colecaoAtendimentoMotivosEncerramentoInseridos == null){
			colecaoAtendimentoMotivosEncerramentoInseridos = new ArrayList();
		}	
			
				
		if(httpServletRequest.getParameter("limpar") != null){
			
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.SIM));
			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);			
			
			Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());			
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		}
		else if(httpServletRequest.getParameter("idRemover") !=null){
			String idMotivoEncerramento = httpServletRequest.getParameter("idRemover");
			ArrayList<AtendimentoMotivoEncerramento> motivos = null;
			if(idMotivoEncerramento !=null && !idMotivoEncerramento.equals("") && !idMotivoEncerramento.equals(" ")){
				int idMotivo = Integer.parseInt(idMotivoEncerramento);
				motivos = new ArrayList<AtendimentoMotivoEncerramento>(colecaoAtendimentoMotivosEncerramentoInseridos);
				for(int i = 0; i < motivos.size(); i++){
					if(motivos.get(i).getId().intValue() == idMotivo){
						motivos.remove(i);
						i = motivos.size();
					}
				}
			}
			sessao.setAttribute("colecaoAtendimentoMotivosEncerramentoInseridos",motivos);
			retorno = actionMapping.findForward("atualizarTipoServico");
		}
		//Adiciona um novo motivo de encerramento
		else{
			PesquisarMotivoDeEncerramentoActionForm form = (PesquisarMotivoDeEncerramentoActionForm) actionForm;
			ArrayList idsMotivos = new ArrayList();
			
			Collection<AtendimentoMotivoEncerramento> motivosEncerramento = null;
			if(sessao.getAttribute("colecaoAtendimentoMotivosEncerramentoInseridos") != null){
				motivosEncerramento = (Collection<AtendimentoMotivoEncerramento>) sessao.getAttribute("colecaoAtendimentoMotivosEncerramentoInseridos"); 
				for (AtendimentoMotivoEncerramento motivo : motivosEncerramento) {
					for (String idMotivoEncerrado : form.getIdsMotivoEncerramento()) {
						if(motivo.getId().intValue() == Integer.parseInt(idMotivoEncerrado)){
							throw new ActionServletException(
									"atencao.motivo.encerramento.ja.existe", null, "");
						}
					}
				}
			}
			
			for(String idMotivo : form.getIdsMotivoEncerramento()){
				idsMotivos.add(Integer.parseInt(idMotivo));
			}

			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimplesIn(FiltroAtendimentoMotivoEncerramento.ID, idsMotivos));
			
			Collection colecao = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
			Iterator iter = colecao.iterator();
			
			while(iter.hasNext()){
				colecaoAtendimentoMotivosEncerramentoInseridos.add(iter.next());
			}
			
			sessao.setAttribute("colecaoAtendimentoMotivosEncerramentoInseridos",colecaoAtendimentoMotivosEncerramentoInseridos);
			httpServletRequest.setAttribute("fecharPopup", "OK");
			
		}
		return retorno;
		
	}

}
