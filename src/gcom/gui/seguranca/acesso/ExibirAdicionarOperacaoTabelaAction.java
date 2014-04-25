package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por exibir o popup de adicionar tabela no inserir operação e atualizar operação
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class ExibirAdicionarOperacaoTabelaAction extends GcomAction {

	/**
	 * Inseri uma operação de uma funcionalida de no sistema
	 *
	 * [UC0284] Inserir Operação
	 *
	 * @author Pedro Alexandre
	 * @date 05/05/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {
		
		//Seta o mapeamento para a o popup de adicionar tabela
		ActionForward retorno = actionMapping.findForward("exibirAdicionarOperacaoTabela");
		
		//Recupera o form de adicionar tabela 
		AdicionarOperacaoTabelaActionForm adicionarOperacaoTabelaActionForm = (AdicionarOperacaoTabelaActionForm) actionForm;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o códigoda tabela se ele foi digitado
		String idTabela = adicionarOperacaoTabelaActionForm.getIdTabela();
		
		//Recupera para onde a tela de popup deve retornar
		//e seta o valor na sessão
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		if(retornarTela != null){
			sessao.setAttribute("retornarTela",retornarTela);
		}
		
		//Recupera a flag que indica se é para limpar o form de adicionar tabela
		String limpaForm = httpServletRequest.getParameter("limpaForm");
		
		if (idTabela != null
				&& !idTabela.trim().equalsIgnoreCase("")
				&& httpServletRequest.getParameter("exibirPesquisarTabela") == null
				&& httpServletRequest.getParameter("limparForm") == null) {

			sessao.setAttribute("tabelaRecebida", idTabela);
		}
		
		//Caso a flag de limpar o form seja true 
		//Limpaos dados dos campos da tabela
		if(limpaForm != null && limpaForm.equalsIgnoreCase("true")){
			adicionarOperacaoTabelaActionForm.setIdTabela("");
			adicionarOperacaoTabelaActionForm.setDescricaoTabela("");
		}else{
			//Caso a flag de limpar o form não for true
			//e caso o código da tabela tenha sido digitado 
			//pesquisa a tabela informada na base de dados
			if (idTabela != null && !idTabela.equals("")){
				
				//Cria o filtro e setao código da tabela informado no filtro
				FiltroTabela filtroTabela = new FiltroTabela();
				filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.ID,idTabela));
				
				//Pesquisa a tabela na base de dados
				Collection<Tabela> colecaoTabela = fachada.pesquisar(filtroTabela, Tabela.class.getName());
				
				//Caso a tabela tenha sido encontrada 
				//Recupera a tabela e seta as informações no form de adicionar
				//Caso contrário indica que a tabela não existe 
				if (colecaoTabela != null && !colecaoTabela.isEmpty()) {
					Tabela tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
					adicionarOperacaoTabelaActionForm.setIdTabela(String.valueOf(tabela.getId()));
					adicionarOperacaoTabelaActionForm.setDescricaoTabela(tabela.getDescricao());
					httpServletRequest.setAttribute("operacaoTabelaEncontrada", "true");
	
				} else {
					adicionarOperacaoTabelaActionForm.setIdTabela("");
					adicionarOperacaoTabelaActionForm.setDescricaoTabela("TABELA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoTabelaNaoEncontrada","exception");
				}
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			adicionarOperacaoTabelaActionForm.setIdTabela(id);
			adicionarOperacaoTabelaActionForm.setDescricaoTabela(descricao);

		}
		
		//Seta a flag para indicar que o popupvai ser fechado
		httpServletRequest.setAttribute("fechaPopup", "false");

		//Retorna o mapeamento contido na variável retorno 
		return retorno;

	}

	
}
