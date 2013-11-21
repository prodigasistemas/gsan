package gcom.gui.cobranca;

import gcom.cobranca.FiltroMotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável por pesquisar os motivos de aceitação
 * para exibir na tela de motivo_nao_aceitacao_encerramento_os_manter.jsp
 * 
 * @author Diogo Peixoto
 * @since 23/05/2011
 */
public class ExibirManterMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("manterMotivoNaoAceitacao");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroMotivoNaoAceitacaoEncerramentoOS filtro = new FiltroMotivoNaoAceitacaoEncerramentoOS();
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoAceitacaoEncerramentoOS.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Componente de Paginação
		Map resultado = controlarPaginacao(httpServletRequest, retorno, filtro, MotivoNaoAceitacaoEncerramentoOS.class.getName());
		Collection colecaoMotivos = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// [FS0001] Verificar existência de dados
		if (colecaoMotivos == null || colecaoMotivos.isEmpty()) {
			throw new ActionServletException("atencao.motivo_nao_aceitacao_encerramento_os");
		}else{
			sessao.setAttribute("colecaoMotivos", colecaoMotivos);
		}
		return retorno;
	}
}