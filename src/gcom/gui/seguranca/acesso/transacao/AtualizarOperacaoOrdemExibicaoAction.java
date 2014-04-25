package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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
 * @author Francisco do Nascimento
 * @since 25/02/08
 */
public class AtualizarOperacaoOrdemExibicaoAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colOperOrdemExib = (Collection) sessao.getAttribute("colecaoOrdens");
		Collection alterados = new Vector();
		
		for (Iterator iter = colOperOrdemExib.iterator(); iter.hasNext();) {
			OperacaoOrdemExibicao ordemExib = (OperacaoOrdemExibicao) iter.next();
			Integer idTabelaColuna = ordemExib.getTabelaColuna().getId();
			String novaOrdem = httpServletRequest.getParameter("ordem" + idTabelaColuna);
			if (!ordemExib.getNumeroOrdem().equals(Integer.parseInt(novaOrdem))){
				ordemExib.setNumeroOrdem(Integer.parseInt(novaOrdem));
				ordemExib.setUltimaAlteracao(new Date());
				alterados.add(ordemExib);
			}
						
		}
		
		String refreshOrdenar = httpServletRequest.getParameter("ordenar");
		if (refreshOrdenar != null){
			retorno = actionMapping.findForward("exibirManterOperacaoOrdemExibicao");
//			Ordena a coleção pelo valor de ordem
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

		} else {
			retorno = actionMapping.findForward("telaSucesso");
			// atualizar no banco
			for (Iterator iter = alterados.iterator(); iter.hasNext();) {
				OperacaoOrdemExibicao operOrdem = (OperacaoOrdemExibicao) iter.next();
				Fachada.getInstancia().inserirOuAtualizar(operOrdem);				
			}
			
			montarPaginaSucesso(httpServletRequest,
					"Ordem de exibição alterada com sucesso.", 
					"Manter outra tabela e coluna",
					"exibirFiltrarTabelaColunaAction.do?menu=sim");
			
		}		
		
		return retorno;

	}

}
