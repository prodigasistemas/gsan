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
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarContaBancariaAction extends GcomAction {
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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarContaBancariaActionForm filtrarContaBancariaActionForm = (FiltrarContaBancariaActionForm) actionForm;
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("contaBancariaAtualizar");
		sessao.removeAttribute("contaBancaria");

		filtrarContaBancariaActionForm.setAtualizar("1");
		
		if (httpServletRequest.getParameter("menu") != null) {
			filtrarContaBancariaActionForm.setAtualizar("1");
			filtrarContaBancariaActionForm.setBanco("");
			filtrarContaBancariaActionForm.setAgenciaBancaria("");
			filtrarContaBancariaActionForm.setContaBanco("");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}

		if (filtrarContaBancariaActionForm.getBanco() == null
				|| filtrarContaBancariaActionForm.getBanco().equals("")) {
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
					FiltroAgencia.BANCO_ID, filtrarContaBancariaActionForm
							.getBanco()));

			Collection colecaoAgencia = fachada.pesquisar(filtroAgencia,
					Agencia.class.getName());
			sessao.setAttribute("colecaoAgencia", colecaoAgencia);
		}

		// filtrarAgenciaBancariaActionForm.setIndicadorAtualizar("1");

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarContaBancaria");

		return retorno;

	}
}
