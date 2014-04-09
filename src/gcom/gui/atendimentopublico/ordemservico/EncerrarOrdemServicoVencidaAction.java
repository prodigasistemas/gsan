package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0734] Encerrar Ordem de Servico Vencida
 * 
 * @author Ivan Sérgio
 * @created 14/01/2008
 */
public class EncerrarOrdemServicoVencidaAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		EncerrarOrdemServicoVencidaActionForm encerrarOrdemServicoVencida = 
			(EncerrarOrdemServicoVencidaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Integer idServicoTipo = Util.converterStringParaInteger(
				encerrarOrdemServicoVencida.getTipoOrdem());
		Integer quantidadeDias = Util.converterStringParaInteger(
				encerrarOrdemServicoVencida.getQuantidadeDias());
	
		/*Alterações a serem testadas
		 * Integer codigoOrigemOS = Util.converterStringParaInteger(
				encerrarOrdemServicoVencida.getOrigemOrdemServico());
		*/
		// Alterações a serem testadas
		//Integer totalOrdemServicoEncerradas = fachada.encerrarOrdemServicoVencida(usuarioLogado,idServicoTipo, codigoOrigemOS, quantidadeDias);
		
		/**TODO: Cosanpa
		 * Mantis 664 - Correção de erro na execução da funcionalidade
		 * @author Wellington Rocha
		 * @date 19/11/2012*/
		Integer totalOrdemServicoEncerradas = fachada.encerrarOrdemServicoVencida(idServicoTipo, quantidadeDias, usuarioLogado);
		sessao.removeAttribute("colecaoTipoServico");
		
		if (totalOrdemServicoEncerradas.equals(0)) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		montarPaginaSucesso(httpServletRequest, "Ordens de Serviço Encerradas com Sucesso." +
				"\nTotal de Ordens de Serviço Encerradas: " + totalOrdemServicoEncerradas,
				"Encerrar Ordem de Servico Vencida",
				"exibirEncerrarOrdemServicoVencidaAction.do");
		
		return retorno;
	}
}
