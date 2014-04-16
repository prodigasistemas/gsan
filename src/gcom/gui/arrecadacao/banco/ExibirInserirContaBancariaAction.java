package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este cado de uso Permite Inserir uma Conta Bancaria [UC0518] Inserir Conta
 * Bancaria
 * 
 * @author Thiago Tenório
 * @date 07/02/2007
 */
public class ExibirInserirContaBancariaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirContaBancaria");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirContaBancariaActionForm inserirContaBancariaActionForm = (InserirContaBancariaActionForm) actionForm;

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			inserirContaBancariaActionForm.setBanco("");
			inserirContaBancariaActionForm.setContaBanco("");
			inserirContaBancariaActionForm.setContaContabil("");

		}

		if (inserirContaBancariaActionForm.getBanco() == null
				|| inserirContaBancariaActionForm.getBanco().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroBanco filtroBanco = new FiltroBanco();

			filtroBanco.setCampoOrderBy(FiltroBanco.NOME_BANCO);

			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroBanco.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna banco
			colecaoPesquisa = fachada.pesquisar(filtroBanco, Banco.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Banco");
			} else {
				sessao.setAttribute("colecaoBanco", colecaoPesquisa);
			}
			sessao.setAttribute("colecaoAgencia", null);
		} else {

			FiltroAgencia filtroAgencia = new FiltroAgencia();
			// Constrói filtro para pesquisa do banco
			filtroAgencia.setCampoOrderBy(FiltroAgencia.ID);
			filtroAgencia.adicionarParametro(new ParametroSimples(
					FiltroAgencia.BANCO_ID, inserirContaBancariaActionForm
							.getBanco()));

			Collection colecaoAgencia = fachada.pesquisar(filtroAgencia,
					Agencia.class.getName());
			sessao.setAttribute("colecaoAgencia", colecaoAgencia);
		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
