package gcom.gui.cobranca;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
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
 * < <Descrição da Classe>>
 * 
 * @author Tiago Moreno
 */
public class ExibirAtualizarAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAtividadeCobranca");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
			
		sessao.setAttribute("colecaoAtividadePredecessora", colecaoCobrancaAtividade);
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
		
		AtualizarAtividadeCobrancaActionForm atualizarAtividadeCobrancaActionForm = (AtualizarAtividadeCobrancaActionForm) actionForm;
		
		String idProcessoAssociado = atualizarAtividadeCobrancaActionForm.getIdProcessoAssociado();
		String idAtividadeCobranca = httpServletRequest.getParameter("idRegistroAtualizar");
		
		if (idAtividadeCobranca != null && !idAtividadeCobranca.equalsIgnoreCase("")){
			sessao.setAttribute("idAtividadeCobranca", idAtividadeCobranca);
			FiltroCobrancaAtividade filtroCobrancaAtividadeNaBase = new FiltroCobrancaAtividade();
			filtroCobrancaAtividadeNaBase.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID, idAtividadeCobranca));
			filtroCobrancaAtividadeNaBase.adicionarCaminhoParaCarregamentoEntidade("processo");
			
			CobrancaAtividade cobrancaAtividade = 
				(CobrancaAtividade) fachada.pesquisar(filtroCobrancaAtividadeNaBase, 
						CobrancaAtividade.class.getName()).iterator().next();
			
			if (cobrancaAtividade != null){
				atualizarAtividadeCobrancaActionForm.setDescricaoAtividadeCobranca(
						cobrancaAtividade.getDescricaoCobrancaAtividade());
				
				if (cobrancaAtividade.getCobrancaAtividadePredecessora() != null){
					atualizarAtividadeCobrancaActionForm.setIdAtividadePredecessora(
							cobrancaAtividade.getCobrancaAtividadePredecessora().getId().toString());
				} else {
					atualizarAtividadeCobrancaActionForm.
						setIdAtividadePredecessora("-1");
				}
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado(
						cobrancaAtividade.getProcesso().getDescricaoProcesso());
				atualizarAtividadeCobrancaActionForm.setIdProcessoAssociado(
						cobrancaAtividade.getProcesso().getId().toString());
				atualizarAtividadeCobrancaActionForm.setOrdemCronograma(
						cobrancaAtividade.getOrdemRealizacao().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoAtividadeObrigatoria(
						cobrancaAtividade.getIndicadorObrigatoriedade().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoCompoeCronograda(
						cobrancaAtividade.getIndicadorCronograma().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoPodeSerComandada(
						cobrancaAtividade.getIndicadorComando().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoPodeSerExecutada(
						cobrancaAtividade.getIndicadorExecucao().toString());
				atualizarAtividadeCobrancaActionForm.setUltimaAlteracao(
						cobrancaAtividade.getUltimaAlteracao());
				if (cobrancaAtividade.getNumeroDiasExecucao() != null) {
					atualizarAtividadeCobrancaActionForm.setQuantidadeDiasExecucao(
							cobrancaAtividade.getNumeroDiasExecucao().toString());
				}
				if (cobrancaAtividade.getCobrancaAcao() != null) {
					atualizarAtividadeCobrancaActionForm.setCobrancaAcao(
							cobrancaAtividade.getCobrancaAcao().getId().toString());
				}
			}
		}
		
		
		if (idProcessoAssociado != null && !idProcessoAssociado.trim().equals("")) {
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, idProcessoAssociado));
			
			Collection<Processo> colecaoProcesso = fachada.pesquisar(filtroProcesso, Processo.class.getName());
			
			if (colecaoProcesso != null && !colecaoProcesso.isEmpty()) {
				Processo processo = (Processo) colecaoProcesso.iterator().next();
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado(processo.getDescricaoProcesso());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				atualizarAtividadeCobrancaActionForm.setIdProcessoAssociado("");
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado("PROCESSO INEXISTENTE");
				httpServletRequest.setAttribute("processoInexistente", true);
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		return retorno;

	}

}
