package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoOrdemExibicao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com as resoluções de diretoria retornadas do
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Tenório
 * @since 31/10/2006
 */
public class ExibirManterOperacaoOrdemExibicaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterOperacaoOrdemExibicao");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idOperacao = (String) httpServletRequest.getParameter("idOperacao");
		boolean novaOperacao = false;
				
		if (idOperacao == null) {
			idOperacao = (String) sessao.getAttribute("idOperacao");
		} else {
			novaOperacao = true;
			sessao.setAttribute("idOperacao", idOperacao);
		}
		Collection colOperOrdemExib  = new Vector();
		
		if (idOperacao != null){
			
			colOperOrdemExib = (Collection) sessao.getAttribute("colecaoOrdens");
			
			if (novaOperacao){
				FiltroOperacaoOrdemExibicao filtro = new FiltroOperacaoOrdemExibicao();
				filtro.adicionarParametro(new ParametroSimples(
						FiltroOperacaoOrdemExibicao.OPERACAO_ID, idOperacao));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA_TABELA);
				colOperOrdemExib = Fachada.getInstancia().pesquisar(filtro,
						OperacaoOrdemExibicao.class.getSimpleName());
			
				FiltroOperacao filtroOper = new FiltroOperacao();
				filtroOper.adicionarParametro(new ParametroSimples(
						FiltroOperacao.ID, idOperacao));
				Collection colOperacoes = Fachada.getInstancia().pesquisar(filtroOper,
						Operacao.class.getSimpleName());
				Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colOperacoes);
				if (operacao != null){
					sessao.setAttribute("operacao", operacao);	
				} else {
					throw new ActionServletException("atencao.operacaoOrdemExibicao.nao.existente");		
				}
				
			}			
			
		}
		 
		//	Ordena a coleção pelo valor de ordem
		Comparator object = new Comparator() {
			public int compare(Object a, Object b) {
				Integer ordem1 = ((OperacaoOrdemExibicao) a).getNumeroOrdem() ;
				Integer ordem2 = ((OperacaoOrdemExibicao) b).getNumeroOrdem() ;
		
				if (ordem1 == null){
					ordem1 = new Integer(99999);					
				}
				if (ordem2 == null){
					ordem2 = new Integer(99999);					
				}
				return ordem1.compareTo(ordem2);

			}
		};
		List list = (List) colOperOrdemExib;
		Collections.sort(list, object);
		
		sessao.setAttribute("colecaoOrdens", colOperOrdemExib);

		return retorno;

	}

}
