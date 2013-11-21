package gcom.gui.faturamento.conta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da pagina de inserir motivo de retificação
 * [UC1117] Inserir Motivo Retificação
 * 
 * @author Mariana Victor
 * @date 11/01/2011
 */
public class ExibirInserirMotivoRetificacaoAction extends GcomAction {

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
				.findForward("inserirMotivoRetificacao");
		
		InserirMotivoRetificacaoActionForm form = (InserirMotivoRetificacaoActionForm) actionForm;

		List<TabelaColuna> colecaoCampos = new ArrayList();
		
		if (sessao.getAttribute("colecaoCampos") != null
				&& !sessao.getAttribute("colecaoCampos").equals("")){
			colecaoCampos = (List<TabelaColuna>) sessao.getAttribute("colecaoCampos");
		}
		
		// Pesquisar Campo
		if(Util.verificarNaoVazio(form.getIdColuna())){
			TabelaColuna tabelaColuna = this.pesquisarCampo(form, sessao);
			if (tabelaColuna != null) {
				form.setIdColuna(tabelaColuna.getDescricaoColuna());
				form.setDescColuna(tabelaColuna.getDescricaoColuna());
				sessao.setAttribute("colunaEncontrada","");
			}
		}
		
		// Adicionar Campo
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("adicionar")
				&& !form.getIdColuna().equals("")) {
			TabelaColuna tabelaColuna = this.pesquisarCampo(form, sessao);
			
			if (tabelaColuna != null) {
				form.setIdColuna("");
				form.setDescColuna("");
				sessao.removeAttribute("colunaEncontrada");
				
				if (!this.adicionado(colecaoCampos, tabelaColuna)) {
					colecaoCampos.add(tabelaColuna);
					sessao.setAttribute("colecaoCampos", colecaoCampos);
				}
			}
		}
		
		// Limpar
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("limpar")) {
			form.setDescricao("");
			form.setNumeroOcorrenciasNoAno("");
			form.setIndicadorCompetenciaConsumo("");
			form.setDescColuna("");
			form.setIdColuna("");
			sessao.removeAttribute("colecaoCampos");
		}
		
		// Remover
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("remover")
				&& httpServletRequest.getParameter("id") != null
				&& !httpServletRequest.getParameter("id").equals("")) {

        	Integer indice = new Integer(httpServletRequest.getParameter("id"));
        	
        	if (colecaoCampos != null
        			&& !colecaoCampos.isEmpty()
        			&& colecaoCampos.size() >= indice) {
        		colecaoCampos.remove(indice-1);
        	}
		}
		
		sessao.removeAttribute("podeRetificarContaAction");
		
		return retorno;
	}		
	
	private TabelaColuna pesquisarCampo(InserirMotivoRetificacaoActionForm form, HttpSession sessao) {
		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		filtroTabelaColuna.adicionarParametro(
				new ParametroSimples(FiltroTabelaColuna.DESCRICAO_COLUNA, form.getIdColuna()));
		
		Collection<TabelaColuna> colecaoTabelaColuna = 
			this.getFachada().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());
	
		// [FS0002] – Verificar existência do campo (coluna)
		if (Util.isVazioOrNulo(colecaoTabelaColuna)) {
			form.setIdColuna("");
			form.setDescColuna("Campo não Localizado");
			sessao.removeAttribute("colunaEncontrada");
		} else {
			TabelaColuna tabelaColuna = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			//[FS0003] – Verificar se o campo (coluna) pode ser associado
			if (!tabelaColuna.getIndicadorPodeRetificarConta().toString().equals(ConstantesSistema.SIM.toString())) {
				form.setIdColuna("");
				form.setDescColuna("Campo não pode ser associado para retificação!");
				sessao.removeAttribute("colunaEncontrada");
			} else {
				return tabelaColuna;
			}
		}
		return null;
	}
	
	private boolean adicionado(Collection<TabelaColuna> colecaoTabelaColuna, TabelaColuna tabelaColuna) {
		
		Iterator iterator = colecaoTabelaColuna.iterator();
		
		while(iterator.hasNext()) {
			TabelaColuna tabelaColunaAdicionada = (TabelaColuna) iterator.next();
			
			if (tabelaColunaAdicionada.getId().equals(tabelaColuna.getId())) {
				return true;
			}
		}
		
		return false;
	}

}
