package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurelio
 *
 */
public class ExibirPesquisarColunaTabelaAction extends GcomAction{
	/**
	 * Este caso de uso efetua pesquisa de coluna de tabela
	 * 
	 * [UC0308] Pesquisar Coluna da Tabela
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 19/04/2007
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

		ActionForward retorno = actionMapping.findForward("colunaTabelaPesquisar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		PesquisarColunaTabelaActionForm form = (PesquisarColunaTabelaActionForm) actionForm;

		FiltroTabela filtroTabela = new FiltroTabela();

		String idTabela=  form.getIdTabela();
		
		//Verifica se o código da Unidade Empresa foi digitado
        if (idTabela != null
				&& !idTabela.trim().equals("")
				&& Integer.parseInt(idTabela) > 0) {

        	filtroTabela.adicionarParametro(new ParametroSimples(
        			FiltroTabela.ID, idTabela));
			
			Collection<Tabela> colecaoTabela = fachada.pesquisar(filtroTabela,
					Tabela.class.getName());

			if (colecaoTabela != null && !colecaoTabela.isEmpty()) {
				//a unidade de Unidade Empresa foi encontrado
				form.setIdTabela(""
						+ ((Tabela) ((List) colecaoTabela).get(0))
								.getId());
				form
						.setNomeTabela(((Tabela) ((List) colecaoTabela)
								.get(0)).getDescricao());
				httpServletRequest.setAttribute("idTabela",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "nomeTabela");
				

			} else {

				form.setIdTabela("");
				httpServletRequest.setAttribute("idTabelaNaoEncontrada",
						"exception");
				form
						.setNomeTabela("Tabela INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idTabela");

			}

        }
        //-------Fim de parte que trata do código quando o usuário tecla enter
        
		
		

		// envia uma flag que será verificado no tabela_coleuna_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaColunaTabela") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaColunaTabela",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaColunaTabela"));
		}
		
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null && 
				!httpServletRequest.getParameter("idCampoEnviarDados").equals("")) {
			
			form.setIdTabela(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setNomeTabela(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
		}
		
		if (httpServletRequest
				.getParameter("podeRetificarContaAction") != null) {
			sessao.setAttribute("podeRetificarContaAction", httpServletRequest
					.getParameter("podeRetificarContaAction"));
		}
		
		return retorno;
	}
	

}
