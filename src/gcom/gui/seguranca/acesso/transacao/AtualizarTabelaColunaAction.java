package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Francisco do Nascimento
 * @since 26/02/08
 */
public class AtualizarTabelaColunaAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("manterTabelaColunaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Tabela tabela = (Tabela) sessao.getAttribute("tabela");
		
		String descricaoTabela = httpServletRequest.getParameter("descricao");
		if(descricaoTabela != null && !descricaoTabela.equalsIgnoreCase("")){
			tabela.setDescricao(descricaoTabela);	
		}
		
		Collection colunas = new Vector();
		
		if (tabela != null){			
			colunas = tabela.getTabelaColunas();
		}
		for (Iterator iter = colunas.iterator(); iter.hasNext();) {
			TabelaColuna coluna = (TabelaColuna) iter.next();
			Integer idTabelaColuna = coluna.getId();
			String descricao = httpServletRequest.getParameter("descricaoColuna" + idTabelaColuna);
			if (descricao != null){
				coluna.setDescricaoColuna(descricao);
				Fachada.getInstancia().atualizar(coluna);
			}						
		}
		Fachada.getInstancia().atualizar(tabela);
		
		httpServletRequest.setAttribute("atualizouTabelaColuna", "true");
		
		return retorno;

	}

}
