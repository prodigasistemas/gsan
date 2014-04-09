package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 15/05/2006
 */
public class ExibirAtualizarPrioridadeTipoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarPrioridadeTipoServico");

		AtualizarPrioridadeTipoServicoActionForm atualizarPrioridadeTipoServicoActionForm = (AtualizarPrioridadeTipoServicoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		String servicoTipoPrioridadeID = null;

		if (httpServletRequest.getParameter("desfazer") != null) {
			servicoTipoPrioridadeID = atualizarPrioridadeTipoServicoActionForm
					.getCodigo();
		} else {
			servicoTipoPrioridadeID = httpServletRequest
					.getParameter("idRegistroAtualizacao");
		}
		
		

		if( httpServletRequest
					.getParameter("idRegistroAtualizacao")!=null){
		
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoPrioridade.ID, servicoTipoPrioridadeID));

		Collection<ServicoTipoPrioridade> colecaoServicoTipoPrioridade = fachada
				.pesquisar(filtroServicoTipoPrioridade,
						ServicoTipoPrioridade.class.getName());

		if (colecaoServicoTipoPrioridade == null
				|| colecaoServicoTipoPrioridade.isEmpty()) {
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}

		ServicoTipoPrioridade servicoTipoPrioridade = (ServicoTipoPrioridade) colecaoServicoTipoPrioridade
				.iterator().next();

		atualizarPrioridadeTipoServicoActionForm
				.setCodigo(servicoTipoPrioridade.getId().toString());

		atualizarPrioridadeTipoServicoActionForm
				.setDescricao(servicoTipoPrioridade.getDescricao());
		atualizarPrioridadeTipoServicoActionForm
				.setAbreviatura(servicoTipoPrioridade.getDescricaoAbreviada());

		atualizarPrioridadeTipoServicoActionForm.setQtdHorasInicio(""
				+ servicoTipoPrioridade.getPrazoExecucaoInicio());

		atualizarPrioridadeTipoServicoActionForm.setQtdHorasFim(""
				+ servicoTipoPrioridade.getPrazoExecucaoFim());
		
		atualizarPrioridadeTipoServicoActionForm.setIndicadorUso( 
				"" + servicoTipoPrioridade.getIndicadorUso() );
		
		sessao.setAttribute("AtualizarPrioridadeTipoServicoActionForm",
				atualizarPrioridadeTipoServicoActionForm);

		sessao.setAttribute("servicoTipoPrioridadeAtualizar",
				servicoTipoPrioridade);
		
		}else{
			
			if(sessao.getAttribute("objetoServicoTipoPrioridade")!=null){
				
				ServicoTipoPrioridade servicoTipoPrioridade = (ServicoTipoPrioridade) sessao.getAttribute("objetoServicoTipoPrioridade");
				
				atualizarPrioridadeTipoServicoActionForm.setCodigo(servicoTipoPrioridade.getId().toString());

				atualizarPrioridadeTipoServicoActionForm.setDescricao(servicoTipoPrioridade.getDescricao());
		
				atualizarPrioridadeTipoServicoActionForm.setAbreviatura(servicoTipoPrioridade.getDescricaoAbreviada());

				atualizarPrioridadeTipoServicoActionForm.setQtdHorasInicio(""+ servicoTipoPrioridade.getPrazoExecucaoInicio());
				
				atualizarPrioridadeTipoServicoActionForm.setIndicadorUso( "" + servicoTipoPrioridade.getIndicadorUso() );				

				atualizarPrioridadeTipoServicoActionForm.setQtdHorasFim(""+ servicoTipoPrioridade.getPrazoExecucaoFim());
				sessao.setAttribute("servicoTipoPrioridadeAtualizar",
						servicoTipoPrioridade);
				
			}
		}

	

		return retorno;

	}
}
