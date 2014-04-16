package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirTabelaAuxiliarAbreviadaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws RemoteException,
			ErroRepositorioException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Prepara o retorno da Ação
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o action form
		TabelaAuxiliarAbreviadaActionForm tabelaAuxiliarAbreviadaActionForm = (TabelaAuxiliarAbreviadaActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) sessao
				.getAttribute("tabela");

		// Seta a descrição informada
		tabelaAuxiliarAbreviada.setDescricao(tabelaAuxiliarAbreviadaActionForm
				.getDescricao().toUpperCase());

		// Seta a descrição abreviada informada
		tabelaAuxiliarAbreviada
				.setDescricaoAbreviada(tabelaAuxiliarAbreviadaActionForm
						.getDescricaoAbreviada());

		// Seta a data e hora
		tabelaAuxiliarAbreviada.setUltimaAlteracao(new Date());

		tabelaAuxiliarAbreviada
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

		filtroTabelaAuxiliarAbreviada
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviada.DESCRICAO,
						tabelaAuxiliarAbreviadaActionForm.getDescricao()
								.toUpperCase()));

		Collection colecaoTabelaAuxiliar = fachada.pesquisar(
				filtroTabelaAuxiliarAbreviada, TabelaAuxiliarAbreviada.class
						.getName());

		if (colecaoTabelaAuxiliar != null && !colecaoTabelaAuxiliar.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),
					tabelaAuxiliarAbreviadaActionForm.getDescricao().toUpperCase());
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarAbreviada);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarAbreviadaInserir")));
		}

		// Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarAbreviadaInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarAbreviada");

		return retorno;
	}
}
