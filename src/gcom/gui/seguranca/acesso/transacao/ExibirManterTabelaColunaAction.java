package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Permite exibir uma lista com as resoluções de diretoria retornadas do
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Tenório
 * @since 31/10/2006
 */
public class ExibirManterTabelaColunaAction extends GcomAction {

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
				.findForward("manterTabelaColuna");

		FiltrarTabelaColunaActionForm filtrarForm = (FiltrarTabelaColunaActionForm) actionForm;
		
		String idTabela = filtrarForm.getIdTabela();
		
		String[] operacoes = filtrarForm.getOperacoes();
		
		Vector<Tabela> tabelas = new Vector<Tabela>();
		
		if (operacoes != null && operacoes.length > 0){
			FiltroOperacaoTabela filtro = new FiltroOperacaoTabela();
			for (int i = 0; i < operacoes.length; i++) {
				filtro.adicionarParametro(new ParametroSimples(
						FiltroOperacaoTabela.OPERACAO_ID, operacoes[i]));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoTabela.TABELA_COLUNAS);
				Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtro,
						OperacaoTabela.class.getSimpleName());
				
				Iterator iterOperTab = colOperTabelas.iterator();
				while (iterOperTab.hasNext()) {
					OperacaoTabela operTabela = (OperacaoTabela) iterOperTab.next();
					tabelas.add(operTabela.getTabela());										
				}				
			}			
		} else if (idTabela != null && !idTabela.equals("")){
			FiltroTabela filtro = new FiltroTabela();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroTabela.ID, idTabela));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroTabela.TABELA_COLUNAS);
		
			Collection colTabelas = Fachada.getInstancia().pesquisar(filtro,
					Tabela.class.getSimpleName());
		
			Tabela tabela =  (Tabela) Util.retonarObjetoDeColecao(colTabelas);
			
			if (tabela == null){
				throw new ActionServletException("atencao.tabela.inexistente");
			}
			
			// A partir do nome da tabela, precisa-se buscar os atributos da classe
			// desta tabela que estao definidos para ser registrados
			String nomeClasse = HibernateUtil.getClassName(tabela.getNomeTabela());
			
			String nomesColunaParaPesquisa = "";
			try {
				Class classe = Class.forName(nomeClasse);
				Object instancia = classe.newInstance();
				if (instancia instanceof ObjetoTransacao){
					ObjetoTransacao objTransacao = (ObjetoTransacao) instancia;
					String[] atributosSelecionados = objTransacao.retornarAtributosSelecionadosRegistro();
					for (int i = 0; i < atributosSelecionados.length; i++) {
						String nomeColuna = HibernateUtil.getNameColumn(classe, atributosSelecionados[i]);
						nomesColunaParaPesquisa += "$" + nomeColuna + "$";
					}					
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpSession sessao = httpServletRequest.getSession(false);
			sessao.setAttribute("tabela", tabela);
			sessao.setAttribute("nomesColunasSelecionadas", nomesColunaParaPesquisa);	
			
			tabelas.add(tabela);

			// consultar operacao_tabela das tabelas selecionadas			
			FiltroOperacaoTabela filtroOperTab = new FiltroOperacaoTabela();
			filtroOperTab.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, idTabela));
			filtroOperTab.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacaoTabela.OPERACAO);
			filtroOperTab.setCampoOrderBy(new String[]{FiltroOperacaoTabela.OPERACAO_ARGUMENTO_ID,
				FiltroOperacaoTabela.OPERACAO_DESCRICAO});

			Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtroOperTab,
					OperacaoTabela.class.getSimpleName());
			
			httpServletRequest.setAttribute("colecaoOperTabela", colOperTabelas);	
			
		}		

		httpServletRequest.setAttribute("colecaoTabela", tabelas);

		return retorno;

	}

}
