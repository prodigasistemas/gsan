package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gcom.atendimentopublico.EspecificacaoUnidadeCobranca;
import gcom.atendimentopublico.FiltroEspecificacaoUnidadeCobranca;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1159] Informar Tramite por Situação de Cobrança
 * 
 * Action responsável pela pre-exibição da pagina Informar Tramite por Situação de Cobrança
 * 
 * @author Mariana Victor
 * @date 14/04/2011
 */
public class ExibirInformarTramiteSituacaoCobrancaAction extends GcomAction {
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("informarTramiteSituacaoCobranca");

		Fachada fachada = Fachada.getInstancia();
		
		InformarTramiteSituacaoCobrancaActionForm informarTramiteSituacaoCobrancaActionForm = 
				(InformarTramiteSituacaoCobrancaActionForm) actionForm;
		
		List<EspecificacaoUnidadeCobranca> colecaoEspecificacaoUnidadeCobranca = new ArrayList();
		List<EspecificacaoUnidadeCobranca> colecaoEspecificacaoUnidadeCobrancaRemover = new ArrayList();

		if(sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca") != null
				&& !sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca").equals("")){
			
			colecaoEspecificacaoUnidadeCobranca = (ArrayList) 
    			sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca");
			
    	}
		if(sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover") != null
				&& !sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover").equals("")){
			
			colecaoEspecificacaoUnidadeCobrancaRemover = (ArrayList) 
    			sessao.getAttribute("colecaoEspecificacaoUnidadeCobrancaRemover");
			
    	}

		if (httpServletRequest.getParameter("menu") != null && 
				!httpServletRequest.getParameter("menu").trim().equals("")) {
			
			// Coleção de Situação de Cobrança
			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);

			Collection<CobrancaSituacao> colecaoCobrancaSituacao = 
				this.getFachada().pesquisar(
					filtroCobrancaSituacao, 
					CobrancaSituacao.class.getName());

			sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
			
			informarTramiteSituacaoCobrancaActionForm.setIdSituacaoCobranca("");
			
		}
		
		// Consultar os dados das associaoes de uma Situação de Cobrança armazenadas na tabela EspecificacaoUnidadeCobranca
		if (httpServletRequest.getParameter("recuperarAssociacoes") != null
				&& httpServletRequest.getParameter("recuperarAssociacoes").trim().equalsIgnoreCase("SIM")
				&& informarTramiteSituacaoCobrancaActionForm.getIdSituacaoCobranca() != null
				&& !informarTramiteSituacaoCobrancaActionForm.getIdSituacaoCobranca().trim().equals("")) {
			
			FiltroEspecificacaoUnidadeCobranca filtroEspecificacaoUnidadeCobranca = new FiltroEspecificacaoUnidadeCobranca();
			filtroEspecificacaoUnidadeCobranca.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoUnidadeCobranca.ID_COBRANCA_SITUACAO, informarTramiteSituacaoCobrancaActionForm.getIdSituacaoCobranca()));
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.SOLICITACAO_TIPO_ESPECIFICACAO);
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.UNIDADE_ORGANIZACIONAL);
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.COBRANCA_SITUACAO);
			
			Collection<EspecificacaoUnidadeCobranca> colecao = fachada.pesquisar(filtroEspecificacaoUnidadeCobranca, EspecificacaoUnidadeCobranca.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				colecaoEspecificacaoUnidadeCobranca = (List) colecao;
				sessao.setAttribute("colecaoEspecificacaoUnidadeCobranca", colecaoEspecificacaoUnidadeCobranca);
			} else {
				sessao.removeAttribute("colecaoEspecificacaoUnidadeCobranca");
			}
			
		}
		
		// Remover EspecificacaoUnidadeCobranca
		if (httpServletRequest.getParameter("removerAssociacao") != null
				&& !httpServletRequest.getParameter("removerAssociacao").equals("")) {
			
			Integer indice = new Integer(httpServletRequest.getParameter("removerAssociacao"));
        	
			if (colecaoEspecificacaoUnidadeCobranca != null
        			&& !colecaoEspecificacaoUnidadeCobranca.isEmpty()
        			&& colecaoEspecificacaoUnidadeCobranca.size() >= indice) {
				EspecificacaoUnidadeCobranca especificacaoUnidadeCobranca = colecaoEspecificacaoUnidadeCobranca.get(indice - 1);
        		if (especificacaoUnidadeCobranca.getUltimaAlteracao() != null) {
        			colecaoEspecificacaoUnidadeCobrancaRemover.add(especificacaoUnidadeCobranca);
    				sessao.setAttribute("colecaoEspecificacaoUnidadeCobrancaRemover", colecaoEspecificacaoUnidadeCobrancaRemover);
        		}
        		
        		colecaoEspecificacaoUnidadeCobranca.remove(indice-1);
        		if (colecaoEspecificacaoUnidadeCobranca != null
        				&& !colecaoEspecificacaoUnidadeCobranca.isEmpty()) {
        			sessao.setAttribute("colecaoEspecificacaoUnidadeCobranca", colecaoEspecificacaoUnidadeCobranca);
        		} else {
        			sessao.removeAttribute("colecaoEspecificacaoUnidadeCobranca");
        		}
        	}
        	
		}

		return retorno;
	}
	
}
