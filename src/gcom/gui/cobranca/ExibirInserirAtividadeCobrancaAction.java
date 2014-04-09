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
public class ExibirInserirAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirAtividadeCobrancaAction");

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
				
		InserirAtividadeCobrancaActionForm inserirAtividadeCobrancaActionForm = (InserirAtividadeCobrancaActionForm) actionForm;
		
		String idProcessoAssociado = inserirAtividadeCobrancaActionForm.getIdProcessoAssociado();
		
		if (idProcessoAssociado != null && !idProcessoAssociado.trim().equals("")) {
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, idProcessoAssociado));
			
			Collection<Processo> colecaoProcesso = fachada.pesquisar(filtroProcesso, Processo.class.getName());
			
			if (colecaoProcesso != null && !colecaoProcesso.isEmpty()) {
				Processo processo = (Processo) colecaoProcesso.iterator().next();
				inserirAtividadeCobrancaActionForm.setDescricaoProcessoAssociado(processo.getDescricaoProcesso());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				inserirAtividadeCobrancaActionForm.setIdProcessoAssociado("");
				inserirAtividadeCobrancaActionForm.setDescricaoProcessoAssociado("PROCESSO INEXISTENTE");
				httpServletRequest.setAttribute("processoInexistente", true);
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		return retorno;

	}

}
