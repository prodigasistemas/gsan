package gcom.gui.cobranca;

import gcom.cobranca.FiltroMotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1172] Manter Motivos de Não Aceitação de Encerramento de O.S.
 * 
 * Classe responsável por configurar os campos da tela
 * motivo_nao_aceitacao_encerramento_os_atualizar.jsp
 * 
 * @author Diogo Peixoto
 * @since 25/05/2011
 *
 */
public class ExibirAtualizarMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("atualizarMotivoNaoAceitacao");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm form = (MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm) actionForm;

		String idRegistroAtualizar = httpServletRequest.getParameter("idRegistroAtualizar");
		MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao = null;

		String primeiraVez = httpServletRequest.getParameter("primeiraVez");
		
		if(Util.verificarNaoVazio(primeiraVez)){
			FiltroMotivoNaoAceitacaoEncerramentoOS filtro = new FiltroMotivoNaoAceitacaoEncerramentoOS();
			filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoAceitacaoEncerramentoOS.ID, idRegistroAtualizar));					
			Collection colecaoMotivo = fachada.pesquisar(filtro, MotivoNaoAceitacaoEncerramentoOS.class.getName());
			if (colecaoMotivo != null && !colecaoMotivo.isEmpty()) {
				motivoNaoAceitacao = (MotivoNaoAceitacaoEncerramentoOS) Util.retonarObjetoDeColecao(colecaoMotivo);
			}
			sessao.setAttribute("motivo", motivoNaoAceitacao);
		}else{
			motivoNaoAceitacao = (MotivoNaoAceitacaoEncerramentoOS) sessao.getAttribute("motivo");
		}

		Integer id = motivoNaoAceitacao.getId();
		if (id != null) {
			form.setId(String.valueOf(id));
		} else {
			form.setId("");
		}

		String descricao = motivoNaoAceitacao.getDescricaoMotivoNaoAceitacaoEncerramentoOS();
		if (descricao != null) {
			form.setDescricao(String.valueOf(descricao));
		} else {
			form.setDescricao("");
		}

		Short multiplicador1 = motivoNaoAceitacao.getMultiplicadorValorServicoDescontarCorteSupressao();
		if (multiplicador1 != null) {
			form.setMultiplicadorValorServicoDescontarCorteSupressao(String.valueOf(multiplicador1));
		} else {
			form.setMultiplicadorValorServicoDescontarCorteSupressao("");
		}

		Short multiplicador2 = motivoNaoAceitacao.getMultiplicadorValorServicoDescontarCorteSupressao();
		if (multiplicador2 != null) {
			form.setMultiplicadorValorServicoDescontarNaoExecutados(String.valueOf(multiplicador2));
		} else {
			form.setMultiplicadorValorServicoDescontarNaoExecutados("");
		}

		BigDecimal percentual = motivoNaoAceitacao.getPercentualMultaAplicar();
		if (percentual != null) {
			form.setPercentualMultaAplicar(String.valueOf(percentual));
		} else {
			form.setPercentualMultaAplicar("");
		}

		Short indicadorUso = motivoNaoAceitacao.getIndicadorUso();
		if (indicadorUso != null) {
			form.setIndicadorUso(String.valueOf(indicadorUso));
		} else {
			form.setIndicadorUso("");
		}		
		
		return retorno;
	}
}